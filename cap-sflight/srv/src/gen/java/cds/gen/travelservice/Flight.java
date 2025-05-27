package cds.gen.travelservice;

import com.sap.cds.CdsData;
import com.sap.cds.Struct;
import com.sap.cds.ql.CdsName;
import java.lang.Integer;
import java.lang.Object;
import java.lang.String;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Map;
import javax.annotation.processing.Generated;

@CdsName("TravelService.Flight")
@Generated(
    value = "cds-maven-plugin",
    date = "2025-05-08T06:34:56.366350Z",
    comments = "com.sap.cds:cds-maven-plugin:3.9.1 / com.sap.cds:cds4j-api:3.9.1"
)
public interface Flight extends CdsData {
  String AIRLINE_ID = "AirlineID";

  String FLIGHT_DATE = "FlightDate";

  String CONNECTION_ID = "ConnectionID";

  String PRICE = "Price";

  String CURRENCY_CODE = "CurrencyCode";

  String CURRENCY_CODE_CODE = "CurrencyCode_code";

  String PLANE_TYPE = "PlaneType";

  String MAXIMUM_SEATS = "MaximumSeats";

  String OCCUPIED_SEATS = "OccupiedSeats";

  String TO_AIRLINE = "to_Airline";

  String TO_CONNECTION = "to_Connection";

  @CdsName(AIRLINE_ID)
  String airlineID();

  @CdsName(AIRLINE_ID)
  Flight airlineID(String airlineID);

  @CdsName(FLIGHT_DATE)
  LocalDate flightDate();

  @CdsName(FLIGHT_DATE)
  Flight flightDate(LocalDate flightDate);

  @CdsName(CONNECTION_ID)
  String connectionID();

  @CdsName(CONNECTION_ID)
  Flight connectionID(String connectionID);

  @CdsName(PRICE)
  BigDecimal price();

  @CdsName(PRICE)
  Flight price(BigDecimal price);

  /**
   * Type for an association to Currencies
   *
   * See https://cap.cloud.sap/docs/cds/common#type-currency
   */
  @CdsName(CURRENCY_CODE)
  Currencies currencyCode();

  /**
   * Type for an association to Currencies
   *
   * See https://cap.cloud.sap/docs/cds/common#type-currency
   */
  @CdsName(CURRENCY_CODE)
  Flight currencyCode(Map<String, ?> currencyCode);

  @CdsName(CURRENCY_CODE_CODE)
  String currencyCodeCode();

  @CdsName(CURRENCY_CODE_CODE)
  Flight currencyCodeCode(String currencyCodeCode);

  @CdsName(PLANE_TYPE)
  String planeType();

  @CdsName(PLANE_TYPE)
  Flight planeType(String planeType);

  @CdsName(MAXIMUM_SEATS)
  Integer maximumSeats();

  @CdsName(MAXIMUM_SEATS)
  Flight maximumSeats(Integer maximumSeats);

  @CdsName(OCCUPIED_SEATS)
  Integer occupiedSeats();

  @CdsName(OCCUPIED_SEATS)
  Flight occupiedSeats(Integer occupiedSeats);

  @CdsName(TO_AIRLINE)
  Airline toAirline();

  @CdsName(TO_AIRLINE)
  Flight toAirline(Map<String, ?> toAirline);

  @CdsName(TO_CONNECTION)
  FlightConnection toConnection();

  @CdsName(TO_CONNECTION)
  Flight toConnection(Map<String, ?> toConnection);

  Flight_ ref();

  static Flight create() {
    return Struct.create(Flight.class);
  }

  static Flight of(Map<String, Object> map) {
    return Struct.access(map).as(Flight.class);
  }
}
