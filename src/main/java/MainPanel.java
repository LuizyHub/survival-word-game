import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Vector;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class MainPanel extends JPanel {
	private ImageIcon backIcon;
	private Image backImg;
	private ImageIcon rockIcon;
	private Image rockImg;
	private JButton startBtn;
	private JButton settingBtn;
	private RecordLabel recordBtn;
	private final int BTNWIDTH = 300, BTNHEIGHT = 300;
	private ImageIcon startNormalIcon = new ImageIcon(new ImageIcon("archer.png").getImage().getScaledInstance(BTNWIDTH, BTNHEIGHT, Image.SCALE_SMOOTH));
	private ImageIcon startOveredIcon = new ImageIcon(new ImageIcon("archer_shoot.png").getImage().getScaledInstance(BTNWIDTH, BTNHEIGHT, Image.SCALE_SMOOTH));
	private ImageIcon startPressedIcon = new ImageIcon(new ImageIcon("archer_shoot.png").getImage().getScaledInstance(BTNWIDTH, BTNHEIGHT, Image.SCALE_SMOOTH));
	private ImageIcon settingNormalIcon = new ImageIcon(new ImageIcon("balloon.png").getImage().getScaledInstance(BTNWIDTH, BTNHEIGHT, Image.SCALE_SMOOTH));
	private ImageIcon settingOveredIcon = new ImageIcon(new ImageIcon("pop.png").getImage().getScaledInstance(BTNWIDTH, BTNHEIGHT, Image.SCALE_SMOOTH));
	private ImageIcon settingPressedIcon = new ImageIcon(new ImageIcon("pop.png").getImage().getScaledInstance(BTNWIDTH, BTNHEIGHT, Image.SCALE_SMOOTH));
	private ImageIcon recodrIcon = new ImageIcon(new ImageIcon("rock.png").getImage().getScaledInstance(BTNWIDTH, BTNHEIGHT, Image.SCALE_SMOOTH));
	public MainPanel() {
		backIcon = new ImageIcon("cave.jpg");
		backImg = backIcon.getImage();
		this.setLayout(null);
		setStartBtn();
		setSettingBtn();
		setRecordBtn();
	}
	
	class RecordLabel extends JLabel{
		private boolean intag = false;
		private String recordString1 = "";
		private String recordString2 = "";
		private String recordString3 = "";
		private String recordString4 = "";
		private String recordString5 = "";
		private String recordString6 = "";
		public RecordLabel() {
			this.setBackground(new Color(200,75,0));
			this.setLayout(null);
			this.setOpaque(true);
			rockIcon = new ImageIcon("rock.png");
			rockImg = rockIcon.getImage();
			this.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseEntered(MouseEvent e) {
					intag = true;
					Vector<String> rVector = GameEngine.getInstance().getRVector();
					recordString1 = "1st " + rVector.get(0);
					recordString2 = rVector.get(1);
					recordString3 = "2nd " + rVector.get(2);
					recordString4 = rVector.get(3);
					recordString5 = "3rd " + rVector.get(4);
					recordString6 = rVector.get(5);
					
//					recordString1 = "1st " + rVector.get(0) + " : " + rVector.get(1);
//					recordString2 = "2nd " + rVector.get(2) + " : " + rVector.get(3);
//					recordString3 = "3rd " + rVector.get(4) + " : " + rVector.get(5);_
					repaint();
				}
				@Override
			    public void mouseExited(MouseEvent e) {
					intag = false;
					recordString1 = "";
					recordString2 = "";
					recordString3 = "";
					recordString4 = "";
					recordString5 = "";
					recordString6 = "";
					repaint();
				}
			});
		}
		
		@Override
		public void paintComponent(Graphics g) {
			super.paintComponent(g);
			g.setColor(new Color(200,75,0));
			g.fillRect(0, 0, getWidth(), getHeight());
			if(!intag)
				g.drawImage(rockImg, 0, 0, getWidth(), getHeight(), null);
			
			g.setColor(new Color(0, 175, 175));
			g.setFont(new Font("Arial", Font.BOLD, 40));
			g.drawString(recordString1, 0, 40);
			g.drawString(recordString2, 60, 80);
			g.drawString(recordString3, 0, 120);
			g.drawString(recordString4, 60, 160);
			g.drawString(recordString5, 0, 200);
			g.drawString(recordString6, 60, 240);
		}
	}
	private void setRecordBtn() {
		recordBtn = new RecordLabel();
		//recordBtn.setPressedIcon(settingOveredIcon);
		//recordBtn.setRolloverIcon(settingPressedIcon);
		recordBtn.setOpaque(true);
		recordBtn.setBackground(Color.white);
		recordBtn.setSize(BTNWIDTH, BTNHEIGHT);
		recordBtn.setLocation(600, 300);
//		recordBtn.addMouseListener(new MouseAdapter() {
//			@Override
//			public void mouseEntered(MouseEvent e) {
//				recordBtn.setText("ff");
//			}
//			@Override
//		    public void mouseExited(MouseEvent e) {}
//		});
//		recordBtn.addActionListener(new ActionListener() {
//			@Override
//			public void actionPerformed(ActionEvent e) {
//				GameEngine.getInstance().showSetting();
//			}	
//		});
		add(recordBtn);
	}
	private void setSettingBtn() {
		
		settingBtn = new JButton(settingNormalIcon);
		settingBtn.setBackground(new Color(200,75,0));
		settingBtn.setPressedIcon(settingOveredIcon);
		settingBtn.setRolloverIcon(settingPressedIcon);
		settingBtn.setSize(BTNWIDTH, BTNHEIGHT);
		settingBtn.setLocation(1000, 300);
		settingBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				GameEngine.getInstance().showSetting();
			}	
		});
		add(settingBtn);
	}
	
	private void setStartBtn() {
		startBtn = new JButton(startNormalIcon);
		startBtn.setBackground(new Color(200,75,0));
		startBtn.setPressedIcon(startOveredIcon);
		startBtn.setRolloverIcon(startPressedIcon);
		startBtn.setSize(BTNWIDTH, BTNHEIGHT);
		startBtn.setLocation(200, 300);
		startBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				GameEngine.getInstance().run();
			}	
		});
		add(startBtn);
	}
	
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		g.drawImage(backImg, 0, 0, getWidth(), getHeight(), null);
		g.setColor(new Color(0, 175, 175));
		g.setFont(new Font("Arial", Font.BOLD, 40));
		g.drawString("GameStart", 250, 280);
		g.drawString("Record", 690, 280);
		g.drawString("Setting", 1080, 280);
		g.setFont(new Font("Arial", Font.BOLD, 20));
		g.drawString("Made By JungJae", 1400, 850);
	}
	
	

}
