public class Personne {
	private final String nom;
	private final String prenom;
	
	/**
	 * @param argNom Prend en parametre le nom de la personne
	 * @param argPrenom Prend en second parametre le prenom de la personne
	 */
	public Personne(String argNom,String argPrenom) {
		nom = argNom;
		prenom = argPrenom;
	}

	public String getNom() {
		return nom;
	}

	public String getPrenom() {
		return prenom;
	}
	

}
