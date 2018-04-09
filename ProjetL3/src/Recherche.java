package src;

import java.sql.*;
import java.util.ArrayList;

public class Recherche {
    private Connection connection = null;

    public Recherche() {
        this.connection = Connect.getConnection();
    }

    public ArrayList<String> rechercher(String r) {
        ArrayList<String> resultat = new ArrayList<String>();
        try {
            PreparedStatement state = this.connection.prepareStatement("SELECT nom FROM Eleve WHERE nom LIKE ? ORDER BY nom DESC LIMIT 0,20");
            state.setString(1,r);
            state.executeUpdate();
            //L'objet ResultSet contient le resultat de la requete SQL
            ResultSet result = state.executeQuery();
            //On recupere les MetaData
            ResultSetMetaData resultMeta = result.getMetaData();  // A quoi cela sert Simon ?
      
            while ( result.next() ) {
                String nomEleve = result.getString( "nom" );
                resultat.add(nomEleve); //TODO separer noms et prenoms
            }
        }
        catch(Exception e) {}
    return resultat;
    }
}