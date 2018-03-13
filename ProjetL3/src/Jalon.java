import java.sql.Date;

public class Jalon {
	
	private String intitule;
	private int progression;
	private int notation;
	private Date dateFin;
	
	
	public Jalon (String argIntitule,Date argDateFin ) {
		intitule = argIntitule;
		dateFin = argDateFin;
		progression = 0;
	}
	
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
	
	public String toString() {
		return ("Le projet " + intitule + " prend fin le "+ dateFin.toString() + ".");
	}
}
