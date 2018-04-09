public class Jalon {
	
	private String intitule;
	/**
	 * La progression du travail a realiser des etudiants pour ce Jalon. 
	 * En pourcentage
	 */
	private int progression;
	private int notation;
	private java.util.Date dateFin;
	
	
	/**
	 * Creer un jalon a partir des parametres entres, etant defini par un intitule, une date, et une progression
	 * @param argIntitule  intitule du Jalon
	 * @param date Date du Jalon
	 */
	public Jalon (String argIntitule,java.util.Date date ) {
		intitule = argIntitule;
		dateFin = date;
		progression = 0;
	}
	
	/**
	 * Permet à l'enseignant d'entrer une note associe au Jalon et au travail des etudiants
	 * @param argNotation la notation associee au Jalon
	 */
	public void setNotation(int argNotation) {	//TODO Note <=20
		notation = argNotation;
	}
	
	public int getNotation() {
		return notation;
	}
	
	public void setProgression(int argProgression) {
		progression=argProgression;
	}
	
	public int getProgression() {
		return progression;
	}
	
	public String getIntitule() {
		return intitule;
	}

	public void setIntitule(String intitule) {
		this.intitule = intitule;
	}
	
	public String toString() {
		return ("Le projet " + intitule + " prend fin le "+ dateFin.toString() + ".");
	}
}
