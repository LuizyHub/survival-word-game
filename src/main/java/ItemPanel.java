import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class ItemPanel extends JPanel implements Moveable{
	private int speed = -1;
	private final int balWidth = 100;
	private final int balHeight = 120;
	private boolean popped = false;
	public boolean ispopped() {return popped;};
	private String word;
	public String getWord() {return word;}
	private ImageIcon icon= new ImageIcon(new ImageIcon("balloon.png").getImage().getScaledInstance(balWidth, balHeight, java.awt.Image.SCALE_SMOOTH));
	private JLabel ballonLabel = new JLabel(icon);
	private ImageIcon popIcon= new ImageIcon(new ImageIcon("pop.png").getImage().getScaledInstance(balWidth, balHeight, java.awt.Image.SCALE_SMOOTH));
	private JLabel popLabel = new JLabel(popIcon);
	private ImageIcon rockIcon= new ImageIcon(new ImageIcon("rock.png").getImage().getScaledInstance(balWidth, 80, java.awt.Image.SCALE_SMOOTH));
	private JLabel rockLabel = new JLabel(rockIcon);
	private JLabel wordLabel;
	private int lastHeight=0; public int getLastHeight() {return lastHeight;}
	public ItemPanel(String w) {
		this.word = w;
		this.setOpaque(false);
		this.setSize(100, 200);
		this.setLocation((int)(Math.random()*1200),600);
		wordLabel = new JLabel(word);
		wordLabel.setForeground(Color.white);
		wordLabel.setFont(new Font("Arial",Font.BOLD,20));
		wordLabel.setSize(balWidth, 50);
		wordLabel.setLocation(0, balHeight+15);
		add(wordLabel);
		ballonLabel.setSize(balWidth, balHeight);
		ballonLabel.setLocation(0, 0);
		rockLabel.setSize(balWidth, 80);
		rockLabel.setLocation(0, balHeight);
		//rockLabel.setOpaque(true);
		add(rockLabel);
		popLabel.setSize(balWidth, balHeight-20);
		popLabel.setLocation(0, 0);
		add(ballonLabel);
		
	}
	
	
	
	
	@Override
	public void move() {
		if(this.getY()<=0) {
			pop();
		}
		//this.setLocation(this.getX(), this.getY()+getSpeed());
		if(this.getY()>600) {
			//GameEngine.getInstance().putScore(-10);
			this.setLocation(this.getX(), 601);
			GameEngine.getInstance().removeItem(this);
			GameEngine.getInstance().getMusicEngine().crashSound();
		}
		else
			this.setLocation(this.getX(), this.getY()+getSpeed());
			
		
		
	}
	private int getSpeed() {
		if(speed ==10)
			this.remove(popLabel);
		if(popped) {
			return ++speed/3;
		}
		else
			return speed;
			
	}
	
	public void pop() {
		GameEngine.getInstance().getMusicEngine().popSound();
		
		popped = true;
		lastHeight = this.getY();
		this.remove(ballonLabel);
		this.add(popLabel);
		speed = 2;
		repaint();
	}

}
