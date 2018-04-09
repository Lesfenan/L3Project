public class Rapport {
	private String titre;
	private String description;
	private int ID; // TODO ID g�n�r� automatiquement et non defini �r utilisateur ?
	
	/**
	 * Constructeur de Rapport
	 * @param newID ID du rapport
	 * @param nomJalon nom du Jalon
	 */
	
	public Rapport (int newID, String nomJalon ){
		setID(newID);
		setTitre("Rapport "+ nomJalon);
	}
	
	 /*
	public void setTitre (String newTitre){
		titre = newTitre;
	}
	*/

	/**
	 * @return the titre
	 */
	public String getTitre() {
		return titre;
	}

	/**
	 * @param titre the titre to set
	 */
	public void setTitre(String titre) {
		this.titre = titre;
	}

	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	public int getID() {
		return ID;
	}

	public void setID(int iD) {
		ID = iD;
	}
	
	
}
