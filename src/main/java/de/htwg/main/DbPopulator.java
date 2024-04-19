package de.htwg.main;

import de.htwg.dbpopulation.generators.Populator;
import de.htwg.dbpopulation.generators.Inserter;

import java.sql.*;


public class DbPopulator {
    public static void main(String[] args) {
        Connection conn = DbConnector.connect();

        boolean inserts = false;

        Inserter.insertCountries(inserts, conn);
        Inserter.insertAttractionCategories(inserts, conn);
        Inserter.insertFacilities(inserts, conn);
        Populator.createCustomers(15000, conn);
        Populator.createApartments(10000, conn);
        Populator.createAttractions(5000, conn);
        Populator.createDistanceMappings(1000, conn);
        Populator.createFacilityMappings(1000, conn);
        Populator.createBookings(1000, conn);
        Populator.createFullPayment(500, conn);

    }
}
