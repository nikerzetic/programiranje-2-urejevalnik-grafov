import java.awt.event.*;
import java.awt.*;
import javax.swing.*;

@SuppressWarnings("serial")
public class Okno extends JFrame implements ActionListener{

	Platno platno;
	JMenuItem meniOdpri, meniShrani, meniKoncaj;
	JMenuItem meniPrazen, meniCikel, meniPoln, meniPolnDvodelen;
	JMenuItem meniBarvaPovezave, meniBarvaTocke, meniBarvaAktivneTocke,
	meniBarvaIzbraneTocke, meniBarvaRoba;
	JMenuItem meniDebelinaPovezave, meniDebelinaRoba, meniPolmer;

	public Okno() {
		super();
		setTitle("Urejevalnik grafov");
		platno = new Platno(600, 600);
		add(platno);

		JMenuBar meni = new JMenuBar();

		JMenu meniDatoteka = new JMenu("Datoteka");
		JMenu meniGraf= new JMenu("Graf");
		JMenu meniNastavitve= new JMenu("Nastavitve");

		meniOdpri = new JMenuItem("Odpri");
		meniShrani = new JMenuItem("Shrani");
		meniKoncaj = new JMenuItem("Koncaj");

		meniPrazen= new JMenuItem("Prazen");
		meniCikel = new JMenuItem("Cikel");
		meniPoln= new JMenuItem("Poln");
		meniPolnDvodelen = new JMenuItem("Poln dvodelen");

		meniBarvaPovezave= new JMenuItem("Barva povezave");
		meniBarvaTocke= new JMenuItem("Barva tocke");
		meniBarvaAktivneTocke= new JMenuItem("Barva aktivne tocke");
		meniBarvaIzbraneTocke= new JMenuItem("Barva izbrane tocke");
		meniBarvaRoba= new JMenuItem("Barva roba");
		meniDebelinaPovezave = new JMenuItem("Debelina povezave");
		meniDebelinaRoba= new JMenuItem("Debelina roba");
		meniPolmer = new JMenuItem("Polmer tocke");

		meniDatoteka.add(meniOdpri);
		meniDatoteka.add(meniShrani);
		meniDatoteka.addSeparator();
		meniDatoteka.add(meniKoncaj);

		meniGraf.add(meniPrazen);
		meniGraf.add(meniCikel);
		meniGraf.add(meniPoln);
		meniGraf.add(meniPolnDvodelen);

		meniNastavitve.add(meniBarvaPovezave);
		meniNastavitve.add(meniBarvaTocke);
		meniNastavitve.add(meniBarvaAktivneTocke);
		meniNastavitve.add(meniBarvaIzbraneTocke);
		meniNastavitve.add(meniBarvaRoba);
		meniNastavitve.addSeparator();
		meniNastavitve.add(meniDebelinaPovezave);
		meniNastavitve.add(meniDebelinaRoba);
		meniNastavitve.addSeparator();
		meniNastavitve.add(meniPolmer);

		meni.add(meniDatoteka);
		meni.add(meniGraf);
		meni.add(meniNastavitve);

		meniOdpri.addActionListener(this);
		meniShrani.addActionListener(this);
		meniKoncaj.addActionListener(this);
		meniPrazen.addActionListener(this);
		meniCikel.addActionListener(this);
		meniPoln.addActionListener(this);
		meniPolnDvodelen.addActionListener(this);
		meniBarvaPovezave.addActionListener(this);
		meniBarvaTocke.addActionListener(this);
		meniBarvaAktivneTocke.addActionListener(this);
		meniBarvaIzbraneTocke.addActionListener(this);
		meniBarvaRoba.addActionListener(this);
		meniDebelinaPovezave.addActionListener(this);
		meniDebelinaRoba.addActionListener(this);
		meniPolmer.addActionListener(this);

		setJMenuBar(meni);
	}

