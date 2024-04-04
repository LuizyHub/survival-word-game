import java.util.Calendar;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class GameEngine {
	private static volatile GameEngine gameEngine; // = new GameEngine();
	private MainPanel mainPanel;
	private JTextField inputField;
	private ScoreLabel scoreLabel;
	private WordVector wordVector;
	private GameFrame gameFrame;
	private GamePanel gamePanel;
	private ScreenPanel screenPanel;
	private InputPanel inputPanel;
	private ScorePanel scorePanel;
	private StayThread stayThread;
	private MoveThread moveThread;
	private MakingTh makingTh;
	private Record record;
	private HealTh healTh;
	private MusicEngine musicEngine; public MusicEngine getMusicEngine() {return musicEngine;}
	private int level = 0; public int getLevel() {return level;} public void setLevel() {level++;level%=2;}
	private int vib = 0; public int getVib() {return vib;} public void setVib() {vib++;vib%=2;}
	private int sound = 0; public int getSound() {return sound;} public void setSound() {sound++;sound%=2;}

	private long startTime;
	private Vector<Stayable> stayVector;
	public Vector<Stayable> getStayVector(){return stayVector;}
	private Vector<Moveable> moveVector;
	public Vector<Moveable> getMoveVector(){return moveVector;}
	private Vector<ItemPanel> itemVector;
	public Vector<ItemPanel> getItemVector(){return itemVector;}
	private Vector<String> rVector;
	public Vector<String> getRVector(){return record.getRVector();}
	private GameEngine() {
		/*
		scorePanel = new ScorePanel();
		inputPanel = new InputPanel();
		screenPanel = new ScreenPanel();
		gamePanel = new GamePanel(screenPanel, inputPanel, scorePanel);
		gameFrame = new GameFrame(gamePanel);
		stayThread = new StayThread(stayVector);
		*/
	}
	public void addWord(String s) {
		wordVector.addWord(s);
	}
	public Vector<String> getWVector(){
		return wordVector.getWVector();
	}
	
	public synchronized static GameEngine getInstance() {
		
		if(gameEngine == null) {
			gameEngine = new GameEngine();
		}
		return gameEngine;
	}
	private boolean isrunned = false;
	
	public void load() {
		musicEngine = new MusicEngine();
		wordVector = new WordVector();
		record = new Record();
		mainPanel = new MainPanel();
		gameFrame = new GameFrame();
		goMain();
	}
	public void goMain() {
		musicEngine.mainBgmON();
		gameFrame.setContentPane(mainPanel);
		gameFrame.setVisible(true);
	}
	
	
	public void showSetting() {
		SettingPanel sp = new SettingPanel();
		gameFrame.setContentPane(sp);
		gameFrame.setVisible(true);
	}
	
	public void run() {
		musicEngine.mainBgmOFF();
		stayVector = new Vector<Stayable>();
		moveVector = new Vector<Moveable>();
		itemVector = new Vector<ItemPanel>();
		scoreLabel = new ScoreLabel(175);
		scorePanel = new ScorePanel(scoreLabel);
		inputField = new JTextField(20);
		inputPanel = new InputPanel(inputField);
		screenPanel = new ScreenPanel();
		gamePanel = new GamePanel(screenPanel, inputPanel, scorePanel);
		gameFrame.setContentPane(gamePanel);
		gameFrame.setVisible(true);
//		if(isrunned) {
//			gameFrame.setContentPane(gamePanel);
//			gameFrame.setVisible(true);
//		}
//		if(!isrunned) {
//			gameFrame = new GameFrame(gamePanel);
//			isrunned = true;
//		}
		//gameFrame = new GameFrame(gamePanel);
		stayThread = new StayThread(stayVector);
		
		startTime = System.currentTimeMillis(); 
		musicEngine.startSound();
		musicEngine.bgmON();
		scorePanel.startCount();
		addItem();
		//System.out.println(wordVector.getWord());
		//System.out.println("run");
		//System.out.println(this.toString());
		//stayThread.start();
		
		moveThread = new MoveThread(moveVector);
		moveThread.start();
		makingTh = new MakingTh();
		makingTh.start();
		healTh = new HealTh();
		healTh.start();
	}
	class MakingTh extends Thread{
		private boolean deadFlag = false; public void setDead() {deadFlag = true;}
		@Override
		public void run() {
			int fre = 1000*level;
			while(!deadFlag) {
				try {Thread.sleep(3000-fre);} catch (InterruptedException e) {}
				addItem();
			}
		}
	}
	class HealTh extends Thread{
		private boolean deadFlag = false; public void setDead() {deadFlag = true;}
		@Override
		public void run() {
			int fre = 1000*level;
			while(!deadFlag) {
				try {Thread.sleep(5000+fre);} catch (InterruptedException e) {}
				addHealItem();
			}
		}
	}
	public void addHealItem() {
		ItemPanel it = new ItemPanel("!HEAL!");
		itemVector.add(it);
		moveVector.add(it);
		screenPanel.add(it);
	}
	
	public void addItem() {
		ItemPanel it = new ItemPanel(wordVector.getWord());
		itemVector.add(it);
		moveVector.add(it);
		screenPanel.add(it);
	}
	public void removeItem(ItemPanel it) {
		moveThread.SyncRemove(it);
		screenPanel.remove(it);
		int level = 12-(it.getLastHeight()/50);
		if(vib ==0)
			gameFrame.vibrate(level/2);
		if(it.getWord().equals("!HEAL!")) {
			this.putScore(10);
			//w bug
			
			scorePanel.setChange(10); 
		}
		else {
			this.putScore(-1*level*2);
			//w bug
			
			scorePanel.setChange(-1*level*2); 
		}
		
		//
		

	}
	
	public GameFrame getFrame() {
		return gameFrame;
	}
	
	public void addStayVector(Stayable s) {
		stayVector.add(s);
	}
	
	public void putScore(int score) {
		scoreLabel.consume(score);
	}
	
	public void inputWord(String in) {//입력 패널로 입력값을 받아옴.
		for(ItemPanel ip : itemVector) {
			if(ip.getWord().equals(in)) {
				screenPanel.shoot();
				ip.pop();
				break;
			}
		}
	}
	public long getScore() {
		return (System.currentTimeMillis() - startTime)*(1+level);
	}
	
	public void endGame(){
		moveThread.setDead();
		makingTh.setDead();
		healTh.setDead();
		scorePanel.stopCount();
		long lastScore = getScore();
		musicEngine.bgmOFF();
		String gamer = JOptionPane.showInputDialog(Long.toString(lastScore) + "!!\n put name. ");
		if(gamer == null) {
			gamer = "gamer";
		}
		record.addRecord(gamer, lastScore);
		try {Thread.sleep(3000);} catch (InterruptedException e) {}
		goMain();
	}
	
	
}