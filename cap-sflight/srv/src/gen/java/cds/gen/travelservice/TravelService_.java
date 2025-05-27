package cds.gen.travelservice;

import com.sap.cds.ql.CdsName;
import java.lang.Class;
import java.lang.String;
import javax.annotation.processing.Generated;

@Generated(
    value = "cds-maven-plugin",
    date = "2025-05-08T06:34:56.366350Z",
    comments = "com.sap.cds:cds-maven-plugin:3.9.1 / com.sap.cds:cds4j-api:3.9.1"
)
@CdsName("TravelService")
public interface TravelService_ {
  String CDS_NAME = "TravelService";

  Class<Airline_> AIRLINE = Airline_.class;

  Class<Travel_> TRAVEL = Travel_.class;

  Class<Supplement_> SUPPLEMENT = Supplement_.class;

  Class<DraftAdministrativeData_> DRAFT_ADMINISTRATIVE_DATA = DraftAdministrativeData_.class;

  Class<BookingSupplement_> BOOKING_SUPPLEMENT = BookingSupplement_.class;

  Class<SupplementTypeTexts_> SUPPLEMENT_TYPE_TEXTS = SupplementTypeTexts_.class;

  Class<Airport_> AIRPORT = Airport_.class;

  Class<CurrenciesTexts_> CURRENCIES_TEXTS = CurrenciesTexts_.class;

  Class<Countries_> COUNTRIES = Countries_.class;

  Class<TravelAgency_> TRAVEL_AGENCY = TravelAgency_.class;

  Class<BookingStatusTexts_> BOOKING_STATUS_TEXTS = BookingStatusTexts_.class;

  Class<BookingStatus_> BOOKING_STATUS = BookingStatus_.class;

  Class<CountriesTexts_> COUNTRIES_TEXTS = CountriesTexts_.class;

  Class<SupplementType_> SUPPLEMENT_TYPE = SupplementType_.class;

  Class<Passenger_> PASSENGER = Passenger_.class;

  Class<TravelStatus_> TRAVEL_STATUS = TravelStatus_.class;

  Class<Booking_> BOOKING = Booking_.class;

  Class<Currencies_> CURRENCIES = Currencies_.class;

  Class<TravelStatusTexts_> TRAVEL_STATUS_TEXTS = TravelStatusTexts_.class;

  Class<FlightConnection_> FLIGHT_CONNECTION = FlightConnection_.class;

  Class<SupplementTexts_> SUPPLEMENT_TEXTS = SupplementTexts_.class;

  Class<Flight_> FLIGHT = Flight_.class;
}
