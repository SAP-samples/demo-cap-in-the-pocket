package cds.gen.analyticsservice;

import com.sap.cds.CdsData;
import com.sap.cds.Struct;
import com.sap.cds.ql.CdsName;
import java.lang.Integer;
import java.lang.Object;
import java.lang.String;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;
import javax.annotation.processing.Generated;

@CdsName("AnalyticsService.Bookings")
@Generated(
    value = "cds-maven-plugin",
    date = "2025-05-08T06:34:56.366350Z",
    comments = "com.sap.cds:cds-maven-plugin:3.9.1 / com.sap.cds:cds4j-api:3.9.1"
)
public interface Bookings extends CdsData {
  String ID = "ID";

  String TRAVEL_ID = "TravelID";

  String BOOKING_ID = "BookingID";

  String COMBINED_ID = "CombinedID";

  String CONNECTION_ID = "ConnectionID";

  String FLIGHT_DATE = "FlightDate";

  String CURRENCY_CODE_CODE = "CurrencyCode_code";

  String FLIGHT_PRICE = "FlightPrice";

  String STATUS = "status";

  String STATUS_NAME = "statusName";

  String AIRLINE = "airline";

  String AIRLINE_NAME = "airlineName";

  String BOOKING_DATE = "BookingDate";

  String TO_TRAVEL = "to_Travel";

  String TO_TRAVEL_TRAVEL_UUID = "to_Travel_TravelUUID";

  String TO_CARRIER = "to_Carrier";

  String TO_CARRIER_AIRLINE_ID = "to_Carrier_AirlineID";

  String TO_FLIGHT = "to_Flight";

  String PLANE_TYPE = "PlaneType";

  String DISTANCE = "Distance";

  String DISTANCE_UNIT = "DistanceUnit";

  String DEP_AIRPORT = "DepAirport";

  String DEP_CITY = "DepCity";

  String DEST_AIRPORT = "DestAirport";

  String DEST_CITY = "DestCity";

  @CdsName(ID)
  String id();

  @CdsName(ID)
  Bookings id(String id);

  @CdsName(TRAVEL_ID)
  Integer travelID();

  @CdsName(TRAVEL_ID)
  Bookings travelID(Integer travelID);

  @CdsName(BOOKING_ID)
  Integer bookingID();

  @CdsName(BOOKING_ID)
  Bookings bookingID(Integer bookingID);

  @CdsName(COMBINED_ID)
  String combinedID();

  @CdsName(COMBINED_ID)
  Bookings combinedID(String combinedID);

  @CdsName(CONNECTION_ID)
  String connectionID();

  @CdsName(CONNECTION_ID)
  Bookings connectionID(String connectionID);

  @CdsName(FLIGHT_DATE)
  LocalDate flightDate();

  @CdsName(FLIGHT_DATE)
  Bookings flightDate(LocalDate flightDate);

  @CdsName(CURRENCY_CODE_CODE)
  String currencyCodeCode();

  @CdsName(CURRENCY_CODE_CODE)
  Bookings currencyCodeCode(String currencyCodeCode);

  @CdsName(FLIGHT_PRICE)
  BigDecimal flightPrice();

  @CdsName(FLIGHT_PRICE)
  Bookings flightPrice(BigDecimal flightPrice);

  String status();

  Bookings status(String status);

  String statusName();

  Bookings statusName(String statusName);

  String airline();

  Bookings airline(String airline);

  String airlineName();

  Bookings airlineName(String airlineName);

  @CdsName(BOOKING_DATE)
  LocalDate bookingDate();

  @CdsName(BOOKING_DATE)
  Bookings bookingDate(LocalDate bookingDate);

  @CdsName(TO_TRAVEL)
  Travels toTravel();

  @CdsName(TO_TRAVEL)
  Bookings toTravel(Map<String, ?> toTravel);

  @CdsName(TO_TRAVEL_TRAVEL_UUID)
  String toTravelTravelUUID();

  @CdsName(TO_TRAVEL_TRAVEL_UUID)
  Bookings toTravelTravelUUID(String toTravelTravelUUID);

  @CdsName(TO_CARRIER)
  Airline toCarrier();

  @CdsName(TO_CARRIER)
  Bookings toCarrier(Map<String, ?> toCarrier);

  @CdsName(TO_CARRIER_AIRLINE_ID)
  String toCarrierAirlineID();

  @CdsName(TO_CARRIER_AIRLINE_ID)
  Bookings toCarrierAirlineID(String toCarrierAirlineID);

  @CdsName(TO_FLIGHT)
  Flight toFlight();

  @CdsName(TO_FLIGHT)
  Bookings toFlight(Map<String, ?> toFlight);

  @CdsName(PLANE_TYPE)
  String planeType();

  @CdsName(PLANE_TYPE)
  Bookings planeType(String planeType);

  @CdsName(DISTANCE)
  Integer distance();

  @CdsName(DISTANCE)
  Bookings distance(Integer distance);

  @CdsName(DISTANCE_UNIT)
  String distanceUnit();

  @CdsName(DISTANCE_UNIT)
  Bookings distanceUnit(String distanceUnit);

  @CdsName(DEP_AIRPORT)
  String depAirport();

  @CdsName(DEP_AIRPORT)
  Bookings depAirport(String depAirport);

  @CdsName(DEP_CITY)
  String depCity();

  @CdsName(DEP_CITY)
  Bookings depCity(String depCity);

  @CdsName(DEST_AIRPORT)
  String destAirport();

  @CdsName(DEST_AIRPORT)
  Bookings destAirport(String destAirport);

  @CdsName(DEST_CITY)
  String destCity();

  @CdsName(DEST_CITY)
  Bookings destCity(String destCity);

  Bookings_ ref();

  static Bookings create() {
    return Struct.create(Bookings.class);
  }

  static Bookings of(Map<String, Object> map) {
    return Struct.access(map).as(Bookings.class);
  }

  static Bookings create(String id) {
    Map<String, Object> keys = new HashMap<>();
    keys.put(ID, id);
    return Struct.access(keys).as(Bookings.class);
  }
}
