package cds.gen.sap.fe.cap.travel;

import cds.gen.sap.common.Countries_;
import com.sap.cds.ql.CdsName;
import com.sap.cds.ql.ElementRef;
import com.sap.cds.ql.StructuredType;
import com.sap.cds.ql.cqn.CqnPredicate;
import java.lang.String;
import java.util.function.Function;
import javax.annotation.processing.Generated;

@CdsName("sap.fe.cap.travel.Airport")
@Generated(
    value = "cds-maven-plugin",
    date = "2025-05-08T06:34:56.366350Z",
    comments = "com.sap.cds:cds-maven-plugin:3.9.1 / com.sap.cds:cds4j-api:3.9.1"
)
public interface Airport_ extends StructuredType<Airport_> {
  String AIRPORT_ID = "AirportID";

  String NAME = "Name";

  String CITY = "City";

  String COUNTRY_CODE_CODE = "CountryCode_code";

  String CDS_NAME = "sap.fe.cap.travel.Airport";

  @CdsName(AIRPORT_ID)
  ElementRef<String> AirportID();

  @CdsName(NAME)
  ElementRef<String> Name();

  @CdsName(CITY)
  ElementRef<String> City();

  Countries_ CountryCode();

  Countries_ CountryCode(Function<Countries_, CqnPredicate> filter);

  @CdsName(COUNTRY_CODE_CODE)
  ElementRef<String> CountryCode_code();
}
