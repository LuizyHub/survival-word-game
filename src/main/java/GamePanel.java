import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JButton;
import javax.swing.JPanel;

public class GamePanel extends JPanel {
	private GameEngine gameEngine;
	private ScreenPanel screenPanel;
	private InputPanel inputPanel;
	private ScorePanel scorePanel;
	private final int SP_WIDTH = 200;
	private final int IP_HEIGHT = 50;
	private final int WIDTH = 1600;
	private final int HEIGHT = 861;
	public GamePanel(ScreenPanel screenPanel, InputPanel inputPanel, ScorePanel scorePanel) {
		//gameEngine = GameEngine.getInstance();
		this.screenPanel = screenPanel;
		this.inputPanel = inputPanel;
		this.scorePanel = scorePanel;
		this.setLayout(null);
		
		this.screenPanel.setSize(WIDTH-SP_WIDTH, HEIGHT-IP_HEIGHT);
		this.inputPanel.setSize(WIDTH-SP_WIDTH,IP_HEIGHT);
		this.scorePanel.setSize(SP_WIDTH, HEIGHT);
		
		this.screenPanel.setLocation(0, 0);
		this.inputPanel.setLocation(0, HEIGHT-IP_HEIGHT);
		this.scorePanel.setLocation(WIDTH-SP_WIDTH, 0);
		
		this.screenPanel.setBackground(Color.CYAN);
		this.inputPanel.setBackground(new Color(175,75,0));
		this.scorePanel.setBackground(Color.orange);
		this.add(screenPanel);
		this.add(inputPanel);		
		this.add(scorePanel);
		
		
		
	}
	
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		//ok
		//GameEngine.getInstance().run();
		
		//notOK
		//gameEngine = GameEngine.getInstance();
		//gameEngine.run();
		//System.out.println("ok");
		//g.fillRect(0, 0, 30, 30);
	}

}
