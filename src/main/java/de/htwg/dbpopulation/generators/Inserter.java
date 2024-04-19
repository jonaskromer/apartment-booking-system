package de.htwg.dbpopulation.generators;

import de.htwg.dbpopulation.Data;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import static de.htwg.dbpopulation.generators.PrintLib.*;

public class Inserter {

    public static void insertCountries(boolean insert, Connection conn) {
        if (!insert) {
            return;
        }

        try {
            printStartOfInsertion("countries", Data.COUNTRIES.length);
            Statement stmt = conn.createStatement();
            for (String country : Data.COUNTRIES) {
                String myUpdateQuery = "INSERT INTO COUNTRY(COUNTRY_NAME) VALUES('" + country + "')";
                stmt.executeUpdate(myUpdateQuery);
                conn.commit();
            }
            printEndOfInsertion();
        } catch (SQLException se) {
            printException(se);
        }
    }

    public static void insertAttractionCategories(boolean insert, Connection conn) {
        if (!insert) {
            return;
        }

        try {
            printStartOfInsertion("attraction categories", Data.ATTRACTION_CATEGORIES.length);
            Statement stmt = conn.createStatement();
            for (String category : Data.ATTRACTION_CATEGORIES) {
                String myUpdateQuery = "INSERT INTO ATTRACTION_CATEGORY(CATEGORY_TYPE) VALUES('" + category + "')";
                stmt.executeUpdate(myUpdateQuery);
                conn.commit();
            }
            printEndOfInsertion();
        } catch (SQLException se) {
            printException(se);
        }
    }

    public static void insertFacilities(boolean insert, Connection conn) {
        if (!insert) {
            return;
        }

        try {
            printStartOfInsertion("facilities", Data.FACILITIES.length);
            Statement stmt = conn.createStatement();
            for (String facility : Data.FACILITIES) {
                String myUpdateQuery = "INSERT INTO FACILITY(FACILITY_NAME) VALUES('" + facility + "')";
                stmt.executeUpdate(myUpdateQuery);
                conn.commit();
            }
            printEndOfInsertion();
        } catch (SQLException se) {
            printException(se);
        }
    }

}
