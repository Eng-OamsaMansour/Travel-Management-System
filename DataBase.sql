CREATE TABLE PIRSON (
    FIRST_NAME VARCHAR2(15) NOT NULL,
    LAST_NAME VARCHAR2(15) NOT NULL,
    SYS_ID VARCHAR2(10) NOT NULL PRIMARY KEY,
    GOV_ID VARCHAR2(9) UNIQUE NOT NULL,
    AGE INTEGER,
    USER_NAME VARCHAR2(15) UNIQUE NOT NULL,
    PASSWORD_ VARCHAR2(15) NOT NULL
);
CREATE TABLE TRIP (
    TRIP_ID VARCHAR2(15) PRIMARY KEY,
    DATE_ DATE NOT NULL,
    PRICE INTEGER NOT NULL,
    TRIP_LOCATION VARCHAR2(15) NOT NULL,
    TRIP_HOTEL VARCHAR2(15),
    TRANSPORTATION_WAY VARCHAR2(15) NOT NULL,
    TRIP_PATH_DESCRIPTION VARCHAR2(300) NOT NULL
);
CREATE TABLE HOTEL (
    HOTEL_ID VARCHAR2(15) PRIMARY KEY,
    HOTEL_NAME VARCHAR2(15) NOT NULL,
    HOTEL_STARS INTEGER NOT NULL,
    LOCATION_ID VARCHAR2(15)
);
CREATE TABLE ROOM_KINDS (
    HOTEL_ID VARCHAR2(15),
    ROOM_KIND VARCHAR2(15)
);
CREATE TABLE LOCATIONS (
    LOCATION_ID VARCHAR2(15) PRIMARY KET,
    REQUIRED_DECUMENTS VARCHAR2(300),
    CONTRY VARCHAR2(20) NOT NULL,
    CITY VARCHAR2(15) NOT NULL
)
CREATE TABLE TRANSPORTATION_WAYS (
    LOCATION_ID VARCHAR2(15),
    TRANSPORTATION_WAY VARCHAR2(15)
);
CREATE TABLE USER_FEEDBACK (
    FEEDBACK_ID VARCHAR2(15) PRIMARY KEY,
    FEEDBACK_TEXT VARCHAR2(400) NOT NULL,
    STATUS VARCHAR2(15) NOT NULL,
    USER_ID VARCHAR2(15)
); 
CREATE TABLE BOOKTRIP (
    SYS_ID VARCHAR2(15),
    TRIP_ID VARCHAR2(15)
);

ALTER TABLE BOOKTRIP
ADD CONSTRAINT USERSYSID_TRIPID PRIMARY KEY (SYS_ID,TRIP_ID);

ALTER TABLE BOOKTRIP 
ADD CONSTRAINT user_sys_id_booktrip_fk FOREIGN KEY (SYS_ID) REFERENCES PIRSON(SYS_ID);

ALTER TABLE BOOKTRIP
ADD CONSTRAINT trip_id_id_booktrip_fk FOREIGN KEY (TRIP_ID) REFERENCES TRIP (TRIP_ID);

ALTER TABLE TRIP
ADD CONSTRAINT triplocation_locationid_fk FOREIGN KEY (TRIP_LOCATION) REFERENCES LOCATIONS (LOCATION_ID);

ALTER TABLE TRIP
ADD CONSTRAINT triphotel_hotelid_fk FOREIGN KEY (TRIP_HOTEL) REFERENCES HOTEL (HOTEL_ID);
