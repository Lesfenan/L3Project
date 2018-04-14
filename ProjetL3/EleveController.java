
import java.io.*;
import java.sql.*;
import java.util.ArrayList;

public class EleveController {
    private Connection connection = null;

    public EleveController() {
        this.connection = Connect.getConnection();
    }

    /**
     * Recherche dans la base de donnees la liste des noms des eleves
     * @param r Mot cle permettant de trouver ce que l'on cherche
     * @return Retourne une arraylist avec tous les résultats de la recherche
     */

    public ArrayList<Eleve> getListOfEleve() {
        ArrayList<Eleve> resultat = new ArrayList<Eleve>();
        try {
            
            String query = "SELECT DISTINCT id, nom, prenom FROM Eleve ORDER BY nom ASC";
            Statement state = this.connection.createStatement();

            ResultSet result = state.executeQuery(query);
            
            while (result.next()) {                
                int idEleve = result.getInt(1);
                String nomEleve = result.getString(2);
                String prenomEleve = result.getString(3);
                Eleve eleve = new Eleve(idEleve,nomEleve,prenomEleve);
                System.out.println(eleve.toString());
                resultat.add(eleve); //TODO separer noms et prenoms
            }
        }
        catch(Exception e) {}
    return resultat;
    }
}