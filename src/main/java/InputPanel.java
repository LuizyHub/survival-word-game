import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class InputPanel extends JPanel {
	private GameEngine gameEngine;
//	private final int TEXTFIELDWIDTH=300,TEXTFIELDHEIGHT=50;
	private JTextField inputField;
	private Font textFieldFont = new Font("Arial", Font.PLAIN,30);
	public InputPanel(JTextField tf) {
		inputField = tf;
//		inputField.setSize(TEXTFIELDWIDTH, TEXTFIELDHEIGHT);
		Font textFieldFont = new Font("Arial", Font.PLAIN,30);
		tf.setFont(textFieldFont);
		add(inputField);
		addInputListener();
//		JButton jb = new JButton("aa");
//		jb.setSize(50, 50);
//		jb.setLocation(0, ABORT);
//		add(jb);
	}
	private void addInputListener() {
		inputField.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				JTextField t  = (JTextField)e.getSource();
				GameEngine.getInstance().inputWord(t.getText());
				//GameEngine.getInstance().getMusicEngine().clickSound();
				t.setText("");
			}
		});
	}

}
