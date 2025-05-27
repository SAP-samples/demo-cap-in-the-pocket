
DROP VIEW IF EXISTS localized_TravelService_SupplementType;
DROP VIEW IF EXISTS localized_TravelService_Supplement;
DROP VIEW IF EXISTS localized_TravelService_BookingStatus;
DROP VIEW IF EXISTS localized_TravelService_Countries;
DROP VIEW IF EXISTS localized_AnalyticsService_Countries;
DROP VIEW IF EXISTS localized_TravelService_TravelStatus;
DROP VIEW IF EXISTS localized_TravelService_Currencies;
DROP VIEW IF EXISTS localized_AnalyticsService_TravelStatus;
DROP VIEW IF EXISTS localized_AnalyticsService_Currencies;
DROP VIEW IF EXISTS localized_AnalyticsService_BookingStatus;
DROP VIEW IF EXISTS localized_AnalyticsService_Bookings;
DROP VIEW IF EXISTS TravelService_DraftAdministrativeData;
DROP VIEW IF EXISTS localized_sap_common_Currencies;
DROP VIEW IF EXISTS localized_sap_common_Countries;
DROP VIEW IF EXISTS localized_sap_fe_cap_travel_TravelStatus;
DROP VIEW IF EXISTS localized_sap_fe_cap_travel_BookingStatus;
DROP VIEW IF EXISTS localized_sap_fe_cap_travel_SupplementType;
DROP VIEW IF EXISTS localized_sap_fe_cap_travel_Supplement;
DROP VIEW IF EXISTS TravelService_SupplementType_texts;
DROP VIEW IF EXISTS TravelService_Airport;
DROP VIEW IF EXISTS TravelService_Supplement_texts;
DROP VIEW IF EXISTS TravelService_SupplementType;
DROP VIEW IF EXISTS TravelService_FlightConnection;
DROP VIEW IF EXISTS TravelService_Supplement;
DROP VIEW IF EXISTS TravelService_BookingStatus_texts;
DROP VIEW IF EXISTS TravelService_Countries_texts;
DROP VIEW IF EXISTS AnalyticsService_Countries_texts;
DROP VIEW IF EXISTS AnalyticsService_Airport;
DROP VIEW IF EXISTS TravelService_Flight;
DROP VIEW IF EXISTS TravelService_Airline;
DROP VIEW IF EXISTS TravelService_BookingSupplement;
DROP VIEW IF EXISTS TravelService_BookingStatus;
DROP VIEW IF EXISTS TravelService_Countries;
DROP VIEW IF EXISTS TravelService_TravelStatus_texts;
DROP VIEW IF EXISTS TravelService_Currencies_texts;
DROP VIEW IF EXISTS AnalyticsService_Countries;
DROP VIEW IF EXISTS AnalyticsService_TravelStatus_texts;
DROP VIEW IF EXISTS AnalyticsService_Currencies_texts;
DROP VIEW IF EXISTS AnalyticsService_FlightConnection;
DROP VIEW IF EXISTS TravelService_Booking;
DROP VIEW IF EXISTS TravelService_Passenger;
DROP VIEW IF EXISTS TravelService_TravelAgency;
DROP VIEW IF EXISTS TravelService_TravelStatus;
DROP VIEW IF EXISTS TravelService_Currencies;
DROP VIEW IF EXISTS AnalyticsService_Passenger;
DROP VIEW IF EXISTS AnalyticsService_TravelAgency;
DROP VIEW IF EXISTS AnalyticsService_TravelStatus;
DROP VIEW IF EXISTS AnalyticsService_Currencies;
DROP VIEW IF EXISTS AnalyticsService_BookingStatus_texts;
DROP VIEW IF EXISTS AnalyticsService_Flight;
DROP VIEW IF EXISTS AnalyticsService_Airline;
DROP VIEW IF EXISTS TravelService_Travel;
DROP VIEW IF EXISTS AnalyticsService_Travels;
DROP VIEW IF EXISTS AnalyticsService_BookingStatus;
DROP VIEW IF EXISTS AnalyticsService_Bookings;
DROP TABLE IF EXISTS TravelService_BookingSupplement_drafts;
DROP TABLE IF EXISTS TravelService_Booking_drafts;
DROP TABLE IF EXISTS TravelService_Travel_drafts;
DROP TABLE IF EXISTS DRAFT_DraftAdministrativeData;
DROP TABLE IF EXISTS sap_common_Currencies_texts;
DROP TABLE IF EXISTS sap_common_Countries_texts;
DROP TABLE IF EXISTS sap_fe_cap_travel_TravelStatus_texts;
DROP TABLE IF EXISTS sap_fe_cap_travel_BookingStatus_texts;
DROP TABLE IF EXISTS sap_fe_cap_travel_SupplementType_texts;
DROP TABLE IF EXISTS sap_fe_cap_travel_Supplement_texts;
DROP TABLE IF EXISTS sap_common_Currencies;
DROP TABLE IF EXISTS sap_common_Countries;
DROP TABLE IF EXISTS sap_fe_cap_travel_TravelStatus;
DROP TABLE IF EXISTS sap_fe_cap_travel_BookingStatus;
DROP TABLE IF EXISTS sap_fe_cap_travel_BookingSupplement;
DROP TABLE IF EXISTS sap_fe_cap_travel_Booking;
DROP TABLE IF EXISTS sap_fe_cap_travel_Travel;
DROP TABLE IF EXISTS sap_fe_cap_travel_SupplementType;
DROP TABLE IF EXISTS sap_fe_cap_travel_TravelAgency;
DROP TABLE IF EXISTS sap_fe_cap_travel_Passenger;
DROP TABLE IF EXISTS sap_fe_cap_travel_FlightConnection;
DROP TABLE IF EXISTS sap_fe_cap_travel_Flight;
DROP TABLE IF EXISTS sap_fe_cap_travel_Supplement;
DROP TABLE IF EXISTS sap_fe_cap_travel_Airport;
DROP TABLE IF EXISTS sap_fe_cap_travel_Airline;

