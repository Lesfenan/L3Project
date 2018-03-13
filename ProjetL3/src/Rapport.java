
public class Rapport {
	private String titre;
	private String description;
	private int ID;
	
	public Rapport (int newID, String nomJalon ){
		ID = newID;
		setTitre("Rapport "+ nomJalon);
	}
	 /*
	public void setTitre (String newTitre){
		titre = newTitre;
	}
	*/
	
	public void setDescritpion (String newDescritpion){
		setDescription(newDescritpion);
	}

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
	
	
}
