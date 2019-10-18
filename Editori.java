import java.awt.BorderLayout;
import java.util.Scanner;
import java.util.regex.Pattern;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.KeyStroke;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.awt.event.InputEvent;
import javax.swing.ImageIcon;
import javax.swing.JToolBar;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;
import javax.swing.JEditorPane;
import javax.swing.JFileChooser;

public class Editori extends JFrame {

	private JPanel contentPane;
	private JMenuBar menuBar;
	private JMenu mnTiedosto;
	private JMenu mnMuokkaa;
	private JMenu mnTietoja;
	private JMenuItem mntmAvaa;
	private JMenuItem mntmTallenna;
	private JMenuItem mntmLopeta;
	private JMenuItem mntmSulje;
	private JMenuItem mntmEtsi;
	private JMenuItem mntmKorvaa;
	private JMenuItem mntmTietojaOhjelmasta;
	private JToolBar toolBar;
	private JButton btnNewButton;
	private JButton btnNewButton_1;
	private JButton btnNewButton_2;
	private JEditorPane editorPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Editori frame = new Editori();
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
	public Editori() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);

		menuBar = new JMenuBar();
		setJMenuBar(menuBar);

		mnTiedosto = new JMenu("Tiedosto");
		menuBar.add(mnTiedosto);

		mntmAvaa = new JMenuItem("Avaa");
		mntmAvaa.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				avaaTiedosto(null);
			}
		});
		mntmAvaa.setIcon(new ImageIcon(Editori.class.getResource("/org/eclipse/jface/images/dots_button.png")));
		mntmAvaa.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, InputEvent.CTRL_MASK));
		mnTiedosto.add(mntmAvaa);

		mntmTallenna = new JMenuItem("Tallenna");
		mntmTallenna.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tallennaTiedosto();
			}
		});
		mnTiedosto.add(mntmTallenna);

		mntmLopeta = new JMenuItem("Lopeta");
		mntmLopeta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		mnTiedosto.add(mntmLopeta);

		mntmSulje = new JMenuItem("Sulje");
		mntmLopeta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		mnTiedosto.add(mntmSulje);

		mnMuokkaa = new JMenu("Muokkaa");
		menuBar.add(mnMuokkaa);

		mntmEtsi = new JMenuItem("Etsi");
		mntmEtsi.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				etsi(null);
			}
		});
		mnMuokkaa.add(mntmEtsi);

		mntmKorvaa = new JMenuItem("Korvaa");
		mntmKorvaa.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				korvaaTeksti(null);
			}
		});
		mnMuokkaa.add(mntmKorvaa);

		mnTietoja = new JMenu("Tietoja");
		menuBar.add(mnTietoja);

		mntmTietojaOhjelmasta = new JMenuItem("Tietoja ohjelmasta");
		mntmTietojaOhjelmasta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null, "Ohjelman tekijä: Jami Niiranen","Tietoja ohjelmasta", JOptionPane.INFORMATION_MESSAGE);
			}
		});
		mnTietoja.add(mntmTietojaOhjelmasta);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);

		toolBar = new JToolBar();
		contentPane.add(toolBar, BorderLayout.NORTH);

		btnNewButton = new JButton("");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				avaaTiedosto(null);
			}
		});
		btnNewButton.setIcon(new ImageIcon("C:\\Users\\Jami\\Desktop\\file.png"));
		toolBar.add(btnNewButton);

		btnNewButton_1 = new JButton("");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tallennaTiedosto();
			}
		});
		btnNewButton_1.setIcon(new ImageIcon("C:\\Users\\Jami\\Desktop\\save.png"));
		toolBar.add(btnNewButton_1);

		btnNewButton_2 = new JButton("");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				korvaaTeksti(null);
			}
		});
		btnNewButton_2.setIcon(new ImageIcon("C:\\Users\\Jami\\Desktop\\cut.png"));
		toolBar.add(btnNewButton_2);

		editorPane = new JEditorPane();
		contentPane.add(editorPane, BorderLayout.CENTER);
	}

	public void tallennaTiedosto() {
		JFileChooser valintaikkuna = new JFileChooser();
		valintaikkuna.showSaveDialog(null);

		String uusiTiedosto = valintaikkuna.getSelectedFile().getAbsolutePath();

		System.out.println("Kirjoitettava tiedosto: " + uusiTiedosto);
		try {
			PrintWriter writer = new PrintWriter(uusiTiedosto);
			String sisalto = editorPane.getText();

			writer.println(sisalto);

			writer.flush();
			writer.close();
		} catch (Exception e1) {
			System.out.println("Tiedoston tallennuksessa tapahtui virhe!");
			e1.printStackTrace();
		}
	}

	public void avaaTiedosto(File tiedosto) {
		JFileChooser valintaikkuna = new JFileChooser();
		valintaikkuna.showOpenDialog(null);
		String rivi = "";
		String uusiTiedosto = valintaikkuna.getSelectedFile().getAbsolutePath();

		try {
			Scanner lukija = null;
			tiedosto = new File(uusiTiedosto);
			lukija = new Scanner(tiedosto);

			while (lukija.hasNextLine()) {
				rivi += lukija.nextLine()+"\n";
				System.out.println(rivi);
			}
		} catch (FileNotFoundException p) {
			System.out.println("Tiedostoa ei löydy..");
		}
		editorPane.setText(rivi);
	}

	public void etsi(String haettava) {
		String sisalto = editorPane.getText();
		sisalto = sisalto.toLowerCase();
		haettava = "auto";

		int indeksi = sisalto.indexOf(haettava);
		System.out.println("Indeksi: " + indeksi);

		editorPane.setSelectionColor(Color.YELLOW);
		editorPane.setSelectionStart(indeksi);
		editorPane.setSelectionEnd(indeksi + haettava.length());
	}

	public void korvaaTeksti(String korvattavaTeksti) {
		String sisalto1 = editorPane.getText();
		sisalto1 = sisalto1.toLowerCase();

		korvattavaTeksti = sisalto1.replaceAll("auto", "kaara");
		editorPane.setText(korvattavaTeksti);
	}
}
