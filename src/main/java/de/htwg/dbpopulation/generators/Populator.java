package de.htwg.dbpopulation.generators;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import static de.htwg.dbpopulation.generators.QueryGenerator.*;
import static de.htwg.dbpopulation.generators.PrintLib.*;

public class Populator {

    public static void createCustomers(int quantity, Connection conn) {
        if (quantity == 0) {
            return;
        }

        int createdCustomers = 0;
        boolean failed = false;

        try {
            printStartOfCreation("customers", quantity);
            Statement stmt = conn.createStatement();
            for (int i = 0; i < quantity; i++) {
                String customer = getCustomer();
                String myUpdateQuery = "INSERT INTO CUSTOMER(MAIL, FIRST_NAME, SURNAME, PSWRD, IBAN, NEWSLETTEROPTIN, STREET, HOUSE_NUMBER, ZIP, CITY, COUNTRY_ID) " +
                        "VALUES(" + customer + ")";
                stmt.executeUpdate(myUpdateQuery);
                createdCustomers++;
            }
            printEndOfCreation();
        } catch (SQLException se) {
            failed = true;
            printException(se);
        } finally {
            try {
                conn.commit();
            } catch (SQLException e) {
                printException(e);
            }
            if (failed) {
                createCustomersRec(quantity - createdCustomers, conn);
            }
        }
    }

    public static void createCustomersRec(int quantity, Connection conn) {
        if (quantity == 0) {
            return;
        }

        int createdCustomers = 0;
        boolean failed = false;

        try {
            printResumeCreation("customers", quantity);
            Statement stmt = conn.createStatement();
            for (int i = 0; i < quantity; i++) {
                String customer = getCustomer();
                String myUpdateQuery = "INSERT INTO CUSTOMER(MAIL, FIRST_NAME, SURNAME, PSWRD, IBAN, NEWSLETTEROPTIN, STREET, HOUSE_NUMBER, ZIP, CITY, COUNTRY_ID) " +
                        "VALUES(" + customer + ")";
                stmt.executeUpdate(myUpdateQuery);
                createdCustomers++;
            }
            printEndOfCreation();
        } catch (SQLException se) {
            failed = true;
            printException(se);
        } finally {
            try {
                conn.commit();
            } catch (SQLException e) {
                printException(e);
            }
            if (failed) {
                createCustomersRec(quantity - createdCustomers, conn);
            }
        }
    }

    public static void createApartments(int quantity, Connection conn) {
        if (quantity == 0) {
            return;
        }

        int createdApartments = 0;
        boolean failed = false;

        try {
            printStartOfCreation("apartments", quantity);
            Statement stmt = conn.createStatement();
            for (int i = 0; i < quantity; i++) {
                String apartment = getApartment();
                String myUpdateQuery = "INSERT INTO APARTMENT(ROOM_COUNT, APARTMENT_NAME, AREA, PRICE, STREET, HOUSE_NUMBER, ZIP, CITY, COUNTRY_ID) " +
                        "VALUES(" + apartment + ")";
                stmt.executeUpdate(myUpdateQuery);
                createdApartments++;

            }
            printEndOfCreation();
        } catch (SQLException se) {
            failed = true;
            printException(se);
        } finally {
            try {
                conn.commit();
            } catch (SQLException e) {
                printException(e);
            }
            if (failed) {
                createApartmentsRec(quantity - createdApartments, conn);
            }
        }
    }

    public static void createApartmentsRec(int quantity, Connection conn) {
        if (quantity == 0) {
            return;
        }

        int createdApartments = 0;
        boolean failed = false;

        try {
            printResumeCreation("apartments", quantity);
            Statement stmt = conn.createStatement();
            for (int i = 0; i < quantity; i++) {
                String apartment = getApartment();
                String myUpdateQuery = "INSERT INTO APARTMENT(ROOM_COUNT, APARTMENT_NAME, AREA, PRICE, STREET, HOUSE_NUMBER, ZIP, CITY, COUNTRY_ID) " +
                        "VALUES(" + apartment + ")";
                stmt.executeUpdate(myUpdateQuery);
                createdApartments++;

            }
            printEndOfCreation();
        } catch (SQLException se) {
            failed = true;
            printException(se);
        } finally {
            try {
                conn.commit();
            } catch (SQLException e) {
                printException(e);
            }
            if (failed) {
                createApartmentsRec(quantity - createdApartments, conn);
            }
        }
    }

