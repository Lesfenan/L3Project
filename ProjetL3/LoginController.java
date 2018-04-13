import java.io.*;
import java.sql.*;
import java.util.ArrayList;

public class LoginController {
    private Connection connection = null;

    public LoginController() {
        this.connection = Connect.getConnection();
    }

    /**
     * Recherche dans la base de donnees la liste des noms des eleves
     * @param r Mot cle permettant de trouver ce que l'on cherche
     * @return Retourne une arraylist avec tous les résultats de la recherche
     */

    public Personne login(String nom, String pass, String type) {
        Personne p = new Personne("Login inconnu","");
        try {
            String query = null;

            if(type == "Eleve") {
                query = "SELECT * FROM Eleve WHERE nom='"+nom+"' AND pass='"+pass+"'";    
            }
            else {
                query = "SELECT * FROM Enseignant WHERE nom='"+nom+"' AND pass='"+pass+"'";
            }
            Statement state = this.connection.createStatement();

            ResultSet result = state.executeQuery(query);
            
            if(result.next()) {
                String nomP = result.getString(3);
                String prenomP = result.getString(4);
                p = new Personne(nomP,prenomP);
            }

        }
        catch(Exception e) {}
    return p;
    }
}