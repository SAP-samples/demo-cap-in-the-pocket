package cds.gen.sap.fe.cap.travel;

import cds.gen.sap.common.Currencies;
import com.sap.cds.CdsData;
import com.sap.cds.Struct;
import com.sap.cds.ql.CdsName;
import java.lang.Integer;
import java.lang.Object;
import java.lang.String;
import java.math.BigDecimal;
import java.time.Instant;
import java.util.HashMap;
import java.util.Map;
import javax.annotation.processing.Generated;

@CdsName("sap.fe.cap.travel.BookingSupplement")
@Generated(
    value = "cds-maven-plugin",
    date = "2025-05-08T06:34:56.366350Z",
    comments = "com.sap.cds:cds-maven-plugin:3.9.1 / com.sap.cds:cds4j-api:3.9.1"
)
public interface BookingSupplement extends CdsData {
  String CREATED_AT = "createdAt";

  String CREATED_BY = "createdBy";

  String LAST_CHANGED_AT = "LastChangedAt";

  String LAST_CHANGED_BY = "LastChangedBy";

  String BOOK_SUPPL_UUID = "BookSupplUUID";

  String BOOKING_SUPPLEMENT_ID = "BookingSupplementID";

  String PRICE = "Price";

  String CURRENCY_CODE = "CurrencyCode";

  String CURRENCY_CODE_CODE = "CurrencyCode_code";

  String TO_BOOKING = "to_Booking";

  String TO_BOOKING_BOOKING_UUID = "to_Booking_BookingUUID";

  String TO_TRAVEL = "to_Travel";

  String TO_TRAVEL_TRAVEL_UUID = "to_Travel_TravelUUID";

  String TO_SUPPLEMENT = "to_Supplement";

  String TO_SUPPLEMENT_SUPPLEMENT_ID = "to_Supplement_SupplementID";

  Instant createdAt();

  BookingSupplement createdAt(Instant createdAt);

  /**
   * Canonical user ID
   */
  String createdBy();

  /**
   * Canonical user ID
   */
  BookingSupplement createdBy(String createdBy);

  @CdsName(LAST_CHANGED_AT)
  Instant lastChangedAt();

  @CdsName(LAST_CHANGED_AT)
  BookingSupplement lastChangedAt(Instant lastChangedAt);

  /**
   * Canonical user ID
   */
  @CdsName(LAST_CHANGED_BY)
  String lastChangedBy();

  /**
   * Canonical user ID
   */
  @CdsName(LAST_CHANGED_BY)
  BookingSupplement lastChangedBy(String lastChangedBy);

  @CdsName(BOOK_SUPPL_UUID)
  String bookSupplUUID();

  @CdsName(BOOK_SUPPL_UUID)
  BookingSupplement bookSupplUUID(String bookSupplUUID);

  @CdsName(BOOKING_SUPPLEMENT_ID)
  Integer bookingSupplementID();

  @CdsName(BOOKING_SUPPLEMENT_ID)
  BookingSupplement bookingSupplementID(Integer bookingSupplementID);

  @CdsName(PRICE)
  BigDecimal price();

  @CdsName(PRICE)
  BookingSupplement price(BigDecimal price);

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
  BookingSupplement currencyCode(Map<String, ?> currencyCode);

  @CdsName(CURRENCY_CODE_CODE)
  String currencyCodeCode();

  @CdsName(CURRENCY_CODE_CODE)
  BookingSupplement currencyCodeCode(String currencyCodeCode);

  @CdsName(TO_BOOKING)
  Booking toBooking();

  @CdsName(TO_BOOKING)
  BookingSupplement toBooking(Map<String, ?> toBooking);

  @CdsName(TO_BOOKING_BOOKING_UUID)
  String toBookingBookingUUID();

  @CdsName(TO_BOOKING_BOOKING_UUID)
  BookingSupplement toBookingBookingUUID(String toBookingBookingUUID);

  @CdsName(TO_TRAVEL)
  Travel toTravel();

  @CdsName(TO_TRAVEL)
  BookingSupplement toTravel(Map<String, ?> toTravel);

  @CdsName(TO_TRAVEL_TRAVEL_UUID)
  String toTravelTravelUUID();

  @CdsName(TO_TRAVEL_TRAVEL_UUID)
  BookingSupplement toTravelTravelUUID(String toTravelTravelUUID);

  @CdsName(TO_SUPPLEMENT)
  Supplement toSupplement();

  @CdsName(TO_SUPPLEMENT)
  BookingSupplement toSupplement(Map<String, ?> toSupplement);

  @CdsName(TO_SUPPLEMENT_SUPPLEMENT_ID)
  String toSupplementSupplementID();

  @CdsName(TO_SUPPLEMENT_SUPPLEMENT_ID)
  BookingSupplement toSupplementSupplementID(String toSupplementSupplementID);

  BookingSupplement_ ref();

  static BookingSupplement create() {
    return Struct.create(BookingSupplement.class);
  }

  static BookingSupplement of(Map<String, Object> map) {
    return Struct.access(map).as(BookingSupplement.class);
  }

  static BookingSupplement create(String bookSupplUUID) {
    Map<String, Object> keys = new HashMap<>();
    keys.put(BOOK_SUPPL_UUID, bookSupplUUID);
    return Struct.access(keys).as(BookingSupplement.class);
  }
}
