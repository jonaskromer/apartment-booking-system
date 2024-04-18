package de.htwg.generators;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import static de.htwg.generators.QueryGenerator.*;
import static de.htwg.generators.PrintLib.*;
import static de.htwg.generators.RandomLib.getInt;

public class Creator {

    public static int COMMIT_INTERVAL = 50;

    public static void createCustomers(int quantity, Connection conn) {
        if (quantity == 0) {
            return;
        }

        int createdCustomers = 0;
        int commitedCustomers = 0;

        try {
            printStartOfCreation("customers", quantity);
            Statement stmt = conn.createStatement();
            for (int i = 0; i < quantity; i++) {
                String customer = getCustomer();
                String myUpdateQuery = "INSERT INTO CUSTOMER(MAIL, FIRST_NAME, SURNAME, PSWRD, IBAN, NEWSLETTEROPTIN, STREET, HOUSE_NUMBER, ZIP, CITY, COUNTRY_ID) " +
                    "VALUES(" + customer + ")";
                stmt.executeUpdate(myUpdateQuery);
                createdCustomers++;
                if (createdCustomers % COMMIT_INTERVAL == 0) {
                    conn.commit();
                    commitedCustomers += COMMIT_INTERVAL;
                }
            }
            printEndOfCreation();
        } catch (SQLException se) {
            printException(se);
        } finally {
            createCustomers(quantity - commitedCustomers, conn);
        }
    }

    public static void createApartments(int quantity, Connection conn) {
        if (quantity == 0) {
            return;
        }

        int createdApartments = 0;
        int commitedApartments = 0;

        try {
            printStartOfCreation("apartments", quantity);
            Statement stmt = conn.createStatement();
            for (int i = 0; i < quantity; i++) {
                String apartment = getApartment();
                String myUpdateQuery = "INSERT INTO APARTMENT(ROOM_COUNT, APARTMENT_NAME, AREA, PRICE, STREET, HOUSE_NUMBER, ZIP, CITY, COUNTRY_ID) " +
                        "VALUES(" + apartment + ")";
                stmt.executeUpdate(myUpdateQuery);
                createdApartments++;
                if (createdApartments % COMMIT_INTERVAL == 0) {
                    conn.commit();
                    commitedApartments += COMMIT_INTERVAL;
                }
            }
            printEndOfCreation();
        } catch (SQLException se) {
            printException(se);
        } finally {
            createCustomers(quantity - commitedApartments, conn);
        }
    }
    
    public static void createAttractions(int quantity, Connection conn) {
        if (quantity == 0) {
            return;
        }

        int createdAttractions = 0;
        int commitedAttractions = 0;

        try {
            printStartOfCreation("attractions", quantity);
            Statement stmt = conn.createStatement();
            for (int i = 0; i < quantity; i++) {
                String attraction = getAttraction();
                String myUpdateQuery = "INSERT INTO ATTRACTION(ATTRACTION_NAME, CATEGORY_ID, ATTRACTION_DESCRIPTION, STREET, HOUSE_NUMBER, ZIP, CITY, COUNTRY_ID) " +
                        "VALUES(" + attraction + ")";
                stmt.executeUpdate(myUpdateQuery);
                createdAttractions++;
                if (createdAttractions % COMMIT_INTERVAL == 0) {
                    conn.commit();
                    commitedAttractions += COMMIT_INTERVAL;
                }
            }
            printEndOfCreation();
        } catch (SQLException se) {
            printException(se);
        } finally {
            createCustomers(quantity - commitedAttractions, conn);
        }
    }

    public static void createDistanceMappings(int quantity, Connection conn) {
        if (quantity == 0) {
            return;
        }

        int createdDistanceMappings = 0;
        int commitedDistanceMappings = 0;

        try {
            printStartOfCreation("distance mappings", quantity);

            // Get all apartment ids
            ResultSet rs = conn.createStatement().executeQuery("SELECT APARTMENT_ID FROM APARTMENT");
            List<String> apartmentIdsList = new ArrayList<>();
            while (rs.next()) {
                apartmentIdsList.add(rs.getString("APARTMENT_ID"));
            }
            String[] apartmentIds = apartmentIdsList.toArray(new String[0]);

            // Get all attraction ids
            rs = conn.createStatement().executeQuery("SELECT ATTRACTION_ID FROM ATTRACTION");
            List<String> attractionIdsList = new ArrayList<>();
            while (rs.next()) {
                attractionIdsList.add(rs.getString("ATTRACTION_ID"));
            }
            String[] attractionIds = attractionIdsList.toArray(new String[0]);

            Statement stmt = conn.createStatement();
            for (int i = 0; i < quantity; i++) {
                String apartmentId = apartmentIds[getInt(0, apartmentIds.length - 1)];
                for (int j = 0; j < getInt(5, 15); j++) {
                    int distance = getInt(0, 50000);
                    String myUpdateQuery = "INSERT INTO DISTANCE_MAPPING(APARTMENT_ID, ATTRACTION_ID, DISTANCE DISTANCE) " +
                            "VALUES('" + apartmentId + "', '" + attractionIds[getInt(0, attractionIds.length - 1)] + "', '" + distance + "')";
                    stmt.executeUpdate(myUpdateQuery);
                }
                createdDistanceMappings++;
                if (createdDistanceMappings % COMMIT_INTERVAL == 0) {
                    conn.commit();
                    commitedDistanceMappings += COMMIT_INTERVAL;
                }
            }
            printEndOfCreation();
        } catch (SQLException se) {
            printException(se);
        } finally {
            createCustomers(quantity - commitedDistanceMappings, conn);
        }
    }

    public static void createFacilityMappings(int quantity, Connection conn) {
        if (quantity == 0) {
            return;
        }

        int createdFacilityMappings = 0;
        int commitedFacilityMappings = 0;

        try {
            printStartOfCreation("facility mappings", quantity);

            // Get all apartment ids
            ResultSet rs = conn.createStatement().executeQuery("SELECT APARTMENT_ID FROM APARTMENT");
            List<String> apartmentIdsList = new ArrayList<>();
            while (rs.next()) {
                apartmentIdsList.add(rs.getString("APARTMENT_ID"));
            }
            String[] apartmentIds = apartmentIdsList.toArray(new String[0]);

            // Get all facility ids
            rs = conn.createStatement().executeQuery("SELECT FACILITY_ID FROM FACILITY");
            List<String> facilityIdsList = new ArrayList<>();
            while (rs.next()) {
                facilityIdsList.add(rs.getString("FACILITY_ID"));
            }
            String[] facilityIds = facilityIdsList.toArray(new String[0]);

            Statement stmt = conn.createStatement();
            for (int i = 0; i < quantity; i++) {
                String apartmentId = apartmentIds[getInt(0, apartmentIds.length - 1)];
                for (int j = 0; j < getInt(1, getInt(5, 15)); j++) {
                    String myUpdateQuery = "INSERT INTO FACILITY_MAPPING(APARTMENT_ID, FACILITY_ID) " +
                            "VALUES('" + apartmentId + "', '" + facilityIds[getInt(0, facilityIds.length - 1)] + "')";
                    stmt.executeUpdate(myUpdateQuery);
                }
                createdFacilityMappings++;
                if (createdFacilityMappings % COMMIT_INTERVAL == 0) {
                    conn.commit();
                    commitedFacilityMappings += COMMIT_INTERVAL;
                }
            }
            printEndOfCreation();
        } catch (SQLException se) {
            printException(se);
        } finally {
            createCustomers(quantity - commitedFacilityMappings, conn);
        }
    }
}
