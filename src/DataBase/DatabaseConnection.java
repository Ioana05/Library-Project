package DataBase;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseConnection {
    private static final String connectionString= "jdbc:mysql://localhost:3306/library_project";
    private static DatabaseConnection databaseConnection;

    private DatabaseConnection(){}

    public static DatabaseConnection getInstance(){
        if (databaseConnection == null){
            databaseConnection = new DatabaseConnection();
        }
        return databaseConnection;
    }
    public Connection start() {
        try {
            // trebuie sa adaug numele user ului si parola in metoda ca sa mearga
            
            return DriverManager.getConnection(connectionString);
        }
        catch (Exception e)
        {
            System.out.println(e);
            return null;
        }
    }
}

