
public class Test {

	public static void main(String[] args) {
		Graf f = Graf.prazen(1);
		f.razporedi(300, 300, 0);
		
		Okno okno = new Okno();
		okno.pack();
		okno.setVisible(true); // okno skrijemo s tem, da spremenimo na false
		okno.platno.narisi(f);
	}

}
