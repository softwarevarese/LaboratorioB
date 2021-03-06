package seatInUser.gui;

import client.UtenteConnesso;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JList;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.JSpinner;
import javax.swing.SpinnerDateModel;
import java.util.Date;
import java.util.Calendar;
import java.awt.Toolkit;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class MonitoraggioDocente extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;

	/**
	 * Launch the application.
	 */
	public static void main(UtenteConnesso utente) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MonitoraggioDocente frame = new MonitoraggioDocente(utente);
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
	public MonitoraggioDocente(UtenteConnesso utente) {
		setIconImage(Toolkit.getDefaultToolkit().getImage(MonitoraggioDocente.class.getResource("/media/Icona.PNG")));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 452, 322);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Monitoraggio seatInUser");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel.setBounds(12, 13, 408, 20);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Utenti connessi");
		lblNewLabel_1.setBounds(12, 91, 91, 16);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Download totali");
		lblNewLabel_2.setBounds(12, 126, 134, 16);
		contentPane.add(lblNewLabel_2);
		
		JLabel lblTempoMedioDi = new JLabel("Tempo medio di connessione al corso");
		lblTempoMedioDi.setBounds(12, 173, 232, 16);
		contentPane.add(lblTempoMedioDi);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setBounds(183, 46, 237, 22);
		contentPane.add(comboBox);
		
		textField = new JTextField();
		textField.setBounds(304, 88, 116, 22);
		contentPane.add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(304, 123, 116, 22);
		contentPane.add(textField_1);
		
		JLabel lblSelezionaIlCorso = new JLabel("Seleziona il corso");
		lblSelezionaIlCorso.setBounds(12, 46, 159, 16);
		contentPane.add(lblSelezionaIlCorso);
		
		JLabel lblSelezionaLintervalloTemporale = new JLabel("Seleziona l'intervallo temporale");
		lblSelezionaLintervalloTemporale.setBounds(12, 202, 198, 16);
		contentPane.add(lblSelezionaLintervalloTemporale);
		
		JLabel lblDa = new JLabel("da:");
		lblDa.setBounds(222, 202, 56, 16);
		contentPane.add(lblDa);
		
		JLabel lblA = new JLabel("a:");
		lblA.setBounds(232, 231, 33, 16);
		contentPane.add(lblA);
		
		textField_2 = new JTextField();
		textField_2.setColumns(10);
		textField_2.setBounds(304, 170, 116, 22);
		contentPane.add(textField_2);
		
		JSpinner spinner = new JSpinner();
		spinner.setModel(new SpinnerDateModel(new Date(1540245602000L), null, null, Calendar.DAY_OF_YEAR));
		spinner.setBounds(304, 199, 116, 22);
		contentPane.add(spinner);
		
		JSpinner spinner_1 = new JSpinner();
		spinner_1.setModel(new SpinnerDateModel(new Date(1540245600000L), null, null, Calendar.DAY_OF_YEAR));
		spinner_1.setBounds(304, 231, 116, 22);
		contentPane.add(spinner_1);
		
		JButton confermaButton = new JButton("Conferma selezione");
		confermaButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//mostra tutto quanto (utenti, download, tempo) (da sistemare)
				//utente
				lu = ClientConnection.GetNumberLoggedUser();
				lblNumeroUtenti.setText("Numero utenti:  " + lu);
				//periodo
				Date datato = (Date)spinnerto.getValue();
				Date datafrom = (Date)spinnerfrom.getValue();

				if(datato.compareTo(datafrom)>=0)
				{
					int intervalTemp = ClientConnection.GetNumberAccessByPeriod(idCorsi.get(courseList1.getSelectedIndex()), datafrom, datato);
					lblAccessiPeriodo.setText("Numero accessi fascia temporale " + Integer.toString(intervalTemp));
				}
				else
				{
					JOptionPane.showMessageDialog(null, "errore data fine minore della data di inizio");
				}
				//tempo
				double media = ClientConnection.GetMediaCorso(idCorsi.get(courseList1.getSelectedIndex()));
				lblTempoMedio.setText("<html><center>Tempo medio connessione al corso :<br> " + media + "</center></html>" );
				//download complessivi
				int media = ClientConnection.DocumentContByCorso(idCorsi.get(courseList1.getSelectedIndex()));
				lblNumeroDownloadComplessivo.setText("Download complessivi : " + media);
			}
		});
		confermaButton.setBounds(22, 231, 167, 25);
		contentPane.add(confermaButton);
	}
}
