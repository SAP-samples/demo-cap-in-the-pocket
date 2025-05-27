package cds.gen.sap.fe.cap.travel;

import com.sap.cds.ql.CdsName;
import java.lang.Class;
import java.lang.String;
import javax.annotation.processing.Generated;

@Generated(
    value = "cds-maven-plugin",
    date = "2025-05-08T06:34:56.366350Z",
    comments = "com.sap.cds:cds-maven-plugin:3.9.1 / com.sap.cds:cds4j-api:3.9.1"
)
@CdsName("sap.fe.cap.travel")
public interface TravelModel_ {
  String CDS_NAME = "sap.fe.cap.travel";

  Class<Airport_> AIRPORT = Airport_.class;

  Class<Airline_> AIRLINE = Airline_.class;

  Class<Supplement_> SUPPLEMENT = Supplement_.class;

  Class<BookingSupplement_> BOOKING_SUPPLEMENT = BookingSupplement_.class;

  Class<TravelAgency_> TRAVEL_AGENCY = TravelAgency_.class;

  Class<BookingStatus_> BOOKING_STATUS = BookingStatus_.class;

  Class<SupplementTypeTexts_> SUPPLEMENT_TYPE_TEXTS = SupplementTypeTexts_.class;

  Class<Flight_> FLIGHT = Flight_.class;

  Class<Passenger_> PASSENGER = Passenger_.class;

  Class<SupplementType_> SUPPLEMENT_TYPE = SupplementType_.class;

  Class<Travel_> TRAVEL = Travel_.class;

  Class<TravelStatusTexts_> TRAVEL_STATUS_TEXTS = TravelStatusTexts_.class;

  Class<BookingStatusTexts_> BOOKING_STATUS_TEXTS = BookingStatusTexts_.class;

  Class<TravelStatus_> TRAVEL_STATUS = TravelStatus_.class;

  Class<Booking_> BOOKING = Booking_.class;

  Class<FlightConnection_> FLIGHT_CONNECTION = FlightConnection_.class;

  Class<SupplementTexts_> SUPPLEMENT_TEXTS = SupplementTexts_.class;
}
