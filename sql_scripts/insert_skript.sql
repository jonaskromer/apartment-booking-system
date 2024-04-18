-- Füge 100 verschiedene Länder hinzu
DECLARE
    i NUMBER := 1;
BEGIN
    WHILE i <= 100 LOOP
        INSERT INTO country (country_name) VALUES ('Land' || i);
        i := i + 1;
    END LOOP;
END;
/

-- Füge 100 verschiedene Kunden hinzu
DECLARE
    i NUMBER := 1;
BEGIN
    WHILE i <= 100 LOOP
        INSERT INTO customer (customer_id, mail, first_name, surname, pswrd, iban, newsletteroptin, street, house_number, zip, city, country_name)
        VALUES (i, 'kunde' || i || '@example.com', 'Vorname' || i, 'Nachname' || i, 'password123', 'DE89370400440532013000', 'Y', 'Musterstraße', '12a', '12345', 'Musterstadt', 'Land' || i);
        i := i + 1;
    END LOOP;
END;
/

-- Füge 100 verschiedene Wohnungen hinzu
DECLARE
    i NUMBER := 1;
BEGIN
    WHILE i <= 100 LOOP
        INSERT INTO apartment (apartment_id, room_count, apartment_name, area, price, street, house_number, zip, city, country_name)
        VALUES (i, DBMS_RANDOM.VALUE(1, 5), 'Apartment' || i, DBMS_RANDOM.VALUE(50, 200), DBMS_RANDOM.VALUE(50, 500), 'Straße' || i, 'Hausnummer' || i, '12345', 'Stadt' || i, 'Land' || i);
        i := i + 1;
    END LOOP;
END;
/

-- Füge 100 verschiedene Buchungen hinzu
DECLARE
    i NUMBER := 1;
BEGIN
    WHILE i <= 100 LOOP
        INSERT INTO booking (booking_number, booking_date, review_date, invoice_number, invoice_sum, invoice_date, booking_start, booking_end, apartment_id, customer_id)
        VALUES ('BK' || LPAD(i, 12, '0'), SYSDATE - DBMS_RANDOM.VALUE(1, 365), CASE WHEN DBMS_RANDOM.VALUE(0, 1) < 0.5 THEN SYSDATE - DBMS_RANDOM.VALUE(1, 365) ELSE NULL END, 'INV' || LPAD(i, 10, '0'), DBMS_RANDOM.VALUE(50, 500), SYSDATE - DBMS_RANDOM.VALUE(1, 365), SYSDATE + DBMS_RANDOM.VALUE(1, 30), SYSDATE + DBMS_RANDOM.VALUE(31, 60), CEIL(DBMS_RANDOM.VALUE(1, 100)), CEIL(DBMS_RANDOM.VALUE(1, 100));
        i := i + 1;
    END LOOP;
END;
/

