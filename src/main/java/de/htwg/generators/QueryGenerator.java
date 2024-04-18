package de.htwg.generators;

import java.time.LocalDate;
import java.sql.Date;

import static de.htwg.generators.Data.*;
import static de.htwg.generators.RandomLib.*;

public class QueryGenerator {



    public static String getAddress() {
        return "'" + STREETS[getInt(0, STREETS.length - 1)] + "', '" + getInt(1, 254) + "', '" + ZIP_CODES[getInt(0, ZIP_CODES.length - 1)] + "', '" + CITIES[getInt(0, CITIES.length - 1)] + "', '" + getInt(1, COUNTRIES.length) + "'";
    }

    public static String getCustomer() {

        String rdm_name = NAMES[getInt(0, NAMES.length - 1)];
        String rdm_surname = SURNAMES[getInt(0, SURNAMES.length - 1)];
        return "'" + mailGenerator(rdm_name, rdm_surname) + "', '" + rdm_name + "', '" + rdm_surname + "', '" + generatePassword(12, 30) + "', '" + generateIBAN() + "', '" + getInt(0, 1) + "', " + getAddress();
    }

    public static String mailGenerator(String name, String surname) {
        StringBuilder mailBuilder = new StringBuilder();

        String[] mail_seperators = {"", ".", "-", "_"};
        String[] mail_endings = {".de", ".com", ".org", ".net", ".info", ".es", ".fr", ".it", ".co.uk", ".nl", ".eu", ".at", ".ch", ".us", ".ru", ".pl", ".cz", ".se", ".dk", ".fi", ".no", ".be", ".gr", ".hu", ".ro", ".pt", ".br", ".mx", ".ar", ".cl", ".co", ".za", ".au", ".nz", ".ca", ".jp", ".cn", ".gg"};
        String[] mail_domains = {"gmail", "web", "mail", "hotmail", "yahoo", "aol", "gmx", "freenet", "t-online", "outlook", "icloud", "protonmail", "mailbox", "posteo"};
        int hasWhat = getInt(0, 5);

        if (hasWhat == 0) {
            mailBuilder.append(name);
            mailBuilder.append(getInt(0, 99));
        } else if (hasWhat == 1) {
            mailBuilder.append(name);
            mailBuilder.append(mail_seperators[getInt(0, mail_seperators.length - 1)]);
            mailBuilder.append(getInt(0, 99));
        } else if (hasWhat == 2) {
            mailBuilder.append(name);
            mailBuilder.append(mail_seperators[getInt(0, mail_seperators.length - 1)]);
            mailBuilder.append(surname);
        } else if (hasWhat == 3) {
            mailBuilder.append(name);
            mailBuilder.append(mail_seperators[getInt(0, mail_seperators.length - 1)]);
            mailBuilder.append(surname);
            mailBuilder.append(getInt(0, 99));
        } else if (hasWhat == 4) {
            mailBuilder.append(name);
            mailBuilder.append(mail_seperators[getInt(0, mail_seperators.length - 1)]);
            mailBuilder.append(getInt(0, 99));
            mailBuilder.append(surname);
        } else if (hasWhat == 5) {
            mailBuilder.append(getInt(0, 99));
            mailBuilder.append(mail_seperators[getInt(0, mail_seperators.length - 1)]);
            mailBuilder.append(surname);
        }

        mailBuilder.append("@");
        mailBuilder.append(mail_domains[getInt(0, mail_domains.length - 1)]);
        mailBuilder.append(mail_endings[getInt(0, mail_endings.length - 1)]);

        return mailBuilder.toString();
    }

    public static String getApartment() {

        return "'" + getInt(1, 12) + "', '" + APARTMENT_NAMES[getInt(0, APARTMENT_NAMES.length - 1)] + "', '" + getInt(12, 200) + "', '" + getInt(3400, 30000) + "', " + getAddress();
    }

    public static String getAttraction() {
        return "'" + ATTRACTION_NAMES[getInt(0, ATTRACTION_NAMES.length - 1)] + "', '" + getInt(1, ATTRACTION_CATEGORIES.length) + "', '" + ATTRACTION_DESCRIPTIONS[getInt(0, ATTRACTION_DESCRIPTIONS.length - 1)] + "', " + getAddress();
    }

    public static String getBooking() {
        Date bookingDate = Date.valueOf(LocalDate.now());
        Date startDate = Date.valueOf(getRandomDate());
        Date endDate = Date.valueOf(getRandomDate(startDate.toLocalDate()));

        return "'" + bookingNumberGenerator(bookingDate.toLocalDate()) + "', TO_DATE('" + bookingDate + "', 'YYYY-MM-DD'), TO_DATE('" + startDate + "', 'YYYY-MM-DD'), TO_DATE('" + endDate + "', 'YYYY-MM-DD')";
    }
    
    public static String bookingNumberGenerator(LocalDate bookingDate) {
        return bookingDate.toString().replace("-", "") + "-" + getInt(100000, 999999);
    }


}
