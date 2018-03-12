import java.sql.Connection;
import java.sql.DriverManager;

public class Connect {
  public static void main(String[] args) {      
    try {
      Class.forName("org.mysql.Driver");
      System.out.println("Driver O.K.");

      String url = "jdbc:mysql://mcregiecujsteve.mysql.db/";
      String user = "mcregiecujsteve";
      String passwd = "TanguyMarwan0";

      Connection conn = DriverManager.getConnection(url, user, passwd);
      System.out.println("Connexion effective !");         
         
    } catch (Exception e) {
      e.printStackTrace();
    }      
  }
}