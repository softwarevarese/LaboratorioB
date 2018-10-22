package seatInAdmin;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.Box;
import java.awt.FlowLayout;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JTextPane;
import javax.swing.JSlider;
import java.awt.Color;
import java.awt.Font;

public class Login extends JFrame {

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login frame = new Login();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame 
	 **/
 private JTextField textField;
 private JPasswordField passwordField;
	public Login() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 514, 330);
		getContentPane().setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JLabel lblNewLabel = new JLabel("");
		getContentPane().add(lblNewLabel);
		
		JLabel lblLoginScreen = new JLabel("LOGIN SCREEN");
		lblLoginScreen.setFont(new Font("Tahoma", Font.BOLD, 18));
		getContentPane().add(lblLoginScreen);
		
		JLabel lblNewLabel_1 = new JLabel("User email");
		getContentPane().add(lblNewLabel_1);
		
		textField = new JTextField();
		getContentPane().add(textField);
		textField.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("Password");
		getContentPane().add(lblNewLabel_2);
		
		passwordField = new JPasswordField();
		passwordField.setColumns(8);
		getContentPane().add(passwordField);
		
		JButton btnNewButton = new JButton("Login");
		getContentPane().add(btnNewButton);
		
		JRadioButton rdbtnNewRadioButton = new JRadioButton("Studente");
		getContentPane().add(rdbtnNewRadioButton);
		
		JRadioButton rdbtnNewRadioButton_1 = new JRadioButton("Docente");
		getContentPane().add(rdbtnNewRadioButton_1);
		
		JRadioButton rdbtnNewRadioButton_2 = new JRadioButton("Amministratore");
		getContentPane().add(rdbtnNewRadioButton_2);
		
		JLabel lblNewLabel_3 = new JLabel("Password dimenticata?");
		lblNewLabel_3.setForeground(Color.BLUE);
		getContentPane().add(lblNewLabel_3);
	}
}
