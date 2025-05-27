package cds.gen.sap.fe.cap.travel;

import cds.gen.sap.common.Currencies;
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

@CdsName("sap.fe.cap.travel.Travel")
@Generated(
    value = "cds-maven-plugin",
    date = "2025-05-08T06:34:56.366350Z",
    comments = "com.sap.cds:cds-maven-plugin:3.9.1 / com.sap.cds:cds4j-api:3.9.1"
)
public interface Travel extends CdsData {
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

  Instant createdAt();

  Travel createdAt(Instant createdAt);

  /**
   * Canonical user ID
   */
  String createdBy();

  /**
   * Canonical user ID
   */
  Travel createdBy(String createdBy);

  @CdsName(LAST_CHANGED_AT)
  Instant lastChangedAt();

  @CdsName(LAST_CHANGED_AT)
  Travel lastChangedAt(Instant lastChangedAt);

  /**
   * Canonical user ID
   */
  @CdsName(LAST_CHANGED_BY)
  String lastChangedBy();

  /**
   * Canonical user ID
   */
  @CdsName(LAST_CHANGED_BY)
  Travel lastChangedBy(String lastChangedBy);

  @CdsName(TRAVEL_UUID)
  String travelUUID();

  @CdsName(TRAVEL_UUID)
  Travel travelUUID(String travelUUID);

  @CdsName(TRAVEL_ID)
  Integer travelID();

  @CdsName(TRAVEL_ID)
  Travel travelID(Integer travelID);

  @CdsName(BEGIN_DATE)
  LocalDate beginDate();

  @CdsName(BEGIN_DATE)
  Travel beginDate(LocalDate beginDate);

  @CdsName(END_DATE)
  LocalDate endDate();

  @CdsName(END_DATE)
  Travel endDate(LocalDate endDate);

  @CdsName(BOOKING_FEE)
  BigDecimal bookingFee();

  @CdsName(BOOKING_FEE)
  Travel bookingFee(BigDecimal bookingFee);

  @CdsName(TOTAL_PRICE)
  BigDecimal totalPrice();

  @CdsName(TOTAL_PRICE)
  Travel totalPrice(BigDecimal totalPrice);

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
  Travel currencyCode(Map<String, ?> currencyCode);

  @CdsName(CURRENCY_CODE_CODE)
  String currencyCodeCode();

  @CdsName(CURRENCY_CODE_CODE)
  Travel currencyCodeCode(String currencyCodeCode);

  @CdsName(DESCRIPTION)
  String description();

  @CdsName(DESCRIPTION)
  Travel description(String description);

  @CdsName(TRAVEL_STATUS)
  TravelStatus travelStatus();

  @CdsName(TRAVEL_STATUS)
  Travel travelStatus(Map<String, ?> travelStatus);

  @CdsName(TRAVEL_STATUS_CODE)
  String travelStatusCode();

  @CdsName(TRAVEL_STATUS_CODE)
  Travel travelStatusCode(String travelStatusCode);

  @CdsName(TO_AGENCY)
  TravelAgency toAgency();

  @CdsName(TO_AGENCY)
  Travel toAgency(Map<String, ?> toAgency);

  @CdsName(TO_AGENCY_AGENCY_ID)
  String toAgencyAgencyID();

  @CdsName(TO_AGENCY_AGENCY_ID)
  Travel toAgencyAgencyID(String toAgencyAgencyID);

  @CdsName(TO_CUSTOMER)
  Passenger toCustomer();

  @CdsName(TO_CUSTOMER)
  Travel toCustomer(Map<String, ?> toCustomer);

  @CdsName(TO_CUSTOMER_CUSTOMER_ID)
  String toCustomerCustomerID();

  @CdsName(TO_CUSTOMER_CUSTOMER_ID)
  Travel toCustomerCustomerID(String toCustomerCustomerID);

  @CdsName(TO_BOOKING)
  List<Booking> toBooking();

  @CdsName(TO_BOOKING)
  Travel toBooking(List<? extends Map<String, ?>> toBooking);

  @CdsName(GO_GREEN)
  Boolean goGreen();

  @CdsName(GO_GREEN)
  Travel goGreen(Boolean goGreen);

  @CdsName(GREEN_FEE)
  BigDecimal greenFee();

  @CdsName(GREEN_FEE)
  Travel greenFee(BigDecimal greenFee);

  @CdsName(TREES_PLANTED)
  Integer treesPlanted();

  @CdsName(TREES_PLANTED)
  Travel treesPlanted(Integer treesPlanted);

  Travel_ ref();

  static Travel create() {
    return Struct.create(Travel.class);
  }

  static Travel of(Map<String, Object> map) {
    return Struct.access(map).as(Travel.class);
  }

  static Travel create(String travelUUID) {
    Map<String, Object> keys = new HashMap<>();
    keys.put(TRAVEL_UUID, travelUUID);
    return Struct.access(keys).as(Travel.class);
  }
}
