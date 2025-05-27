package cds.gen.sap.fe.cap.travel;

import cds.gen.sap.common.Currencies;
import com.sap.cds.CdsData;
import com.sap.cds.Struct;
import com.sap.cds.ql.CdsName;
import java.lang.Object;
import java.lang.String;
import java.util.HashMap;
import java.util.Map;
import javax.annotation.processing.Generated;

@CdsName("sap.fe.cap.travel.Airline")
@Generated(
    value = "cds-maven-plugin",
    date = "2025-05-08T06:34:56.366350Z",
    comments = "com.sap.cds:cds-maven-plugin:3.9.1 / com.sap.cds:cds4j-api:3.9.1"
)
public interface Airline extends CdsData {
  String AIRLINE_ID = "AirlineID";

  String NAME = "Name";

  String CURRENCY_CODE = "CurrencyCode";

  String CURRENCY_CODE_CODE = "CurrencyCode_code";

  String AIRLINE_PIC_URL = "AirlinePicURL";

  @CdsName(AIRLINE_ID)
  String airlineID();

  @CdsName(AIRLINE_ID)
  Airline airlineID(String airlineID);

  @CdsName(NAME)
  String name();

  @CdsName(NAME)
  Airline name(String name);

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
  Airline currencyCode(Map<String, ?> currencyCode);

  @CdsName(CURRENCY_CODE_CODE)
  String currencyCodeCode();

  @CdsName(CURRENCY_CODE_CODE)
  Airline currencyCodeCode(String currencyCodeCode);

  @CdsName(AIRLINE_PIC_URL)
  String airlinePicURL();

  @CdsName(AIRLINE_PIC_URL)
  Airline airlinePicURL(String airlinePicURL);

  Airline_ ref();

  static Airline create() {
    return Struct.create(Airline.class);
  }

  static Airline of(Map<String, Object> map) {
    return Struct.access(map).as(Airline.class);
  }

  static Airline create(String airlineID) {
    Map<String, Object> keys = new HashMap<>();
    keys.put(AIRLINE_ID, airlineID);
    return Struct.access(keys).as(Airline.class);
  }
}
