package seatInAdmin.gui;

import client.UtenteConnesso;
import seatInUser.gui.MonitoraggioDocente;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class MonitoraggioHub extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private final ButtonGroup buttonGroupUtente = new ButtonGroup();

	/**
	 * Launch the application.
	 */
	public static void main(UtenteConnesso admin) {
		try {
			MonitoraggioHub dialog = new MonitoraggioHub(admin);
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public MonitoraggioHub(UtenteConnesso admin) {
		setTitle("seatInServer - Monitoraggio");
		setIconImage(Toolkit.getDefaultToolkit().getImage("E:\\Documenti\\Diapositive\\Laboratorio Interdisciplinare B\\Progetto\\LaboratorioB\\src\\media\\Icona.PNG"));
		setBounds(100, 100, 450, 210);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);

			JRadioButton utenteButton = new JRadioButton("Livello Utente");
			buttonGroupUtente.add(utenteButton);
			utenteButton.setBounds(53, 57, 127, 25);
			contentPanel.add(utenteButton);

			JRadioButton adminButton = new JRadioButton("Livello Admin");
			buttonGroupUtente.add(adminButton);
			adminButton.setBounds(262, 57, 127, 25);
			contentPanel.add(adminButton);

			JLabel lblScegliIlTipoi = new JLabel("Scegli il tipo di monitoraggio");
			lblScegliIlTipoi.setFont(new Font("Tahoma", Font.PLAIN, 16));
			lblScegliIlTipoi.setBounds(122, 13, 218, 25);
			contentPanel.add(lblScegliIlTipoi);

			JSeparator separator = new JSeparator();
			separator.setBounds(0, 115, 444, 2);
			contentPanel.add(separator);

			JButton indietroButton = new JButton("Indietro");
			indietroButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					PrincipaleAdmin.main(admin);
				}
			});
			indietroButton.setBounds(12, 130, 97, 25);
			contentPanel.add(indietroButton);

			JButton avantiButton = new JButton("Avanti");
			avantiButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					if (utenteButton.isSelected()) {
						MonitoraggioDocente.main(admin);
					}
					else if (adminButton.isSelected()) {
						MonitoraggioAdmin.main(admin);
					}
					else {
						JOptionPane.showMessageDialog(null,"Selezionare una tipologia di monitoraggio");
					}
				}
			});
			avantiButton.setBounds(323, 130, 97, 25);
			contentPanel.add(avantiButton);

			JPanel buttonPane = new JPanel();
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			buttonPane.setLayout(null);
	}
}