    public static void createAttractions(int quantity, Connection conn) {
        if (quantity == 0) {
            return;
        }

        int createdAttractions = 0;
        boolean failed = false;

        try {
            printStartOfCreation("attractions", quantity);
            Statement stmt = conn.createStatement();
            for (int i = 0; i < quantity; i++) {
                String attraction = getAttraction();
                String myUpdateQuery = "INSERT INTO ATTRACTION(ATTRACTION_NAME, CATEGORY_ID, ATTRACTION_DESCRIPTION, STREET, HOUSE_NUMBER, ZIP, CITY, COUNTRY_ID) " +
                        "VALUES(" + attraction + ")";
                stmt.executeUpdate(myUpdateQuery);
                createdAttractions++;
            }
            printEndOfCreation();
        } catch (SQLException se) {
            failed = true;
            printException(se);
        } finally {
            try {
                conn.commit();
            } catch (SQLException e) {
                printException(e);
            }
            if (failed) {
                createAttractionsRec(quantity - createdAttractions, conn);
            }
        }
    }

    public static void createAttractionsRec(int quantity, Connection conn) {
        if (quantity == 0) {
            return;
        }

        int createdAttractions = 0;
        boolean failed = false;

        try {
            printResumeCreation("attractions", quantity);
            Statement stmt = conn.createStatement();
            for (int i = 0; i < quantity; i++) {
                String attraction = getAttraction();
                String myUpdateQuery = "INSERT INTO ATTRACTION(ATTRACTION_NAME, CATEGORY_ID, ATTRACTION_DESCRIPTION, STREET, HOUSE_NUMBER, ZIP, CITY, COUNTRY_ID) " +
                        "VALUES(" + attraction + ")";
                stmt.executeUpdate(myUpdateQuery);
                createdAttractions++;
            }
            printEndOfCreation();
        } catch (SQLException se) {
            failed = true;
            printException(se);
        } finally {
            try {
                conn.commit();
            } catch (SQLException e) {
                printException(e);
            }
            if (failed) {
                createAttractionsRec(quantity - createdAttractions, conn);
            }
        }
    }

    public static void createDistanceMappings(int quantity, Connection conn) {
        if (quantity == 0) {
            return;
        }

        boolean failed = false;
        int createdDistanceMappings = 0;
        String[] apartmentIds = {};
        String[] attractionIds = {};
        String myUpdateQuery = "";

        try {
            printGatheringData();

            // Get all apartment ids
            ResultSet rs = conn.createStatement().executeQuery("SELECT APARTMENT_ID FROM APARTMENT");
            List<String> apartmentIdsList = new ArrayList<>();
            while (rs.next()) {
                apartmentIdsList.add(rs.getString("APARTMENT_ID"));
            }
            apartmentIds = apartmentIdsList.toArray(new String[0]);

            // Get all attraction ids
            rs = conn.createStatement().executeQuery("SELECT ATTRACTION_ID FROM ATTRACTION");
            List<String> attractionIdsList = new ArrayList<>();
            while (rs.next()) {
                attractionIdsList.add(rs.getString("ATTRACTION_ID"));
            }
            attractionIds = attractionIdsList.toArray(new String[0]);

            printReceivedData(apartmentIds.length + attractionIds.length);
            printStartOfCreation("distance mappings", quantity);

            Statement stmt = conn.createStatement();
            for (int i = 0; i < quantity; i++) {
                String apartmentId = apartmentIds[RandomLib.getInt(0, apartmentIds.length - 1)];
                int mappings = RandomLib.getInt(4, 15);
                RandomLib.RandomIntSequence rand = new RandomLib.RandomIntSequence();
                rand.createSequence(0, attractionIds.length - 1, mappings);
                for (int j = 0; j < mappings; j++) {
                    int distance = RandomLib.getInt(0, 50000);
                    myUpdateQuery = "INSERT INTO DISTANCE_MAPPING(APARTMENT_ID, ATTRACTION_ID, DISTANCE) " +
                            "VALUES('" + apartmentId + "', '" + attractionIds[rand.getNextInt()] + "', '" + distance + "')";
                    stmt.executeUpdate(myUpdateQuery);
                }
                createdDistanceMappings++;
            }
            printEndOfCreation();
        } catch (SQLException se) {
            failed = true;
            printException(se);
        } finally {
            try {
                conn.commit();
            } catch (SQLException e) {
                printException(e);
            }
            if (failed) {
                printFailedQuery(myUpdateQuery);
                createDistanceMappingsRec(quantity - createdDistanceMappings, conn, apartmentIds, attractionIds);
            }
        }
    }

