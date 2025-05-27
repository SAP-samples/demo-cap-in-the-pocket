package cds.gen.analyticsservice;

import com.sap.cds.CdsData;
import com.sap.cds.Struct;
import com.sap.cds.ql.CdsName;
import java.lang.Object;
import java.lang.String;
import java.util.HashMap;
import java.util.Map;
import javax.annotation.processing.Generated;

@CdsName("AnalyticsService.Airport")
@Generated(
    value = "cds-maven-plugin",
    date = "2025-05-08T06:34:56.366350Z",
    comments = "com.sap.cds:cds-maven-plugin:3.9.1 / com.sap.cds:cds4j-api:3.9.1"
)
public interface Airport extends CdsData {
  String AIRPORT_ID = "AirportID";

  String NAME = "Name";

  String CITY = "City";

  String COUNTRY_CODE = "CountryCode";

  String COUNTRY_CODE_CODE = "CountryCode_code";

  @CdsName(AIRPORT_ID)
  String airportID();

  @CdsName(AIRPORT_ID)
  Airport airportID(String airportID);

  @CdsName(NAME)
  String name();

  @CdsName(NAME)
  Airport name(String name);

  @CdsName(CITY)
  String city();

  @CdsName(CITY)
  Airport city(String city);

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
  Airport countryCode(Map<String, ?> countryCode);

  @CdsName(COUNTRY_CODE_CODE)
  String countryCodeCode();

  @CdsName(COUNTRY_CODE_CODE)
  Airport countryCodeCode(String countryCodeCode);

  Airport_ ref();

  static Airport create() {
    return Struct.create(Airport.class);
  }

  static Airport of(Map<String, Object> map) {
    return Struct.access(map).as(Airport.class);
  }

  static Airport create(String airportID) {
    Map<String, Object> keys = new HashMap<>();
    keys.put(AIRPORT_ID, airportID);
    return Struct.access(keys).as(Airport.class);
  }
}
