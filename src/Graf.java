import java.util.*;
import java.io.*;

public class Graf {
	
	int stevec;
	Map<String, Tocka> tocke;
	
	public Graf() {
		stevec = 0;
		tocke = new HashMap<String, Tocka>();
	}

	public Tocka tocka(String ime) {
		return tocke.get(ime);
	}
	
	public boolean povezava(Tocka T1, Tocka T2) {
		return (T1.sosedi.contains(T2) || T2.sosedi.contains(T1));
	}
	
	public Tocka dodajTocko(String ime) {
		Tocka T = tocka(ime);
		if (T == null) {
			T = new Tocka(ime);
			tocke.put(ime, T);
		}
		return T;
	}
	
	public Tocka dodajTocko() {
		while (true) {
			String ime = Integer.toString(stevec++);
			if (tocka(ime) != null) continue;
			Tocka T = new Tocka(ime);
			tocke.put(ime, T);
			return T;
		}
	}
	
	public void dodajPovezavo(Tocka T1, Tocka T2) {
		if (T1 == T2) return;
		T1.sosedi.add(T2);
		T2.sosedi.add(T1);
	}
	
	public void odstraniPovezavo(Tocka T1, Tocka T2) {
		T1.sosedi.remove(T2);
		T2.sosedi.remove(T1);
	}
	
	public void odstraniTocko(Tocka T) {
		for (Tocka U : T.sosedi) U.sosedi.remove(T);
		tocke.remove(T.ime);
	}
	
	private Tocka[] dodajTocke(int n) {
		Tocka[] tocke = new Tocka[n];
		for (int i = 0; i < n; i++) {
			tocke[i] = dodajTocko();
		}
		return tocke;
	}
	
	public static Graf prazen(int n) {
		Graf graf = new Graf();
		graf.dodajTocke(n);
		return graf;
	}
	
	public static Graf cikel(int n) {
		Graf graf = new Graf();
		Tocka[] tocke = graf.dodajTocke(n);
		for (int i = 0; i < n; i++) graf.dodajPovezavo(tocke[i], tocke[(i + 1) % n]);
		return graf;
	}
	
	public static Graf poln(int n) {
		Graf graf = new Graf();
		Tocka[] tocke = graf.dodajTocke(n);
		for (int i = 0; i < n; i++) {
			for (int j = i + 1; j < n; j++) graf.dodajPovezavo(tocke[i], tocke[j]);
		}
		return graf;
	}
	
	public static Graf polnDvodelen(int n, int m) {
		Graf graf = new Graf();
		Tocka[] tocke1 = graf.dodajTocke(n);
		Tocka[] tocke2 = graf.dodajTocke(m);
		for (Tocka T1 : tocke1) {
			for (Tocka T2 : tocke2) graf.dodajPovezavo(T1, T2);
		}
		return graf;
	}
	
	public void izpis() {
		for (Tocka T : tocke.values()) {
			System.out.print(T + ":");
			for (Tocka U : T.sosedi) System.out.print(" " + U);
			System.out.println();
		}
	}
	
	public void razporedi(double x, double y, double r) {
		int n = tocke.size();
		int i = 0;
		for (Tocka T : tocke.values()) {
			double kot = 2 * Math.PI * i / n;
			T.x = x + r * Math.cos(kot);
			T.y = y + r * Math.sin(kot);
			i++;
		}
	}
	
	public void shrani(String ime) {
		try {
			PrintWriter dat = new PrintWriter(new FileWriter(ime));
			for (Tocka T: tocke.values()) {
				dat.println(T + ": " + T.x + " " +  T.y);
			}
			dat.println("*************");
			for (Tocka T: tocke.values()) {
				dat.print(T + ":");
				for (Tocka U: T.sosedi) dat.print(" " + U);
				dat.println();
			}
			dat.close();
		}
		catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static Graf preberi(String ime) {
		try {
			BufferedReader dat = new BufferedReader(new FileReader(ime));
			Graf graf = new Graf();
			boolean stikalo = true;
			while (dat.ready()) {
				String vrstica = dat.readLine().trim();
				if (vrstica.equals("")) continue;
				if (vrstica.equals("*************")) {
					stikalo = false;
				}
				else if (stikalo) {
					String[] podatki = vrstica.split("[ :]+");
					Tocka T = new Tocka(podatki[0]);
					T.x = Double.parseDouble(podatki[1]);
					T.y = Double.parseDouble(podatki[2]);
				}
				else {
					String[] podatki = vrstica.split("[ :]+");
					Tocka T = graf.tocka(podatki[0]);
					if (T == null) T = graf.dodajTocko(podatki[0]);
					for (int i = 1; i < podatki.length; i++) {
						Tocka U = graf.tocka(podatki[i]);
						if (U == null) U = graf.dodajTocko(podatki[i]);
						graf.dodajPovezavo(T, U);
					}
				}
			}
			dat.close();
			return graf;
		}
		catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}
}
