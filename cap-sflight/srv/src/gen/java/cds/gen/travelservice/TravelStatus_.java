package cds.gen.travelservice;

import com.sap.cds.ql.CdsName;
import com.sap.cds.ql.ElementRef;
import com.sap.cds.ql.StructuredType;
import com.sap.cds.ql.cqn.CqnPredicate;
import java.lang.Boolean;
import java.lang.Short;
import java.lang.String;
import java.util.function.Function;
import javax.annotation.processing.Generated;

/**
 * Aspect for a code list with name and description
 *
 * See https://cap.cloud.sap/docs/cds/common#aspect-codelist
 */
@CdsName("TravelService.TravelStatus")
@Generated(
    value = "cds-maven-plugin",
    date = "2025-05-08T06:34:56.366350Z",
    comments = "com.sap.cds:cds-maven-plugin:3.9.1 / com.sap.cds:cds4j-api:3.9.1"
)
public interface TravelStatus_ extends StructuredType<TravelStatus_> {
  String CDS_NAME = "TravelService.TravelStatus";

  ElementRef<String> name();

  ElementRef<String> descr();

  ElementRef<String> code();

  ElementRef<Short> fieldControl();

  ElementRef<Boolean> createDeleteHidden();

  ElementRef<Boolean> insertDeleteRestriction();

  TravelStatusTexts_ texts();

  TravelStatusTexts_ texts(Function<TravelStatusTexts_, CqnPredicate> filter);

  TravelStatusTexts_ localized();

  TravelStatusTexts_ localized(Function<TravelStatusTexts_, CqnPredicate> filter);
}
