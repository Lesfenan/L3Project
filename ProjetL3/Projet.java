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

    private String classe;
    
    public Projet(int argId, String argSujet, String argClasse, int argAnnee, Enseignant argEnseignant, ArrayList<Eleve> argEleve, ArrayList<Jalon> argJalon) {
        setCollectionEleves(argEleve);
        setEnseignant(argEnseignant);
        annee = argAnnee;
        sujet = argSujet;
        id = argId;
        classe = argClasse;
        annee = Calendar.getInstance().get(Calendar.YEAR);
        collectionEleves = new ArrayList<Eleve>();
        motsCles = new ArrayList<String>();
        motsCles.add(argSujet);
        collectionJalons = new ArrayList<Jalon>();
    }
   

	public Projet(int argId, String argSujet, int argAnnee, Enseignant argEnseignant, ArrayList<Eleve> argEleve, ArrayList<Jalon> argJalon) {
        setCollectionEleves(argEleve);
        setEnseignant(argEnseignant);
        
        collectionJalons = argJalon;
        collectionEleves = argEleve;
        
        
        
        annee = argAnnee;
        sujet = argSujet;
        id = argId;
        annee = Calendar.getInstance().get(Calendar.YEAR);
        motsCles = new ArrayList<String>();
        motsCles.add(argSujet);
    }
	
	public Projet(int argId, String argSujet, int argAnnee, Enseignant argEnseignant, ArrayList<Eleve> argEleve) {
        setCollectionEleves(argEleve);
        setEnseignant(argEnseignant);
        
        collectionJalons = new ArrayList<Jalon>();
        collectionEleves = new ArrayList<Eleve>();
   
        
        annee = argAnnee;
        sujet = argSujet;
        id = argId;
        annee = Calendar.getInstance().get(Calendar.YEAR);
        motsCles = new ArrayList<String>();
        motsCles.add(argSujet);
    }
    
    public void addMotCle(String argMotCle) {
        motsCles.add(argMotCle);
    }
    
    public void addJalon(String intitule, java.util.Date date, String classe){

        JalonController j = new JalonController();
        Jalon newJalon = j.addJalonToDB(intitule, date, this.id, classe);
        collectionJalons.add(newJalon);
    }
    
    public String getSujet () {
        return sujet;
    }
    
    public int getAnnee() {
        return annee;
    }

    public ArrayList<Eleve> getCollectionEleves() {
        return this.collectionEleves;
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
    
    public ArrayList<Jalon> getCollectionJalons() {
		return this.collectionJalons;
	}

	public void setCollectionJalons(ArrayList<Jalon> collectionJalons) {
		this.collectionJalons = collectionJalons;
	}
    
    public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

}
