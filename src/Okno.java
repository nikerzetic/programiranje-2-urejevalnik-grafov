import java.awt.event.*;
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

		}
		else if (source == meniPrazen) {

		}
		else if (source == meniCikel) {

		}
		else if (source == meniPoln) {

		}
		else if (source == meniPolnDvodelen) {

		}
		else if (source == meniBarvaPovezave) {

		}
		else if (source == meniBarvaTocke) {

		}
		else if (source == meniBarvaAktivneTocke) {

		}
		else if (source == meniBarvaIzbraneTocke) {

		}
		else if (source == meniBarvaRoba) {

		}
		else if (source == meniDebelinaPovezave) {

		}
		else if (source == meniDebelinaRoba) {

		}
		else if (source == meniPolmer) {

		}

	}

}
