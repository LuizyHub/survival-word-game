import java.awt.Component;

import javax.swing.JButton;
import javax.swing.JFrame;

public class GameFrame extends JFrame {
	private GameEngine gameEngine;
	private GamePanel gamePanel;
	private final int FRAME_WIDTH = 1600;
	private final int FRAME_HEIGHT = 900;
	public GameFrame() {
		super("Survival Word Game");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.gamePanel = gamePanel;
		this.setSize(FRAME_WIDTH, FRAME_HEIGHT);
		//System.out.println(this.getContentPane().getWidth());
		//this.setContentPane(gamePanel);
		this.setResizable(false);
		this.setVisible(true);
	}
	public GameFrame(GamePanel gamePanel) {
		super("SurvivalWordGame");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.gamePanel = gamePanel;
		this.setSize(FRAME_WIDTH, FRAME_HEIGHT);
		this.setContentPane(gamePanel);
		this.setResizable(false);
		this.setVisible(true);
		
	}
	
	public void vibrate(int level) {
		new MoveThread(this,level).start();
	}
	
	class MoveThread extends Thread{
		private Component c;
		private int level;
		public MoveThread(Component c) {
			this(c,5);
		}
		public MoveThread(Component c,int level) {
			this.c = c;
			this.level = level*3;
		}
		
		@Override
		public void run(){
			for(int i = 0; i < 8; i++) {
				try {
					Thread.sleep(1000/60);
					
				}catch(InterruptedException e){
					
				}
				// 11, 5
				int x = (int)(Math.random()*level)-(level/2);
				int y = (int)(Math.random()*level)-(level/2);
				c.setLocation(c.getX()+x, c.getY()+y);
				try {
					Thread.sleep(1000/60);
				}catch(InterruptedException e){
					
				}
				c.setLocation(c.getX()-x, c.getY()-y);
			}
		}
	}
	

}
