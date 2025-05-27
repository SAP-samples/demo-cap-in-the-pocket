package cds.gen.travelservice;

import com.sap.cds.CdsData;
import com.sap.cds.Struct;
import com.sap.cds.ql.CdsName;
import java.lang.Boolean;
import java.lang.Integer;
import java.lang.Object;
import java.lang.String;
import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import javax.annotation.processing.Generated;

@CdsName("TravelService.Booking")
@Generated(
    value = "cds-maven-plugin",
    date = "2025-05-08T06:34:56.366350Z",
    comments = "com.sap.cds:cds-maven-plugin:3.9.1 / com.sap.cds:cds4j-api:3.9.1"
)
public interface Booking extends CdsData {
  String CREATED_AT = "createdAt";

  String CREATED_BY = "createdBy";

  String LAST_CHANGED_AT = "LastChangedAt";

  String LAST_CHANGED_BY = "LastChangedBy";

  String BOOKING_UUID = "BookingUUID";

  String BOOKING_ID = "BookingID";

  String BOOKING_DATE = "BookingDate";

  String CONNECTION_ID = "ConnectionID";

  String FLIGHT_DATE = "FlightDate";

  String FLIGHT_PRICE = "FlightPrice";

  String CURRENCY_CODE = "CurrencyCode";

  String CURRENCY_CODE_CODE = "CurrencyCode_code";

  String BOOKING_STATUS = "BookingStatus";

  String BOOKING_STATUS_CODE = "BookingStatus_code";

  String TO_BOOK_SUPPLEMENT = "to_BookSupplement";

  String TO_CARRIER = "to_Carrier";

  String TO_CARRIER_AIRLINE_ID = "to_Carrier_AirlineID";

  String TO_CUSTOMER = "to_Customer";

  String TO_CUSTOMER_CUSTOMER_ID = "to_Customer_CustomerID";

  String TO_TRAVEL = "to_Travel";

  String TO_TRAVEL_TRAVEL_UUID = "to_Travel_TravelUUID";

  String TO_FLIGHT = "to_Flight";

  String IS_ACTIVE_ENTITY = "IsActiveEntity";

  String HAS_ACTIVE_ENTITY = "HasActiveEntity";

  String HAS_DRAFT_ENTITY = "HasDraftEntity";

  String DRAFT_ADMINISTRATIVE_DATA = "DraftAdministrativeData";

  String DRAFT_ADMINISTRATIVE_DATA_DRAFT_UUID = "DraftAdministrativeData_DraftUUID";

  String SIBLING_ENTITY = "SiblingEntity";

  Instant createdAt();

  Booking createdAt(Instant createdAt);

  /**
   * Canonical user ID
   */
  String createdBy();

  /**
   * Canonical user ID
   */
  Booking createdBy(String createdBy);

  @CdsName(LAST_CHANGED_AT)
  Instant lastChangedAt();

  @CdsName(LAST_CHANGED_AT)
  Booking lastChangedAt(Instant lastChangedAt);

  /**
   * Canonical user ID
   */
  @CdsName(LAST_CHANGED_BY)
  String lastChangedBy();

  /**
   * Canonical user ID
   */
  @CdsName(LAST_CHANGED_BY)
  Booking lastChangedBy(String lastChangedBy);

  @CdsName(BOOKING_UUID)
  String bookingUUID();

  @CdsName(BOOKING_UUID)
  Booking bookingUUID(String bookingUUID);

  @CdsName(BOOKING_ID)
  Integer bookingID();

  @CdsName(BOOKING_ID)
  Booking bookingID(Integer bookingID);

  @CdsName(BOOKING_DATE)
  LocalDate bookingDate();

  @CdsName(BOOKING_DATE)
  Booking bookingDate(LocalDate bookingDate);

  @CdsName(CONNECTION_ID)
  String connectionID();

  @CdsName(CONNECTION_ID)
  Booking connectionID(String connectionID);

  @CdsName(FLIGHT_DATE)
  LocalDate flightDate();

  @CdsName(FLIGHT_DATE)
  Booking flightDate(LocalDate flightDate);

  @CdsName(FLIGHT_PRICE)
  BigDecimal flightPrice();

