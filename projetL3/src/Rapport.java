
public class Rapport {
	private String titre;
	private String description;
	private int ID;
	
	public Rapport (int newID, String nomJalon ){
		ID = newID;
		titre = "Rapport "+ nomJalon;
	}
	 /*
	public void setTitre (String newTitre){
		titre = newTitre;
	}
	*/
	
	public void setDescritpion (String newDescritpion){
		description = newDescritpion;
	}
	
	
}
