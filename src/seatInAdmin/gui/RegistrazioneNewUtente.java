package seatInAdmin.gui;

import client.*;
import seatInServer.DBModel.Admin;
import seatInServer.DBModel.Docente;
import seatInServer.DBModel.Studente;

import java.awt.EventQueue;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RegistrazioneNewUtente extends JFrame {

	private JPanel contentPane;
	private JTextField nomeTextField;
	private JTextField cognomeTextField;
	private JTextField mailField;
	private JPasswordField passwordField;
	private final ButtonGroup tipoUtenteButtons = new ButtonGroup();
	static final String EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
			+ "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";


	/**
	 * Launch the application.
	 */
	public static void main(UtenteConnesso admin) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					RegistrazioneNewUtente frame = new RegistrazioneNewUtente(admin);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public RegistrazioneNewUtente(UtenteConnesso admin) {
		setIconImage(Toolkit.getDefaultToolkit().getImage("E:\\Documenti\\Diapositive\\Laboratorio Interdisciplinare B\\Progetto\\LaboratorioB\\src\\media\\Icona.PNG"));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 435, 327);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel label = new JLabel("REGISTRAZIONE NUOVO UTENTE");
		label.setFont(new Font("Tahoma", Font.BOLD, 16));
		label.setBounds(70, 13, 284, 16);
		contentPane.add(label);
		
		JLabel nomeLabel = new JLabel("Nome");
		nomeLabel.setBounds(81, 65, 54, 16);
		contentPane.add(nomeLabel);
		
		nomeTextField = new JTextField();
		nomeTextField.setColumns(10);
		nomeTextField.setBounds(166, 59, 116, 22);
		contentPane.add(nomeTextField);
		
		JLabel cognomeLabel = new JLabel("Cognome");
		cognomeLabel.setBounds(81, 91, 54, 22);
		contentPane.add(cognomeLabel);
		
		cognomeTextField = new JTextField();
		cognomeTextField.setColumns(10);
		cognomeTextField.setBounds(166, 91, 116, 22);
		contentPane.add(cognomeTextField);
		
		JLabel mailLabel = new JLabel("e-mail");
		mailLabel.setBounds(81, 126, 36, 16);
		contentPane.add(mailLabel);
		
		mailField = new JTextField();
		mailField.setColumns(10);
		mailField.setBounds(166, 126, 116, 22);
		contentPane.add(mailField);
		
		passwordField = new JPasswordField();
		passwordField.setColumns(8);
		passwordField.setBounds(166, 158, 116, 22);
		contentPane.add(passwordField);
		
		JLabel passwordLabel = new JLabel("Password");
		passwordLabel.setBounds(80, 161, 55, 16);
		contentPane.add(passwordLabel);
		
		JRadioButton studenteButton = new JRadioButton("Studente");
		tipoUtenteButtons.add(studenteButton);
		studenteButton.setBounds(20, 202, 127, 25);
		contentPane.add(studenteButton);
		
		JRadioButton docenteButton = new JRadioButton("Docente");
		tipoUtenteButtons.add(docenteButton);
		docenteButton.setBounds(155, 202, 127, 25);
		contentPane.add(docenteButton);

		JRadioButton adminButton = new JRadioButton("Amministratore");
		tipoUtenteButtons.add(adminButton);
		adminButton.setBounds(282, 202, 127, 25);
		contentPane.add(adminButton);

		JButton avantiButton = new JButton("Avanti");
		avantiButton.setBounds(179, 242, 85, 25);
		contentPane.add(avantiButton);
		avantiButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (mailField.getText().matches(EMAIL_PATTERN)) {
					if (studenteButton.isSelected()) {  //vai alla pagina per registrare uno studente
						Studente newStud = new Studente();
						newStud.setNome(nomeTextField.getText());
						newStud.setCognome(cognomeTextField.getText());
						newStud.setEmail(mailField.getText());
						newStud.setPassword(String.valueOf(passwordField.getPassword()));
						RegistrazioneNewStudente.main(admin, newStud);
					} else if (docenteButton.isSelected()) {
						Docente newDoc = new Docente();
						newDoc.setNome(nomeTextField.getText());
						newDoc.setCognome(cognomeTextField.getText());
						newDoc.setEmail(mailField.getText());
						newDoc.setPassword(String.valueOf(passwordField.getPassword()));
						RegistrazioneNewDocente.main(admin,newDoc);

					} //pag per registrare un docente
					else if (adminButton.isSelected()) {
						Admin newAdmin = new Admin();
						newAdmin.setNome(nomeTextField.getText());
						newAdmin.setCognome(cognomeTextField.getText());
						newAdmin.setEmail(mailField.getText());
						newAdmin.setPassword(String.valueOf(passwordField.getPassword()));
						PrincipaleAdmin.main(admin);
					}
					else {
						JOptionPane.showMessageDialog(null, "Selezionare una tipologia di utente");
					}
				}
				else { JOptionPane.showMessageDialog(null, "Mail non valida"); }

			}
		});

		JSeparator separator = new JSeparator();
		separator.setBounds(0, 236, 427, 2);
		contentPane.add(separator);
	}
}
