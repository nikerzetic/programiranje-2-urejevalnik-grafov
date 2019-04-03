import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;

@SuppressWarnings("serial")
public class Platno extends JPanel implements MouseListener, MouseMotionListener, KeyListener {
	
	int sirina, visina;
	Graf graf;
	Tocka aktivnaTocka;
	Set<Tocka> izbraneTocke;
	
	Color barvaTocke;
	Color barvaPovezave;
	Color barvaRoba;
	Color barvaOzadja;
	Color barvaAktivneTocke;
	Color barvaIzbraneTocke;
	double polmerTocke;
	float debelinaPovezave;
	float debelinaRoba;
	
	private int klikX, klikY;
	private int premikX, premikY;
	
	public Platno(int sirina, int visina) {
		this.sirina = sirina;
		this.visina = visina;
		graf = null;
		aktivnaTocka = null;
		izbraneTocke = new HashSet<Tocka>();
		
		barvaTocke = Color.WHITE;
		barvaPovezave = Color.ORANGE;
		barvaRoba = Color.BLACK;
		barvaOzadja = Color.GRAY;
		barvaIzbraneTocke = Color.YELLOW;
		barvaAktivneTocke = Color.MAGENTA;
		polmerTocke = 6;
		debelinaPovezave = 2;
		debelinaRoba = 4;
		
		addMouseListener(this);
		addMouseMotionListener(this);
		addKeyListener(this);
		setFocusable(true);
	}
	
	public void narisi(Graf g) {
		graf = g;
		repaint();
	}
	
	@Override
	public Dimension getPreferredSize() {
		return new Dimension(sirina, visina);
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		Graphics2D g2 = (Graphics2D)g;
		super.paintComponent(g);
		if (graf == null) return;
		
		setBackground(barvaOzadja);
		
		g.setColor(barvaPovezave);
		g2.setStroke(new BasicStroke(debelinaPovezave));
		
		for (Tocka T : graf.tocke.values()) {
			for (Tocka U : T.sosedi) {
				if (T.ime.compareTo(U.ime) > 0) {
					g.drawLine(round(T.x), round(T.y), round(U.x), round(U.y));
				}
			}
		}
		
		
		for (Tocka T : graf.tocke.values()) {
			g.setColor(barvaRoba);
			int polmer = round(polmerTocke);
			g2.setStroke(new BasicStroke(debelinaRoba));
			g.drawOval(round(T.x) - polmer, round(T.y) - polmer, 2 * polmer, 2 * polmer);
			
			polmer = round(polmerTocke);
			if (T == aktivnaTocka) g.setColor(barvaAktivneTocke);
			else if (izbraneTocke.contains(T)) g.setColor(barvaIzbraneTocke);
			else g.setColor(barvaTocke);
			g.fillOval(round(T.x) - polmer, round(T.y) - polmer, 2 * polmer, 2 * polmer);
		}
		
	}
	
	private static int round(double x) {
		return (int)(x + 0.5);
	}

	@Override
	public void keyPressed(KeyEvent e) {
		if (graf == null) return;
		int tipka = e.getKeyCode();
		if (tipka == KeyEvent.VK_DELETE) {
			// Delete - izbrise vse izbrane tocke
			for (Tocka T : izbraneTocke) {
				graf.odstraniTocko(T);
				izbraneTocke.clear();
			}
		}
		if (tipka == KeyEvent.VK_A) {
			// A - izbere vse tocke
			for (Tocka T : graf.tocke.values()) izbraneTocke.add(T);
		}
		if (tipka == KeyEvent.VK_P) {
			// P - poveze vse izbrane tocke
		}
		if (tipka == KeyEvent.VK_O) {
			// O - odstrani povezave med izbranimi tockami
			for (Tocka T : izbraneTocke) {
				for (Tocka U : izbraneTocke) {
					graf.odstraniPovezavo(T, U);
				}
			}
		}
		
		repaint();
	}
	

	@Override
	public void mousePressed(MouseEvent e) {
		if (graf == null) return;
		klikX = premikX = e.getX();
		klikY = premikY =  e.getY();
		Tocka najblizja = null;
		double razdalja = Double.POSITIVE_INFINITY;
		for (Tocka T : graf.tocke.values()) {
			double r = Math.sqrt(Math.pow((T.x - klikX), 2) + Math.pow(T.y - klikY, 2));
			if (r < razdalja) {
				najblizja = T;
				razdalja = r;
			}
		}
		if (razdalja < polmerTocke + debelinaRoba + 3) {
			aktivnaTocka = najblizja;
			repaint();
		}
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		if (graf == null) return;
		if (e.getX() == klikX && e.getY() == klikY) {
			if (aktivnaTocka == null) {
				Tocka T = graf.dodajTocko();
				T.x = e.getX();
				T.y = e.getY();
				for (Tocka U : izbraneTocke) graf.dodajPovezavo(T, U);
			}
			else {
				if (izbraneTocke.contains(aktivnaTocka)) izbraneTocke.remove(aktivnaTocka);
				else izbraneTocke.add(aktivnaTocka);
			}
		}
		aktivnaTocka = null;
		repaint();
	}
	
	@Override
	public void mouseDragged(MouseEvent e) {
		if (graf == null) return;
		if (aktivnaTocka == null) {
			for (Tocka T : izbraneTocke) {
				T.x += e.getX() - premikX;
				T.y += e.getY() - premikY;
			}
		}
		else {
			aktivnaTocka.x += e.getX() - premikX;
			aktivnaTocka.y += e.getY() - premikY;
		}
		premikX = e.getX();
		premikY = e.getY();
		repaint();
	}

	@Override
	public void keyReleased(KeyEvent e) {}

	@Override
	public void keyTyped(KeyEvent e) {}

	@Override
	public void mouseMoved(MouseEvent e) {}

	@Override
	public void mouseClicked(MouseEvent e) {}

	@Override
	public void mouseEntered(MouseEvent e) {}

	@Override
	public void mouseExited(MouseEvent e) {}

}
