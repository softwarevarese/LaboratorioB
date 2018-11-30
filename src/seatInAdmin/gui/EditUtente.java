package seatInAdmin.gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Toolkit;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.JTextArea;
import javax.swing.JSeparator;

public class EditUtente extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	private JTextField textField_5;
	private final JSeparator separator = new JSeparator();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					EditUtente frame = new EditUtente();
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
	public EditUtente() {
		setIconImage(Toolkit.getDefaultToolkit().getImage("E:\\Documenti\\Diapositive\\Laboratorio Interdisciplinare B\\Progetto\\LaboratorioB\\src\\media\\Icona.PNG"));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 657, 484);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblModificaUtente = new JLabel("MODIFICA DATI UTENTE");
		lblModificaUtente.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblModificaUtente.setBounds(213, 13, 239, 20);
		contentPane.add(lblModificaUtente);
		
		JLabel lblRicercaUtente = new JLabel("Ricerca utente");
		lblRicercaUtente.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblRicercaUtente.setBounds(12, 48, 130, 16);
		contentPane.add(lblRicercaUtente);
		
		JLabel lblCognome = new JLabel("Cognome");
		lblCognome.setBounds(12, 77, 56, 16);
		contentPane.add(lblCognome);
		
		JLabel lblNome = new JLabel("Nome");
		lblNome.setBounds(140, 77, 56, 16);
		contentPane.add(lblNome);
		
		JLabel lblMatricola = new JLabel("Matricola");
		lblMatricola.setBounds(271, 77, 56, 16);
		contentPane.add(lblMatricola);
		
		textField = new JTextField();
		textField.setBounds(12, 106, 116, 22);
		contentPane.add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(140, 106, 116, 22);
		contentPane.add(textField_1);
		
		textField_2 = new JTextField();
		textField_2.setColumns(10);
		textField_2.setBounds(268, 106, 116, 22);
		contentPane.add(textField_2);
		
		JLabel lblTipologia = new JLabel("Tipologia");
		lblTipologia.setBounds(396, 77, 56, 16);
		contentPane.add(lblTipologia);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setBounds(396, 106, 122, 22);
		contentPane.add(comboBox);
		
		JButton btnNewButton = new JButton("Cerca");
		btnNewButton.setBounds(530, 105, 97, 25);
		contentPane.add(btnNewButton);
		
		JTextArea textArea = new JTextArea();
		textArea.setBounds(12, 170, 615, 121);
		contentPane.add(textArea);
		
		JLabel lblRisultatiRicerca = new JLabel("Risultati ricerca");
		lblRisultatiRicerca.setBounds(12, 141, 130, 16);
		contentPane.add(lblRisultatiRicerca);
		
		JLabel lblCognome_1 = new JLabel("Cognome");
		lblCognome_1.setBounds(140, 304, 56, 16);
		contentPane.add(lblCognome_1);
		
		JLabel lblNome_1 = new JLabel("Nome");
		lblNome_1.setBounds(12, 304, 56, 16);
		contentPane.add(lblNome_1);
		
		textField_3 = new JTextField();
		textField_3.setColumns(10);
		textField_3.setBounds(12, 333, 116, 22);
		contentPane.add(textField_3);
		
		textField_4 = new JTextField();
		textField_4.setColumns(10);
		textField_4.setBounds(140, 333, 116, 22);
		contentPane.add(textField_4);
		
		textField_5 = new JTextField();
		textField_5.setColumns(10);
		textField_5.setBounds(271, 333, 211, 22);
		contentPane.add(textField_5);
		
		JLabel lblEmail = new JLabel("e-mail");
		lblEmail.setBounds(271, 304, 91, 16);
		contentPane.add(lblEmail);
		
		JButton btnModifica = new JButton("Invia modifiche");
		btnModifica.setBounds(494, 332, 133, 25);
		contentPane.add(btnModifica);
		separator.setBounds(0, 399, 639, 9);
		contentPane.add(separator);
		
		JButton btnIndietro = new JButton("Indietro");
		btnIndietro.setBounds(12, 410, 97, 25);
		contentPane.add(btnIndietro);
	}

}
