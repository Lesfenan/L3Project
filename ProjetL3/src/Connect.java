import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;

//CTRL + SHIFT + O pour générer les imports
public class Connect {

  public static void main(String[] args) {
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
      ResultSetMetaData resultMeta = result.getMetaData();
      
      while ( result.next() ) {
    	    String emailUtilisateur = result.getString( "nom" );
    	    System.out.println((emailUtilisateur));

    	}

      result.close();
      state.close();
         
    } catch (Exception e) {
    	System.out.println("Erreur Serveur");
      e.printStackTrace();
    }      
  }
}