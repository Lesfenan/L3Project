

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import java.util.ArrayList;

//CTRL + SHIFT + O pour generer les imports
public class Connect {
  protected ArrayList<Eleve> collectionElevesBD;
  protected ArrayList<Enseignant> collectionEnseignantBD;
  protected ArrayList<Jalon> collectionJalonsBD;
  private static Connection conn = null;

  public Connect() {

  }

  /**
 * La methode getConnection se connecte a la BD et renvoi un objet avec lequelle on peut directement faire des requetes SQL
 * 
 * @return Retourne un objet connection
 */
public static Connection getConnection() {
      if(conn != null) {
          return conn;
      }
      else {
          try {
              String server = "jdbc:mysql://localhost:8889/projet";
              String dbname = "mcregiecujsteve"; 
              Class.forName("com.mysql.jdbc.Driver").newInstance();
              conn = DriverManager.getConnection(server,"root","root");
              System.out.println("Connection");
          }
          catch(Exception e) {
        	  	System.out.println(e);
          }
          return conn;  
      }
  }      
}