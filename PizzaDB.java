import java.sql.*;

public class PizzaDB {
    public PizzaDB(String ric, double prezzo, double mancia) {
        try {
            Connection c = DriverManager.getConnection("jdbc:mysql://localhost:3306/pizzadb");

            Statement stmt = c.createStatement();
            stmt.execute("INSERT INTO fatture (Richiesta,Prezzo,Mancia,Totale) " + 
                            "VALUES ( '" + ric + "' , " + prezzo + " , " + mancia +  " , " + (prezzo+mancia) + " ) ");
            stmt.close();
            c.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

}
