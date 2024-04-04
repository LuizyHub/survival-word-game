import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Archer extends JPanel implements Stayable {
	private ImageIcon archerIcon;
	private Image archerImg;
	private Image archerShootImg;
	private int height = 150;
	private int width = 150;
	private boolean stayTag = false;
	public Archer() {
		//archerIcon = new ImageIcon("archer.png");
		archerImg = (new ImageIcon(new ImageIcon("archer.png").getImage().getScaledInstance(150, 150, Image.SCALE_SMOOTH))).getImage();
		archerShootImg = (new ImageIcon(new ImageIcon("archer_shoot.png").getImage().getScaledInstance(150, 150, Image.SCALE_SMOOTH))).getImage();
		this.setOpaque(false);
		JLabel arc = new JLabel(new ImageIcon(new ImageIcon("archer.png").getImage().getScaledInstance(150, 150, Image.SCALE_SMOOTH)));
		
		arc.setSize(150, 150);
		arc.setLocation(700, 600);
		//add(arc);
		
	}
	
	
	@Override
	public void stay() {
		if(stayTag) {
			height = 130;
			width = 150;
		}
		else {
			height = 150;
			width = 130;
		}
		stayTag = !stayTag;
	}
	public void shoot() {
		new Thread() {
			@Override
			public void run() {
				archerImg = (new ImageIcon(new ImageIcon("archer_shoot.png").getImage().getScaledInstance(150, 150, Image.SCALE_SMOOTH))).getImage();
				redraw();
				try {
					Thread.sleep(500);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				archerImg = (new ImageIcon(new ImageIcon("archer.png").getImage().getScaledInstance(150, 150, Image.SCALE_SMOOTH))).getImage();
				redraw();
			}
		}.start();
	}
	private void redraw() {
		repaint();
	}
	
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		//g.drawImage(archerImg, height, height, width, height, getBackground(), getFocusCycleRootAncestor())
		g.drawImage(archerImg, 0, 0, width, height, null , null);
	}

}