  @CdsName(FLIGHT_PRICE)
  Booking flightPrice(BigDecimal flightPrice);

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
  Booking currencyCode(Map<String, ?> currencyCode);

  @CdsName(CURRENCY_CODE_CODE)
  String currencyCodeCode();

  @CdsName(CURRENCY_CODE_CODE)
  Booking currencyCodeCode(String currencyCodeCode);

  @CdsName(BOOKING_STATUS)
  BookingStatus bookingStatus();

  @CdsName(BOOKING_STATUS)
  Booking bookingStatus(Map<String, ?> bookingStatus);

  @CdsName(BOOKING_STATUS_CODE)
  String bookingStatusCode();

  @CdsName(BOOKING_STATUS_CODE)
  Booking bookingStatusCode(String bookingStatusCode);

  @CdsName(TO_BOOK_SUPPLEMENT)
  List<BookingSupplement> toBookSupplement();

  @CdsName(TO_BOOK_SUPPLEMENT)
  Booking toBookSupplement(List<? extends Map<String, ?>> toBookSupplement);

  @CdsName(TO_CARRIER)
  Airline toCarrier();

  @CdsName(TO_CARRIER)
  Booking toCarrier(Map<String, ?> toCarrier);

  @CdsName(TO_CARRIER_AIRLINE_ID)
  String toCarrierAirlineID();

  @CdsName(TO_CARRIER_AIRLINE_ID)
  Booking toCarrierAirlineID(String toCarrierAirlineID);

  @CdsName(TO_CUSTOMER)
  Passenger toCustomer();

  @CdsName(TO_CUSTOMER)
  Booking toCustomer(Map<String, ?> toCustomer);

  @CdsName(TO_CUSTOMER_CUSTOMER_ID)
  String toCustomerCustomerID();

  @CdsName(TO_CUSTOMER_CUSTOMER_ID)
  Booking toCustomerCustomerID(String toCustomerCustomerID);

  @CdsName(TO_TRAVEL)
  Travel toTravel();

  @CdsName(TO_TRAVEL)
  Booking toTravel(Map<String, ?> toTravel);

  @CdsName(TO_TRAVEL_TRAVEL_UUID)
  String toTravelTravelUUID();

  @CdsName(TO_TRAVEL_TRAVEL_UUID)
  Booking toTravelTravelUUID(String toTravelTravelUUID);

  @CdsName(TO_FLIGHT)
  Flight toFlight();

  @CdsName(TO_FLIGHT)
  Booking toFlight(Map<String, ?> toFlight);

  @CdsName(IS_ACTIVE_ENTITY)
  Boolean isActiveEntity();

  @CdsName(IS_ACTIVE_ENTITY)
  Booking isActiveEntity(Boolean isActiveEntity);

  @CdsName(HAS_ACTIVE_ENTITY)
  Boolean hasActiveEntity();

  @CdsName(HAS_ACTIVE_ENTITY)
  Booking hasActiveEntity(Boolean hasActiveEntity);

  @CdsName(HAS_DRAFT_ENTITY)
  Boolean hasDraftEntity();

  @CdsName(HAS_DRAFT_ENTITY)
  Booking hasDraftEntity(Boolean hasDraftEntity);

  @CdsName(DRAFT_ADMINISTRATIVE_DATA)
  DraftAdministrativeData draftAdministrativeData();

  @CdsName(DRAFT_ADMINISTRATIVE_DATA)
  Booking draftAdministrativeData(Map<String, ?> draftAdministrativeData);

  @CdsName(DRAFT_ADMINISTRATIVE_DATA_DRAFT_UUID)
  String draftAdministrativeDataDraftUUID();

  @CdsName(DRAFT_ADMINISTRATIVE_DATA_DRAFT_UUID)
  Booking draftAdministrativeDataDraftUUID(String draftAdministrativeDataDraftUUID);

  @CdsName(SIBLING_ENTITY)
  Booking siblingEntity();

  @CdsName(SIBLING_ENTITY)
  Booking siblingEntity(Map<String, ?> siblingEntity);

  Booking_ ref();

  static Booking create() {
    return Struct.create(Booking.class);
  }

  static Booking of(Map<String, Object> map) {
    return Struct.access(map).as(Booking.class);
  }
}
