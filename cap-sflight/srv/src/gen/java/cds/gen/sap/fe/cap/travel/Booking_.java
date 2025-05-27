package cds.gen.sap.fe.cap.travel;

import cds.gen.sap.common.Currencies_;
import com.sap.cds.ql.CdsName;
import com.sap.cds.ql.ElementRef;
import com.sap.cds.ql.StructuredType;
import com.sap.cds.ql.cqn.CqnPredicate;
import java.lang.Integer;
import java.lang.String;
import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDate;
import java.util.function.Function;
import javax.annotation.processing.Generated;

@CdsName("sap.fe.cap.travel.Booking")
@Generated(
    value = "cds-maven-plugin",
    date = "2025-05-08T06:34:56.366350Z",
    comments = "com.sap.cds:cds-maven-plugin:3.9.1 / com.sap.cds:cds4j-api:3.9.1"
)
public interface Booking_ extends StructuredType<Booking_> {
  String LAST_CHANGED_AT = "LastChangedAt";

  String LAST_CHANGED_BY = "LastChangedBy";

  String BOOKING_UUID = "BookingUUID";

  String BOOKING_ID = "BookingID";

  String BOOKING_DATE = "BookingDate";

  String CONNECTION_ID = "ConnectionID";

  String FLIGHT_DATE = "FlightDate";

  String FLIGHT_PRICE = "FlightPrice";

  String CURRENCY_CODE_CODE = "CurrencyCode_code";

  String BOOKING_STATUS_CODE = "BookingStatus_code";

  String TO_CARRIER_AIRLINE_ID = "to_Carrier_AirlineID";

  String TO_CUSTOMER_CUSTOMER_ID = "to_Customer_CustomerID";

  String TO_TRAVEL_TRAVEL_UUID = "to_Travel_TravelUUID";

  String CDS_NAME = "sap.fe.cap.travel.Booking";

  ElementRef<Instant> createdAt();

  ElementRef<String> createdBy();

  @CdsName(LAST_CHANGED_AT)
  ElementRef<Instant> LastChangedAt();

  @CdsName(LAST_CHANGED_BY)
  ElementRef<String> LastChangedBy();

  @CdsName(BOOKING_UUID)
  ElementRef<String> BookingUUID();

  @CdsName(BOOKING_ID)
  ElementRef<Integer> BookingID();

  @CdsName(BOOKING_DATE)
  ElementRef<LocalDate> BookingDate();

  @CdsName(CONNECTION_ID)
  ElementRef<String> ConnectionID();

  @CdsName(FLIGHT_DATE)
  ElementRef<LocalDate> FlightDate();

  @CdsName(FLIGHT_PRICE)
  ElementRef<BigDecimal> FlightPrice();

  Currencies_ CurrencyCode();

  Currencies_ CurrencyCode(Function<Currencies_, CqnPredicate> filter);

  @CdsName(CURRENCY_CODE_CODE)
  ElementRef<String> CurrencyCode_code();

  BookingStatus_ BookingStatus();

  BookingStatus_ BookingStatus(Function<BookingStatus_, CqnPredicate> filter);

  @CdsName(BOOKING_STATUS_CODE)
  ElementRef<String> BookingStatus_code();

  BookingSupplement_ to_BookSupplement();

  BookingSupplement_ to_BookSupplement(Function<BookingSupplement_, CqnPredicate> filter);

  Airline_ to_Carrier();

  Airline_ to_Carrier(Function<Airline_, CqnPredicate> filter);

  @CdsName(TO_CARRIER_AIRLINE_ID)
  ElementRef<String> to_Carrier_AirlineID();

  Passenger_ to_Customer();

  Passenger_ to_Customer(Function<Passenger_, CqnPredicate> filter);

  @CdsName(TO_CUSTOMER_CUSTOMER_ID)
  ElementRef<String> to_Customer_CustomerID();

  Travel_ to_Travel();

  Travel_ to_Travel(Function<Travel_, CqnPredicate> filter);

  @CdsName(TO_TRAVEL_TRAVEL_UUID)
  ElementRef<String> to_Travel_TravelUUID();

  Flight_ to_Flight();

  Flight_ to_Flight(Function<Flight_, CqnPredicate> filter);
}
