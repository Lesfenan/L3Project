
import java.io.*;
import java.sql.*;
import java.util.ArrayList;

public class EnseignantController {
    private Connection connection = null;

    public EnseignantController() {
        this.connection = Connect.getConnection();
    }

    /**
     * Recherche dans la base de donnees la liste des noms des eleves
     * @param r Mot cle permettant de trouver ce que l'on cherche
     * @return Retourne une arraylist avec tous les résultats de la recherche
     */

    public ArrayList<Enseignant> getListOfEnseignant() {
        ArrayList<Enseignant> resultat = new ArrayList<Enseignant>();
        try {
            
            String query = "SELECT DISTINCT id, nom, prenom FROM Enseignant ORDER BY prenom ASC";
            Statement state = this.connection.createStatement();

            ResultSet result = state.executeQuery(query);
            
            while (result.next()) {                
                int idEnseignant = result.getInt(1);
                String nomEnseignant = result.getString(2);
                String prenomEnseignant = result.getString(3);
                Enseignant enseignant = new Enseignant(idEnseignant,nomEnseignant,prenomEnseignant);
                System.out.println(enseignant.toString());
                resultat.add(enseignant); //TODO separer noms et prenoms
            }
        }
        catch(Exception e) {}
    return resultat;
    }
}