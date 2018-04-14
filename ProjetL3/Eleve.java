


public class Eleve extends Personne {
	
	/**
	 * @param argNom Prend en parametre le nom de l'eleve
	 * @param argPrenom Prend en second parametre le prenom de l'eleve
	 */
	public Eleve (int argId, String argNom,String argPrenom) {
		super(argId, argNom, argPrenom);
	}

	public Eleve (String argNom,String argPrenom) {
		super(argNom, argPrenom);
	}
}
