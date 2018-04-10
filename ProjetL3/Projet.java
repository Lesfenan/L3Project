

import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;

public class Projet {
	
	private String sujet;
	private int annee;
	/**
	 * motsCles associes au Projet et facilitant la recherche
	 */
	private ArrayList<String> motsCles;
	private Enseignant enseignant;
	/**
	 * Eleves associes au Projet
	 */
	private ArrayList<Eleve> collectionEleves;
	/**
	 * Jalons associes au Projet
	 */
	private ArrayList<Jalon> collectionJalons;

	
	/**
	 * Constructeur de Projet
	 * @param argSujet sujet du Projet
	 * @param argEnseignant Enseignant associee au Projet
	 */
	public Projet(String argSujet, Enseignant argEnseignant) {
		setEnseignant(argEnseignant);
		
		sujet = argSujet;
		
		annee = Calendar.getInstance().get(Calendar.YEAR);
		collectionEleves = new ArrayList<Eleve>();
		motsCles = new ArrayList<String>();
		motsCles.add(argSujet);
		collectionJalons = new ArrayList<Jalon>();
	}
	
	/**
	 * Permet de rajouter un motcle
	 * @param argMotCle motcle a rajouter
	 */
	public void addMotCle(String argMotCle) {
		motsCles.add(argMotCle);
	}
	
	/**
	 * Permet de creer et rajouter directement un Jalon au Projet
	 * @param intitule intitule du Jalon
	 * @param dateFin date du Jalon
	 */
	public void addJalon(String intitule, Date dateFin){
		Jalon newJalon = new Jalon(intitule,dateFin);
		collectionJalons.add(newJalon);
	}
	
	/**
	 * Permet de rajouter au Projet un Jalon deja cree
	 * @param argJalon le Jalon a rajouter au projet
	 */
	public void addJalon(Jalon argJalon){
		collectionJalons.add(argJalon);
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
	
	/**
	 * Rajoute un eleve au Projet
	 * @param newEleve Eleve a rajouter au Projet
	 */
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
	
	
}
