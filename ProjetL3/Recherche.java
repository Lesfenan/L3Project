import java.io.*;
import java.sql.*;
import java.util.ArrayList;

public class Recherche {
    private Connection connection = null;

    public Recherche() {
        this.connection = Connect.getConnection();
    }

    /**
     * Recherche dans la base de donnees la liste des noms des eleves
     * @param r Mot cle permettant de trouver ce que l'on cherche
     * @return Retourne une arraylist avec tous les résultats de la recherche
     */

    public ArrayList<String> rechercher(String r) {
        ArrayList<String> resultat = new ArrayList<String>();
        try {
            String query = "SELECT nom FROM Eleve WHERE nom LIKE '" + r + "%' ORDER BY nom DESC LIMIT 0,20";
            Statement state = this.connection.createStatement();
            
            ResultSet result = state.executeQuery(query);

            while (result.next()) {
                System.out.println("codoodo");
                String nomEleve = result.getString(1);
                resultat.add(nomEleve); //TODO separer noms et prenoms
            }
        }
        catch(Exception e) {}
    return resultat;
    }
}