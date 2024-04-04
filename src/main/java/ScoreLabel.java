import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class ScoreLabel extends JLabel {
	private int max;
	private int current;
	private final int WIDTH  = 175;
	private final int HEIGHT = 40;
	
	public ScoreLabel(int max) {
		super();
		this.setOpaque(false);
		this.max = max;
		current = this.max;
		this.setSize(WIDTH,HEIGHT);
		this.setLocation(5, 100);
		this.setBackground(Color.black);
		this.setOpaque(false);
	}
	
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawRect(0, 0, WIDTH, HEIGHT);
		g.setColor(Color.red);
		g.fillRect(0, 0, Math.min(WIDTH, (Math.max(0, current))),HEIGHT);
	}
	
	public synchronized void consume(int score) {
		current += score;
		repaint();
		if(current<=0)
			GameEngine.getInstance().endGame();
	}
}