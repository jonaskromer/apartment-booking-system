package de.htwg.tests;

import java.io.*;
import java.sql.*;

public class JDBC_Demo {
	public static void main(String args[]) {

		String name = null;
		String passwd = null;
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		Connection conn = null;
		Statement stmt = null;
		ResultSet rset = null;

		try {
			System.out.println("Benutzername: ");
			name = in.readLine();
			System.out.println("Passwort:");
			passwd = in.readLine();
		} catch (IOException e) {
			System.out.println("Fehler beim Lesen der Eingabe: " + e);
			System.exit(-1);
		}

		System.out.println("");

		try {
			DriverManager.registerDriver(new oracle.jdbc.OracleDriver()); 				// Treiber laden
			String url = "jdbc:oracle:thin:@oracle19c.in.htwg-konstanz.de:1521:ora19c"; // String für DB-Connection
			conn = DriverManager.getConnection(url, name, passwd); 						// Verbindung erstellen
					
			conn.setTransactionIsolation(Connection.TRANSACTION_SERIALIZABLE); 			// Transaction Isolations-Level setzen
			conn.setAutoCommit(false);													// Kein automatisches Commit

			stmt = conn.createStatement(); 												// Statement-Objekt erzeugen

			String myUpdateQuery = "INSERT INTO COUNTRY(COUNTRY_NAME) " +
					"VALUES('Deutschland')";											// Mitarbeiter hinzufügen
			stmt.executeUpdate(myUpdateQuery);	

			String mySelectQuery = "SELECT * FROM COUNTRY";
			rset = stmt.executeQuery(mySelectQuery);									// Query ausführen

			System.out.println("Länder in der Tabelle COUNTRY:");
			while(rset.next()) {
				System.out.println(rset.getString("country_name"));
			}

			myUpdateQuery = "DELETE FROM COUNTRY WHERE COUNTRY_NAME = 'Deutschland'";
			stmt.executeUpdate(myUpdateQuery);											// Mitarbeiter wieder löschen

			stmt.close();																// Verbindung trennen
			conn.commit();
			conn.close();
		} catch (SQLException se) {														// SQL-Fehler abfangen
			System.out.println();
			System.out
			.println("SQL Exception occurred while establishing connection to DBS: "
					+ se.getMessage());
			System.out.println("- SQL state  : " + se.getSQLState());
			System.out.println("- Message    : " + se.getMessage());
			System.out.println("- Vendor code: " + se.getErrorCode());
			System.out.println();
			System.out.println("EXITING WITH FAILURE ... !!!");
			System.out.println();
			try {
				conn.rollback();														// Rollback durchführen
			} catch (SQLException e) {
				e.printStackTrace();
			}
			System.exit(-1);
		}
	}
}