CREATE TABLE sap_fe_cap_travel_Airline (
  AirlineID NVARCHAR(3) NOT NULL,
  Name NVARCHAR(40),
  CurrencyCode_code NVARCHAR(3),
  AirlinePicURL NVARCHAR(255),
  PRIMARY KEY(AirlineID)
);


CREATE TABLE sap_fe_cap_travel_Airport (
  AirportID NVARCHAR(3) NOT NULL,
  Name NVARCHAR(40),
  City NVARCHAR(40),
  CountryCode_code NVARCHAR(3),
  PRIMARY KEY(AirportID)
);


CREATE TABLE sap_fe_cap_travel_Supplement (
  createdAt TIMESTAMP(7),
  createdBy NVARCHAR(255),
  LastChangedAt TIMESTAMP(7),
  LastChangedBy NVARCHAR(255),
  SupplementID NVARCHAR(10) NOT NULL,
  Price DECIMAL(16, 3),
  Type_code NVARCHAR(2),
  Description NVARCHAR(1024),
  CurrencyCode_code NVARCHAR(3),
  PRIMARY KEY(SupplementID)
);


CREATE TABLE sap_fe_cap_travel_Flight (
  AirlineID NVARCHAR(3) NOT NULL,
  FlightDate DATE NOT NULL,
  ConnectionID NVARCHAR(4) NOT NULL,
  Price DECIMAL(16, 3),
  CurrencyCode_code NVARCHAR(3),
  PlaneType NVARCHAR(10),
  MaximumSeats INTEGER,
  OccupiedSeats INTEGER,
  PRIMARY KEY(AirlineID, FlightDate, ConnectionID)
);


CREATE TABLE sap_fe_cap_travel_FlightConnection (
  ConnectionID NVARCHAR(4) NOT NULL,
  AirlineID NVARCHAR(3) NOT NULL,
  DepartureAirport_AirportID NVARCHAR(3),
  DestinationAirport_AirportID NVARCHAR(3),
  DepartureTime TIME,
  ArrivalTime TIME,
  Distance INTEGER,
  DistanceUnit NVARCHAR(3),
  PRIMARY KEY(ConnectionID, AirlineID)
);


CREATE TABLE sap_fe_cap_travel_Passenger (
  createdAt TIMESTAMP(7),
  createdBy NVARCHAR(255),
  LastChangedAt TIMESTAMP(7),
  LastChangedBy NVARCHAR(255),
  CustomerID NVARCHAR(6) NOT NULL,
  FirstName NVARCHAR(40),
  LastName NVARCHAR(40),
  Title NVARCHAR(10),
  Street NVARCHAR(60),
  PostalCode NVARCHAR(10),
  City NVARCHAR(40),
  CountryCode_code NVARCHAR(3),
  PhoneNumber NVARCHAR(30),
  EMailAddress NVARCHAR(256),
  PRIMARY KEY(CustomerID)
);


CREATE TABLE sap_fe_cap_travel_TravelAgency (
  AgencyID NVARCHAR(6) NOT NULL,
  Name NVARCHAR(80),
  Street NVARCHAR(60),
  PostalCode NVARCHAR(10),
  City NVARCHAR(40),
  CountryCode_code NVARCHAR(3),
  PhoneNumber NVARCHAR(30),
  EMailAddress NVARCHAR(256),
  WebAddress NVARCHAR(256),
  PRIMARY KEY(AgencyID)
);


CREATE TABLE sap_fe_cap_travel_SupplementType (
  name NVARCHAR(255),
  descr NVARCHAR(1000),
  code NVARCHAR(2) NOT NULL,
  PRIMARY KEY(code)
);


CREATE TABLE sap_fe_cap_travel_Travel (
  createdAt TIMESTAMP(7),
  createdBy NVARCHAR(255),
  LastChangedAt TIMESTAMP(7),
  LastChangedBy NVARCHAR(255),
  TravelUUID NVARCHAR(36) NOT NULL,
  TravelID INTEGER DEFAULT 0,
  BeginDate DATE,
  EndDate DATE,
  BookingFee DECIMAL(16, 3) DEFAULT 0,
  TotalPrice DECIMAL(16, 3),
  CurrencyCode_code NVARCHAR(3) DEFAULT 'EUR',
  Description NVARCHAR(1024),
  TravelStatus_code NVARCHAR(1) DEFAULT 'O',
  to_Agency_AgencyID NVARCHAR(6),
  to_Customer_CustomerID NVARCHAR(6),
  GoGreen BOOLEAN DEFAULT FALSE,
  GreenFee DECIMAL(16, 3),
  TreesPlanted INTEGER,
  PRIMARY KEY(TravelUUID)
);


CREATE TABLE sap_fe_cap_travel_Booking (
  createdAt TIMESTAMP(7),
  createdBy NVARCHAR(255),
  LastChangedAt TIMESTAMP(7),
  LastChangedBy NVARCHAR(255),
  BookingUUID NVARCHAR(36) NOT NULL,
  BookingID INTEGER,
  BookingDate DATE,
  ConnectionID NVARCHAR(4),
  FlightDate DATE,
  FlightPrice DECIMAL(16, 3),
  CurrencyCode_code NVARCHAR(3),
  BookingStatus_code NVARCHAR(1) DEFAULT 'N',
  to_Carrier_AirlineID NVARCHAR(3),
  to_Customer_CustomerID NVARCHAR(6),
  to_Travel_TravelUUID NVARCHAR(36),
  PRIMARY KEY(BookingUUID)
);


