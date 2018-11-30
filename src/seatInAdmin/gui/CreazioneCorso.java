package seatInAdmin.gui;

import client.UtenteConnesso;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JSeparator;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Toolkit;
import javax.swing.JComboBox;

public class CreazioneCorso extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;

	/**
	 * Launch the application.
	 */
	public static void main(UtenteConnesso admin) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CreazioneCorso frame = new CreazioneCorso(admin);
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
	public CreazioneCorso(UtenteConnesso admin) {
		setIconImage(Toolkit.getDefaultToolkit().getImage("E:\\Documenti\\Diapositive\\Laboratorio Interdisciplinare B\\Progetto\\LaboratorioB\\src\\media\\Icona.PNG"));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 489, 524);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("CREAZIONE NUOVO CORSO");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNewLabel.setBounds(125, 13, 233, 20);
		contentPane.add(lblNewLabel);
		
		JLabel lblCodiceIdentificativo = new JLabel("Codice Identificativo");
		lblCodiceIdentificativo.setBounds(24, 62, 131, 16);
		contentPane.add(lblCodiceIdentificativo);
		
		JLabel lblNome = new JLabel("Nome");
		lblNome.setBounds(24, 91, 131, 16);
		contentPane.add(lblNome);
		
		JLabel lblAnnoDiAttivazione = new JLabel("Anno di Attivazione");
		lblAnnoDiAttivazione.setBounds(24, 120, 131, 16);
		contentPane.add(lblAnnoDiAttivazione);
		
		JLabel lblCorsiDilaurea = new JLabel("Corso di Laurea");
		lblCorsiDilaurea.setBounds(24, 149, 131, 16);
		contentPane.add(lblCorsiDilaurea);
		
		JLabel lblDescrizione = new JLabel("Descrizione");
		lblDescrizione.setBounds(24, 252, 93, 16);
		contentPane.add(lblDescrizione);
		
		JTextArea textArea = new JTextArea();
		textArea.setBounds(24, 281, 425, 111);
		contentPane.add(textArea);
		
		textField = new JTextField();
		textField.setBounds(268, 88, 181, 22);
		contentPane.add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(268, 59, 181, 22);
		contentPane.add(textField_1);
		
		textField_2 = new JTextField();
		textField_2.setColumns(10);
		textField_2.setBounds(268, 117, 181, 22);
		contentPane.add(textField_2);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(0, 432, 487, 2);
		contentPane.add(separator);
		
		JButton btnNewButton = new JButton("Fine");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		btnNewButton.setBounds(348, 439, 101, 25);
		contentPane.add(btnNewButton);
		
		JButton btnAggiungiUnAltro = new JButton("Aggiungi un altro corso");
		btnAggiungiUnAltro.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		btnAggiungiUnAltro.setBounds(150, 439, 186, 25);
		contentPane.add(btnAggiungiUnAltro);
		
		JButton btnNewButton_1 = new JButton("Indietro");
		btnNewButton_1.setBounds(24, 439, 107, 25);
		contentPane.add(btnNewButton_1);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setBounds(268, 146, 181, 22);
		contentPane.add(comboBox);
		
		JLabel lblDipartimento = new JLabel("Dipartimento");
		lblDipartimento.setBounds(24, 178, 131, 16);
		contentPane.add(lblDipartimento);
		
		JComboBox comboBox_1 = new JComboBox();
		comboBox_1.setBounds(268, 181, 181, 22);
		contentPane.add(comboBox_1);
	}
}
