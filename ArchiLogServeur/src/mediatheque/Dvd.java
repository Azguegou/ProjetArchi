package mediatheque;

public class Dvd implements Document {

	private int numeroDoc;

	public Dvd(int numero, String titre, boolean adulte) {
		// TODO Auto-generated constructor stub
	}

	@Override
	public int numero() {
		// TODO Auto-generated method stub
		return numeroDoc;
	}

	@Override
	public void retour() {
		// TODO Auto-generated method stub

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
		// TODO Auto-generated method stub
		
	}

	@Override
	public void emprunt(Abonne ab) {
		// TODO Auto-generated method stub
		
	}

}
