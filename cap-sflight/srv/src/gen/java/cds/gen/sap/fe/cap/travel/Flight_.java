package cds.gen.sap.fe.cap.travel;

import cds.gen.sap.common.Currencies_;
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

@CdsName("sap.fe.cap.travel.Flight")
@Generated(
    value = "cds-maven-plugin",
    date = "2025-05-08T06:34:56.366350Z",
    comments = "com.sap.cds:cds-maven-plugin:3.9.1 / com.sap.cds:cds4j-api:3.9.1"
)
public interface Flight_ extends StructuredType<Flight_> {
  String AIRLINE_ID = "AirlineID";

  String FLIGHT_DATE = "FlightDate";

  String CONNECTION_ID = "ConnectionID";

  String PRICE = "Price";

  String CURRENCY_CODE_CODE = "CurrencyCode_code";

  String PLANE_TYPE = "PlaneType";

  String MAXIMUM_SEATS = "MaximumSeats";

  String OCCUPIED_SEATS = "OccupiedSeats";

  String CDS_NAME = "sap.fe.cap.travel.Flight";

  @CdsName(AIRLINE_ID)
  ElementRef<String> AirlineID();

  @CdsName(FLIGHT_DATE)
  ElementRef<LocalDate> FlightDate();

  @CdsName(CONNECTION_ID)
  ElementRef<String> ConnectionID();

  @CdsName(PRICE)
  ElementRef<BigDecimal> Price();

  Currencies_ CurrencyCode();

  Currencies_ CurrencyCode(Function<Currencies_, CqnPredicate> filter);

  @CdsName(CURRENCY_CODE_CODE)
  ElementRef<String> CurrencyCode_code();

  @CdsName(PLANE_TYPE)
  ElementRef<String> PlaneType();

  @CdsName(MAXIMUM_SEATS)
  ElementRef<Integer> MaximumSeats();

  @CdsName(OCCUPIED_SEATS)
  ElementRef<Integer> OccupiedSeats();

  Airline_ to_Airline();

  Airline_ to_Airline(Function<Airline_, CqnPredicate> filter);

  FlightConnection_ to_Connection();

  FlightConnection_ to_Connection(Function<FlightConnection_, CqnPredicate> filter);
}
