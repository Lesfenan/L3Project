public class Jalon {
    private int id;
	private String intitule;
    private String classe;
    private int progression;
    private int notation;
    private java.util.Date dateFin;
    
    


	public Jalon (int argId, String argIntitule, java.util.Date argDate, int argProgression, int argNotation, String argClasse) {
        id = argId;
        classe = argClasse;
        intitule = argIntitule;
        dateFin = argDate;
        progression = argProgression;
        notation = argNotation;
    }

    public void setClasse(String argClasse) {
        classe = argClasse;
    }

    public String getClasse() {
        return classe;
    }

    public void setId(int argId) {
        id = argId;
    }
    
    public void setNotation(int argNotation) {  //TODO Note <=20
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
    
    public java.util.Date getDateFin() {
		return dateFin;
	}

	public void setDateFin(java.util.Date dateFin) {
		this.dateFin = dateFin;
	}
	
    public int getId() {
		return id;
	}

}