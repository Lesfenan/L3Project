import java.sql.Date;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

public class Projet {
    private int id;
	private String sujet;
    private int annee;
    private ArrayList<String> motsCles;
    private String motCleRaw;


	private Enseignant enseignant;
    private ArrayList<Eleve> collectionEleves;
    private ArrayList<Jalon> collectionJalons;

    private String classe;
    
    public Projet(int argId, String argSujet, String argClasse, int argAnnee, Enseignant argEnseignant, ArrayList<Eleve> argEleve, ArrayList<Jalon> argJalon, String argMotcle) {
    	collectionJalons = argJalon;
    	collectionEleves = argEleve;
    	
    		setCollectionEleves(argEleve);
        setEnseignant(argEnseignant);
        annee = argAnnee;
        sujet = argSujet;
        id = argId;
        classe = argClasse;
        annee = Calendar.getInstance().get(Calendar.YEAR);
        motsCles = new ArrayList<String>();
        motsCles.add(argSujet);
        motCleRaw = argMotcle;
		String[] mcArray = motCleRaw.split(",");
		
		
		List<String> mc = Arrays.asList(mcArray);
		for(String data : mc)
		{
			motsCles.add(data);
		}
		
    }
   

	public Projet(int argId, String argSujet, int argAnnee, Enseignant argEnseignant, ArrayList<Eleve> argEleve, ArrayList<Jalon> argJalon, String argMotcle) {
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
        motCleRaw = argMotcle;
		String[] mcArray = motCleRaw.split(",");
		
		
		List<String> mc = Arrays.asList(mcArray);
		for(String data : mc)
		{
			motsCles.add(data);
		}
		
    }
	
	public Projet(int argId, String argSujet, int argAnnee, Enseignant argEnseignant, ArrayList<Eleve> argEleve, String argMotcle) {
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
        motCleRaw = argMotcle;
		String[] mcArray = motCleRaw.split(",");
		
		
		List<String> mc = Arrays.asList(mcArray);
		for(String data : mc)
		{
			motsCles.add(data);
		}
		
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
    
    public void setMotCleRawToList() 
    {
		String[] mcArray = motCleRaw.split(",");
		
		
		List<String> mc = Arrays.asList(mcArray);
		for(String data : mc)
		{
			motsCles.add(data);
		}
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


	public String getMotCleRaw() {
		return motCleRaw;
	}


	public void setMotCleRaw(String motCleRaw) {
		this.motCleRaw = motCleRaw;
	}

}
