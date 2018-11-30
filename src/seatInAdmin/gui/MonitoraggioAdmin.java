package seatInAdmin.gui;

import client.UtenteConnesso;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Toolkit;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JSpinner;

public class MonitoraggioAdmin extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(UtenteConnesso admin) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MonitoraggioAdmin frame = new MonitoraggioAdmin(admin);
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
	public MonitoraggioAdmin(UtenteConnesso admin) {
		setIconImage(Toolkit.getDefaultToolkit().getImage("E:\\Documenti\\Diapositive\\Laboratorio Interdisciplinare B\\Progetto\\LaboratorioB\\src\\media\\Icona.PNG"));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 958, 556);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Monitoraggio seatInAdmin");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblNewLabel.setBounds(32, 13, 233, 30);
		contentPane.add(lblNewLabel);
		
		JButton btnEsci = new JButton("Esci");
		btnEsci.setBounds(819, 471, 97, 25);
		contentPane.add(btnEsci);
		
		JLabel lblUtentiConnessi = new JLabel("Utenti connessi: <numero>");
		lblUtentiConnessi.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblUtentiConnessi.setBounds(272, 22, 178, 16);
		contentPane.add(lblUtentiConnessi);
		
		JLabel lblSelezionaIlCorso = new JLabel("Seleziona il corso di laurea");
		lblSelezionaIlCorso.setBounds(32, 143, 210, 16);
		contentPane.add(lblSelezionaIlCorso);
		
		JLabel lblSelezionaIlDipartimento = new JLabel("Seleziona il dipartimento");
		lblSelezionaIlDipartimento.setBounds(32, 98, 210, 16);
		contentPane.add(lblSelezionaIlDipartimento);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setBounds(254, 98, 331, 22);
		contentPane.add(comboBox);
		
		JComboBox comboBox_1 = new JComboBox();
		comboBox_1.setBounds(254, 143, 331, 22);
		contentPane.add(comboBox_1);
		
		JLabel label = new JLabel("Seleziona il corso");
		label.setBounds(32, 188, 210, 16);
		contentPane.add(label);
		
		JComboBox comboBox_2 = new JComboBox();
		comboBox_2.setBounds(254, 188, 331, 22);
		contentPane.add(comboBox_2);
		
		JLabel lblNewLabel_1 = new JLabel("Seleziona il periodo di tempo");
		lblNewLabel_1.setBounds(32, 231, 178, 16);
		contentPane.add(lblNewLabel_1);
		
		JLabel label_1 = new JLabel("da:");
		label_1.setBounds(253, 234, 56, 16);
		contentPane.add(label_1);
		
		JLabel label_2 = new JLabel("a:");
		label_2.setBounds(438, 234, 33, 16);
		contentPane.add(label_2);
		
		JSpinner spinner = new JSpinner();
		spinner.setBounds(468, 231, 116, 22);
		contentPane.add(spinner);
		
		JSpinner spinner_1 = new JSpinner();
		spinner_1.setBounds(295, 231, 116, 22);
		contentPane.add(spinner_1);
		
		JButton btnNewButton = new JButton("Mostra risultato");
		btnNewButton.setBounds(355, 270, 135, 25);
		contentPane.add(btnNewButton);
		
		JLabel lblNewLabel_2 = new JLabel("Accessi nel periodo indicato: <num> ");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_2.setBounds(32, 56, 233, 16);
		contentPane.add(lblNewLabel_2);
		
		JLabel lblTempoMedioDi = new JLabel("Tempo medio di connessione al corso: <tempo>");
		lblTempoMedioDi.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblTempoMedioDi.setBounds(597, 56, 331, 16);
		contentPane.add(lblTempoMedioDi);
		
		JLabel lblNumeroDiDownload = new JLabel("Numero di download totali: <num>");
		lblNumeroDiDownload.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNumeroDiDownload.setBounds(32, 302, 233, 16);
		contentPane.add(lblNumeroDiDownload);
		
		JComboBox comboBox_3 = new JComboBox();
		comboBox_3.setBounds(597, 98, 331, 22);
		contentPane.add(comboBox_3);
		
		JComboBox comboBox_4 = new JComboBox();
		comboBox_4.setBounds(597, 143, 331, 22);
		contentPane.add(comboBox_4);
		
		JComboBox comboBox_5 = new JComboBox();
		comboBox_5.setBounds(597, 188, 331, 22);
		contentPane.add(comboBox_5);
		
		JComboBox comboBox_6 = new JComboBox();
		comboBox_6.setBounds(254, 345, 331, 22);
		contentPane.add(comboBox_6);
		
		JComboBox comboBox_7 = new JComboBox();
		comboBox_7.setBounds(254, 390, 331, 22);
		contentPane.add(comboBox_7);
		
		JComboBox comboBox_8 = new JComboBox();
		comboBox_8.setBounds(254, 435, 331, 22);
		contentPane.add(comboBox_8);
		
		JLabel label_3 = new JLabel("Seleziona il dipartimento");
		label_3.setBounds(32, 345, 210, 16);
		contentPane.add(label_3);
		
		JLabel label_4 = new JLabel("Seleziona il corso di laurea");
		label_4.setBounds(32, 390, 210, 16);
		contentPane.add(label_4);
		
		JLabel label_5 = new JLabel("Seleziona il corso");
		label_5.setBounds(32, 435, 210, 16);
		contentPane.add(label_5);
		
		JButton btnMostraRisultato = new JButton("Mostra risultato");
		btnMostraRisultato.setBounds(355, 471, 135, 25);
		contentPane.add(btnMostraRisultato);
		
		JButton btnMostraRisultato_1 = new JButton("Mostra risultato");
		btnMostraRisultato_1.setBounds(692, 270, 135, 25);
		contentPane.add(btnMostraRisultato_1);
	}
}
