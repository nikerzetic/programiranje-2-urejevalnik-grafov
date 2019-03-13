
public class Test {

	public static void main(String[] args) {
		Graf f = Graf.cikel(10);
		Graf g = Graf.poln(4);
		Graf h = Graf.polnDvodelen(2, 3);
		
		f.razporedi(300, 300, 200);
		f.izpis();
		g.izpis();
		h.izpis();
		
		Okno okno = new Okno();
		okno.pack();
		okno.setVisible(true); // okno skrijemo s tem, da spremenimo na false
		okno.platno.narisi(f);
	}

}
