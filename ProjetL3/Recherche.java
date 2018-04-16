import java.io.*;
import java.sql.*;
import java.util.ArrayList;

public class Recherche {
    private Connection connection = null;

    public Recherche() {
        this.connection = Connect.getConnection();
    }
        //


    /**
     * Recherche dans la base de donnees la liste des noms des eleves
     * @param r Mot cle permettant de trouver ce que l'on cherche
     * @return Retourne une arraylist avec tous les rÃ©sultats de la recherche
     */

    public ArrayList<String> rechercherEleve(String r) {
        ArrayList<String> resultat = new ArrayList<String>();
        try {
            String query = "SELECT nom FROM Eleve WHERE nom LIKE '" + r + "%' ORDER BY nom DESC LIMIT 0,20";
            Statement state = this.connection.createStatement();
            
            ResultSet result = state.executeQuery(query);

            while (result.next()) {
                String nomEleve = result.getString(1);
                resultat.add(nomEleve); //TODO separer noms et prenoms
            }
        }
        catch(Exception e) {}
    return resultat;
    }
    
    public ArrayList<String> rechercherProjet(String r) {
        ArrayList<String> resultat = new ArrayList<String>();
        try {
            String query = "SELECT sujet FROM Projet WHERE sujet LIKE '" + r + "%' ORDER BY sujet DESC LIMIT 0,20";
            Statement state = this.connection.createStatement();
            
            ResultSet result = state.executeQuery(query);

            while (result.next()) {
                String sujetProjet = result.getString(1);
                resultat.add(sujetProjet); //TODO separer noms et prenoms
            }
        }
        catch(Exception e) {}
    return resultat;
    }

    public ArrayList<String> rechercherProjetMotCle(String r) {
        ArrayList<String> resultat = new ArrayList<String>();
        try {
            String query = "SELECT P.sujet FROM Projet P, MotCleProjet M WHERE M.motcle LIKE '" + r + "%' AND M.idProjet = P.id ORDER BY M.motcle ASC";
            Statement state = this.connection.createStatement();
            
            ResultSet result = state.executeQuery(query);

            while (result.next()) {
                String sujetProjet = result.getString(1);
                resultat.add(sujetProjet); //TODO separer noms et prenoms
            }
        }
        catch(Exception e) {}
    return resultat;
    }
}