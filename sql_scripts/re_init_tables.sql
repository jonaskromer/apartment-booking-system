DROP TABLE distance_mapping;
DROP TABLE facility;
DROP TABLE facility_mapping;
DROP TABLE payment;
DROP TABLE picture;
DROP TABLE attraction;
DROP TABLE attraction_category;
DROP TABLE booking;
DROP TABLE customer;
DROP TABLE apartment;
DROP TABLE country;

CREATE TABLE country (
country_id                  INTEGER GENERATED AS IDENTITY,
country_name                VARCHAR2(76),

CONSTRAINT pk_country PRIMARY KEY(country_id),
CONSTRAINT country_unique UNIQUE (country_name)
);

CREATE TABLE customer (
customer_id                 INTEGER GENERATED AS IDENTITY,
mail                        VARCHAR2(254)   NOT NULL,
first_name                  VARCHAR2(30)    NOT NULL,
surname                     VARCHAR2(30)    NOT NULL,
pswrd                       VARCHAR2(120)   NOT NULL,
iban                        VARCHAR2(32)    NOT NULL,
newsletteroptin             char(1)         NOT NULL,
street                      VARCHAR2(85)    NOT NULL,
house_number                VARCHAR2(30)    NOT NULL,
zip                         VARCHAR2(6)     NOT NULL,
city                        VARCHAR2(85)    NOT NULL,
country_id                  INTEGER         NOT NULL,

CONSTRAINT pk_customer PRIMARY KEY(customer_id),
CONSTRAINT newsletter_bool CHECK (newsletteroptin in ( '1', '0' )),
CONSTRAINT regex_mail CHECK (REGEXP_LIKE(mail,'^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,}$')),
CONSTRAINT fk_customer  FOREIGN KEY(country_id)
                        REFERENCES country(country_id),
CONSTRAINT mail_unique UNIQUE (mail)
);

CREATE TABLE apartment (
apartment_id                INTEGER GENERATED AS IDENTITY,
room_count                  INTEGER         NOT NULL,
apartment_name              VARCHAR2(120)   NOT NULL,
area                        INTEGER         NOT NULL,
price                       INTEGER         NOT NULL,
street                      VARCHAR2(85)    NOT NULL,
house_number                VARCHAR2(30)    NOT NULL,
zip                         VARCHAR2(6)     NOT NULL,
city                        VARCHAR2(85)    NOT NULL,
country_id                  INTEGER         NOT NULL,

CONSTRAINT pk_apartment PRIMARY KEY(apartment_id),
CONSTRAINT roomcount CHECK (room_count > 0),
CONSTRAINT area CHECK (area > 0),
CONSTRAINT price CHECK (price > 0),
CONSTRAINT fk_apartment FOREIGN KEY(country_id)
                        REFERENCES country(country_id)
);


CREATE TABLE facility (
facility_id             INTEGER GENERATED AS IDENTITY,
facility_name           VARCHAR2(30),

CONSTRAINT pk_facility PRIMARY KEY(facility_id),
CONSTRAINT facility_unique UNIQUE (facility_name)
);

CREATE TABLE attraction_category (
category_id             INTEGER GENERATED AS IDENTITY,
category_type           VARCHAR2(30),

CONSTRAINT pk_attraction_category PRIMARY KEY(category_id),
CONSTRAINT type_unique UNIQUE (category_type)
);

CREATE TABLE attraction (
attraction_id               INTEGER GENERATED AS IDENTITY,
attraction_name             VARCHAR2(30)    NOT NULL,
category_id                 INTEGER         NOT NULL,
attraction_description      VARCHAR2(500)   NOT NULL,
street                      VARCHAR2(85)    NOT NULL,
house_number                VARCHAR2(30)    NOT NULL,
zip                         VARCHAR2(6)     NOT NULL,
city                        VARCHAR2(85)    NOT NULL,
country_id                  INTEGER         NOT NULL,

CONSTRAINT pk_attraction PRIMARY KEY(attraction_id),
CONSTRAINT fk_attraction1   FOREIGN KEY(category_id)
                REFERENCES attraction_category(category_id),
CONSTRAINT fk_attraction2   FOREIGN KEY(country_id)
                            REFERENCES country(country_id)
);

