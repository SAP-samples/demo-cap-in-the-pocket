package cds.gen.sap.fe.cap.travel;

import cds.gen.sap.common.Countries;
import com.sap.cds.CdsData;
import com.sap.cds.Struct;
import com.sap.cds.ql.CdsName;
import java.lang.Object;
import java.lang.String;
import java.util.HashMap;
import java.util.Map;
import javax.annotation.processing.Generated;

@CdsName("sap.fe.cap.travel.TravelAgency")
@Generated(
    value = "cds-maven-plugin",
    date = "2025-05-08T06:34:56.366350Z",
    comments = "com.sap.cds:cds-maven-plugin:3.9.1 / com.sap.cds:cds4j-api:3.9.1"
)
public interface TravelAgency extends CdsData {
  String AGENCY_ID = "AgencyID";

  String NAME = "Name";

  String STREET = "Street";

  String POSTAL_CODE = "PostalCode";

  String CITY = "City";

  String COUNTRY_CODE = "CountryCode";

  String COUNTRY_CODE_CODE = "CountryCode_code";

  String PHONE_NUMBER = "PhoneNumber";

  String EMAIL_ADDRESS = "EMailAddress";

  String WEB_ADDRESS = "WebAddress";

  @CdsName(AGENCY_ID)
  String agencyID();

  @CdsName(AGENCY_ID)
  TravelAgency agencyID(String agencyID);

  @CdsName(NAME)
  String name();

  @CdsName(NAME)
  TravelAgency name(String name);

  @CdsName(STREET)
  String street();

  @CdsName(STREET)
  TravelAgency street(String street);

  @CdsName(POSTAL_CODE)
  String postalCode();

  @CdsName(POSTAL_CODE)
  TravelAgency postalCode(String postalCode);

  @CdsName(CITY)
  String city();

  @CdsName(CITY)
  TravelAgency city(String city);

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
  TravelAgency countryCode(Map<String, ?> countryCode);

  @CdsName(COUNTRY_CODE_CODE)
  String countryCodeCode();

  @CdsName(COUNTRY_CODE_CODE)
  TravelAgency countryCodeCode(String countryCodeCode);

  @CdsName(PHONE_NUMBER)
  String phoneNumber();

  @CdsName(PHONE_NUMBER)
  TravelAgency phoneNumber(String phoneNumber);

  @CdsName(EMAIL_ADDRESS)
  String eMailAddress();

  @CdsName(EMAIL_ADDRESS)
  TravelAgency eMailAddress(String eMailAddress);

  @CdsName(WEB_ADDRESS)
  String webAddress();

  @CdsName(WEB_ADDRESS)
  TravelAgency webAddress(String webAddress);

  TravelAgency_ ref();

  static TravelAgency create() {
    return Struct.create(TravelAgency.class);
  }

  static TravelAgency of(Map<String, Object> map) {
    return Struct.access(map).as(TravelAgency.class);
  }

  static TravelAgency create(String agencyID) {
    Map<String, Object> keys = new HashMap<>();
    keys.put(AGENCY_ID, agencyID);
    return Struct.access(keys).as(TravelAgency.class);
  }
}
