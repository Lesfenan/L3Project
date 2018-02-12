import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;

public class Projet {
	
	private String sujet;
	private int annee;
	private ArrayList motsCles;
	private Enseignant enseignant;
	private ArrayList<Eleve> collectionEleves;

	
	public Projet(String argSujet) {
		ArrayList<String> motsCles = new ArrayList<String>();
		sujet = argSujet;
		motsCles.add(argSujet);
		annee = Calendar.getInstance().get(Calendar.YEAR);
	}
	
	public void addMotCle(String argMotCle) {
		motsCles.add(argMotCle);
	}
	
	public String getSujet () {
		return sujet;
	}
	
	public int getAnnee() {
		return annee;
	}
	
}
