package cds.gen.analyticsservice;

import com.sap.cds.ql.CdsName;
import com.sap.cds.ql.ElementRef;
import com.sap.cds.ql.StructuredType;
import com.sap.cds.ql.cqn.CqnPredicate;
import java.lang.Integer;
import java.lang.String;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.function.Function;
import javax.annotation.processing.Generated;

@CdsName("AnalyticsService.Bookings")
@Generated(
    value = "cds-maven-plugin",
    date = "2025-05-08T06:34:56.366350Z",
    comments = "com.sap.cds:cds-maven-plugin:3.9.1 / com.sap.cds:cds4j-api:3.9.1"
)
public interface Bookings_ extends StructuredType<Bookings_> {
  String ID = "ID";

  String TRAVEL_ID = "TravelID";

  String BOOKING_ID = "BookingID";

  String COMBINED_ID = "CombinedID";

  String CONNECTION_ID = "ConnectionID";

  String FLIGHT_DATE = "FlightDate";

  String CURRENCY_CODE_CODE = "CurrencyCode_code";

  String FLIGHT_PRICE = "FlightPrice";

  String BOOKING_DATE = "BookingDate";

  String TO_TRAVEL_TRAVEL_UUID = "to_Travel_TravelUUID";

  String TO_CARRIER_AIRLINE_ID = "to_Carrier_AirlineID";

  String PLANE_TYPE = "PlaneType";

  String DISTANCE = "Distance";

  String DISTANCE_UNIT = "DistanceUnit";

  String DEP_AIRPORT = "DepAirport";

  String DEP_CITY = "DepCity";

  String DEST_AIRPORT = "DestAirport";

  String DEST_CITY = "DestCity";

  String CDS_NAME = "AnalyticsService.Bookings";

  @CdsName(ID)
  ElementRef<String> ID();

  @CdsName(TRAVEL_ID)
  ElementRef<Integer> TravelID();

  @CdsName(BOOKING_ID)
  ElementRef<Integer> BookingID();

  @CdsName(COMBINED_ID)
  ElementRef<String> CombinedID();

  @CdsName(CONNECTION_ID)
  ElementRef<String> ConnectionID();

  @CdsName(FLIGHT_DATE)
  ElementRef<LocalDate> FlightDate();

  @CdsName(CURRENCY_CODE_CODE)
  ElementRef<String> CurrencyCode_code();

  @CdsName(FLIGHT_PRICE)
  ElementRef<BigDecimal> FlightPrice();

  ElementRef<String> status();

  ElementRef<String> statusName();

  ElementRef<String> airline();

  ElementRef<String> airlineName();

  @CdsName(BOOKING_DATE)
  ElementRef<LocalDate> BookingDate();

  Travels_ to_Travel();

  Travels_ to_Travel(Function<Travels_, CqnPredicate> filter);

  @CdsName(TO_TRAVEL_TRAVEL_UUID)
  ElementRef<String> to_Travel_TravelUUID();

  Airline_ to_Carrier();

  Airline_ to_Carrier(Function<Airline_, CqnPredicate> filter);

  @CdsName(TO_CARRIER_AIRLINE_ID)
  ElementRef<String> to_Carrier_AirlineID();

  Flight_ to_Flight();

  Flight_ to_Flight(Function<Flight_, CqnPredicate> filter);

  @CdsName(PLANE_TYPE)
  ElementRef<String> PlaneType();

  @CdsName(DISTANCE)
  ElementRef<Integer> Distance();

  @CdsName(DISTANCE_UNIT)
  ElementRef<String> DistanceUnit();

  @CdsName(DEP_AIRPORT)
  ElementRef<String> DepAirport();

  @CdsName(DEP_CITY)
  ElementRef<String> DepCity();

  @CdsName(DEST_AIRPORT)
  ElementRef<String> DestAirport();

  @CdsName(DEST_CITY)
  ElementRef<String> DestCity();
}
