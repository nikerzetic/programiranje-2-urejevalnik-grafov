
import java.util.*;

public class Tocka {
	
	String ime;
	Set<Tocka> sosedi;
	double x, y;
	
	public Tocka(String ime) {
		this.ime = ime;
		sosedi = new HashSet<Tocka>();
		x = y = 0;
	}
	
	public int stopnja() {
		return this.sosedi.size();
	}
	
	public String toString() {
		return ime;
	}
	
}
