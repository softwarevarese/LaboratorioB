package seatInUser;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JList;
import javax.swing.JTextArea;
import javax.swing.JLabel;
import java.awt.Font;

public class ListaCorsiStudente extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ListaCorsiStudente frame = new ListaCorsiStudente();
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
	public ListaCorsiStudente() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 500);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JTextArea textArea = new JTextArea();
		textArea.setRows(9);
		textArea.setBounds(12, 48, 393, 362);
		contentPane.add(textArea);
		
		JLabel lblListaDeiCorsi = new JLabel("Lista dei corsi a cui sei iscritto");
		lblListaDeiCorsi.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblListaDeiCorsi.setBounds(12, 19, 384, 16);
		contentPane.add(lblListaDeiCorsi);
	}
}
