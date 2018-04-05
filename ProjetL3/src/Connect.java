import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;

//CTRL + SHIFT + O pour g�n�rer les imports
public class Connect {

  public static void main(String[] args) {
    try {
      Class.forName("com.mysql.jdbc.Driver");
    }catch(ClassNotFoundException cnfe){ 
        System.out.println("La classe com.mysql.jdbc.Driver n'a pas �t� trouv�e"); 
        cnfe.printStackTrace(); 
    }
    try {
         
      String server = "jdbc:mysql://localhost:3306/mcregiecujsteve";
      //String server = "http://localhost/phpmyadmin:3306/mcregiecujsteve";
      String dbname = "mcregiecujsteve";
         
      Connection conn = DriverManager.getConnection(server,"root","");
      
      
      
         
      //Cr�ation d'un objet Statement
      Statement state = conn.createStatement();
      //L'objet ResultSet contient le r�sultat de la requ�te SQL
      ResultSet result = state.executeQuery("SELECT * FROM eleve");
      //On r�cup�re les MetaData
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