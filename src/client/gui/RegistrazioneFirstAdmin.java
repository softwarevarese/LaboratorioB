package client.gui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.ImageIcon;
import java.awt.Toolkit;
import java.awt.Color;

public class RegistrazioneFirstAdmin extends JDialog {
	private JTextField textField;
	private JTextField textField_1;
	private JPasswordField passwordField;
	private JTextField textField_2;
	private JTextField textField_3;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			RegistrazioneFirstAdmin dialog = new RegistrazioneFirstAdmin();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public RegistrazioneFirstAdmin() {
		setTitle("SEATIN");
		getContentPane().setBackground(new Color(240, 248, 255));
		setBackground(new Color(240, 248, 255));
		setIconImage(Toolkit.getDefaultToolkit().getImage(RegistrazioneFirstAdmin.class.getResource("/media/Icona.PNG")));
		setBounds(100, 100, 490, 387);
		getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("REGISTRAZIONE UTENTE AMMINISTRATORE");
		lblNewLabel.setBounds(30, 13, 377, 16);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 16));
		getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Cognome");
		lblNewLabel_1.setBounds(8, 110, 89, 16);
		getContentPane().add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Nome");
		lblNewLabel_2.setBounds(8, 150, 87, 16);
		getContentPane().add(lblNewLabel_2);
		
		JLabel lblMatricola = new JLabel("Matricola");
		lblMatricola.setBounds(10, 70, 87, 18);
		getContentPane().add(lblMatricola);
		
		JLabel lblNewLabel_3 = new JLabel("Password");
		lblNewLabel_3.setBounds(8, 230, 87, 16);
		getContentPane().add(lblNewLabel_3);
		
		textField = new JTextField();
		textField.setBounds(96, 70, 152, 22);
		getContentPane().add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setBounds(96, 110, 152, 22);
		getContentPane().add(textField_1);
		textField_1.setColumns(10);
		
		JLabel lblEmail = new JLabel("e-mail");
		lblEmail.setBounds(8, 190, 87, 16);
		getContentPane().add(lblEmail);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(96, 230, 152, 22);
		passwordField.setColumns(8);
		getContentPane().add(passwordField);
		
		textField_2 = new JTextField();
		textField_2.setBounds(96, 150, 153, 22);
		textField_2.setColumns(10);
		getContentPane().add(textField_2);
		
		textField_3 = new JTextField();
		textField_3.setBounds(95, 190, 152, 22);
		textField_3.setColumns(10);
		getContentPane().add(textField_3);
		
		JLabel label = new JLabel("");
		label.setIcon(new ImageIcon(RegistrazioneFirstAdmin.class.getResource("/media/Insubria.PNG")));
		label.setBounds(283, 70, 181, 152);
		getContentPane().add(label);
		
		JButton btnNewButton = new JButton("Registra");
		btnNewButton.setToolTipText("Completa il processo di registrazione");
		btnNewButton.setBounds(8, 294, 240, 23);
		getContentPane().add(btnNewButton);
	}
}
