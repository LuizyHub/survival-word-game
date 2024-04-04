import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class ScorePanel extends JPanel {
	private GameEngine gameEngine;
	private ScoreLabel scoreLabel;
	private JPanel bar;
	private JLabel scoreChange;
	private JLabel timeScoreLabel;
	private String changedScore= "";
	private JPanel current;
	private JPanel pre;
	private TimeScorePanel timeScorePanel;
	private ScoreCounter scoreCounter;
	public ScorePanel(ScoreLabel scoreLabel) {
		this.scoreLabel = scoreLabel;
		this.setLayout(null);
		//makeTimeScoreLabel();
		//makeScoreChange();
		
		
//		JButton jb = new JButton("aa");
//		jb.setSize(50, 50);
//		jb.setLocation(0, ABORT);
//		add(jb);
		
//		scoreLabel = new ScoreLabel(100);
//		scoreLabel.setLocation(0, 0);
//		add(scoreLabel);
		add(scoreLabel);
		
		//add(scoreChange);
		
		//addScore();
		//add(timeScoreLabel);
		
	}
	public void startCount() {
		scoreCounter = new ScoreCounter();
		scoreCounter.start();
	}
	public void stopCount() {
		scoreCounter.setDead();
	}
	class ScoreCounter extends Thread{
		private boolean deadFlag = false; public void setDead() {deadFlag = true;}
		@Override
		public void run() {
			try {Thread.sleep(200);} catch (InterruptedException e) {}
			timeScorePanel = new TimeScorePanel();
			add(timeScorePanel);
			while(!deadFlag) {
				try {Thread.sleep(100);} catch (InterruptedException e) {}
				//timeScorePanel.reload();
				timeScorePanel.repaint();
			}	
		}
	}
//	private void makeTimeScoreLabel() {
//		timeScoreLabel = new JLabel("00000000");
//		timeScoreLabel.setSize(175, 80);
//		timeScoreLabel.setLocation(5,300);
//		timeScoreLabel.setFont(new Font("Arial", Font.BOLD, 20));
//	}
//	private void setTimeScore() {
//		timeScoreLabel.setText(Long.toString(gameEngine.getInstance().getScore()));
//	}
//	private void makeScoreChange() {
//		this.scoreChange = new JLabel("000");
//		scoreChange.setSize(175,40);
//		scoreChange.setForeground(Color.red);
//		scoreChange.setLocation(5, 85);
//		scoreChange.setFont(new Font("Arial", Font.BOLD, 20));
//	}
	
	public void setChange(int sc) {
		//scoreChange.setText(Integer.toString(sc));
		changedScore = Integer.toString(sc);
		repaint();
	}
	
	public void addScore() {
		bar = new JPanel();
		bar.setSize(175, 40);
		bar.setLocation(5, 50);
		bar.setBackground(Color.black);
		add(bar);
	}
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.setColor(Color.red);
		g.setFont(new Font("Arial", Font.BOLD, 40));
		g.drawString(changedScore, 100, 180);
		//g.setFont(new Font("Arial", Font.BOLD, 40));
		g.drawString("Score", 5, 400);
		g.drawString("LIFE", 5, 90);
//		g.setColor(Color.black);
//		g.drawString(changedScore, 5, 105);
	}
	
	class TimeScorePanel extends JPanel{
//		JLabel j;
		public TimeScorePanel() {
			setSize(175,100);
			setForeground(Color.red);
			setLocation(5, 400);
			this.setOpaque(false);
			this.setLayout(null);
			//
			
//			j = new JLabel(Long.toString(gameEngine.getInstance().getScore()));
//			j.setSize(30, 30);
//			j.setLocation(0, 0);
//			add(j);
			//setFont(new Font("Arial", Font.BOLD, 20));
		}
//		public void reload() {
//			j.setText(Long.toString(gameEngine.getInstance().getScore()));
//			// TODO Auto-generated method stub
//			
//		}
		@Override
		public void paintComponent(Graphics g) {
			super.paintComponent(g);
			g.setFont(new Font("Arial", Font.PLAIN, 30));
			g.setColor(Color.black);
			g.drawString(Long.toString(gameEngine.getInstance().getScore()), 0, 30);
			//g.fillOval(0, 0, 10, 10);
		}
	}
}
