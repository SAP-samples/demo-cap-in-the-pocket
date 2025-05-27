package cds.gen.analyticsservice;

import com.sap.cds.ql.CdsName;
import com.sap.cds.ql.ElementRef;
import com.sap.cds.ql.StructuredType;
import com.sap.cds.ql.cqn.CqnPredicate;
import java.lang.String;
import java.util.function.Function;
import javax.annotation.processing.Generated;

@CdsName("AnalyticsService.Airline")
@Generated(
    value = "cds-maven-plugin",
    date = "2025-05-08T06:34:56.366350Z",
    comments = "com.sap.cds:cds-maven-plugin:3.9.1 / com.sap.cds:cds4j-api:3.9.1"
)
public interface Airline_ extends StructuredType<Airline_> {
  String AIRLINE_ID = "AirlineID";

  String NAME = "Name";

  String CURRENCY_CODE_CODE = "CurrencyCode_code";

  String AIRLINE_PIC_URL = "AirlinePicURL";

  String CDS_NAME = "AnalyticsService.Airline";

  @CdsName(AIRLINE_ID)
  ElementRef<String> AirlineID();

  @CdsName(NAME)
  ElementRef<String> Name();

  Currencies_ CurrencyCode();

  Currencies_ CurrencyCode(Function<Currencies_, CqnPredicate> filter);

  @CdsName(CURRENCY_CODE_CODE)
  ElementRef<String> CurrencyCode_code();

  @CdsName(AIRLINE_PIC_URL)
  ElementRef<String> AirlinePicURL();
}
