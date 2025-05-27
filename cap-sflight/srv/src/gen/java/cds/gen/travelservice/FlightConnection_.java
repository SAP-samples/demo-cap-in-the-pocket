package cds.gen.travelservice;

import com.sap.cds.ql.CdsName;
import com.sap.cds.ql.ElementRef;
import com.sap.cds.ql.StructuredType;
import com.sap.cds.ql.cqn.CqnPredicate;
import java.lang.Integer;
import java.lang.String;
import java.time.LocalTime;
import java.util.function.Function;
import javax.annotation.processing.Generated;

@CdsName("TravelService.FlightConnection")
@Generated(
    value = "cds-maven-plugin",
    date = "2025-05-08T06:34:56.366350Z",
    comments = "com.sap.cds:cds-maven-plugin:3.9.1 / com.sap.cds:cds4j-api:3.9.1"
)
public interface FlightConnection_ extends StructuredType<FlightConnection_> {
  String CONNECTION_ID = "ConnectionID";

  String AIRLINE_ID = "AirlineID";

  String DEPARTURE_AIRPORT_AIRPORT_ID = "DepartureAirport_AirportID";

  String DESTINATION_AIRPORT_AIRPORT_ID = "DestinationAirport_AirportID";

  String DEPARTURE_TIME = "DepartureTime";

  String ARRIVAL_TIME = "ArrivalTime";

  String DISTANCE = "Distance";

  String DISTANCE_UNIT = "DistanceUnit";

  String CDS_NAME = "TravelService.FlightConnection";

  @CdsName(CONNECTION_ID)
  ElementRef<String> ConnectionID();

  @CdsName(AIRLINE_ID)
  ElementRef<String> AirlineID();

  Airport_ DepartureAirport();

  Airport_ DepartureAirport(Function<Airport_, CqnPredicate> filter);

  @CdsName(DEPARTURE_AIRPORT_AIRPORT_ID)
  ElementRef<String> DepartureAirport_AirportID();

  Airport_ DestinationAirport();

  Airport_ DestinationAirport(Function<Airport_, CqnPredicate> filter);

  @CdsName(DESTINATION_AIRPORT_AIRPORT_ID)
  ElementRef<String> DestinationAirport_AirportID();

  @CdsName(DEPARTURE_TIME)
  ElementRef<LocalTime> DepartureTime();

  @CdsName(ARRIVAL_TIME)
  ElementRef<LocalTime> ArrivalTime();

  @CdsName(DISTANCE)
  ElementRef<Integer> Distance();

  @CdsName(DISTANCE_UNIT)
  ElementRef<String> DistanceUnit();

  Airline_ to_Airline();

  Airline_ to_Airline(Function<Airline_, CqnPredicate> filter);
}
