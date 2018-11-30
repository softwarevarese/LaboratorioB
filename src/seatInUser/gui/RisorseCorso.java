package seatInUser.gui;

import client.*;
import client.enums.TipoUtente;
import client.enums.VisibilitaSezione;
import seatInServer.DBModel.*;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JOptionPane;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.util.ArrayList;

import java.awt.Color;
import java.awt.SystemColor;
import java.awt.Toolkit;
import java.util.Vector;
import javax.swing.JTree;

import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.JScrollPane;
import javax.swing.JProgressBar;
import javax.swing.JTextArea;

public class RisorseCorso {

	private JFrame frmAsd;
	private JTextField NameCourseField;
	private int query = 0;
	private static String titleFolder = null;
	private int indicecorso;
	File road = null;
	private int idDoc;
	private String path;
	private String nameDoc;
	private int rif_Sezione;
	private JDialog diag = new JDialog();
	private JDialog diag2 = new JDialog();
	JNodoAlbero controlNode;
	private ArrayList<Corso> courseList = new ArrayList();

	private UtenteConnesso utenteConnesso;

	/**
	 * Create the application.
	 */
	public RisorseCorso(UtenteConnesso utente) {
		initialize();
		this.utenteConnesso=utente;

		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					frmAsd.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Launch the application.
	 */
	public void SottoNodiFs(DefaultMutableTreeNode father, ArrayList<Sezione> subFolders) {
		JNodoAlbero node = null;

		if (utenteConnesso.tipoUtente == TipoUtente.Docente) {
			for (Sezione folder : subFolders) {
				node = new JNodoAlbero(folder.getNomeSez(), folder.getIdSezione(), true);
				SottoNodiFs(node, folder.getSottoSezioni());
				father.add(node);
			}
		} else {
			for (Sezione folder : subFolders) {
				if (folder.isVisibile()) {
					node = new JNodoAlbero(folder.getNomeSez(), folder.getIdSezione(), true);
					SottoNodiFs(node, folder.getSottoSezioni());
					father.add(node);
				}
			}
		}
	}

	public void ContenutiFs(DefaultMutableTreeNode father, ArrayList<Documento> contents) {
		try {
			JNodoAlbero node = null;
			for (Documento folder : contents) {
				node = new JNodoAlbero(folder.getNomeDoc() + folder.getFormato(), folder.getIdDocumento(), false);
				father.add(node);
			}
		} catch (Exception e) {
		}
	}

	public void creaNodiFileSystem(FileSystem fs, JTree tree, JNodoAlbero radice) {
		try {
			JNodoAlbero node = null;

			if (fs.sezioni != null) {
				
				if (utenteConnesso.tipoUtente == TipoUtente.Docente) {
					for (Sezione folder : fs.sezioni) {
						node = new JNodoAlbero(folder.getNomeSez(), folder.getIdSezione(), true);
						SottoNodiFs(node, folder.getSottoSezioni());
						ContenutiFs(node, folder.getDocumenti());
						radice.add(node);
					}
				} else {
					for (Sezione folder : fs.sezioni) {
						if (folder.isVisibile()) {
							node = new JNodoAlbero(folder.getNomeSez(), folder.getIdSezione(), true);
							SottoNodiFs(node, folder.getSottoSezioni());
							ContenutiFs(node, folder.getDocumenti());
							radice.add(node);
						}
					}
				}
			}
		} catch (Exception e) {
			System.out.println("Errore nella generazione del tree");
		}
	}

	/*public static void main() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					RisorseCorso window = new RisorseCorso();
					window.frmAsd.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}*/

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		ListaCorsi windowCorsi = new ListaCorsi(utenteConnesso);

		if (utenteConnesso.tipoUtente == TipoUtente.Docente) {
			courseList = windowCorsi.LcorsiregisteredDocente;
		} else
			courseList = windowCorsi.LcorsiregisteredStudent;

		String title = windowCorsi.titolodainviare;
		indicecorso = windowCorsi.idsend;
		frmAsd = new JFrame();
		frmAsd.setIconImage(Toolkit.getDefaultToolkit().getImage("media/f.png"));
		frmAsd.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		frmAsd.setTitle(title);
		frmAsd.getContentPane().setBackground(SystemColor.inactiveCaption);
		frmAsd.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent arg0) {
				if (utenteConnesso.tipoUtente.toString().equals("Studente"))
					ClientConnection.updateLoc(utenteConnesso.connesso.getMatricola(), -1);
				ListaCorsi.main(utenteConnesso);
				frmAsd.dispose();
			}
		});
		frmAsd.setBounds(100, 100, 493, 418);
		frmAsd.getContentPane().setLayout(null);

		NameCourseField = new JTextField(title);
		NameCourseField.setEditable(false);
		NameCourseField.setBounds(27, 13, 384, 25);
		frmAsd.getContentPane().add(NameCourseField);
		NameCourseField.setColumns(10);

		JTextArea textArea = new JTextArea(windowCorsi.descrizionedasend);
		textArea.setEditable(false);
		textArea.setBounds(265, 66, 197, 233);
		frmAsd.getContentPane().add(textArea);
		Icon ic = new ImageIcon("media/chiave.png");

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(27, 96, 214, 204);
		frmAsd.getContentPane().add(scrollPane);

		JNodoAlbero radice = new JNodoAlbero(title, -1, true);
		JTree tree = new JTree(radice);
		tree.setRootVisible(true);
		indicecorso = windowCorsi.idsend;
		FileSystem fs = ClientConnection.getFS(indicecorso);
		creaNodiFileSystem(fs, tree, radice);
		tree.setForeground(SystemColor.inactiveCaption);
		tree.setBackground(SystemColor.inactiveCaption);
		tree.setVisible(true);
		scrollPane.setViewportView(tree);

		JProgressBar progressBar = new JProgressBar();
		progressBar.setForeground(Color.RED);
		progressBar.setVisible(false);
		progressBar.setBounds(155, 354, 146, 14);
		frmAsd.getContentPane().add(progressBar);

		JButton btnDownload_file = new JButton("download");
		btnDownload_file.setVisible(false);
		btnDownload_file.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				progressBar.setIndeterminate(true);
				progressBar.setVisible(true);
				File temp = new File("nameDoc");
				JFileChooser jf = new JFileChooser();
				jf.setSelectedFile(temp);// devo prendere il nome del file dal filesistem
				int n = jf.showSaveDialog(null);
				if (n == 0) {
					road = jf.getSelectedFile();
					path = road.toString();
					Utente id = utenteConnesso.connesso;
					progressBar.setVisible(false);
					Utente.documentDownload(idDoc, id.getMatricola(), path, "txt");

				}
			}
		});
		btnDownload_file.setBounds(136, 311, 105, 23);
		frmAsd.getContentPane().add(btnDownload_file);

		JButton BTN_addfile = new JButton("aggiungi");
		BTN_addfile.setVisible(false);
		BTN_addfile.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				JFileChooser jf = new JFileChooser();
				int n = jf.showOpenDialog(null);
				if (n == 0) {
					File f = jf.getSelectedFile();
					String str = f.getPath();

					new Docente().uploadDocumento(str, rif_Sezione);

					JOptionPane.showMessageDialog(null, "File aggiunto con successo");
				}

				frmAsd.dispose();
				new RisorseCorso(utenteConnesso);//RisorseCorso.main();
			}
		});
		BTN_addfile.setBounds(27, 311, 105, 23);
		frmAsd.getContentPane().add(BTN_addfile);

		JButton btnaddcart = new JButton("aggiungi cartella");
	//	if (UtenteConnesso.tipoUtente.toString().equals("Studente")) {
			btnaddcart.setVisible(false);
		//}
		btnaddcart.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Sezione NF = new Sezione();
				NF.setNomeSez(JOptionPane.showInputDialog(null, "inserisci il nome della cartella"));
				NF.setDescrizione(JOptionPane.showInputDialog(null, "inserisci una descrizione della cartella"));
				Vector<String> vis = new Vector<String>();
				vis.add("Privata");
				vis.add("Pubblica");
				JComboBox jcd = new JComboBox(vis);

				Object[] options = new Object[] {};

				JOptionPane jop = new JOptionPane("Please Select", JOptionPane.QUESTION_MESSAGE,
						JOptionPane.DEFAULT_OPTION, null, options, null);

				JButton btn_visibilita = new JButton("conferma");
				btn_visibilita.addActionListener(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent e) {
						// TODO Auto-generated method stub
						//NF.setVisibilita((VisibilitaSezione) jcd.getSelectedItem());
						String visibil = (String) jcd.getSelectedItem();
						if (visibil.equals("pubblica")) {NF.setVisibilita(true);}
						else if (visibil.equals("privata")) {NF.setVisibilita(false);}

						NF.setCartellaPadre(rif_Sezione);
						NF.setSottoSezioni(null);
						NF.setDocumenti(null);
						diag.dispose();
						frmAsd.dispose();
						new Docente().creaSezione(NF,indicecorso);

						//RisorseCorso.main();
						new RisorseCorso(utenteConnesso);

					}
				});
				jop.add(jcd);
				jop.add(btn_visibilita);

				diag.getContentPane().add(jop);
				diag.pack();
				diag.setVisible(true);

			}
		});
		btnaddcart.setBounds(27, 66, 217, 23);
		frmAsd.getContentPane().add(btnaddcart);

		JButton btnCambiaVisibilita = new JButton("Cambia visibilita");
		btnCambiaVisibilita.setVisible(false);
		btnCambiaVisibilita.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				Vector<String> v = new Vector<String>();
				v.addElement("Privata");
				v.addElement("Pubblica");
				JComboBox jcd1 = new JComboBox(v);

				Object[] options = new Object[] {};

				JOptionPane jop1 = new JOptionPane("Please Select", JOptionPane.QUESTION_MESSAGE,
						JOptionPane.DEFAULT_OPTION, null, options, null);

				JButton btn_visibilita = new JButton("conferma");
				btn_visibilita.addActionListener(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent e) {
						// TODO Auto-generated method stub

						VisibilitaSezione vis = (VisibilitaSezione) jcd1.getSelectedItem();
						new Admin().cambiaVisibilita(rif_Sezione, vis);
						diag2.dispose();
						frmAsd.dispose();
						new RisorseCorso(utenteConnesso); //main();

					}
				});
				jop1.add(jcd1);
				jop1.add(btn_visibilita);

				diag2.getContentPane().add(jop1);
				diag2.pack();
				diag2.setVisible(true);

			}
		});
		btnCambiaVisibilita.setBounds(10, 345, 118, 23);
		frmAsd.getContentPane().add(btnCambiaVisibilita);

		JButton btnInviaMail = new JButton("Invia mail");
		if (utenteConnesso.tipoUtente.toString().equals("Studente")) {
			btnInviaMail.setVisible(false);
		}
		btnInviaMail.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				NewsLetter newsLetter = new NewsLetter();
				frmAsd.dispose();
				newsLetter.main();
			}
		});
		btnInviaMail.setBounds(332, 310, 89, 23);
		frmAsd.getContentPane().add(btnInviaMail);

		tree.addTreeSelectionListener(new TreeSelectionListener() {
			@Override
			public void valueChanged(TreeSelectionEvent arg0) {
				progressBar.setVisible(false);
				btnDownload_file.setVisible(false);
				BTN_addfile.setVisible(false);
				controlNode = (JNodoAlbero) arg0.getPath().getLastPathComponent();

				if (!controlNode.isFolder) {
					btnDownload_file.setVisible(true);
					idDoc = controlNode.id;
					rif_Sezione = controlNode.id;

					btnaddcart.setVisible(false);
				} else {
					if (!utenteConnesso.tipoUtente.toString().equals("Studente")) {
						BTN_addfile.setVisible(true);
						btnCambiaVisibilita.setVisible(true);
						if (controlNode.id == -1)
						btnaddcart.setVisible(true);
						else
							btnaddcart.setVisible(false);
					}
					idDoc = 0;
					rif_Sezione = controlNode.id;

				}

				rif_Sezione = controlNode.id;

			}
		});

	}

	public static String getTitolo() {
		return titleFolder;
	}
}
