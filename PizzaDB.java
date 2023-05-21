import java.sql.*;

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

}