    public static void createDistanceMappingsRec(int quantity, Connection conn, String[] apartmentIdsArr, String[] attractionIdsArr) {
        if (quantity == 0) {
            return;
        }

        boolean failed = false;
        int createdDistanceMappings = 0;
        String myUpdateQuery = "";

        try {
            printResumeCreation("distance mappings", quantity);

            Statement stmt = conn.createStatement();
            for (int i = 0; i < quantity; i++) {
                String apartmentId = apartmentIdsArr[RandomLib.getInt(0, apartmentIdsArr.length - 1)];
                int mappings = RandomLib.getInt(4, 15);
                RandomLib.RandomIntSequence rand = new RandomLib.RandomIntSequence();
                rand.createSequence(0, attractionIdsArr.length - 1, mappings);
                for (int j = 0; j < mappings; j++) {
                    int distance = RandomLib.getInt(0, 50000);
                    myUpdateQuery = "INSERT INTO DISTANCE_MAPPING(APARTMENT_ID, ATTRACTION_ID, DISTANCE) " +
                            "VALUES('" + apartmentId + "', '" + attractionIdsArr[rand.getNextInt()] + "', '" + distance + "')";
                    stmt.executeUpdate(myUpdateQuery);
                }
                createdDistanceMappings++;
            }
            printEndOfCreation();
        } catch (SQLException se) {
            failed = true;
            printException(se);
        } finally {
            try {
                conn.commit();
            } catch (SQLException e) {
                printException(e);
            }
            if (failed) {
                printFailedQuery(myUpdateQuery);
                createDistanceMappingsRec(quantity - createdDistanceMappings, conn, apartmentIdsArr, attractionIdsArr);
            }
        }
    }

    public static void createFacilityMappings(int quantity, Connection conn) {
        if (quantity == 0) {
            return;
        }

        int createdFacilityMappings = 0;
        String[] apartmentIds = {};
        String[] facilityIds = {};
        boolean failed = false;
        String myUpdateQuery = "";

        try {
            printStartOfCreation("facility mappings", quantity);

            // Get all apartment ids
            ResultSet rs = conn.createStatement().executeQuery("SELECT APARTMENT_ID FROM APARTMENT");
            List<String> apartmentIdsList = new ArrayList<>();
            while (rs.next()) {
                apartmentIdsList.add(rs.getString("APARTMENT_ID"));
            }
            apartmentIds = apartmentIdsList.toArray(new String[0]);

            // Get all facility ids
            rs = conn.createStatement().executeQuery("SELECT FACILITY_ID FROM FACILITY");
            List<String> facilityIdsList = new ArrayList<>();
            while (rs.next()) {
                facilityIdsList.add(rs.getString("FACILITY_ID"));
            }
            facilityIds = facilityIdsList.toArray(new String[0]);

            Statement stmt = conn.createStatement();
            for (int i = 0; i < quantity; i++) {
                String apartmentId = apartmentIds[RandomLib.getInt(0, apartmentIds.length - 1)];
                int mappings = RandomLib.getInt(4, 15);
                RandomLib.RandomIntSequence rand = new RandomLib.RandomIntSequence();
                rand.createSequence(0, facilityIds.length - 1, mappings);
                for (int j = 0; j < mappings; j++) {
                    myUpdateQuery = "INSERT INTO FACILITY_MAPPING(APARTMENT_ID, FACILITY_ID) " +
                            "VALUES('" + apartmentId + "', '" + facilityIds[rand.getNextInt()] + "')";
                    stmt.executeUpdate(myUpdateQuery);
                }
                createdFacilityMappings++;
            }
            printEndOfCreation();
        } catch (SQLException se) {
            failed = true;
            printException(se);
        } finally {
            try {
                conn.commit();
            } catch (SQLException e) {
                printException(e);
            }
            if (failed) {
                printFailedQuery(myUpdateQuery);
                createFacilityMappingsRec(quantity - createdFacilityMappings, conn, apartmentIds, facilityIds);
            }
        }
    }

