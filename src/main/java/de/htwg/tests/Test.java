package de.htwg.tests;

import de.htwg.generators.QueryGenerator;

public class Test {
    public static void main(String[] args) {
        String query = QueryGenerator.getApartment();
        System.out.println(query);
    }
}
