package seatInUser.gui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextPane;
import javax.swing.JTextArea;
import java.awt.Toolkit;
import java.awt.Color;

public class InfoCorso extends JDialog {

	private final JPanel contentPanel = new JPanel();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			InfoCorso dialog = new InfoCorso();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public InfoCorso() {
		setTitle("SEATIN USER");
		getContentPane().setBackground(new Color(240, 248, 255));
		setBackground(new Color(240, 248, 255));
		setIconImage(Toolkit.getDefaultToolkit().getImage(InfoCorso.class.getResource("/media/Icona.PNG")));
		setBounds(100, 100, 456, 315);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(new Color(240, 248, 255));
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JLabel label = new JLabel("<nome corso>");
			label.setBounds(5, 5, 424, 14);
			contentPanel.add(label);
		}
		{
			JTextArea textArea = new JTextArea();
			textArea.setBounds(5, 28, 424, 195);
			textArea.setRows(8);
			contentPanel.add(textArea);
		}
		{
			JButton button = new JButton("Registrazione");
			button.setBounds(10, 242, 200, 23);
			contentPanel.add(button);
		}
		{
			JButton button = new JButton("Torna a corsi");
			button.setBounds(229, 242, 200, 23);
			contentPanel.add(button);
		}
	}

}
