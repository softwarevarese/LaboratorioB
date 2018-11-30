package seatInAdmin.gui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.JScrollBar;
import java.awt.Toolkit;

public class AssegnaCorsoPostRegistrazione extends JDialog {

	private final JPanel contentPanel = new JPanel();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			AssegnaCorsoPostRegistrazione dialog = new AssegnaCorsoPostRegistrazione();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public AssegnaCorsoPostRegistrazione() {
		setIconImage(Toolkit.getDefaultToolkit().getImage("E:\\Documenti\\Diapositive\\Laboratorio Interdisciplinare B\\Progetto\\LaboratorioB\\src\\media\\Icona.PNG"));
		setBounds(100, 100, 914, 481);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JLabel lblListaDeiCorsi = new JLabel("Lista dei corsi disponibili per il Corso di Laurea");
			lblListaDeiCorsi.setBounds(75, 13, 401, 16);
			contentPanel.add(lblListaDeiCorsi);
		}
		{
			JTextArea textArea = new JTextArea();
			textArea.setRows(15);
			textArea.setColumns(20);
			textArea.setBounds(75, 42, 379, 271);
			contentPanel.add(textArea);
		}
		{
			JScrollBar scrollBar = new JScrollBar();
			scrollBar.setBounds(455, 42, 21, 271);
			contentPanel.add(scrollBar);
		}
		{
			JButton btnInformazioniSulCorso = new JButton("Informazioni sul corso");
			btnInformazioniSulCorso.setBounds(75, 326, 169, 25);
			contentPanel.add(btnInformazioniSulCorso);
		}
		{
			JButton btnNewButton = new JButton("Seleziona corso");
			btnNewButton.setBounds(275, 326, 179, 25);
			contentPanel.add(btnNewButton);
		}
		{
			JTextArea textArea = new JTextArea();
			textArea.setRows(15);
			textArea.setColumns(20);
			textArea.setBounds(488, 42, 379, 271);
			contentPanel.add(textArea);
		}
		{
			JLabel lblCorsiSelezionati = new JLabel("Corsi selezionati");
			lblCorsiSelezionati.setBounds(494, 13, 373, 16);
			contentPanel.add(lblCorsiSelezionati);
		}
		{
			JButton btnConfermaSelezione = new JButton("Conferma selezione");
			btnConfermaSelezione.setBounds(599, 326, 179, 25);
			contentPanel.add(btnConfermaSelezione);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Annulla");
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}

}
