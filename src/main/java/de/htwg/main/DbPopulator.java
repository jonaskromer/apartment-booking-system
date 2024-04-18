package de.htwg.main;

import de.htwg.generators.Creator;
import de.htwg.generators.Inserter;

import java.sql.*;


public class DbPopulator {
    public static void main(String[] args) {
        Connection conn = DbConnector.connect();

        Inserter.insertCountries(false, conn);
        Inserter.insertAttractionCategories(false, conn);
        Inserter.insertFacilities(false, conn);
        Creator.createCustomers(1000, conn);
        Creator.createApartments(0, conn);
        Creator.createAttractions(0, conn);
        Creator.createDistanceMappings(0, conn);
        Creator.createFacilityMappings(0, conn);



    }
}