CREATE TABLE sap_fe_cap_travel_BookingSupplement (
  createdAt TIMESTAMP(7),
  createdBy NVARCHAR(255),
  LastChangedAt TIMESTAMP(7),
  LastChangedBy NVARCHAR(255),
  BookSupplUUID NVARCHAR(36) NOT NULL,
  BookingSupplementID INTEGER,
  Price DECIMAL(16, 3),
  CurrencyCode_code NVARCHAR(3),
  to_Booking_BookingUUID NVARCHAR(36),
  to_Travel_TravelUUID NVARCHAR(36),
  to_Supplement_SupplementID NVARCHAR(10),
  PRIMARY KEY(BookSupplUUID)
);


CREATE TABLE sap_fe_cap_travel_BookingStatus (
  name NVARCHAR(255),
  descr NVARCHAR(1000),
  code NVARCHAR(1) NOT NULL,
  PRIMARY KEY(code)
);


CREATE TABLE sap_fe_cap_travel_TravelStatus (
  name NVARCHAR(255),
  descr NVARCHAR(1000),
  code NVARCHAR(1) NOT NULL,
  fieldControl SMALLINT,
  createDeleteHidden BOOLEAN,
  insertDeleteRestriction BOOLEAN,
  PRIMARY KEY(code)
);


CREATE TABLE sap_common_Countries (
  name NVARCHAR(255),
  descr NVARCHAR(1000),
  code NVARCHAR(3) NOT NULL,
  PRIMARY KEY(code)
);


CREATE TABLE sap_common_Currencies (
  name NVARCHAR(255),
  descr NVARCHAR(1000),
  code NVARCHAR(3) NOT NULL,
  symbol NVARCHAR(5),
  minorUnit SMALLINT,
  numcode INTEGER,
  exponent INTEGER,
  minor NVARCHAR(255),
  PRIMARY KEY(code)
);


CREATE TABLE sap_fe_cap_travel_Supplement_texts (
  locale NVARCHAR(14) NOT NULL,
  SupplementID NVARCHAR(10) NOT NULL,
  Description NVARCHAR(1024),
  PRIMARY KEY(locale, SupplementID)
);


CREATE TABLE sap_fe_cap_travel_SupplementType_texts (
  locale NVARCHAR(14) NOT NULL,
  name NVARCHAR(255),
  descr NVARCHAR(1000),
  code NVARCHAR(2) NOT NULL,
  PRIMARY KEY(locale, code)
);


CREATE TABLE sap_fe_cap_travel_BookingStatus_texts (
  locale NVARCHAR(14) NOT NULL,
  name NVARCHAR(255),
  descr NVARCHAR(1000),
  code NVARCHAR(1) NOT NULL,
  PRIMARY KEY(locale, code)
);


CREATE TABLE sap_fe_cap_travel_TravelStatus_texts (
  locale NVARCHAR(14) NOT NULL,
  name NVARCHAR(255),
  descr NVARCHAR(1000),
  code NVARCHAR(1) NOT NULL,
  PRIMARY KEY(locale, code)
);


CREATE TABLE sap_common_Countries_texts (
  locale NVARCHAR(14) NOT NULL,
  name NVARCHAR(255),
  descr NVARCHAR(1000),
  code NVARCHAR(3) NOT NULL,
  PRIMARY KEY(locale, code)
);


CREATE TABLE sap_common_Currencies_texts (
  locale NVARCHAR(14) NOT NULL,
  name NVARCHAR(255),
  descr NVARCHAR(1000),
  code NVARCHAR(3) NOT NULL,
  PRIMARY KEY(locale, code)
);


CREATE TABLE DRAFT_DraftAdministrativeData (
  DraftUUID NVARCHAR(36) NOT NULL,
  CreationDateTime TIMESTAMP(7),
  CreatedByUser NVARCHAR(256),
  DraftIsCreatedByMe BOOLEAN,
  LastChangeDateTime TIMESTAMP(7),
  LastChangedByUser NVARCHAR(256),
  InProcessByUser NVARCHAR(256),
  DraftIsProcessedByMe BOOLEAN,
  PRIMARY KEY(DraftUUID)
);


CREATE TABLE TravelService_Travel_drafts (
  createdAt TIMESTAMP(7) NULL,
  createdBy NVARCHAR(255) NULL,
  LastChangedAt TIMESTAMP(7) NULL,
  LastChangedBy NVARCHAR(255) NULL,
  TravelUUID NVARCHAR(36) NOT NULL,
  TravelID INTEGER NULL DEFAULT 0,
  BeginDate DATE NULL,
  EndDate DATE NULL,
  BookingFee DECIMAL(16, 3) NULL DEFAULT 0,
  TotalPrice DECIMAL(16, 3) NULL,
  CurrencyCode_code NVARCHAR(3) NULL DEFAULT 'EUR',
  Description NVARCHAR(1024) NULL,
  TravelStatus_code NVARCHAR(1) NULL DEFAULT 'O',
  to_Agency_AgencyID NVARCHAR(6) NULL,
  to_Customer_CustomerID NVARCHAR(6) NULL,
  GoGreen BOOLEAN NULL DEFAULT FALSE,
  GreenFee DECIMAL(16, 3) NULL,
  TreesPlanted INTEGER NULL,
  IsActiveEntity BOOLEAN,
  HasActiveEntity BOOLEAN,
  HasDraftEntity BOOLEAN,
  DraftAdministrativeData_DraftUUID NVARCHAR(36) NOT NULL,
  PRIMARY KEY(TravelUUID)
);


