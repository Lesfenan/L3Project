import java.io.*;
import java.sql.*;
import java.util.ArrayList;

public class JalonController {
    private Connection connection = null;

    public JalonController() {
        this.connection = Connect.getConnection();
    }

    /**
     * Recherche dans la base de donnees la liste des noms des eleves
     * @param r Mot cle permettant de trouver ce que l'on cherche
     * @return Retourne une arraylist avec tous les résultats de la recherche
     */

    public Jalon addJalonToDB(String intitule, java.util.Date dateFin, int idProjet, String classe) {
        Jalon j = new Jalon(-1,intitule,dateFin,0,-1,classe);
        java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyy-MM-dd");
        String date = sdf.format(dateFin);
            try {

                String insert = "INSERT INTO Jalon(intitule, dateFin, projet, classe) VALUES ('" + intitule + "', '" + date + "', '" + idProjet + "', '" + classe + "');";
                Statement state = this.connection.createStatement();

                state.executeUpdate(insert);

                ResultSet result = state.executeQuery("SELECT MAX(id) FROM Jalon");
                int idJalon = result.getInt(1);         
                j.setId(idJalon);
            }
            catch(Exception e) {}
        return j;
    }
}