    public static void createFacilityMappingsRec(int quantity, Connection conn, String[] apartmentIdsArr, String[] facilityIdsArr) {
        if (quantity == 0) {
            return;
        }

        boolean failed = false;
        int createdFacilityMappings = 0;
        String myUpdateQuery = "";

        try {
            printStartOfCreation("facility mappings", quantity);

            Statement stmt = conn.createStatement();
            for (int i = 0; i < quantity; i++) {
                String apartmentId = apartmentIdsArr[RandomLib.getInt(0, apartmentIdsArr.length - 1)];
                int mappings = RandomLib.getInt(4, 15);
                RandomLib.RandomIntSequence rand = new RandomLib.RandomIntSequence();
                rand.createSequence(0, facilityIdsArr.length - 1, mappings);
                for (int j = 0; j < mappings; j++) {
                    myUpdateQuery = "INSERT INTO FACILITY_MAPPING(APARTMENT_ID, FACILITY_ID) " +
                            "VALUES('" + apartmentId + "', '" + facilityIdsArr[rand.getNextInt()] + "')";
                    stmt.executeUpdate(myUpdateQuery);
                }
                createdFacilityMappings++;
            }
            printEndOfCreation();
        } catch (SQLException se) {
            failed = true;
            printException(se);
        } finally {
            try {
                conn.commit();
            } catch (SQLException e) {
                printException(e);
            }
            if (failed) {
                printFailedQuery(myUpdateQuery);
                createFacilityMappingsRec(quantity - createdFacilityMappings, conn, apartmentIdsArr, facilityIdsArr);
            }
        }
    }

    public static void createBookings(int quantity, Connection conn) {
        if (quantity == 0) {
            return;
        }

        int createdBookings = 0;
        boolean failed = false;
        String[] apartmentIds = {};
        String[] customerIds = {};
        String myUpdateQuery = "";

        try {
            printGatheringData();

            // Get all apartment ids
            ResultSet rs = conn.createStatement().executeQuery("SELECT APARTMENT_ID FROM APARTMENT");
            List<String> apartmentIdsList = new ArrayList<>();
            while (rs.next()) {
                apartmentIdsList.add(rs.getString("APARTMENT_ID"));
            }
            apartmentIds = apartmentIdsList.toArray(new String[0]);

            // Get all customer ids
            rs = conn.createStatement().executeQuery("SELECT CUSTOMER_ID FROM CUSTOMER");
            List<String> customerIdsList = new ArrayList<>();
            while (rs.next()) {
                customerIdsList.add(rs.getString("CUSTOMER_ID"));
            }
            customerIds = customerIdsList.toArray(new String[0]);

            printReceivedData(apartmentIds.length + customerIds.length);

            printStartOfCreation("bookings", quantity);
            Statement stmt = conn.createStatement();
            for (int i = 0; i < quantity; i++) {
                String booking = getBooking();
                myUpdateQuery = "INSERT INTO BOOKING(BOOKING_NUMBER, BOOKING_DATE, BOOKING_START, BOOKING_END, APARTMENT_ID, CUSTOMER_ID) " +
                        "VALUES(" + booking + ", '" + apartmentIds[RandomLib.getInt(0, apartmentIds.length - 1)] + "', '" + customerIds[RandomLib.getInt(0, customerIds.length - 1)] + "')";
                stmt.executeUpdate(myUpdateQuery);
                createdBookings++;
            }
            printEndOfCreation();
        } catch (SQLException se) {
            failed = true;
            printException(se);
        } finally {
            try {
                conn.commit();
            } catch (SQLException e) {
                printException(e);
            }
            if (failed) {
                printFailedQuery(myUpdateQuery);
                createBookingsRec(quantity - createdBookings, conn, apartmentIds, customerIds);
            }
        }
    }