CREATE TABLE TravelService_Booking_drafts (
  createdAt TIMESTAMP(7) NULL,
  createdBy NVARCHAR(255) NULL,
  LastChangedAt TIMESTAMP(7) NULL,
  LastChangedBy NVARCHAR(255) NULL,
  BookingUUID NVARCHAR(36) NOT NULL,
  BookingID INTEGER NULL,
  BookingDate DATE NULL,
  ConnectionID NVARCHAR(4) NULL,
  FlightDate DATE NULL,
  FlightPrice DECIMAL(16, 3) NULL,
  CurrencyCode_code NVARCHAR(3) NULL,
  BookingStatus_code NVARCHAR(1) NULL DEFAULT 'N',
  to_Carrier_AirlineID NVARCHAR(3) NULL,
  to_Customer_CustomerID NVARCHAR(6) NULL,
  to_Travel_TravelUUID NVARCHAR(36) NULL,
  IsActiveEntity BOOLEAN,
  HasActiveEntity BOOLEAN,
  HasDraftEntity BOOLEAN,
  DraftAdministrativeData_DraftUUID NVARCHAR(36) NOT NULL,
  PRIMARY KEY(BookingUUID)
);


CREATE TABLE TravelService_BookingSupplement_drafts (
  createdAt TIMESTAMP(7) NULL,
  createdBy NVARCHAR(255) NULL,
  LastChangedAt TIMESTAMP(7) NULL,
  LastChangedBy NVARCHAR(255) NULL,
  BookSupplUUID NVARCHAR(36) NOT NULL,
  BookingSupplementID INTEGER NULL,
  Price DECIMAL(16, 3) NULL,
  CurrencyCode_code NVARCHAR(3) NULL,
  to_Booking_BookingUUID NVARCHAR(36) NULL,
  to_Travel_TravelUUID NVARCHAR(36) NULL,
  to_Supplement_SupplementID NVARCHAR(10) NULL,
  IsActiveEntity BOOLEAN,
  HasActiveEntity BOOLEAN,
  HasDraftEntity BOOLEAN,
  DraftAdministrativeData_DraftUUID NVARCHAR(36) NOT NULL,
  PRIMARY KEY(BookSupplUUID)
);


CREATE VIEW AnalyticsService_Bookings AS SELECT
  Booking_0.BookingUUID AS ID,
  to_Travel_1.TravelID,
  Booking_0.BookingID,
  to_Travel_1.TravelID || '/' || Booking_0.BookingID AS CombinedID,
  Booking_0.ConnectionID,
  Booking_0.FlightDate,
  'USD' AS CurrencyCode_code,
  Booking_0.FlightPrice,
  BookingStatus_2.code AS status,
  BookingStatus_2.name AS statusName,
  to_Carrier_3.AirlineID AS airline,
  to_Carrier_3.Name AS airlineName,
  Booking_0.BookingDate,
  Booking_0.to_Travel_TravelUUID,
  Booking_0.to_Carrier_AirlineID,
  to_Flight_4.PlaneType,
  to_Connection_5.Distance,
  to_Connection_5.DistanceUnit,
  DepartureAirport_6.AirportID AS DepAirport,
  DepartureAirport_6.City AS DepCity,
  DestinationAirport_7.AirportID AS DestAirport,
  DestinationAirport_7.City AS DestCity
FROM (((((((sap_fe_cap_travel_Booking AS Booking_0 LEFT JOIN sap_fe_cap_travel_Travel AS to_Travel_1 ON Booking_0.to_Travel_TravelUUID = to_Travel_1.TravelUUID) LEFT JOIN sap_fe_cap_travel_BookingStatus AS BookingStatus_2 ON Booking_0.BookingStatus_code = BookingStatus_2.code) LEFT JOIN sap_fe_cap_travel_Airline AS to_Carrier_3 ON Booking_0.to_Carrier_AirlineID = to_Carrier_3.AirlineID) LEFT JOIN sap_fe_cap_travel_Flight AS to_Flight_4 ON to_Flight_4.AirlineID = Booking_0.to_Carrier_AirlineID AND to_Flight_4.FlightDate = Booking_0.FlightDate AND to_Flight_4.ConnectionID = Booking_0.ConnectionID) LEFT JOIN sap_fe_cap_travel_FlightConnection AS to_Connection_5 ON to_Connection_5.AirlineID = to_Flight_4.AirlineID AND to_Connection_5.ConnectionID = to_Flight_4.ConnectionID) LEFT JOIN sap_fe_cap_travel_Airport AS DepartureAirport_6 ON to_Connection_5.DepartureAirport_AirportID = DepartureAirport_6.AirportID) LEFT JOIN sap_fe_cap_travel_Airport AS DestinationAirport_7 ON to_Connection_5.DestinationAirport_AirportID = DestinationAirport_7.AirportID);


CREATE VIEW AnalyticsService_BookingStatus AS SELECT
  BookingStatus_0.name,
  BookingStatus_0.descr,
  BookingStatus_0.code
FROM sap_fe_cap_travel_BookingStatus AS BookingStatus_0;


CREATE VIEW AnalyticsService_Travels AS SELECT
  Travel_0.createdAt,
  Travel_0.createdBy,
  Travel_0.LastChangedAt,
  Travel_0.LastChangedBy,
  Travel_0.TravelUUID,
  Travel_0.TravelID,
  Travel_0.BeginDate,
  Travel_0.EndDate,
  Travel_0.BookingFee,
  Travel_0.TotalPrice,
  Travel_0.CurrencyCode_code,
  Travel_0.Description,
  Travel_0.TravelStatus_code,
  Travel_0.to_Agency_AgencyID,
  Travel_0.to_Customer_CustomerID,
  Travel_0.GoGreen,
  Travel_0.GreenFee,
  Travel_0.TreesPlanted,
  to_Customer_1.FirstName || ' ' || to_Customer_1.LastName AS CustomerName