CREATE TABLE distance_mapping (
apartment_id                INTEGER,
attraction_id               INTEGER,
distance                    INTEGER NOT NULL,

CONSTRAINT pk_distance_mapping PRIMARY KEY(apartment_id, attraction_id),
CONSTRAINT dist CHECK (distance > 0),
CONSTRAINT fk_distance_mapping1 FOREIGN KEY(apartment_id)
                                REFERENCES apartment(apartment_id)
                                ON DELETE CASCADE,
CONSTRAINT fk_distance_mapping2 FOREIGN KEY(attraction_id)
                                REFERENCES attraction(attraction_id)
                                ON DELETE CASCADE
);

CREATE TABLE facility_mapping (
apartment_id                INTEGER,
facility_id               INTEGER,

CONSTRAINT pk_facility_mapping PRIMARY KEY(apartment_id, facility_id)
);

CREATE TABLE picture (
pic_id                      INTEGER GENERATED AS IDENTITY,
src                         VARCHAR2(120)   NOT NULL,
apartment_id                INTEGER         NOT NULL,

CONSTRAINT pk_picture PRIMARY KEY(pic_id),
CONSTRAINT src_unique UNIQUE (src),
CONSTRAINT fk_picture   FOREIGN KEY(apartment_id)
                        REFERENCES apartment(apartment_id)
                        ON DELETE CASCADE
);

CREATE TABLE booking (
booking_id                  INTEGER GENERATED AS IDENTITY,
booking_number              CHAR(15),
booking_date                DATE            NOT NULL,
cancellation_date           DATE,
review_date                 DATE,
review_stars                INTEGER,
invoice_number              CHAR(13),
invoice_sum                 INTEGER,
invoice_date                DATE,
booking_start               DATE            NOT NULL,
booking_end                 DATE            NOT NULL,
apartment_id                INTEGER         NOT NULL,
customer_id                 INTEGER         NOT NULL,

CONSTRAINT pk_booking PRIMARY KEY(booking_id),
CONSTRAINT booking_number_unique UNIQUE (booking_number),
CONSTRAINT regex_bookingnumber CHECK (REGEXP_LIKE(booking_number, '^\d{8}-\d{6}$')),
CONSTRAINT regec_invnumver CHECK (REGEXP_LIKE(invoice_number, '^[A-Z]{2}-\d{4}-\d{6}$ ')),
CONSTRAINT revdate CHECK (review_date >= booking_end),
CONSTRAINT starval CHECK (review_stars > 0 AND review_stars < 6),
CONSTRAINT invuniq UNIQUE (invoice_number),
CONSTRAINT sumgrzero CHECK (invoice_sum > 0),
CONSTRAINT bookstrt CHECK (booking_start < booking_end),
CONSTRAINT bookend CHECK (booking_end > booking_start),
CONSTRAINT minstay CHECK (booking_end - booking_start >= 3),
CONSTRAINT fk_booking1  FOREIGN KEY(apartment_id)
                        REFERENCES apartment(apartment_id),
CONSTRAINT fk_booking2  FOREIGN KEY(customer_id)
                        REFERENCES customer(customer_id)
                        ON DELETE SET NULL

);

CREATE TABLE payment (
payment_id                  INTEGER GENERATED AS IDENTITY,
booking_id                  INTEGER         NOT NULL,
payment_amount              INTEGER         NOT NULL,
payment_date                DATE            NOT NULL,

CONSTRAINT pk_payment PRIMARY KEY(payment_id),
CONSTRAINT am CHECK (payment_amount > 0),
CONSTRAINT fk_payment   FOREIGN KEY(booking_id)
                        REFERENCES booking(booking_id)
);