    public static void createBookingsRec(int quantity, Connection conn, String[] apartmentIdsArr, String[] customerIdsArr) {
        if (quantity == 0) {
            return;
        }

        int createdBookings = 0;
        boolean failed = false;
        String myUpdateQuery = "";

        try {

            printStartOfCreation("bookings", quantity);
            Statement stmt = conn.createStatement();
            for (int i = 0; i < quantity; i++) {
                String booking = getBooking();
                myUpdateQuery = "INSERT INTO BOOKING(BOOKING_NUMBER, BOOKING_DATE, BOOKING_START, BOOKING_END, APARTMENT_ID, CUSTOMER_ID) " +
                        "VALUES(" + booking + ", '" + apartmentIdsArr[RandomLib.getInt(0, apartmentIdsArr.length - 1)] + "', '" + customerIdsArr[RandomLib.getInt(0, customerIdsArr.length - 1)] + "')";
                stmt.executeUpdate(myUpdateQuery);
                createdBookings++;
            }
            printEndOfCreation();
        } catch (SQLException se) {
            failed = true;
            printException(se);
        } finally {
            try {
                conn.commit();
            } catch (SQLException e) {
                printException(e);
            }
            if (failed) {
                printFailedQuery(myUpdateQuery);
                createBookingsRec(quantity - createdBookings, conn, apartmentIdsArr, customerIdsArr);
            }
        }
    }

    public static void createFullPayment(int quantity, Connection conn) {
        if (quantity == 0) {
            return;
        }

        int createdFullPayments = 0;
        boolean failed = false;
        String[] bookingIds = {};
        String myUpdateQuery = "";

        try {
            printGatheringData();

            // Get all booking ids
            ResultSet rs = conn.createStatement().executeQuery("SELECT BOOKING_ID FROM BOOKING");
            List<String> bookingIdsList = new ArrayList<>();
            while (rs.next()) {
                bookingIdsList.add(rs.getString("BOOKING_ID"));
            }
            bookingIds = bookingIdsList.toArray(new String[0]);

            printReceivedData(bookingIds.length);

            printStartOfCreation("full payments", quantity);
            Statement stmt = conn.createStatement();
            for (int i = 0; i < quantity; i++) {
                myUpdateQuery = "INSERT INTO PAYMENT(BOOKING_ID, PAYMENT_AMOUNT, PAYMENT_DATE) " +
                        "VALUES('" + bookingIds[RandomLib.getInt(0, bookingIds.length - 1)] + "', '" + RandomLib.getInt(100, 30000) + "', TO_DATE('" + RandomLib.getRandomDate() + "', 'YYYY-MM-DD'))";
                stmt.executeUpdate(myUpdateQuery);
                createdFullPayments++;
            }
            printEndOfCreation();
        } catch (SQLException se) {
            failed = true;
            printException(se);
        } finally {
            try {
                conn.commit();
            } catch (SQLException e) {
                printException(e);
            }
            if (failed) {
                printFailedQuery(myUpdateQuery);
                createFullPaymentRec(quantity - createdFullPayments, conn, bookingIds);
            }
        }
    }

    public static void createFullPaymentRec(int quantity, Connection conn, String[] bookingIdsArr) {
        if (quantity == 0) {
            return;
        }

        int createdFullPayments = 0;
        boolean failed = false;
        String myUpdateQuery = "";

        try {

            printResumeCreation("full payments", quantity);
            Statement stmt = conn.createStatement();
            for (int i = 0; i < quantity; i++) {
                myUpdateQuery = "INSERT INTO FULL_PAYMENT(BOOKING_ID, PAYMENT_AMOUNT, PAYMENT_DATE) " +
                        "VALUES('" + bookingIdsArr[RandomLib.getInt(0, bookingIdsArr.length - 1)] + "', '" + RandomLib.getInt(100, 30000) + "', TO_DATE('" + RandomLib.getRandomDate() + "', 'YYYY-MM-DD'))";
                stmt.executeUpdate(myUpdateQuery);
                createdFullPayments++;
            }
            printEndOfCreation();
        } catch (SQLException se) {
            failed = true;
            printException(se);
        } finally {
            try {
                conn.commit();
            } catch (SQLException e) {
                printException(e);
            }
            if (failed) {
                printFailedQuery(myUpdateQuery);
                createFullPaymentRec(quantity - createdFullPayments, conn, bookingIdsArr);
            }
        }
    }

}