FROM (sap_fe_cap_travel_Travel AS Travel_0 LEFT JOIN sap_fe_cap_travel_Passenger AS to_Customer_1 ON Travel_0.to_Customer_CustomerID = to_Customer_1.CustomerID);


CREATE VIEW TravelService_Travel AS SELECT
  Travel_0.createdAt,
  Travel_0.createdBy,
  Travel_0.LastChangedAt,
  Travel_0.LastChangedBy,
  Travel_0.TravelUUID,
  Travel_0.TravelID,
  Travel_0.BeginDate,
  Travel_0.EndDate,
  Travel_0.BookingFee,
  Travel_0.TotalPrice,
  Travel_0.CurrencyCode_code,
  Travel_0.Description,
  Travel_0.TravelStatus_code,
  Travel_0.to_Agency_AgencyID,
  Travel_0.to_Customer_CustomerID,
  Travel_0.GoGreen,
  Travel_0.GreenFee,
  Travel_0.TreesPlanted
FROM sap_fe_cap_travel_Travel AS Travel_0;


CREATE VIEW AnalyticsService_Airline AS SELECT
  Airline_0.AirlineID,
  Airline_0.Name,
  Airline_0.CurrencyCode_code,
  Airline_0.AirlinePicURL
FROM sap_fe_cap_travel_Airline AS Airline_0;


CREATE VIEW AnalyticsService_Flight AS SELECT
  Flight_0.AirlineID,
  Flight_0.FlightDate,
  Flight_0.ConnectionID,
  Flight_0.Price,
  Flight_0.CurrencyCode_code,
  Flight_0.PlaneType,
  Flight_0.MaximumSeats,
  Flight_0.OccupiedSeats
FROM sap_fe_cap_travel_Flight AS Flight_0;


CREATE VIEW AnalyticsService_BookingStatus_texts AS SELECT
  texts_0.locale,
  texts_0.name,
  texts_0.descr,
  texts_0.code
FROM sap_fe_cap_travel_BookingStatus_texts AS texts_0;


CREATE VIEW AnalyticsService_Currencies AS SELECT
  Currencies_0.name,
  Currencies_0.descr,
  Currencies_0.code,
  Currencies_0.symbol,
  Currencies_0.minorUnit,
  Currencies_0.numcode,
  Currencies_0.exponent,
  Currencies_0.minor
FROM sap_common_Currencies AS Currencies_0;


CREATE VIEW AnalyticsService_TravelStatus AS SELECT
  TravelStatus_0.name,
  TravelStatus_0.descr,
  TravelStatus_0.code,
  TravelStatus_0.fieldControl,
  TravelStatus_0.createDeleteHidden,
  TravelStatus_0.insertDeleteRestriction
FROM sap_fe_cap_travel_TravelStatus AS TravelStatus_0;


CREATE VIEW AnalyticsService_TravelAgency AS SELECT
  TravelAgency_0.AgencyID,
  TravelAgency_0.Name,
  TravelAgency_0.Street,
  TravelAgency_0.PostalCode,
  TravelAgency_0.City,
  TravelAgency_0.CountryCode_code,
  TravelAgency_0.PhoneNumber,
  TravelAgency_0.EMailAddress,
  TravelAgency_0.WebAddress
FROM sap_fe_cap_travel_TravelAgency AS TravelAgency_0;


CREATE VIEW AnalyticsService_Passenger AS SELECT
  Passenger_0.createdAt,
  Passenger_0.createdBy,
  Passenger_0.LastChangedAt,
  Passenger_0.LastChangedBy,
  Passenger_0.CustomerID,
  Passenger_0.FirstName,
  Passenger_0.LastName,
  Passenger_0.Title,
  Passenger_0.Street,
  Passenger_0.PostalCode,
  Passenger_0.City,
  Passenger_0.CountryCode_code,
  Passenger_0.PhoneNumber,
  Passenger_0.EMailAddress
FROM sap_fe_cap_travel_Passenger AS Passenger_0;


CREATE VIEW TravelService_Currencies AS SELECT
  Currencies_0.name,
  Currencies_0.descr,
  Currencies_0.code,
  Currencies_0.symbol,
  Currencies_0.minorUnit,
  Currencies_0.numcode,
  Currencies_0.exponent,
  Currencies_0.minor
FROM sap_common_Currencies AS Currencies_0;


CREATE VIEW TravelService_TravelStatus AS SELECT
  TravelStatus_0.name,
  TravelStatus_0.descr,
  TravelStatus_0.code,
  TravelStatus_0.fieldControl,
  TravelStatus_0.createDeleteHidden,
  TravelStatus_0.insertDeleteRestriction
FROM sap_fe_cap_travel_TravelStatus AS TravelStatus_0;


CREATE VIEW TravelService_TravelAgency AS SELECT
  TravelAgency_0.AgencyID,
  TravelAgency_0.Name,
  TravelAgency_0.Street,
  TravelAgency_0.PostalCode,
  TravelAgency_0.City,
  TravelAgency_0.CountryCode_code,
  TravelAgency_0.PhoneNumber,
  TravelAgency_0.EMailAddress,
  TravelAgency_0.WebAddress
FROM sap_fe_cap_travel_TravelAgency AS TravelAgency_0;


