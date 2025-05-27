package cds.gen.analyticsservice;

import com.sap.cds.CdsData;
import com.sap.cds.Struct;
import com.sap.cds.ql.CdsName;
import java.lang.Integer;
import java.lang.Object;
import java.lang.String;
import java.time.LocalTime;
import java.util.Map;
import javax.annotation.processing.Generated;

@CdsName("AnalyticsService.FlightConnection")
@Generated(
    value = "cds-maven-plugin",
    date = "2025-05-08T06:34:56.366350Z",
    comments = "com.sap.cds:cds-maven-plugin:3.9.1 / com.sap.cds:cds4j-api:3.9.1"
)
public interface FlightConnection extends CdsData {
  String CONNECTION_ID = "ConnectionID";

  String AIRLINE_ID = "AirlineID";

  String DEPARTURE_AIRPORT = "DepartureAirport";

  String DEPARTURE_AIRPORT_AIRPORT_ID = "DepartureAirport_AirportID";

  String DESTINATION_AIRPORT = "DestinationAirport";

  String DESTINATION_AIRPORT_AIRPORT_ID = "DestinationAirport_AirportID";

  String DEPARTURE_TIME = "DepartureTime";

  String ARRIVAL_TIME = "ArrivalTime";

  String DISTANCE = "Distance";

  String DISTANCE_UNIT = "DistanceUnit";

  String TO_AIRLINE = "to_Airline";

  @CdsName(CONNECTION_ID)
  String connectionID();

  @CdsName(CONNECTION_ID)
  FlightConnection connectionID(String connectionID);

  @CdsName(AIRLINE_ID)
  String airlineID();

  @CdsName(AIRLINE_ID)
  FlightConnection airlineID(String airlineID);

  @CdsName(DEPARTURE_AIRPORT)
  Airport departureAirport();

  @CdsName(DEPARTURE_AIRPORT)
  FlightConnection departureAirport(Map<String, ?> departureAirport);

  @CdsName(DEPARTURE_AIRPORT_AIRPORT_ID)
  String departureAirportAirportID();

  @CdsName(DEPARTURE_AIRPORT_AIRPORT_ID)
  FlightConnection departureAirportAirportID(String departureAirportAirportID);

  @CdsName(DESTINATION_AIRPORT)
  Airport destinationAirport();

  @CdsName(DESTINATION_AIRPORT)
  FlightConnection destinationAirport(Map<String, ?> destinationAirport);

  @CdsName(DESTINATION_AIRPORT_AIRPORT_ID)
  String destinationAirportAirportID();

  @CdsName(DESTINATION_AIRPORT_AIRPORT_ID)
  FlightConnection destinationAirportAirportID(String destinationAirportAirportID);

  @CdsName(DEPARTURE_TIME)
  LocalTime departureTime();

  @CdsName(DEPARTURE_TIME)
  FlightConnection departureTime(LocalTime departureTime);

  @CdsName(ARRIVAL_TIME)
  LocalTime arrivalTime();

  @CdsName(ARRIVAL_TIME)
  FlightConnection arrivalTime(LocalTime arrivalTime);

  @CdsName(DISTANCE)
  Integer distance();

  @CdsName(DISTANCE)
  FlightConnection distance(Integer distance);

  @CdsName(DISTANCE_UNIT)
  String distanceUnit();

  @CdsName(DISTANCE_UNIT)
  FlightConnection distanceUnit(String distanceUnit);

  @CdsName(TO_AIRLINE)
  Airline toAirline();

  @CdsName(TO_AIRLINE)
  FlightConnection toAirline(Map<String, ?> toAirline);

  FlightConnection_ ref();

  static FlightConnection create() {
    return Struct.create(FlightConnection.class);
  }

  static FlightConnection of(Map<String, Object> map) {
    return Struct.access(map).as(FlightConnection.class);
  }
}
