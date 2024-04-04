import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class SettingPanel extends JPanel {
	private ImageIcon backIcon;
	private Image backImg;
	private JButton levelBtn = new JButton();
	private JButton backBtn;
	private JButton vibrateBtn;
	private JButton soundBtn;
	private final int BTNWIDTH = 250, BTNHEIGHT = 100;
	private JTextField inputField;
	public SettingPanel() {
		backIcon = new ImageIcon("cave.jpg");
		backImg = backIcon.getImage();
		this.setLayout(null);
		levelBtn.setSize(BTNWIDTH, BTNHEIGHT);
		levelBtn.setLocation(200, 200);
		levelBtn.setFont(new Font("Arial", Font.BOLD, 30));
		levelBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				GameEngine.getInstance().getMusicEngine().clickSound();
				GameEngine.getInstance().setLevel();
				setLevelBtn();
			}	
		});
		setSoundBtn();
		setInputField();
		setVibrateBtn();
		setLevelBtn();
		JLabel levelExplain1 = new JLabel("easy : new word every 3sec");
		levelExplain1.setFont(new Font("Arial", Font.BOLD, 20));
		levelExplain1.setForeground(new Color(0, 175, 175));
		levelExplain1.setSize(400, 50);
		levelExplain1.setLocation(200, 300);
		add(levelExplain1);
		JLabel levelExplain2 = new JLabel("          score x1");
		levelExplain2.setFont(new Font("Arial", Font.BOLD, 20));
		levelExplain2.setForeground(new Color(0, 175, 175));
		levelExplain2.setSize(300, 50);
		levelExplain2.setLocation(200, 320);
		add(levelExplain2);
		JLabel levelExplain3 = new JLabel("hard : new word every 2sec");
		levelExplain3.setFont(new Font("Arial", Font.BOLD, 20));
		levelExplain3.setForeground(new Color(0, 175, 175));
		levelExplain3.setSize(400, 50);
		levelExplain3.setLocation(200, 340);
		add(levelExplain3);
		JLabel levelExplain4 = new JLabel("          score x2");
		levelExplain4.setFont(new Font("Arial", Font.BOLD, 20));
		levelExplain4.setForeground(new Color(0, 175, 175));
		levelExplain4.setSize(300, 50);
		levelExplain4.setLocation(200, 360);
		add(levelExplain4);
		setBackBtn();
		add(levelBtn);
		
	}
	
	
	private void setVibrateBtn() {
		vibrateBtn = new JButton("");
		vibrateBtn.setSize(BTNWIDTH, BTNHEIGHT);
		vibrateBtn.setLocation(600, 200);
		vibrateBtn.setFont(new Font("Arial", Font.BOLD, 30));
		vibrateBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				GameEngine.getInstance().getMusicEngine().clickSound();
				GameEngine.getInstance().setVib();
				onoffvibrateBtn();
			}	
		});
		onoffvibrateBtn();
		add(vibrateBtn);
	}
	private void onoffvibrateBtn() {
		if(GameEngine.getInstance().getVib()==0) {
			vibrateBtn.setText("Vibrate ON");
			vibrateBtn.setBackground(new Color(110,255,0));
		}
		if(GameEngine.getInstance().getVib()==1) {
			vibrateBtn.setText("Vibrate OFF");
			vibrateBtn.setBackground(new Color(255,0,0));
		}
	}
	
	private void setSoundBtn() {
		soundBtn = new JButton("");
		soundBtn.setSize(BTNWIDTH, BTNHEIGHT);
		soundBtn.setLocation(1000, 200);
		soundBtn.setFont(new Font("Arial", Font.BOLD, 30));
		soundBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				GameEngine.getInstance().setSound();
				GameEngine.getInstance().getMusicEngine().clickSound();
				onoffsoundBtn();
			}	
		});
		onoffsoundBtn();
		add(soundBtn);
	}
	private void onoffsoundBtn() {
		if(GameEngine.getInstance().getSound()==0) {
			soundBtn.setText("Sound ON");
			soundBtn.setBackground(new Color(110,255,0));
		}
		if(GameEngine.getInstance().getSound()==1) {
			soundBtn.setText("Sound OFF");
			soundBtn.setBackground(new Color(255,0,0));
		}
	}
	
	
	private void setInputField() {
		
		Font textFieldFont = new Font("Arial", Font.PLAIN,30);
		inputField = new JTextField();
		inputField.setSize(300, 50);
		inputField.setFont(textFieldFont);
		inputField.setLocation(200, 600);
		inputField.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				JTextField t  = (JTextField)e.getSource();
				if(!t.getText().equals(""))
					GameEngine.getInstance().addWord(t.getText());
				t.setText("");
			}
		});
		add(inputField);
		JLabel inputExplain = new JLabel("add word");
		inputExplain.setFont(new Font("Arial", Font.BOLD, 40));
		inputExplain.setForeground(new Color(0, 175, 175));
		inputExplain.setSize(300, 50);
		inputExplain.setLocation(200, 550);
		add(inputExplain);
	}
	
	private void setBackBtn() {
		backBtn = new JButton("Back");
		backBtn.setSize(BTNWIDTH, BTNHEIGHT);
		backBtn.setLocation(1300, 700);
		backBtn.setBackground(Color.red);
		backBtn.setFont(new Font("Arial", Font.BOLD, 20));
		backBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				GameEngine.getInstance().goMain();
			}	
		});
		add(backBtn);
	}
	
	private void setLevelBtn() {
		if(GameEngine.getInstance().getLevel()==0) {
			levelBtn.setText("Easy");
			levelBtn.setBackground(new Color(110,255,0));
		}
		if(GameEngine.getInstance().getLevel()==1) {
			levelBtn.setText("Hard");
			levelBtn.setBackground(new Color(255,0,0));
		}
	}
	
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		g.drawImage(backImg, 0, 0, getWidth(), getHeight(), null);
	}
	
}
