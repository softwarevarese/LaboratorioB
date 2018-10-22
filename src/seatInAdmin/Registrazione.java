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

public class Registrazione extends JDialog {
	private JTextField textField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			Registrazione dialog = new Registrazione();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public Registrazione() {
		setBounds(100, 100, 450, 428);
		getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("REGISTRAZIONE UTENTE");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNewLabel.setBounds(110, 13, 223, 16);
		getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Cognome");
		lblNewLabel_1.setBounds(44, 56, 56, 16);
		getContentPane().add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Nome");
		lblNewLabel_2.setBounds(44, 79, 56, 16);
		getContentPane().add(lblNewLabel_2);
		
		JLabel lblMatricola = new JLabel("Matricola");
		lblMatricola.setBounds(44, 108, 56, 16);
		getContentPane().add(lblMatricola);
		
		JLabel lblNewLabel_3 = new JLabel("Password");
		lblNewLabel_3.setBounds(44, 137, 56, 16);
		getContentPane().add(lblNewLabel_3);
		
		textField = new JTextField();
		textField.setBounds(120, 53, 116, 22);
		getContentPane().add(textField);
		textField.setColumns(10);
	}
}
