package de.htwg.main;

import de.htwg.generators.Creator;
import de.htwg.generators.Inserter;

import java.sql.*;


public class DbPopulator {
    public static void main(String[] args) {
        Connection conn = DbConnector.connect();

        boolean inserts = false;

        Inserter.insertCountries(inserts, conn);
        Inserter.insertAttractionCategories(inserts, conn);
        Inserter.insertFacilities(inserts, conn);
        Creator.createCustomers(0, conn);
        Creator.createApartments(0, conn);
        Creator.createAttractions(0, conn);
        Creator.createDistanceMappings(0, conn);
        Creator.createFacilityMappings(0, conn);
        Creator.createBookings(0, conn);
        Creator.createFullPayment(100, conn);

    }
}
