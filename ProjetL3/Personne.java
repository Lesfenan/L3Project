public class Personne {
	private int id;
	private String nom;
	private String prenom;
	
	public Personne(int argId, String argNom,String argPrenom) {
		id = argId;
		nom = argNom;
		prenom = argPrenom;
	}

	public Personne(String argNom,String argPrenom) {
		id = -1;
		nom = argNom;
		prenom = argPrenom;
	}

	public void setNom(String n) {
		this.nom = n;
	}

	public void setPrenom(String p) {
		this.prenom = p;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNom() {
		return nom;
	}

	public int getId() {
		return id;
	}

	public String getPrenom() {
		return prenom;
	}

	public String toString() {
		return nom + " " + prenom;
	}
	

}
