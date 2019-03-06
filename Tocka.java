
import java.util.*;

public class Tocka {
	
	String ime;
	Set<Tocka> sosedi;
	
	public Tocka(String ime) {
		this.ime = ime;
		sosedi = new HashSet<Tocka>();
	}
	
	public int stopnja() {
		return this.sosedi.size();
	}
	
	public String toString() {
		return ime;
	}
	
}
