import java.io.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.*;

public class ProjetController {
	private Connection connection = null;

	public ProjetController() {
		this.connection = Connect.getConnection();
	}

	/**
	 * Recherche dans la base de donnees la liste des noms des eleves
	 * @param r Mot cle permettant de trouver ce que l'on cherche
	 * @return Retourne une arraylist avec tous les résultats de la recherche
	 */

	public Projet addProjetToDB(String sujet, String classe, Enseignant tuteur, ArrayList<Eleve> eleve ,ArrayList<String> motCle) {
		Projet p = new Projet(-1,sujet,classe,Calendar.YEAR,tuteur,eleve,new ArrayList<Jalon>());
		try {
			java.util.Date d = new java.util.Date();
			java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyy-MM-dd");
			String date = sdf.format(d);

			String insertProjet = "INSERT INTO Projet(annee, classe, sujet) VALUES ('" + date + "', '" + classe + "', '" + sujet + "');";
			Statement state = this.connection.createStatement();

			state.executeUpdate(insertProjet);

			Statement state2 = this.connection.createStatement();
			ResultSet result = state2.executeQuery("SELECT MAX(id) FROM Projet");
			result.next();
			int idProjet = result.getInt(1); 
			p.setId(idProjet); 

			String insertTuteur = "INSERT INTO TuteurProjet(idProjet, idEnseignant) VALUES ('" + p.getId() + "', '" + tuteur.getId() + "');";

			state.executeUpdate(insertTuteur);

			for(int i = 0; i < eleve.size(); i++) {
				state.executeUpdate("INSERT INTO EleveProjet(idProjet, idEleve) VALUES ('" + p.getId() + "', '" + eleve.get(i).getId() + "');");
			}

			for(int i = 0; i < motCle.size(); i++) {
				state.executeUpdate("INSERT INTO MotCleProjet(idProjet, motCle) VALUES ('" + p.getId() + "', '" + motCle.get(i) + "');");
			}
		}
		catch(Exception e) {}
		return p;
	}

	public ArrayList<Projet> getListOfProjet(String classe) {
		ArrayList<Projet> resultat = new ArrayList<Projet>();
		try {
			String query = "SELECT P.id, P.annee, P.classe, P.sujet FROM Projet P WHERE P.classe = '" + classe +"' ORDER BY id ASC";
			Statement state = this.connection.createStatement();

			ResultSet result = state.executeQuery(query);

			while (result.next()) { 
				String sujetProjet = result.getString(4);
				String classeProjet = result.getString(3);
				java.util.Date anneeProjet = result.getDate(2);
				Calendar cal = Calendar.getInstance();
				cal.setTime(anneeProjet);
				int year = cal.get(Calendar.YEAR);
				int id = result.getInt(1);

				Projet projet = new Projet(id, sujetProjet, classe, year, this.getTuteurProjet(id), this.getEleveProjet(id), this.getJalonProjet(id));
				System.out.println("nb jalons");
				System.out.println(this.getJalonProjet(id).size());
				//System.out.println(projet.getCollectionJalons().size());
				resultat.add(projet); //TODO separer noms et prenoms
			}
		}
		catch(Exception e) {}
		return resultat;
	}

	public Enseignant getTuteurProjet(int idProjet) {
		Enseignant p = new Enseignant(0,"","");
		try {
			String query = "SELECT DISTINCT E.id, E.nom, E.prenom FROM Enseignant E, Projet P, TuteurProjet T WHERE " + idProjet + " = T.idProjet AND T.idEnseignant = E.id;";
			Statement state = this.connection.createStatement();

			ResultSet result = state.executeQuery(query);

			if(result.next()) { 
				int idTuteur = result.getInt(1);
				String nomTuteur = result.getString(2);
				String prenomTuteur = result.getString(3);

				p.setId(idTuteur);
				p.setNom(nomTuteur);
				p.setPrenom(prenomTuteur);
			}
		}
		catch(Exception e) {}
		return p; 
	}

	public ArrayList<Eleve> getEleveProjet(int idProjet) {
		ArrayList<Eleve> el = new ArrayList<Eleve>();
		try {
			String query = "SELECT DISTINCT E.id, E.nom, E.prenom FROM Eleve E, Projet P, EleveProjet Ep WHERE " + String.valueOf(idProjet) + " = Ep.idProjet AND Ep.idEleve = E.id;";
			Statement state = this.connection.createStatement();

			ResultSet result = state.executeQuery(query);
			while(result.next()) { 
				int idEleve = result.getInt(1);
				String nomEleve = result.getString(2);
				String prenomEleve = result.getString(3);
				Eleve a = new Eleve(idEleve,nomEleve,prenomEleve);
				el.add(a);
			}
		}
		catch(Exception e) {}
		return el; 
	}

	public ArrayList<Jalon> getJalonProjet(int idProjet) {
		ArrayList<Jalon> jl = new ArrayList<Jalon>();
		try {
			String query = "SELECT J.id, J.intitule, J.notation, J.progression, J.dateFin, J.classe, J.description FROM Jalon J WHERE J.projet = " + String.valueOf(idProjet);
			Statement state = this.connection.createStatement();

			ResultSet result = state.executeQuery(query);
			while(result.next()) { 
				int idJalon = result.getInt(1);
				String intituleJalon = result.getString(2);
				int notationJalon = result.getInt(3);
				int progressionJalon = result.getInt(4);
				java.util.Date finJalon = result.getDate(5);
				String classeJalon = result.getString(6);
				String description = result.getString(7);
				Jalon j = new Jalon(idJalon, intituleJalon, finJalon, progressionJalon, notationJalon, classeJalon, description);
				jl.add(j);
			}
		}
		catch(Exception e) {}
		return jl; 
	}

	public ArrayList<Projet> getProjetFromEleve(int idEleve) {
		ArrayList<Projet> prj = new ArrayList<Projet>();
		try {
			String query = "SELECT P.id, P.annee, P.classe, P.sujet FROM Projet P, EleveProjet E WHERE P.id = E.idProjet AND E.idEleve = '" + idEleve + "';";
			Statement state = this.connection.createStatement();

			ResultSet result = state.executeQuery(query);
			while(result.next()) { 
				int idProjet = result.getInt(1);
				java.util.Date anneeProjet = result.getDate(2);
				Calendar cal = Calendar.getInstance();
				cal.setTime(anneeProjet);
				int year = cal.get(Calendar.YEAR);
				String classeProjet = result.getString(3);
				String sujetProjet = result.getString(4);
				Projet p = new Projet(idProjet, sujetProjet, classeProjet, year, this.getTuteurProjet(idProjet), this.getEleveProjet(idProjet), this.getJalonProjet(idProjet));

				prj.add(p);
			}
		}
		catch(Exception e) {}
		return prj; 
	}


}