CREATE VIEW TravelService_Passenger AS SELECT
  Passenger_0.createdAt,
  Passenger_0.createdBy,
  Passenger_0.LastChangedAt,
  Passenger_0.LastChangedBy,
  Passenger_0.CustomerID,
  Passenger_0.FirstName,
  Passenger_0.LastName,
  Passenger_0.Title,
  Passenger_0.Street,
  Passenger_0.PostalCode,
  Passenger_0.City,
  Passenger_0.CountryCode_code,
  Passenger_0.PhoneNumber,
  Passenger_0.EMailAddress
FROM sap_fe_cap_travel_Passenger AS Passenger_0;


CREATE VIEW TravelService_Booking AS SELECT
  Booking_0.createdAt,
  Booking_0.createdBy,
  Booking_0.LastChangedAt,
  Booking_0.LastChangedBy,
  Booking_0.BookingUUID,
  Booking_0.BookingID,
  Booking_0.BookingDate,
  Booking_0.ConnectionID,
  Booking_0.FlightDate,
  Booking_0.FlightPrice,
  Booking_0.CurrencyCode_code,
  Booking_0.BookingStatus_code,
  Booking_0.to_Carrier_AirlineID,
  Booking_0.to_Customer_CustomerID,
  Booking_0.to_Travel_TravelUUID
FROM sap_fe_cap_travel_Booking AS Booking_0;


CREATE VIEW AnalyticsService_FlightConnection AS SELECT
  FlightConnection_0.ConnectionID,
  FlightConnection_0.AirlineID,
  FlightConnection_0.DepartureAirport_AirportID,
  FlightConnection_0.DestinationAirport_AirportID,
  FlightConnection_0.DepartureTime,
  FlightConnection_0.ArrivalTime,
  FlightConnection_0.Distance,
  FlightConnection_0.DistanceUnit
FROM sap_fe_cap_travel_FlightConnection AS FlightConnection_0;


CREATE VIEW AnalyticsService_Currencies_texts AS SELECT
  texts_0.locale,
  texts_0.name,
  texts_0.descr,
  texts_0.code
FROM sap_common_Currencies_texts AS texts_0;


CREATE VIEW AnalyticsService_TravelStatus_texts AS SELECT
  texts_0.locale,
  texts_0.name,
  texts_0.descr,
  texts_0.code
FROM sap_fe_cap_travel_TravelStatus_texts AS texts_0;


CREATE VIEW AnalyticsService_Countries AS SELECT
  Countries_0.name,
  Countries_0.descr,
  Countries_0.code
FROM sap_common_Countries AS Countries_0;


CREATE VIEW TravelService_Currencies_texts AS SELECT
  texts_0.locale,
  texts_0.name,
  texts_0.descr,
  texts_0.code
FROM sap_common_Currencies_texts AS texts_0;


CREATE VIEW TravelService_TravelStatus_texts AS SELECT
  texts_0.locale,
  texts_0.name,
  texts_0.descr,
  texts_0.code
FROM sap_fe_cap_travel_TravelStatus_texts AS texts_0;


CREATE VIEW TravelService_Countries AS SELECT
  Countries_0.name,
  Countries_0.descr,
  Countries_0.code
FROM sap_common_Countries AS Countries_0;


CREATE VIEW TravelService_BookingStatus AS SELECT
  BookingStatus_0.name,
  BookingStatus_0.descr,
  BookingStatus_0.code
FROM sap_fe_cap_travel_BookingStatus AS BookingStatus_0;


CREATE VIEW TravelService_BookingSupplement AS SELECT
  BookingSupplement_0.createdAt,
  BookingSupplement_0.createdBy,
  BookingSupplement_0.LastChangedAt,
  BookingSupplement_0.LastChangedBy,
  BookingSupplement_0.BookSupplUUID,
  BookingSupplement_0.BookingSupplementID,
  BookingSupplement_0.Price,
  BookingSupplement_0.CurrencyCode_code,
  BookingSupplement_0.to_Booking_BookingUUID,
  BookingSupplement_0.to_Travel_TravelUUID,
  BookingSupplement_0.to_Supplement_SupplementID
FROM sap_fe_cap_travel_BookingSupplement AS BookingSupplement_0;


CREATE VIEW TravelService_Airline AS SELECT
  Airline_0.AirlineID,
  Airline_0.Name,
  Airline_0.CurrencyCode_code,
  Airline_0.AirlinePicURL
FROM sap_fe_cap_travel_Airline AS Airline_0;


CREATE VIEW TravelService_Flight AS SELECT
  Flight_0.AirlineID,
  Flight_0.FlightDate,
  Flight_0.ConnectionID,
  Flight_0.Price,
  Flight_0.CurrencyCode_code,
  Flight_0.PlaneType,
  Flight_0.MaximumSeats,
  Flight_0.OccupiedSeats
FROM sap_fe_cap_travel_Flight AS Flight_0;


CREATE VIEW AnalyticsService_Airport AS SELECT
  Airport_0.AirportID,
  Airport_0.Name,
  Airport_0.City,
  Airport_0.CountryCode_code
FROM sap_fe_cap_travel_Airport AS Airport_0;


CREATE VIEW AnalyticsService_Countries_texts AS SELECT
  texts_0.locale,
  texts_0.name,
  texts_0.descr,
  texts_0.code
FROM sap_common_Countries_texts AS texts_0;


CREATE VIEW TravelService_Countries_texts AS SELECT
  texts_0.locale,
  texts_0.name,
  texts_0.descr,
  texts_0.code
FROM sap_common_Countries_texts AS texts_0;


CREATE VIEW TravelService_BookingStatus_texts AS SELECT
  texts_0.locale,
  texts_0.name,
  texts_0.descr,
  texts_0.code
FROM sap_fe_cap_travel_BookingStatus_texts AS texts_0;


