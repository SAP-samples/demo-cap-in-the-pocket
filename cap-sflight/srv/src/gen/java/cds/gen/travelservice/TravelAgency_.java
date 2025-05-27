package cds.gen.travelservice;

import com.sap.cds.ql.CdsName;
import com.sap.cds.ql.ElementRef;
import com.sap.cds.ql.StructuredType;
import com.sap.cds.ql.cqn.CqnPredicate;
import java.lang.String;
import java.util.function.Function;
import javax.annotation.processing.Generated;

@CdsName("TravelService.TravelAgency")
@Generated(
    value = "cds-maven-plugin",
    date = "2025-05-08T06:34:56.366350Z",
    comments = "com.sap.cds:cds-maven-plugin:3.9.1 / com.sap.cds:cds4j-api:3.9.1"
)
public interface TravelAgency_ extends StructuredType<TravelAgency_> {
  String AGENCY_ID = "AgencyID";

  String NAME = "Name";

  String STREET = "Street";

  String POSTAL_CODE = "PostalCode";

  String CITY = "City";

  String COUNTRY_CODE_CODE = "CountryCode_code";

  String PHONE_NUMBER = "PhoneNumber";

  String EMAIL_ADDRESS = "EMailAddress";

  String WEB_ADDRESS = "WebAddress";

  String CDS_NAME = "TravelService.TravelAgency";

  @CdsName(AGENCY_ID)
  ElementRef<String> AgencyID();

  @CdsName(NAME)
  ElementRef<String> Name();

  @CdsName(STREET)
  ElementRef<String> Street();

  @CdsName(POSTAL_CODE)
  ElementRef<String> PostalCode();

  @CdsName(CITY)
  ElementRef<String> City();

  Countries_ CountryCode();

  Countries_ CountryCode(Function<Countries_, CqnPredicate> filter);

  @CdsName(COUNTRY_CODE_CODE)
  ElementRef<String> CountryCode_code();

  @CdsName(PHONE_NUMBER)
  ElementRef<String> PhoneNumber();

  @CdsName(EMAIL_ADDRESS)
  ElementRef<String> EMailAddress();

  @CdsName(WEB_ADDRESS)
  ElementRef<String> WebAddress();
}
