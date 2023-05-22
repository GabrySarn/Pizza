import java.sql.*;
import java.util.ArrayList;

public class PizzaDB {
    private Connection c;

    public PizzaDB() {
        try {
            c = DriverManager.getConnection("jdbc:mysql://localhost:3306/pizzadb");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void scriviDB(String ric, double prezzo, double mancia) {
        try {
            Statement stmt = c.createStatement();
            stmt.execute("INSERT INTO fatture (Richiesta,Prezzo,Mancia) " +
                    "VALUES ( '" + ric + "' , " + prezzo + " , " + mancia + " ) ");
            stmt.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public double leggiDB() {
        double tot = 0;
        double tmp;
        try {
            Statement stmt = c.createStatement();
            stmt.executeQuery("SELECT SUM(Prezzo+Mancia) Somma FROM fatture");
            ResultSet rs = stmt.getResultSet();
            while (rs.next())
                tot = rs.getFloat("Somma");
            stmt.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return Math.round(tot * 100.0) / 100.0;
    }

    public ArrayList<String[]> leggiRichiesteDB() {
        ArrayList<String[]> row = new ArrayList<>();
        try {
            Statement stmt = c.createStatement();
            stmt.executeQuery("SELECT Richiesta,Pizza FROM richieste");
            ResultSet rs = stmt.getResultSet();
            while (rs.next()) {
                String[] ob = new String[3];
                ob[0] = rs.getString("Richiesta");
                ob[1] = rs.getString("Pizza");
                row.add(ob);
            }
            stmt.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return row;
    }

    public ArrayList<Double> leggiPrezziDB(){
        ArrayList<Double> prezzi = new ArrayList<>();
        try {
            Statement stmt = c.createStatement();
            stmt.executeQuery("SELECT Prezzo FROM richieste");
            ResultSet rs = stmt.getResultSet();
            while (rs.next()) {
                prezzi.add(rs.getDouble("Prezzo"));
            }
            stmt.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return prezzi;
    }

    public void scriviRichiesteDB(ArrayList<String> req, ArrayList<String> pizze, ArrayList<String> prezzi) {
        try (PreparedStatement stmt = c.prepareStatement("INSERT INTO richieste (Richiesta,Pizza,Prezzo) " +
                "VALUES ( ?,?,? ) ")) {
            for (int i = 0; i < req.size(); i++) {
                stmt.setString(1, req.get(i));
                stmt.setString(2, pizze.get(i));
                stmt.setDouble(3, Double.parseDouble(prezzi.get(i)));
                stmt.executeUpdate();
            }
            stmt.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

}
