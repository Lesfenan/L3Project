import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;

public class Projet {
	private int id;
	private String sujet;
	private int annee;
	private ArrayList<String> motsCles;


	private Enseignant enseignant;
	private ArrayList<Eleve> collectionEleves;
	private ArrayList<Jalon> collectionJalons;

	
	public Projet(int argId, String argSujet, int argAnnee, Enseignant argEnseignant, ArrayList<Eleve> argEleve, ArrayList<Jalon> argJalon) {
		setCollectionEleves(argEleve);
		setEnseignant(argEnseignant);
		annee = argAnnee;
		sujet = argSujet;
		id = argId;
		annee = Calendar.getInstance().get(Calendar.YEAR);
		collectionEleves = new ArrayList<Eleve>();
		motsCles = new ArrayList<String>();
		motsCles.add(argSujet);
		collectionJalons = new ArrayList<Jalon>();
	}
	
	public void addMotCle(String argMotCle) {
		motsCles.add(argMotCle);
	}
	
	public void addJalon(String intitule, Date dateFin){
		//Jalon newJalon = new Jalon(intitule,dateFin);
		//collectionJalons.add(newJalon);
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
	
	public ArrayList<String> getMotsCles() {
		return motsCles;
	}

	public void setMotsCles(ArrayList<String> motsCles) {
		this.motsCles = motsCles;
	}
	
	
}
