import javax.swing.JFrame;

@SuppressWarnings("serial")
public class Okno extends JFrame {
	
	Platno platno;
	
	public Okno() {
		super();
		setTitle("Urejevalnik grafov");
		platno = new Platno(600, 600);
		add(platno);
	}

}
