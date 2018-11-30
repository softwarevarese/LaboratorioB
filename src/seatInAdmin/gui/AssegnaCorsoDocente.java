package seatInAdmin.gui;

import client.UtenteConnesso;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.JButton;
import javax.swing.JSeparator;
import java.awt.Toolkit;

public class AssegnaCorsoDocente extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;

	/**
	 * Launch the application.
	 */
	public static void main(UtenteConnesso admin) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AssegnaCorsoDocente frame = new AssegnaCorsoDocente(admin);
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
	public AssegnaCorsoDocente(UtenteConnesso admin) {
		setIconImage(Toolkit.getDefaultToolkit().getImage("E:\\Documenti\\Diapositive\\Laboratorio Interdisciplinare B\\Progetto\\LaboratorioB\\src\\media\\Icona.PNG"));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 710, 755);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblAssegnaUn = new JLabel("ASSEGNA UN DOCENTE AD UN CORSO ");
		lblAssegnaUn.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblAssegnaUn.setBounds(188, 13, 330, 20);
		contentPane.add(lblAssegnaUn);
		
		JLabel lblSelezionaDipartimento = new JLabel("Seleziona dipartimento");
		lblSelezionaDipartimento.setBounds(26, 49, 164, 16);
		contentPane.add(lblSelezionaDipartimento);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setBounds(216, 46, 298, 22);
		contentPane.add(comboBox);
		
		JLabel lblSelezionaDocente = new JLabel("Seleziona docente");
		lblSelezionaDocente.setBounds(26, 103, 144, 16);
		contentPane.add(lblSelezionaDocente);
		
		JLabel lblRicercaDocente = new JLabel("Ricerca docente");
		lblRicercaDocente.setBounds(26, 132, 103, 16);
		contentPane.add(lblRicercaDocente);
		
		JComboBox comboBox_1 = new JComboBox();
		comboBox_1.setBounds(172, 103, 109, 22);
		contentPane.add(comboBox_1);
		
		JLabel lblCognome = new JLabel("Cognome");
		lblCognome.setBounds(172, 78, 56, 16);
		contentPane.add(lblCognome);
		
		JComboBox comboBox_2 = new JComboBox();
		comboBox_2.setBounds(313, 103, 114, 22);
		contentPane.add(comboBox_2);
		
		JLabel lblNome = new JLabel("Nome");
		lblNome.setBounds(317, 78, 56, 16);
		contentPane.add(lblNome);
		
		textField = new JTextField();
		textField.setBounds(172, 132, 109, 22);
		contentPane.add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(313, 132, 114, 22);
		contentPane.add(textField_1);
		
		textField_2 = new JTextField();
		textField_2.setBounds(458, 132, 103, 22);
		contentPane.add(textField_2);
		textField_2.setColumns(10);
		
		JLabel lblMatricola = new JLabel("Matricola");
		lblMatricola.setBounds(459, 103, 56, 16);
		contentPane.add(lblMatricola);
		
		JLabel lblSelezionaCorsoDi = new JLabel("Seleziona Corso di Laurea");
		lblSelezionaCorsoDi.setBounds(27, 371, 164, 16);
		contentPane.add(lblSelezionaCorsoDi);
		
		JLabel label = new JLabel("Seleziona dipartimento");
		label.setBounds(27, 339, 164, 16);
		contentPane.add(label);
		
		JComboBox comboBox_3 = new JComboBox();
		comboBox_3.setBounds(217, 336, 298, 22);
		contentPane.add(comboBox_3);
		
		JComboBox comboBox_4 = new JComboBox();
		comboBox_4.setBounds(217, 368, 298, 22);
		contentPane.add(comboBox_4);
		
		JLabel lblSelezionaCorso = new JLabel("Seleziona Corso");
		lblSelezionaCorso.setBounds(27, 406, 164, 16);
		contentPane.add(lblSelezionaCorso);
		
		JComboBox comboBox_5 = new JComboBox();
		comboBox_5.setBounds(217, 403, 298, 22);
		contentPane.add(comboBox_5);
		
		JLabel lblRicercaCorso = new JLabel("Ricerca Corso");
		lblRicercaCorso.setBounds(27, 441, 164, 16);
		contentPane.add(lblRicercaCorso);
		
		textField_3 = new JTextField();
		textField_3.setColumns(10);
		textField_3.setBounds(217, 438, 299, 22);
		contentPane.add(textField_3);
		
		JTextArea textArea = new JTextArea();
		textArea.setRows(24);
		textArea.setColumns(7);
		textArea.setBounds(25, 174, 536, 89);
		contentPane.add(textArea);
		
		JButton btnSeleziona = new JButton("Seleziona");
		btnSeleziona.setBounds(573, 99, 97, 25);
		contentPane.add(btnSeleziona);
		
		JButton btnRicerca = new JButton("Ricerca");
		btnRicerca.setBounds(573, 131, 97, 25);
		contentPane.add(btnRicerca);
		
		JButton button = new JButton("Ricerca");
		button.setBounds(574, 437, 97, 25);
		contentPane.add(button);
		
		JTextArea textArea_1 = new JTextArea();
		textArea_1.setRows(24);
		textArea_1.setColumns(7);
		textArea_1.setBounds(26, 485, 536, 109);
		contentPane.add(textArea_1);
		
		JButton btnAggiungi = new JButton("Assegna");
		btnAggiungi.setBounds(574, 402, 97, 25);
		contentPane.add(btnAggiungi);
		
		JButton btnAggiungiDocente = new JButton("Seleziona docente");
		btnAggiungiDocente.setBounds(573, 174, 97, 89);
		contentPane.add(btnAggiungiDocente);
		
		JButton btnNewButton = new JButton("Assegna corso");
		btnNewButton.setBounds(574, 484, 97, 109);
		contentPane.add(btnNewButton);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(0, 655, 692, 2);
		contentPane.add(separator);
		
		JButton btnNewButton_1 = new JButton("Indietro");
		btnNewButton_1.setBounds(26, 670, 97, 25);
		contentPane.add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("OK");
		btnNewButton_2.setBounds(573, 670, 97, 25);
		contentPane.add(btnNewButton_2);
		
		JButton btnNewButton_3 = new JButton("Visualizza profilo docente");
		btnNewButton_3.setBounds(246, 283, 181, 25);
		contentPane.add(btnNewButton_3);
		
		JButton btnVisualizzaInformazioniSul = new JButton("Visualizza informazioni sul corso");
		btnVisualizzaInformazioniSul.setBounds(216, 607, 247, 25);
		contentPane.add(btnVisualizzaInformazioniSul);
	}

}
