package client.gui;

import java.awt.*;

import javax.swing.JFrame;

import seatInServer.DBModel.Admin;
import client.ClientConnection;
import client.UtenteConnesso;
import client.enums.TipoUtente;
import seatInAdmin.gui.PrincipaleAdmin;
import seatInUser.gui.PrincipaleDocente;
import seatInUser.gui.PrincipaleStudente;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.ImageIcon;
import javax.swing.ButtonGroup;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Login extends JFrame {
	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login frame = new Login();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame 
	 **/
private int contaErroriPassword=0;
	
private JTextField textField;
 private JPasswordField passwordField;
 private final ButtonGroup buttonGroup = new ButtonGroup();

//protected String[] null;

	public Login() {
		setTitle("LOGIN");
		setIconImage(Toolkit.getDefaultToolkit().getImage(Login.class.getResource("/media/Icona.PNG")));
		getContentPane().setBackground(new Color(240, 248, 255));
		getContentPane().setForeground(Color.WHITE);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 514, 330);
		getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setBounds(14, 16, 0, 0);
		getContentPane().add(lblNewLabel);
		
		JLabel lblLoginScreen = new JLabel("LOGIN SCREEN");
		lblLoginScreen.setForeground(Color.BLUE);
		lblLoginScreen.setBounds(168, 11, 137, 22);
		lblLoginScreen.setFont(new Font("Consolas", Font.BOLD, 18));
		getContentPane().add(lblLoginScreen);
		
		JLabel lblNewLabel_1 = new JLabel("User email");
		lblNewLabel_1.setBounds(14, 142, 86, 14);
		getContentPane().add(lblNewLabel_1);
		
		textField = new JTextField();
		textField.setToolTipText("User email");
		textField.setBounds(112, 139, 170, 20);
		getContentPane().add(textField);
		textField.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("Password");
		lblNewLabel_2.setBounds(14, 167, 86, 14);
		getContentPane().add(lblNewLabel_2);
		
		passwordField = new JPasswordField();
		passwordField.setToolTipText("Password");
		passwordField.setBounds(112, 170, 170, 20);
		passwordField.setColumns(8);
		getContentPane().add(passwordField);

		JLabel contatore_errori = new JLabel("");
		contatore_errori.setForeground(Color.RED);
		contatore_errori.setBounds(12, 379, 286, 16);
		getContentPane().add(contatore_errori);

		JButton btnNewButton = new JButton("Login");
		btnNewButton.setToolTipText("Press to Login");
		btnNewButton.setBounds(6, 243, 276, 23);
		getContentPane().add(btnNewButton);
		
		JRadioButton buttonStudente = new JRadioButton("Studente");
		buttonGroup.add(buttonStudente);
		buttonStudente.setBounds(6, 71, 102, 23);
		getContentPane().add(buttonStudente);
		
		JRadioButton buttonDocente = new JRadioButton("Docente");
		buttonGroup.add(buttonDocente);
		buttonDocente.setBounds(112, 71, 103, 23);
		getContentPane().add(buttonDocente);
		
		JRadioButton buttonAdmin = new JRadioButton("Amministratore");
		buttonGroup.add(buttonAdmin);
		buttonAdmin.setBounds(219, 71, 160, 23);
		getContentPane().add(buttonAdmin);
		
		JLabel userTypeLabel = new JLabel("Che tipo di utente sei?");
		userTypeLabel.setFont(new Font("Consolas", Font.PLAIN, 12));
		userTypeLabel.setBounds(10, 48, 478, 14);
		getContentPane().add(userTypeLabel);
		
		JLabel label = new JLabel("");
		label.setIcon(new ImageIcon(Login.class.getResource("/media/Insubria.PNG")));
		label.setBounds(307, 111, 181, 152);
		getContentPane().add(label);
		
		JLabel lblPasswordDimenticata = new JLabel("Password dimenticata?");
		lblPasswordDimenticata.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				RecuperoPassword finestra = new RecuperoPassword();
				try {
					frame.setVisible(false);
				}
				catch (Exception e) {
					String str = e.getMessage();
					e.printStackTrace();
				}
				finestra.main(null);
			}
		});
		lblPasswordDimenticata.setForeground(Color.BLUE);
		lblPasswordDimenticata.setBounds(89, 208, 144, 22);
		getContentPane().add(lblPasswordDimenticata);
		
		

		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) { //verifica i dati al momento della pressione di "login"
				String email = textField.getText();
				String psw = String.valueOf(passwordField.getPassword());

				TipoUtente tipo_login;
				ClientConnection connessione = new ClientConnection();

				if (buttonStudente.isSelected()) { tipo_login= TipoUtente.Studente; }
				else if (buttonDocente.isSelected()) { tipo_login = TipoUtente.Docente; }
				else if (buttonAdmin.isSelected()) { tipo_login = TipoUtente.Admin; }
				else { tipo_login=null; }

				//boolean credenziali = false;
				UtenteConnesso connesso=null;
				switch (tipo_login) {
					case Studente:
						connesso = connessione.LoginRequest(email, psw, TipoUtente.Studente ); //credenziali = ClientConnection.LoginRequest(email, psw, TipoUtente.Studente);
						break;
					case Docente:
						connesso = connessione.LoginRequest(email, psw, TipoUtente.Docente );
						//credenziali = ClientConnection.LoginRequest(email, psw, TipoUtente.Docente);
						break;
					case Admin:
						connesso = connessione.LoginRequest(email, psw, TipoUtente.Admin);
					default:
						String s = "ERRORE NESSUNA SPECIFICA PER IL LOGIN SELEZIONATA";
						contatore_errori.setText(s);

						break;
				}
				if (connesso != null)
				{
					//se l'utente ha già effettuato l'accesso in passato -> non deve eseguire l'attivazione

					// mail
					if(connesso.isVerificato()) // se true non e primo accesso   (verifica sul server)
					{
						if (tipo_login== TipoUtente.Studente) {
							new PrincipaleStudente(connesso);
						}
						else if (tipo_login== TipoUtente.Docente) {
							new PrincipaleDocente(connesso);
						}
						else if (tipo_login==TipoUtente.Admin) {
							new PrincipaleAdmin(connesso);
						}
						frame.dispose();
					}

					//altrimenti l'attivazione la deve fare
					else //primo accesso quindi invio mail e chiamo la finestra per attivarlo
					{
						//invio mail;
						AttivazioneProfilo.main(textField.getText(),connesso);
					}
				} else {    //autenticazione fallita
					contaErroriPassword++;// contatore blocco account
					String stringa_errore = "ERRORE Email o Password ERRATI";
					contatore_errori.setText(stringa_errore + ": " + contaErroriPassword);
					if (contaErroriPassword++ == 10)
					{
						new Admin().disableProfile(email);
						contaErroriPassword = 0;
						contatore_errori.setText("account bloccato; controllare servizio mail" );
					}
				}
			}
		});
	}
}
