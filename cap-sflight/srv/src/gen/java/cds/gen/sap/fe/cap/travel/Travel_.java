package cds.gen.sap.fe.cap.travel;

import cds.gen.sap.common.Currencies_;
import com.sap.cds.ql.CdsName;
import com.sap.cds.ql.ElementRef;
import com.sap.cds.ql.StructuredType;
import com.sap.cds.ql.cqn.CqnPredicate;
import java.lang.Boolean;
import java.lang.Integer;
import java.lang.String;
import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDate;
import java.util.function.Function;
import javax.annotation.processing.Generated;

@CdsName("sap.fe.cap.travel.Travel")
@Generated(
    value = "cds-maven-plugin",
    date = "2025-05-08T06:34:56.366350Z",
    comments = "com.sap.cds:cds-maven-plugin:3.9.1 / com.sap.cds:cds4j-api:3.9.1"
)
public interface Travel_ extends StructuredType<Travel_> {
  String LAST_CHANGED_AT = "LastChangedAt";

  String LAST_CHANGED_BY = "LastChangedBy";

  String TRAVEL_UUID = "TravelUUID";

  String TRAVEL_ID = "TravelID";

  String BEGIN_DATE = "BeginDate";

  String END_DATE = "EndDate";

  String BOOKING_FEE = "BookingFee";

  String TOTAL_PRICE = "TotalPrice";

  String CURRENCY_CODE_CODE = "CurrencyCode_code";

  String DESCRIPTION = "Description";

  String TRAVEL_STATUS_CODE = "TravelStatus_code";

  String TO_AGENCY_AGENCY_ID = "to_Agency_AgencyID";

  String TO_CUSTOMER_CUSTOMER_ID = "to_Customer_CustomerID";

  String GO_GREEN = "GoGreen";

  String GREEN_FEE = "GreenFee";

  String TREES_PLANTED = "TreesPlanted";

  String CDS_NAME = "sap.fe.cap.travel.Travel";

  ElementRef<Instant> createdAt();

  ElementRef<String> createdBy();

  @CdsName(LAST_CHANGED_AT)
  ElementRef<Instant> LastChangedAt();

  @CdsName(LAST_CHANGED_BY)
  ElementRef<String> LastChangedBy();

  @CdsName(TRAVEL_UUID)
  ElementRef<String> TravelUUID();

  @CdsName(TRAVEL_ID)
  ElementRef<Integer> TravelID();

  @CdsName(BEGIN_DATE)
  ElementRef<LocalDate> BeginDate();

  @CdsName(END_DATE)
  ElementRef<LocalDate> EndDate();

  @CdsName(BOOKING_FEE)
  ElementRef<BigDecimal> BookingFee();

  @CdsName(TOTAL_PRICE)
  ElementRef<BigDecimal> TotalPrice();

  Currencies_ CurrencyCode();

  Currencies_ CurrencyCode(Function<Currencies_, CqnPredicate> filter);

  @CdsName(CURRENCY_CODE_CODE)
  ElementRef<String> CurrencyCode_code();

  @CdsName(DESCRIPTION)
  ElementRef<String> Description();

  TravelStatus_ TravelStatus();

  TravelStatus_ TravelStatus(Function<TravelStatus_, CqnPredicate> filter);

  @CdsName(TRAVEL_STATUS_CODE)
  ElementRef<String> TravelStatus_code();

  TravelAgency_ to_Agency();

  TravelAgency_ to_Agency(Function<TravelAgency_, CqnPredicate> filter);

  @CdsName(TO_AGENCY_AGENCY_ID)
  ElementRef<String> to_Agency_AgencyID();

  Passenger_ to_Customer();

  Passenger_ to_Customer(Function<Passenger_, CqnPredicate> filter);

  @CdsName(TO_CUSTOMER_CUSTOMER_ID)
  ElementRef<String> to_Customer_CustomerID();

  Booking_ to_Booking();

  Booking_ to_Booking(Function<Booking_, CqnPredicate> filter);

  @CdsName(GO_GREEN)
  ElementRef<Boolean> GoGreen();

  @CdsName(GREEN_FEE)
  ElementRef<BigDecimal> GreenFee();

  @CdsName(TREES_PLANTED)
  ElementRef<Integer> TreesPlanted();
}
