package client.gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.Color;
import javax.swing.SwingConstants;

import seatInServer.DBModel.Admin;

import javax.swing.JButton;
import java.awt.SystemColor;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;

public class RecuperoPassword {

	private JFrame frame;
	private JTextField mailField;

	/**
	 * Launch the application.
	 */
	public void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					RecuperoPassword window = new RecuperoPassword();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public RecuperoPassword() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setResizable(false);
		frame.setTitle("SEATIN");
		frame.getContentPane().setBackground(SystemColor.inactiveCaption);
		frame.getContentPane().setForeground(SystemColor.inactiveCaption);
		frame.setIconImage(Toolkit.getDefaultToolkit().getImage("media/f.png"));
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.addWindowListener(new WindowAdapter() 
		{
			@Override
			public void windowClosing(WindowEvent arg0) 
			{
				Login.main(null);
				frame.dispose();
			}
		});

		JLabel label_titolo = new JLabel("RESET DELLA PASSWORD");
		label_titolo.setForeground(Color.RED);
		label_titolo.setFont(new Font("Arial Black", Font.ITALIC, 21));
		label_titolo.setBounds(59, 13, 325, 33);
		frame.getContentPane().add(label_titolo);

		JLabel lblLePasswordNon = new JLabel();
		lblLePasswordNon.setForeground(Color.RED);
		lblLePasswordNon.setVisible(false);
		lblLePasswordNon.setHorizontalAlignment(SwingConstants.CENTER);
		lblLePasswordNon.setBounds(12, 236, 420, 16);
		frame.getContentPane().add(lblLePasswordNon);

		JButton btnVerificaEmail = new JButton("verifica email");
		btnVerificaEmail.setBounds(122, 154, 233, 25);
		frame.getContentPane().add(btnVerificaEmail);
		
		JLabel lblLaNuovaPassword = new JLabel("la nuova password sar\u00E0 inviata per mail ");
		lblLaNuovaPassword.setBounds(115, 211, 232, 16);
		frame.getContentPane().add(lblLaNuovaPassword);
		
		mailField = new JTextField();
		mailField.setBounds(122, 105, 225, 22);
		frame.getContentPane().add(mailField);
		mailField.setColumns(10);
		btnVerificaEmail.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
			new Admin().disableProfile(mailField.getText());
			
			frame.dispose();
			Login.main(null);
			}
		});

	}
}
