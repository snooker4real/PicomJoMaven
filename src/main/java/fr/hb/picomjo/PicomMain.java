package fr.hb.picomjo;
//import org.json.*;

import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.Properties;

public class PicomMain {

    public static void testEnv(String[] args) throws IOException {
        //System.out.println("Jonathan");
        InputStream aIn = PicomMain.class.getResourceAsStream("/env/app.env");
        Properties env = new Properties();

        env.load(aIn);

        String aBasePath = env.getProperty("basepath");
        System.out.println("basepath=" + aBasePath);

        System.out.println("appli.name=" + env.getProperty("appli.name"));
    }
    /*
    public static void viewTable(Connection con) throws SQLException {
        String query = "select COF_NAME, SUP_ID, PRICE, SALES, TOTAL from COFFEES";
        try (Statement stmt = con.createStatement()) {
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                String coffeeName = rs.getString("COF_NAME");
                int supplierID = rs.getInt("SUP_ID");
                float price = rs.getFloat("PRICE");
                int sales = rs.getInt("SALES");
                int total = rs.getInt("TOTAL");
                System.out.println(coffeeName + ", " + supplierID + ", " + price +
                        ", " + sales + ", " + total);
            }
        } catch (SQLException e) {
            JDBCTutorialUtilities.printSQLException(e);
        }
    }

     */

    public static void main(String[] args) {
        Statement stmt = null;
        ResultSet rs = null;
        Connection conn = null;
        try {
            conn = DriverManager.getConnection("jdbc:mysql://localhost/client?" + "user=root&password=");


           stmt = conn.createStatement(
                   ResultSet.TYPE_SCROLL_INSENSITIVE,
                   ResultSet.CONCUR_UPDATABLE
           );
            // rs will be scrollable, will not show changes made by others,
            // and will be updatable

            rs = stmt.executeQuery("SELECT * FROM `utilisateur` WHERE 1");

            if (stmt.execute("SELECT * FROM `utilisateur` WHERE 1")){
                rs = stmt.getResultSet();

                while (rs.next()){
                    int utilisateur = rs.getInt("utilisateur");
                    String utilisateur_nom = rs.getString("utilisateur_nom");
                    String utilisateur_prenom = rs.getString("utilisateur_prenom");
                    String utilisateur_email = rs.getString("utilisateur_email");
                    String utlisateur_mdp = rs.getString("utlisateur_mdp");
                    String utilisateur_telephone = rs.getString("utilisateur_telephone");
                    int id_ville =rs.getInt("id_ville");
                    System.out.println(utilisateur_nom + ", " + utilisateur_prenom + ", " + utilisateur_email + ", " + utilisateur_telephone + ", " + id_ville );
                }
            }
        } catch (SQLException ex) {
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        }

        finally{
            // it is a good idea to release
            // resources in a finally {} block
            // in reverse order of their creation
            // if they are no longer needed

            if (rs != null){
                try {
                    rs.close();
                } catch (SQLException sqlEx) { } // ignore

                rs = null;
            }

            if (stmt != null) {
                try {
                    stmt.close();
                } catch (SQLException sqlEx) { } // ignore

                stmt = null;
            }
        }
    }
}
