package mediatheque;

public class Dvd implements Document {

	private int numeroDoc;
	
	private String titre;
	
	private boolean adultFilm;

	public Dvd(int numero) {
		this.numeroDoc = numero;
		this.titre = "";
		this.adultFilm = false;
	}
	
	public Dvd(int numero, String titre, boolean adulte) {
		this.numeroDoc = numero;
		this.titre = titre;
		this.adultFilm = adulte;
	}

	@Override
	public int numero() {
		return numeroDoc;
	}

	@Override
	public void retour() {

	}

	@Override
	public Abonne empruntePar() {
		return null;
	}

	@Override
	public Abonne reservePar() {
		return null;
	}

	@Override
	public void reservation(Abonne ab) {		
	}

	@Override
	public void emprunt(Abonne ab) {		
	}
	
	public String getTitre() {
		return this.titre;
	}
	
	public boolean isAdult() {
		return this.adultFilm;
	}

}