CREATE VIEW TravelService_Supplement AS SELECT
  Supplement_0.createdAt,
  Supplement_0.createdBy,
  Supplement_0.LastChangedAt,
  Supplement_0.LastChangedBy,
  Supplement_0.SupplementID,
  Supplement_0.Price,
  Supplement_0.Type_code,
  Supplement_0.Description,
  Supplement_0.CurrencyCode_code
FROM sap_fe_cap_travel_Supplement AS Supplement_0;


CREATE VIEW TravelService_FlightConnection AS SELECT
  FlightConnection_0.ConnectionID,
  FlightConnection_0.AirlineID,
  FlightConnection_0.DepartureAirport_AirportID,
  FlightConnection_0.DestinationAirport_AirportID,
  FlightConnection_0.DepartureTime,
  FlightConnection_0.ArrivalTime,
  FlightConnection_0.Distance,
  FlightConnection_0.DistanceUnit
FROM sap_fe_cap_travel_FlightConnection AS FlightConnection_0;


CREATE VIEW TravelService_SupplementType AS SELECT
  SupplementType_0.name,
  SupplementType_0.descr,
  SupplementType_0.code
FROM sap_fe_cap_travel_SupplementType AS SupplementType_0;


CREATE VIEW TravelService_Supplement_texts AS SELECT
  texts_0.locale,
  texts_0.SupplementID,
  texts_0.Description
FROM sap_fe_cap_travel_Supplement_texts AS texts_0;


CREATE VIEW TravelService_Airport AS SELECT
  Airport_0.AirportID,
  Airport_0.Name,
  Airport_0.City,
  Airport_0.CountryCode_code
FROM sap_fe_cap_travel_Airport AS Airport_0;


CREATE VIEW TravelService_SupplementType_texts AS SELECT
  texts_0.locale,
  texts_0.name,
  texts_0.descr,
  texts_0.code
FROM sap_fe_cap_travel_SupplementType_texts AS texts_0;


CREATE VIEW localized_sap_fe_cap_travel_Supplement AS SELECT
  L_0.createdAt,
  L_0.createdBy,
  L_0.LastChangedAt,
  L_0.LastChangedBy,
  L_0.SupplementID,
  L_0.Price,
  L_0.Type_code,
  coalesce(localized_1.Description, L_0.Description) AS Description,
  L_0.CurrencyCode_code
FROM (sap_fe_cap_travel_Supplement AS L_0 LEFT JOIN sap_fe_cap_travel_Supplement_texts AS localized_1 ON localized_1.SupplementID = L_0.SupplementID AND localized_1.locale = @locale);


CREATE VIEW localized_sap_fe_cap_travel_SupplementType AS SELECT
  coalesce(localized_1.name, L_0.name) AS name,
  coalesce(localized_1.descr, L_0.descr) AS descr,
  L_0.code
FROM (sap_fe_cap_travel_SupplementType AS L_0 LEFT JOIN sap_fe_cap_travel_SupplementType_texts AS localized_1 ON localized_1.code = L_0.code AND localized_1.locale = @locale);


CREATE VIEW localized_sap_fe_cap_travel_BookingStatus AS SELECT
  coalesce(localized_1.name, L_0.name) AS name,
  coalesce(localized_1.descr, L_0.descr) AS descr,
  L_0.code
FROM (sap_fe_cap_travel_BookingStatus AS L_0 LEFT JOIN sap_fe_cap_travel_BookingStatus_texts AS localized_1 ON localized_1.code = L_0.code AND localized_1.locale = @locale);


CREATE VIEW localized_sap_fe_cap_travel_TravelStatus AS SELECT
  coalesce(localized_1.name, L_0.name) AS name,
  coalesce(localized_1.descr, L_0.descr) AS descr,
  L_0.code,
  L_0.fieldControl,
  L_0.createDeleteHidden,
  L_0.insertDeleteRestriction
FROM (sap_fe_cap_travel_TravelStatus AS L_0 LEFT JOIN sap_fe_cap_travel_TravelStatus_texts AS localized_1 ON localized_1.code = L_0.code AND localized_1.locale = @locale);


CREATE VIEW localized_sap_common_Countries AS SELECT
  coalesce(localized_1.name, L_0.name) AS name,
  coalesce(localized_1.descr, L_0.descr) AS descr,
  L_0.code
FROM (sap_common_Countries AS L_0 LEFT JOIN sap_common_Countries_texts AS localized_1 ON localized_1.code = L_0.code AND localized_1.locale = @locale);


CREATE VIEW localized_sap_common_Currencies AS SELECT
  coalesce(localized_1.name, L_0.name) AS name,
  coalesce(localized_1.descr, L_0.descr) AS descr,
  L_0.code,
  L_0.symbol,
  L_0.minorUnit,
  L_0.numcode,
  L_0.exponent,
  L_0.minor
FROM (sap_common_Currencies AS L_0 LEFT JOIN sap_common_Currencies_texts AS localized_1 ON localized_1.code = L_0.code AND localized_1.locale = @locale);


CREATE VIEW TravelService_DraftAdministrativeData AS SELECT
  DraftAdministrativeData.DraftUUID,
  DraftAdministrativeData.CreationDateTime,
  DraftAdministrativeData.CreatedByUser,
  DraftAdministrativeData.DraftIsCreatedByMe,
  DraftAdministrativeData.LastChangeDateTime,
  DraftAdministrativeData.LastChangedByUser,
  DraftAdministrativeData.InProcessByUser,
  DraftAdministrativeData.DraftIsProcessedByMe
FROM DRAFT_DraftAdministrativeData AS DraftAdministrativeData;


