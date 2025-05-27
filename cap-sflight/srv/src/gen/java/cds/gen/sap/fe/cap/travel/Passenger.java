package cds.gen.sap.fe.cap.travel;

import cds.gen.sap.common.Countries;
import com.sap.cds.CdsData;
import com.sap.cds.Struct;
import com.sap.cds.ql.CdsName;
import java.lang.Object;
import java.lang.String;
import java.time.Instant;
import java.util.HashMap;
import java.util.Map;
import javax.annotation.processing.Generated;

@CdsName("sap.fe.cap.travel.Passenger")
@Generated(
    value = "cds-maven-plugin",
    date = "2025-05-08T06:34:56.366350Z",
    comments = "com.sap.cds:cds-maven-plugin:3.9.1 / com.sap.cds:cds4j-api:3.9.1"
)
public interface Passenger extends CdsData {
  String CREATED_AT = "createdAt";

  String CREATED_BY = "createdBy";

  String LAST_CHANGED_AT = "LastChangedAt";

  String LAST_CHANGED_BY = "LastChangedBy";

  String CUSTOMER_ID = "CustomerID";

  String FIRST_NAME = "FirstName";

  String LAST_NAME = "LastName";

  String TITLE = "Title";

  String STREET = "Street";

  String POSTAL_CODE = "PostalCode";

  String CITY = "City";

  String COUNTRY_CODE = "CountryCode";

  String COUNTRY_CODE_CODE = "CountryCode_code";

  String PHONE_NUMBER = "PhoneNumber";

  String EMAIL_ADDRESS = "EMailAddress";

  Instant createdAt();

  Passenger createdAt(Instant createdAt);

  /**
   * Canonical user ID
   */
  String createdBy();

  /**
   * Canonical user ID
   */
  Passenger createdBy(String createdBy);

  @CdsName(LAST_CHANGED_AT)
  Instant lastChangedAt();

  @CdsName(LAST_CHANGED_AT)
  Passenger lastChangedAt(Instant lastChangedAt);

  /**
   * Canonical user ID
   */
  @CdsName(LAST_CHANGED_BY)
  String lastChangedBy();

  /**
   * Canonical user ID
   */
  @CdsName(LAST_CHANGED_BY)
  Passenger lastChangedBy(String lastChangedBy);

  @CdsName(CUSTOMER_ID)
  String customerID();

  @CdsName(CUSTOMER_ID)
  Passenger customerID(String customerID);

  @CdsName(FIRST_NAME)
  String firstName();

  @CdsName(FIRST_NAME)
  Passenger firstName(String firstName);

  @CdsName(LAST_NAME)
  String lastName();

  @CdsName(LAST_NAME)
  Passenger lastName(String lastName);

  @CdsName(TITLE)
  String title();

  @CdsName(TITLE)
  Passenger title(String title);

  @CdsName(STREET)
  String street();

  @CdsName(STREET)
  Passenger street(String street);

  @CdsName(POSTAL_CODE)
  String postalCode();

  @CdsName(POSTAL_CODE)
  Passenger postalCode(String postalCode);

  @CdsName(CITY)
  String city();

  @CdsName(CITY)
  Passenger city(String city);

  /**
   * Type for an association to Countries
   *
   * See https://cap.cloud.sap/docs/cds/common#type-country
   */
  @CdsName(COUNTRY_CODE)
  Countries countryCode();

  /**
   * Type for an association to Countries
   *
   * See https://cap.cloud.sap/docs/cds/common#type-country
   */
  @CdsName(COUNTRY_CODE)
  Passenger countryCode(Map<String, ?> countryCode);

  @CdsName(COUNTRY_CODE_CODE)
  String countryCodeCode();

  @CdsName(COUNTRY_CODE_CODE)
  Passenger countryCodeCode(String countryCodeCode);

  @CdsName(PHONE_NUMBER)
  String phoneNumber();

  @CdsName(PHONE_NUMBER)
  Passenger phoneNumber(String phoneNumber);

  @CdsName(EMAIL_ADDRESS)
  String eMailAddress();

  @CdsName(EMAIL_ADDRESS)
  Passenger eMailAddress(String eMailAddress);

  Passenger_ ref();

  static Passenger create() {
    return Struct.create(Passenger.class);
  }

  static Passenger of(Map<String, Object> map) {
    return Struct.access(map).as(Passenger.class);
  }

  static Passenger create(String customerID) {
    Map<String, Object> keys = new HashMap<>();
    keys.put(CUSTOMER_ID, customerID);
    return Struct.access(keys).as(Passenger.class);
  }
}
