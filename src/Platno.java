import java.awt.*;
import javax.swing.*;

@SuppressWarnings("serial")
public class Platno extends JPanel {
	
	int sirina;
	int visina;
	Graf graf;
	
	Color barvaTocke;
	Color barvaPovezave;
	Color barvaRoba;
	Color barvaOzadja;
	double polmerTocke;
	float debelinaPovezave;
	float debelinaRoba;
	
	public Platno(int sirina, int visina) {
		this.sirina = sirina;
		this.visina = visina;
		graf = null;
		barvaTocke = Color.WHITE;
		barvaPovezave = Color.ORANGE;
		barvaRoba = Color.BLACK;
		barvaOzadja = Color.GRAY;
		polmerTocke = 4;
		debelinaPovezave = 2;
		debelinaRoba = 1;
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
		
		g.setColor(barvaTocke);
		
		for (Tocka T : graf.tocke.values()) {
			int polmer = round(polmerTocke);
			g.fillOval(round(T.x) - polmer, round(T.y) - polmer, 2 * polmer, 2 * polmer);
		}
		
		g.setColor(barvaRoba);
		
		for (Tocka T : graf.tocke.values()) {
			int polmer = round(debelinaRoba) + round(polmerTocke);
			g.drawOval(round(T.x) - polmer, round(T.y) - polmer, 2 * polmer, 2 * polmer);
		}
		
		
	}
	
	private static int round(double x) {
		return (int)(x + 0.5);
	}

}
