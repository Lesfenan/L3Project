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

                Projet projet = new Projet(id, sujetProjet, year, this.getTuteurProjet(id), this.getEleveProjet(id), this.getJalonProjet(id));
                System.out.println(projet);
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
            String query = "SELECT J.id, J.intitule, J.notation, J.progression, J.dateFin FROM Jalon J WHERE J.projet = " + String.valueOf(idProjet);
            Statement state = this.connection.createStatement();

            ResultSet result = state.executeQuery(query);
            while(result.next()) {                
                int idJalon = result.getInt(1);
                String intituleJalon = result.getString(2);
                int notationJalon = result.getInt(3);
                int progressionJalon = result.getInt(4);
                java.util.Date finJalon = result.getDate(5);
                Jalon j = new Jalon(idJalon, intituleJalon, finJalon, progressionJalon, notationJalon);
                System.out.println(j.getIntitule());
                jl.add(j);
            }
        }
        catch(Exception e) {}
    return jl;  
    }
}


//SELECT DISTINCT motCle FROM MotCleProjet M WHERE 5 = M.idProjet
