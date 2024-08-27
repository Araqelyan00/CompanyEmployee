package companyEmployee.db;


import lombok.Getter;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnectionProvider {
    @Getter
    private final static DBConnectionProvider instance = new DBConnectionProvider();
    private Connection connection;
    private static final String url = "jdbc:mysql://localhost:3306/company_employee";
    private static final String user = "root";
    private static final String password = "Adjareguju1@";

    private DBConnectionProvider() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e){
            e.printStackTrace();
        }
    }

    public Connection getConnection() {
        try{
            if (connection == null || connection.isClosed()){
                connection = DriverManager.getConnection(url, user, password);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return connection;
    }

}
