import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class ScreenPanel extends JPanel {
	private GameEngine gameEngine;
	private ImageIcon backIcon;
	private Image backImg;
	private Archer archer;
	
//	//sss
//	
//	private ImageIcon icon= new ImageIcon(new ImageIcon("balloons.png").getImage().getScaledInstance(100, 100, java.awt.Image.SCALE_SMOOTH));
//	private JLabel ballonLabel = new JLabel(icon);
//	
//	//sss
	
	public ScreenPanel() {
		backIcon = new ImageIcon("cave.jpg");
		backImg = backIcon.getImage();
		this.setLayout(null);
		
//		JLabel arc = new JLabel(new ImageIcon(new ImageIcon("archer.png").getImage().getScaledInstance(150, 150, java.awt.Image.SCALE_SMOOTH)));
//		arc.setSize(150, 150);
//		arc.setLocation(500, 600);
//		add(arc);
		//
//		JLabel ss = new JLabel(icon);
//		ss.setSize(100,100);
//		ss.setLocation(200, 200);
//		add(ss);
		
		archer = new Archer();
		archer.setSize(150, 150);
		archer.setLocation(550, 650);
		add(archer);
		
		///
//		JPanel bar;
//		bar = new JPanel();
//		bar.setSize(180, 50);
//		bar.setLocation(10, 50);
//		bar.setBackground(Color.black);
//		add(bar);
		//
		
		
	}
	
	public void shoot() {
		archer.shoot();
	}
	
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		//System.out.println("ScreenPanel");
		
		g.drawImage(backImg, 0, 0, getWidth(), getHeight(), null);
	}

}
