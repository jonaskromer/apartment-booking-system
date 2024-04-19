package de.htwg.tests;

import de.htwg.dbpopulation.generators.QueryGenerator;

import java.time.LocalDate;

public class Test {
    public static void main(String[] args) {
        String query = QueryGenerator.getApartment();
        System.out.println(query);

        LocalDate date = LocalDate.now();
        System.out.println(date.toString().replace("-", ""));
    }
}
