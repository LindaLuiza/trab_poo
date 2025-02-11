package view;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

public class CriarHospede extends JFrame{
	 private static final long serialVersionUID = 7606755412652491686L;

	public CriarHospede() {
	        setTitle("Nova Janela");
	        setSize(300, 200);
	        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); 

	        JLabel label = new JLabel("Esta é uma nova janela");
	        label.setHorizontalAlignment(SwingConstants.CENTER);
	        getContentPane().add(label);

	        setVisible(true);
	    }
}