	@Override
	public void actionPerformed(ActionEvent event) {
		Object source = event.getSource();

		if (source == meniOdpri) {
			JFileChooser chooser = new JFileChooser();
			int gumb = chooser.showOpenDialog(this);
			if (gumb == JFileChooser.APPROVE_OPTION) {
				String ime = chooser.getSelectedFile().getPath();
				Graf graf = Graf.preberi(ime);
				platno.narisi(graf);
			}
		}

		else if (source == meniShrani) {
			JFileChooser chooser = new JFileChooser();
			int gumb = chooser.showSaveDialog(this);
			if (gumb == JFileChooser.APPROVE_OPTION) {
				String ime = chooser.getSelectedFile().getPath();
				platno.graf.shrani(ime);
			}
		}
		else if (source == meniKoncaj) {
			dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));
		}
		else if (source == meniPrazen) {
			String niz = JOptionPane.showInputDialog(this, "Stevilo tock:");
			if (niz != null && niz.matches("\\d+")) {
				Graf g = Graf.prazen(Integer.parseInt(niz));
				g.razporedi(300, 300, 250);
				platno.narisi(g);
			}
		}
		else if (source == meniCikel) {
			String niz = JOptionPane.showInputDialog(this, "Stevilo tock:");
			if (niz != null && niz.matches("\\d+")) {
				Graf g = Graf.cikel(Integer.parseInt(niz));
				g.razporedi(300, 300, 250);
				platno.narisi(g);
			}

		}
		else if (source == meniPoln) {
			String niz = JOptionPane.showInputDialog(this, "Stevilo tock:");
			if (niz != null && niz.matches("\\d+")) {
				Graf g = Graf.poln(Integer.parseInt(niz));
				g.razporedi(300, 300, 250);
				platno.narisi(g);
			}

		}
		else if (source == meniPolnDvodelen) {
			JTextField prva = new JTextField();
			JTextField druga = new JTextField();
			JComponent[] polja = {
					new JLabel("Velikost prve mnozice tock: "), prva,
					new JLabel("Velikost druge mnozice tock: "), druga
			};
			int rezultat = JOptionPane.showConfirmDialog(this, polja, "Input", JOptionPane.OK_CANCEL_OPTION);
			if (rezultat == JOptionPane.OK_OPTION &&
					prva.getText().matches("\\d+") &&
					druga.getText().matches("\\d+")) {
				int n = Integer.parseInt(prva.getText());
				int m = Integer.parseInt(druga.getText());
				Graf g = Graf.polnDvodelen(n, m);
				g.razporedi(300, 300, 250);
				platno.narisi(g);
			}
		}
		else if (source == meniBarvaPovezave) {
			Color barva = JColorChooser.showDialog(this, "Barva povezave", platno.barvaPovezave);
			if (barva != null) {
				platno.barvaPovezave = barva;
				platno.repaint();
			}
		}
		else if (source == meniBarvaTocke) {
			Color barva = JColorChooser.showDialog(this, "Barva tocke", platno.barvaTocke);
			if (barva != null) {
				platno.barvaTocke = barva;
				platno.repaint();
			}
		}
		else if (source == meniBarvaAktivneTocke) {
			Color barva = JColorChooser.showDialog(this, "Barva aktivne tocke", platno.barvaAktivneTocke);
			if (barva != null) {
				platno.barvaAktivneTocke = barva;
				platno.repaint();
			}
		}
		else if (source == meniBarvaIzbraneTocke) {
			Color barva = JColorChooser.showDialog(this, "Barva izbrane tocke", platno.barvaIzbraneTocke);
			if (barva != null) {
				platno.barvaIzbraneTocke = barva;
				platno.repaint();
			}
		}
		else if (source == meniBarvaRoba) {
			Color barva = JColorChooser.showDialog(this, "Barva roba", platno.barvaRoba);
			if (barva != null) {
				platno.barvaRoba = barva;
				platno.repaint();
			}
		}
		else if (source == meniDebelinaPovezave) {
			String niz = JOptionPane.showInputDialog(this, "Debelina povezave: ", platno.debelinaPovezave);
			if (niz != null && niz.matches("\\d+\\.?\\d+")) {
				platno.debelinaPovezave = Float.parseFloat(niz);
				platno.repaint();
			}
		}
		else if (source == meniDebelinaRoba) {
			String niz = JOptionPane.showInputDialog(this, "Debelina roba: ", platno.debelinaRoba);
			if (niz != null && niz.matches("\\d+\\.?\\d+")) {
				platno.debelinaRoba = Float.parseFloat(niz);
				platno.repaint();
			}

		}
		else if (source == meniPolmer) {
			String niz = JOptionPane.showInputDialog(this, "Polmer tocke: ", platno.polmerTocke);
			if (niz != null && niz.matches("\\d+\\.?\\d+")) {
				platno.polmerTocke = Double.parseDouble(niz);
				platno.repaint();
			}

		}

	}

}
