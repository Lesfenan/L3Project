import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;

public class Projet {
	
	private String sujet;
	private int annee;
	private ArrayList<String> motsCles;
	private Enseignant enseignant;
	private ArrayList<Eleve> collectionEleves;
	private ArrayList<Jalon> collectionJalons;
	private String description;

	
	public Projet(String argSujet, Enseignant argEnseignant) {
		setEnseignant(argEnseignant);
		ArrayList<String> motsCles = new ArrayList<String>();
		sujet = argSujet;
		motsCles.add(argSujet);
		annee = Calendar.getInstance().get(Calendar.YEAR);
	}
	
	public void addMotCle(String argMotCle) {
		motsCles.add(argMotCle);
	}
	
	public void addJalon(String intitule, Date dateFin){
		Jalon newJalon = new Jalon(intitule,dateFin);
		collectionJalons.add(newJalon);
	}
	
	public String getSujet () {
		return sujet;
	}
	
	public int getAnnee() {
		return annee;
	}

	public ArrayList<Eleve> getCollectionEleves() {
		return collectionEleves;
	}

	public void setCollectionEleves(ArrayList<Eleve> collectionEleves) {
		this.collectionEleves = collectionEleves;
	}
	
	public void addEleve(Eleve newEleve){
		collectionEleves.add(newEleve);
	}

	/**
	 * @return the enseignant
	 */
	public Enseignant getEnseignant() {
		return enseignant;
	}

	/**
	 * @param enseignant the enseignant to set
	 */
	public void setEnseignant(Enseignant enseignant) {
		this.enseignant = enseignant;
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
