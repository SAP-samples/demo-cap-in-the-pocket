package cds.gen.analyticsservice;

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
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.processing.Generated;

@CdsName("AnalyticsService.Travels")
@Generated(
    value = "cds-maven-plugin",
    date = "2025-05-08T06:34:56.366350Z",
    comments = "com.sap.cds:cds-maven-plugin:3.9.1 / com.sap.cds:cds4j-api:3.9.1"
)
public interface Travels extends CdsData {
  String CREATED_AT = "createdAt";

  String CREATED_BY = "createdBy";

  String LAST_CHANGED_AT = "LastChangedAt";

  String LAST_CHANGED_BY = "LastChangedBy";

  String TRAVEL_UUID = "TravelUUID";

  String TRAVEL_ID = "TravelID";

  String BEGIN_DATE = "BeginDate";

  String END_DATE = "EndDate";

  String BOOKING_FEE = "BookingFee";

  String TOTAL_PRICE = "TotalPrice";

  String CURRENCY_CODE = "CurrencyCode";

  String CURRENCY_CODE_CODE = "CurrencyCode_code";

  String DESCRIPTION = "Description";

  String TRAVEL_STATUS = "TravelStatus";

  String TRAVEL_STATUS_CODE = "TravelStatus_code";

  String TO_AGENCY = "to_Agency";

  String TO_AGENCY_AGENCY_ID = "to_Agency_AgencyID";

  String TO_CUSTOMER = "to_Customer";

  String TO_CUSTOMER_CUSTOMER_ID = "to_Customer_CustomerID";

  String TO_BOOKING = "to_Booking";

  String GO_GREEN = "GoGreen";

  String GREEN_FEE = "GreenFee";

  String TREES_PLANTED = "TreesPlanted";

  String CUSTOMER_NAME = "CustomerName";

  Instant createdAt();

  Travels createdAt(Instant createdAt);

  /**
   * Canonical user ID
   */
  String createdBy();

  /**
   * Canonical user ID
   */
  Travels createdBy(String createdBy);

  @CdsName(LAST_CHANGED_AT)
  Instant lastChangedAt();

  @CdsName(LAST_CHANGED_AT)
  Travels lastChangedAt(Instant lastChangedAt);

  /**
   * Canonical user ID
   */
  @CdsName(LAST_CHANGED_BY)
  String lastChangedBy();

  /**
   * Canonical user ID
   */
  @CdsName(LAST_CHANGED_BY)
  Travels lastChangedBy(String lastChangedBy);

  @CdsName(TRAVEL_UUID)
  String travelUUID();

  @CdsName(TRAVEL_UUID)
  Travels travelUUID(String travelUUID);

  @CdsName(TRAVEL_ID)
  Integer travelID();

  @CdsName(TRAVEL_ID)
  Travels travelID(Integer travelID);

  @CdsName(BEGIN_DATE)
  LocalDate beginDate();

  @CdsName(BEGIN_DATE)
  Travels beginDate(LocalDate beginDate);

  @CdsName(END_DATE)
  LocalDate endDate();

  @CdsName(END_DATE)
  Travels endDate(LocalDate endDate);

  @CdsName(BOOKING_FEE)
  BigDecimal bookingFee();

  @CdsName(BOOKING_FEE)
  Travels bookingFee(BigDecimal bookingFee);

  @CdsName(TOTAL_PRICE)
  BigDecimal totalPrice();

  @CdsName(TOTAL_PRICE)
  Travels totalPrice(BigDecimal totalPrice);

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
  Travels currencyCode(Map<String, ?> currencyCode);

  @CdsName(CURRENCY_CODE_CODE)
  String currencyCodeCode();

  @CdsName(CURRENCY_CODE_CODE)
  Travels currencyCodeCode(String currencyCodeCode);

  @CdsName(DESCRIPTION)
  String description();

  @CdsName(DESCRIPTION)
  Travels description(String description);

  @CdsName(TRAVEL_STATUS)
  TravelStatus travelStatus();

  @CdsName(TRAVEL_STATUS)
  Travels travelStatus(Map<String, ?> travelStatus);

  @CdsName(TRAVEL_STATUS_CODE)
  String travelStatusCode();

  @CdsName(TRAVEL_STATUS_CODE)
  Travels travelStatusCode(String travelStatusCode);

  @CdsName(TO_AGENCY)
  TravelAgency toAgency();

  @CdsName(TO_AGENCY)
  Travels toAgency(Map<String, ?> toAgency);

  @CdsName(TO_AGENCY_AGENCY_ID)
  String toAgencyAgencyID();

  @CdsName(TO_AGENCY_AGENCY_ID)
  Travels toAgencyAgencyID(String toAgencyAgencyID);

  @CdsName(TO_CUSTOMER)
  Passenger toCustomer();

  @CdsName(TO_CUSTOMER)
  Travels toCustomer(Map<String, ?> toCustomer);

  @CdsName(TO_CUSTOMER_CUSTOMER_ID)
  String toCustomerCustomerID();

  @CdsName(TO_CUSTOMER_CUSTOMER_ID)
  Travels toCustomerCustomerID(String toCustomerCustomerID);

  @CdsName(TO_BOOKING)
  List<Bookings> toBooking();

  @CdsName(TO_BOOKING)
  Travels toBooking(List<? extends Map<String, ?>> toBooking);

  @CdsName(GO_GREEN)
  Boolean goGreen();

  @CdsName(GO_GREEN)
  Travels goGreen(Boolean goGreen);

  @CdsName(GREEN_FEE)
  BigDecimal greenFee();

  @CdsName(GREEN_FEE)
  Travels greenFee(BigDecimal greenFee);

  @CdsName(TREES_PLANTED)
  Integer treesPlanted();

  @CdsName(TREES_PLANTED)
  Travels treesPlanted(Integer treesPlanted);

  @CdsName(CUSTOMER_NAME)
  String customerName();

  @CdsName(CUSTOMER_NAME)
  Travels customerName(String customerName);

  Travels_ ref();

  static Travels create() {
    return Struct.create(Travels.class);
  }

  static Travels of(Map<String, Object> map) {
    return Struct.access(map).as(Travels.class);
  }

  static Travels create(String travelUUID) {
    Map<String, Object> keys = new HashMap<>();
    keys.put(TRAVEL_UUID, travelUUID);
    return Struct.access(keys).as(Travels.class);
  }
}
