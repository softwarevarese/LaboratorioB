package seatInUser.gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import javax.swing.JTextArea;
import javax.swing.JScrollBar;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JProgressBar;
import java.awt.Color;
import java.awt.SystemColor;
import javax.swing.SwingConstants;
import java.awt.Toolkit;

public class RisorseCorsoStudente extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					RisorseCorsoStudente frame = new RisorseCorsoStudente();
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
	public RisorseCorsoStudente() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(RisorseCorsoStudente.class.getResource("/media/Icona.PNG")));
		setBackground(new Color(240, 248, 255));
		setTitle("SEATIN USER");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 594, 411);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(240, 248, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		JButton btnNewButton = new JButton("Download");
		contentPane.add(btnNewButton, BorderLayout.SOUTH);
		
		JLabel lblNewLabel = new JLabel("<nome corso>");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
		contentPane.add(lblNewLabel, BorderLayout.NORTH);
		
		JScrollBar scrollBar = new JScrollBar();
		contentPane.add(scrollBar, BorderLayout.WEST);
		
		JTextArea textArea = new JTextArea();
		textArea.setBackground(SystemColor.control);
		contentPane.add(textArea, BorderLayout.CENTER);
		
		JTextArea textArea_1 = new JTextArea();
		textArea_1.setColumns(30);
		textArea_1.setBackground(Color.WHITE);
		contentPane.add(textArea_1, BorderLayout.EAST);
	}

}
