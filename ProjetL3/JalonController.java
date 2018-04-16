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
 Statement state = null;
 Statement state2 = null;
 try {
 state = this.connection.createStatement();
 String insert = "INSERT INTO Jalon(intitule, dateFin, projet, classe, description) VALUES ('" + intitule + "', '" + date + "', '" + idProjet + "', '" + classe + "', 'ioojoj');";
 state.executeUpdate(insert);
 }
 catch(SQLException e) {
 System.out.println(e.getMessage());
 }
 try {
 if (state != null) {
                 state.close();
             }
 }
 catch(SQLException e) {
 System.out.println(e.getMessage());
 }
 
 try {
 state2 = this.connection.createStatement(); 
 ResultSet result = state2.executeQuery("SELECT MAX(id) FROM Jalon");
 result.next();
 int idJalon = result.getInt(1);
 j.setId(idJalon);
 System.out.println(idJalon);
 }
 catch(SQLException e) {
 System.out.println(e.getMessage());
 }
 try {
 if (state2 != null) {
                 state2.close();
             }
 }
 catch(SQLException e) {
 System.out.println(e.getMessage());
 }

 
 return j;
 }

 public void addDescription(int idJalon, String description) {
 try {
 String update = "UPDATE Jalon set description = '" + description + "' WHERE id = '" + idJalon + "';";
 Statement state = this.connection.createStatement();
 state.executeUpdate(update);
 }
 catch(Exception e) {}
 }

 public void addNotation(int idJalon, int notation) {
 try {
 String update = "UPDATE Jalon set notation = '" + notation + "' WHERE id = '" + idJalon + "';";
 Statement state = this.connection.createStatement();
 state.executeUpdate(update);
 }
 catch(Exception e) {}
 }

 public void addProgression(int idJalon, int progression) {
 try {
 String update = "UPDATE Jalon set progression = '" + progression + "' WHERE id = '" + idJalon + "';";
 Statement state = this.connection.createStatement();
 state.executeUpdate(update);
 }
 catch(Exception e) {}
 }
}