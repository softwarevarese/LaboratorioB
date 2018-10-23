package seatInAdmin;

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
		setBounds(100, 100, 450, 428);
		getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("REGISTRAZIONE UTENTE AMMINISTRATORE");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNewLabel.setBounds(30, 13, 377, 16);
		getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Cognome");
		lblNewLabel_1.setBounds(96, 100, 54, 16);
		getContentPane().add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Nome");
		lblNewLabel_2.setBounds(96, 132, 33, 16);
		getContentPane().add(lblNewLabel_2);
		
		JLabel lblMatricola = new JLabel("Matricola");
		lblMatricola.setBounds(96, 71, 52, 16);
		getContentPane().add(lblMatricola);
		
		JLabel lblNewLabel_3 = new JLabel("Password");
		lblNewLabel_3.setBounds(95, 196, 55, 16);
		getContentPane().add(lblNewLabel_3);
		
		textField = new JTextField();
		textField.setBounds(181, 71, 116, 22);
		getContentPane().add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setBounds(181, 100, 116, 22);
		getContentPane().add(textField_1);
		textField_1.setColumns(10);
		
		JLabel lblEmail = new JLabel("e-mail");
		lblEmail.setBounds(96, 167, 36, 16);
		getContentPane().add(lblEmail);
		
		passwordField = new JPasswordField();
		passwordField.setColumns(8);
		passwordField.setBounds(181, 199, 116, 22);
		getContentPane().add(passwordField);
		
		textField_2 = new JTextField();
		textField_2.setColumns(10);
		textField_2.setBounds(181, 132, 116, 22);
		getContentPane().add(textField_2);
		
		textField_3 = new JTextField();
		textField_3.setColumns(10);
		textField_3.setBounds(181, 167, 116, 22);
		getContentPane().add(textField_3);
	}
}
