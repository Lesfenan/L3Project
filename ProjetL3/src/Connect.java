import java.sql.Connection;
import java.sql.DriverManager;
<<<<<<< HEAD
=======
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
>>>>>>> branch 'master' of https://github.com/Lesfenan/L3Project.git
import java.util.ArrayList;

//CTRL + SHIFT + O pour generer les imports
public class Connect {
<<<<<<< HEAD
  protected ArrayList<Eleve> collectionElevesBD;
  protected ArrayList<Enseignant> collectionEnseignantBD;
  protected ArrayList<Jalon> collectionJalonsBD;
  private static Connection conn = null;
=======
	protected ArrayList<Eleve> collectionElevesBD;
	protected ArrayList<Enseignant> collectionEnseignantBD;
	protected ArrayList<Jalon> collectionJalonsBD;
>>>>>>> branch 'master' of https://github.com/Lesfenan/L3Project.git

  public Connect() {
<<<<<<< HEAD
=======
	  
    try {
      Class.forName("com.mysql.jdbc.Driver");
    }catch(ClassNotFoundException cnfe){ 
        System.out.println("La classe com.mysql.jdbc.Driver n'a pas été trouvée"); 
        cnfe.printStackTrace(); 
    }
    try {
         
      String server = "jdbc:mysql://localhost:3306/mcregiecujsteve";
      //String server = "http://localhost/phpmyadmin:3306/mcregiecujsteve";
      String dbname = "mcregiecujsteve";
         
      Connection conn = DriverManager.getConnection(server,"root","");  
         
      //Création d'un objet Statement
      Statement state = conn.createStatement();
      //L'objet ResultSet contient le résultat de la requête SQL
      ResultSet result = state.executeQuery("SELECT * FROM eleve");
      //On récupère les MetaData
      ResultSetMetaData resultMeta = result.getMetaData();  // A quoi cela sert Simon ?
      
      while ( result.next() ) {
    	    String nomEleve = result.getString( "nom" );
    	    collectionElevesBD.add(new Eleve(nomEleve,nomEleve)); //TODO séparer noms et prenoms
    	    														// Apres c'est ptet pas opti de à chaque fois remplir des ArrayList. Faudrait plutot faire la requeste direct dans la BD
>>>>>>> branch 'master' of https://github.com/Lesfenan/L3Project.git

  }

  public static Connection getConnection() {
      if(conn != null) {
          return conn;
      }
      else {
          try {
              String server = "jdbc:mysql://localhost:3306/mcregiecujsteve";
              String dbname = "mcregiecujsteve"; 
              conn = DriverManager.getConnection(server,"root","");
          }
          catch(Exception e) {

          }
          return conn;  
      }
  }      
}