



public class Jalon {
	
	private String intitule;
	private int progression;
	private int notation;
	private java.util.Date dateFin;
	
	
	public Jalon (String argIntitule,java.util.Date date ) {
		intitule = argIntitule;
		dateFin = date;
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
