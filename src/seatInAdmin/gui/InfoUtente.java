package seatInAdmin.gui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Toolkit;

public class InfoUtente extends JDialog {

	private final JPanel contentPanel = new JPanel();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			InfoUtente dialog = new InfoUtente();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public InfoUtente() {
		setIconImage(Toolkit.getDefaultToolkit().getImage("E:\\Documenti\\Diapositive\\Laboratorio Interdisciplinare B\\Progetto\\LaboratorioB\\src\\media\\Icona.PNG"));
		setBounds(100, 100, 709, 459);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JLabel lblInformazioniUtente = new JLabel("INFORMAZIONI UTENTE");
			lblInformazioniUtente.setFont(new Font("Tahoma", Font.BOLD, 16));
			lblInformazioniUtente.setBounds(236, 13, 239, 20);
			contentPanel.add(lblInformazioniUtente);
		}
		{
			JLabel lblNome = new JLabel("Nome Cognome");
			lblNome.setFont(new Font("Tahoma", Font.PLAIN, 15));
			lblNome.setBounds(54, 57, 232, 28);
			contentPanel.add(lblNome);
		}
		{
			JLabel lblNewLabel = new JLabel("e-mail");
			lblNewLabel.setBounds(54, 87, 56, 16);
			contentPanel.add(lblNewLabel);
		}
		{
			JLabel lblTipoUtente = new JLabel("Tipo utente: ");
			lblTipoUtente.setBounds(54, 114, 143, 16);
			contentPanel.add(lblTipoUtente);
		}
		{
			JLabel lblMatricola = new JLabel("Matricola");
			lblMatricola.setBounds(54, 143, 143, 16);
			contentPanel.add(lblMatricola);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.LEFT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton cancelButton = new JButton("Indietro");
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}

}
