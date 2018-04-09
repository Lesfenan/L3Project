

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

  public static Connection getConnection() {
      if(conn != null) {
          return conn;
      }
      else {
          try {
              String server = "jdbc:mysql://localhost:3306/mcregiecujsteve";
              String dbname = "mcregiecujsteve"; 
              conn = DriverManager.getConnection(server,"root","root");
          }
          catch(Exception e) {
        	  	System.out.println(e);
          }
          return conn;  
      }
  }      
}