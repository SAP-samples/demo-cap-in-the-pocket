package cds.gen.analyticsservice;

import com.sap.cds.ql.CdsName;
import com.sap.cds.ql.ElementRef;
import com.sap.cds.ql.StructuredType;
import com.sap.cds.ql.cqn.CqnPredicate;
import java.lang.String;
import java.time.Instant;
import java.util.function.Function;
import javax.annotation.processing.Generated;

@CdsName("AnalyticsService.Passenger")
@Generated(
    value = "cds-maven-plugin",
    date = "2025-05-08T06:34:56.366350Z",
    comments = "com.sap.cds:cds-maven-plugin:3.9.1 / com.sap.cds:cds4j-api:3.9.1"
)
public interface Passenger_ extends StructuredType<Passenger_> {
  String LAST_CHANGED_AT = "LastChangedAt";

  String LAST_CHANGED_BY = "LastChangedBy";

  String CUSTOMER_ID = "CustomerID";

  String FIRST_NAME = "FirstName";

  String LAST_NAME = "LastName";

  String TITLE = "Title";

  String STREET = "Street";

  String POSTAL_CODE = "PostalCode";

  String CITY = "City";

  String COUNTRY_CODE_CODE = "CountryCode_code";

  String PHONE_NUMBER = "PhoneNumber";

  String EMAIL_ADDRESS = "EMailAddress";

  String CDS_NAME = "AnalyticsService.Passenger";

  ElementRef<Instant> createdAt();

  ElementRef<String> createdBy();

  @CdsName(LAST_CHANGED_AT)
  ElementRef<Instant> LastChangedAt();

  @CdsName(LAST_CHANGED_BY)
  ElementRef<String> LastChangedBy();

  @CdsName(CUSTOMER_ID)
  ElementRef<String> CustomerID();

  @CdsName(FIRST_NAME)
  ElementRef<String> FirstName();

  @CdsName(LAST_NAME)
  ElementRef<String> LastName();

  @CdsName(TITLE)
  ElementRef<String> Title();

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
}
