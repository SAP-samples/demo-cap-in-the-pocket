package cds.gen.analyticsservice;

import com.sap.cds.ql.CdsName;
import com.sap.cds.ql.ElementRef;
import com.sap.cds.ql.StructuredType;
import com.sap.cds.ql.cqn.CqnPredicate;
import java.lang.String;
import java.util.function.Function;
import javax.annotation.processing.Generated;

/**
 * Code list for countries
 *
 * See https://cap.cloud.sap/docs/cds/common#entity-countries
 */
@CdsName("AnalyticsService.Countries")
@Generated(
    value = "cds-maven-plugin",
    date = "2025-05-08T06:34:56.366350Z",
    comments = "com.sap.cds:cds-maven-plugin:3.9.1 / com.sap.cds:cds4j-api:3.9.1"
)
public interface Countries_ extends StructuredType<Countries_> {
  String CDS_NAME = "AnalyticsService.Countries";

  ElementRef<String> name();

  ElementRef<String> descr();

  ElementRef<String> code();

  CountriesTexts_ texts();

  CountriesTexts_ texts(Function<CountriesTexts_, CqnPredicate> filter);

  CountriesTexts_ localized();

  CountriesTexts_ localized(Function<CountriesTexts_, CqnPredicate> filter);
}
