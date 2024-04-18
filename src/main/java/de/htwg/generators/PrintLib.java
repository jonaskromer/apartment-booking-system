package de.htwg.generators;

import java.io.Console;
import java.sql.SQLException;

public class PrintLib {

    public static long START;
    public static long END;
    public static String TYPE;
    public static int QUANTITY;
    public static final String RESET = "\u001B[0m";
    public static final String RED = "\u001B[31m";

    public static void printHashs(boolean newline) {
        System.out.println("#################################################");
        if (newline) {
            System.out.println();
        }
    }

    public static void printHashs() {
        printHashs(false);
    }

    public static void printStartOfCreation(String type, int quantity) {
        TYPE = type;
        QUANTITY = quantity;
        printHashs();
        System.out.println("Creating " + quantity + " " + type + " ...");
        printHashs(true);
        START = System.nanoTime();
    }

    public static void printEndOfCreation() {
        END = System.nanoTime();
        printHashs();
        System.out.println("Created " + QUANTITY + " " + TYPE + " successfully in " + (END - START) / 1000000 + " ms.");
        printHashs(true);
    }

    public static void printResumeCreation(String type, int quantity) {
        printHashs();
        System.out.println("Resuming creation of " + quantity + " " + type + " ...");
        printHashs(true);
    }

    public static void printStartOfInsertion(String type, int quantity) {
        TYPE = type;
        QUANTITY = quantity;
        printHashs();
        System.out.println("Inserting " + quantity + " " + type + " ...");
        printHashs(true);
        START = System.nanoTime();
    }

    public static void printEndOfInsertion() {
        END = System.nanoTime();
        printHashs();
        System.out.println("Inserted " + QUANTITY + " " + TYPE + " successfully in " + (END - START) / 1000000 + " ms.");
        printHashs(true);
    }

    public static void printException(SQLException se) {
        System.out.println(RED);
        System.out
                .println("SQL Exception occurred while establishing connection to DBS: "
                        + se.getMessage());
        System.out.println("- SQL state  : " + se.getSQLState());
        System.out.println("- Message    : " + se.getMessage());
        System.out.println("- Vendor code: " + se.getErrorCode());
        System.out.println();
        System.out.println("EXITING WITH FAILURE ... !!!");
        System.out.println(RESET);
    }

    public static void printFailedQuery(String query) {

        System.out.println(RED);
        System.out.println("Failed query: " + query);
        System.out.println(RESET);
    }

    public static void printGatheringData() {
        printHashs();
        System.out.println("Gathering data from the database ...");
        printHashs(true);
        START = System.nanoTime();
    }

    public static void printReceivedData(int quantity) {
        END = System.nanoTime();
        printHashs();
        System.out.println("Received " + quantity + " entries successfully in " + (END - START) / 1000000 + " ms.");
        printHashs(true);
    }

}
