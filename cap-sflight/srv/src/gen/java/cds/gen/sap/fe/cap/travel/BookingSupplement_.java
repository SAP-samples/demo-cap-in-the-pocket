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
import java.util.function.Function;
import javax.annotation.processing.Generated;

@CdsName("sap.fe.cap.travel.BookingSupplement")
@Generated(
    value = "cds-maven-plugin",
    date = "2025-05-08T06:34:56.366350Z",
    comments = "com.sap.cds:cds-maven-plugin:3.9.1 / com.sap.cds:cds4j-api:3.9.1"
)
public interface BookingSupplement_ extends StructuredType<BookingSupplement_> {
  String LAST_CHANGED_AT = "LastChangedAt";

  String LAST_CHANGED_BY = "LastChangedBy";

  String BOOK_SUPPL_UUID = "BookSupplUUID";

  String BOOKING_SUPPLEMENT_ID = "BookingSupplementID";

  String PRICE = "Price";

  String CURRENCY_CODE_CODE = "CurrencyCode_code";

  String TO_BOOKING_BOOKING_UUID = "to_Booking_BookingUUID";

  String TO_TRAVEL_TRAVEL_UUID = "to_Travel_TravelUUID";

  String TO_SUPPLEMENT_SUPPLEMENT_ID = "to_Supplement_SupplementID";

  String CDS_NAME = "sap.fe.cap.travel.BookingSupplement";

  ElementRef<Instant> createdAt();

  ElementRef<String> createdBy();

  @CdsName(LAST_CHANGED_AT)
  ElementRef<Instant> LastChangedAt();

  @CdsName(LAST_CHANGED_BY)
  ElementRef<String> LastChangedBy();

  @CdsName(BOOK_SUPPL_UUID)
  ElementRef<String> BookSupplUUID();

  @CdsName(BOOKING_SUPPLEMENT_ID)
  ElementRef<Integer> BookingSupplementID();

  @CdsName(PRICE)
  ElementRef<BigDecimal> Price();

  Currencies_ CurrencyCode();

  Currencies_ CurrencyCode(Function<Currencies_, CqnPredicate> filter);

  @CdsName(CURRENCY_CODE_CODE)
  ElementRef<String> CurrencyCode_code();

  Booking_ to_Booking();

  Booking_ to_Booking(Function<Booking_, CqnPredicate> filter);

  @CdsName(TO_BOOKING_BOOKING_UUID)
  ElementRef<String> to_Booking_BookingUUID();

  Travel_ to_Travel();

  Travel_ to_Travel(Function<Travel_, CqnPredicate> filter);

  @CdsName(TO_TRAVEL_TRAVEL_UUID)
  ElementRef<String> to_Travel_TravelUUID();

  Supplement_ to_Supplement();

  Supplement_ to_Supplement(Function<Supplement_, CqnPredicate> filter);

  @CdsName(TO_SUPPLEMENT_SUPPLEMENT_ID)
  ElementRef<String> to_Supplement_SupplementID();
}
