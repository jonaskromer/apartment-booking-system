package de.htwg.main;

import oracle.jdbc.OracleDriver;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import static de.htwg.dbpopulation.generators.PrintLib.printException;
import static de.htwg.dbpopulation.generators.PrintLib.printHashs;


public class DbConnector {

    private static String URL;
    private static String USERNAME;
    private static String PASSWORD;

    public static void getDatabaseCredentials(){
        String fileName = "assets/server_data.txt";

        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;
            String[] data = new String[3];
            while ((line = br.readLine()) != null) {
                 data = line.split(",");
            }
            URL = data[0];
            USERNAME = data[1];
            PASSWORD = data[2];

        } catch (IOException e) {
            System.err.println("Error reading the file: " + e.getMessage());
        }
    }

    public static Connection connect() {
        Connection conn = null;
        try {
            getDatabaseCredentials();
            DriverManager.registerDriver(new OracleDriver());
            conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            conn.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);
            conn.setAutoCommit(false);
            printHashs();
            System.out.println("Connection established successfully!");
            printHashs(true);
        } catch (SQLException se) {
            printException(se);
        }
        return conn;
    }
}
