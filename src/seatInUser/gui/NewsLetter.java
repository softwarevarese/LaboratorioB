package seatInUser.gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JTextPane;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.Toolkit;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class NewsLetter {
	// questa classe deve essere chiamata dopo la selezione del corso
	private JFrame frame;
	private JTextField textfieldoggettoMail;
	String oggetto;
	String messaggio;

	/**
	 * Launch the application.
	 */

	public static void main() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					NewsLetter window = new NewsLetter();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public NewsLetter() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 460);
		frame.setIconImage(Toolkit.getDefaultToolkit().getImage("media/f.png"));
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		textfieldoggettoMail = new JTextField();
		textfieldoggettoMail.setBounds(12, 33, 230, 22);
		frame.getContentPane().add(textfieldoggettoMail);
		textfieldoggettoMail.setColumns(10);

		long corsoid = StudentManagment.idsend;// deve restituire id corso in base al corso che ha selezionato non
												// ancora implementato

		JLabel lblMesaggio = new JLabel("Messaggio:");
		lblMesaggio.setBounds(12, 56, 105, 16);
		frame.getContentPane().add(lblMesaggio);

		JLabel lblOggetto = new JLabel("Oggetto:");
		lblOggetto.setBounds(12, 4, 80, 22);
		frame.getContentPane().add(lblOggetto);

		JTextPane textPane = new JTextPane();
		textPane.setBounds(12, 85, 408, 237);
		frame.getContentPane().add(textPane);

		JButton btnsendmail = new JButton("Invia mail");
		btnsendmail.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {

				oggetto = textfieldoggettoMail.getText();
				messaggio = textPane.getText();
				Client.ClientConnection.SendNewsLetter(StudentManagment.idsend, oggetto, messaggio);
				frame.dispose();
				CourseFile.Main();

			}

		});
		btnsendmail.setBounds(12, 335, 141, 25);
		frame.getContentPane().add(btnsendmail);

		JButton indietro = new JButton("indietro");
		indietro.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				CourseFile.Main();
			}

		});
		indietro.setBounds(279, 335, 141, 25);
		frame.getContentPane().add(indietro);

		JLabel lblmessaggi = new JLabel("");
		lblmessaggi.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblmessaggi.setBounds(66, 369, 299, 31);
		frame.getContentPane().add(lblmessaggi);
	}
}