CREATE VIEW localized_AnalyticsService_Bookings AS SELECT
  Booking_0.BookingUUID AS ID,
  to_Travel_1.TravelID,
  Booking_0.BookingID,
  to_Travel_1.TravelID || '/' || Booking_0.BookingID AS CombinedID,
  Booking_0.ConnectionID,
  Booking_0.FlightDate,
  'USD' AS CurrencyCode_code,
  Booking_0.FlightPrice,
  BookingStatus_2.code AS status,
  BookingStatus_2.name AS statusName,
  to_Carrier_3.AirlineID AS airline,
  to_Carrier_3.Name AS airlineName,
  Booking_0.BookingDate,
  Booking_0.to_Travel_TravelUUID,
  Booking_0.to_Carrier_AirlineID,
  to_Flight_4.PlaneType,
  to_Connection_5.Distance,
  to_Connection_5.DistanceUnit,
  DepartureAirport_6.AirportID AS DepAirport,
  DepartureAirport_6.City AS DepCity,
  DestinationAirport_7.AirportID AS DestAirport,
  DestinationAirport_7.City AS DestCity
FROM (((((((sap_fe_cap_travel_Booking AS Booking_0 LEFT JOIN sap_fe_cap_travel_Travel AS to_Travel_1 ON Booking_0.to_Travel_TravelUUID = to_Travel_1.TravelUUID) LEFT JOIN localized_sap_fe_cap_travel_BookingStatus AS BookingStatus_2 ON Booking_0.BookingStatus_code = BookingStatus_2.code) LEFT JOIN sap_fe_cap_travel_Airline AS to_Carrier_3 ON Booking_0.to_Carrier_AirlineID = to_Carrier_3.AirlineID) LEFT JOIN sap_fe_cap_travel_Flight AS to_Flight_4 ON to_Flight_4.AirlineID = Booking_0.to_Carrier_AirlineID AND to_Flight_4.FlightDate = Booking_0.FlightDate AND to_Flight_4.ConnectionID = Booking_0.ConnectionID) LEFT JOIN sap_fe_cap_travel_FlightConnection AS to_Connection_5 ON to_Connection_5.AirlineID = to_Flight_4.AirlineID AND to_Connection_5.ConnectionID = to_Flight_4.ConnectionID) LEFT JOIN sap_fe_cap_travel_Airport AS DepartureAirport_6 ON to_Connection_5.DepartureAirport_AirportID = DepartureAirport_6.AirportID) LEFT JOIN sap_fe_cap_travel_Airport AS DestinationAirport_7 ON to_Connection_5.DestinationAirport_AirportID = DestinationAirport_7.AirportID);


CREATE VIEW localized_AnalyticsService_BookingStatus AS SELECT
  BookingStatus_0.name,
  BookingStatus_0.descr,
  BookingStatus_0.code
FROM localized_sap_fe_cap_travel_BookingStatus AS BookingStatus_0;


CREATE VIEW localized_AnalyticsService_Currencies AS SELECT
  Currencies_0.name,
  Currencies_0.descr,
  Currencies_0.code,
  Currencies_0.symbol,
  Currencies_0.minorUnit,
  Currencies_0.numcode,
  Currencies_0.exponent,
  Currencies_0.minor
FROM localized_sap_common_Currencies AS Currencies_0;


CREATE VIEW localized_AnalyticsService_TravelStatus AS SELECT
  TravelStatus_0.name,
  TravelStatus_0.descr,
  TravelStatus_0.code,
  TravelStatus_0.fieldControl,
  TravelStatus_0.createDeleteHidden,
  TravelStatus_0.insertDeleteRestriction
FROM localized_sap_fe_cap_travel_TravelStatus AS TravelStatus_0;


CREATE VIEW localized_TravelService_Currencies AS SELECT
  Currencies_0.name,
  Currencies_0.descr,
  Currencies_0.code,
  Currencies_0.symbol,
  Currencies_0.minorUnit,
  Currencies_0.numcode,
  Currencies_0.exponent,
  Currencies_0.minor
FROM localized_sap_common_Currencies AS Currencies_0;


CREATE VIEW localized_TravelService_TravelStatus AS SELECT
  TravelStatus_0.name,
  TravelStatus_0.descr,
  TravelStatus_0.code,
  TravelStatus_0.fieldControl,
  TravelStatus_0.createDeleteHidden,
  TravelStatus_0.insertDeleteRestriction
FROM localized_sap_fe_cap_travel_TravelStatus AS TravelStatus_0;


CREATE VIEW localized_AnalyticsService_Countries AS SELECT
  Countries_0.name,
  Countries_0.descr,
  Countries_0.code
FROM localized_sap_common_Countries AS Countries_0;


CREATE VIEW localized_TravelService_Countries AS SELECT
  Countries_0.name,
  Countries_0.descr,
  Countries_0.code
FROM localized_sap_common_Countries AS Countries_0;


CREATE VIEW localized_TravelService_BookingStatus AS SELECT
  BookingStatus_0.name,
  BookingStatus_0.descr,
  BookingStatus_0.code
FROM localized_sap_fe_cap_travel_BookingStatus AS BookingStatus_0;


CREATE VIEW localized_TravelService_Supplement AS SELECT
  Supplement_0.createdAt,
  Supplement_0.createdBy,
  Supplement_0.LastChangedAt,
  Supplement_0.LastChangedBy,
  Supplement_0.SupplementID,
  Supplement_0.Price,
  Supplement_0.Type_code,
  Supplement_0.Description,
  Supplement_0.CurrencyCode_code
FROM localized_sap_fe_cap_travel_Supplement AS Supplement_0;


CREATE VIEW localized_TravelService_SupplementType AS SELECT
  SupplementType_0.name,
  SupplementType_0.descr,
  SupplementType_0.code
FROM localized_sap_fe_cap_travel_SupplementType AS SupplementType_0;

