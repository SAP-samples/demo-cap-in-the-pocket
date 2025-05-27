package cds.gen.travelservice;

import com.sap.cds.ql.CdsName;
import com.sap.cds.ql.ElementRef;
import com.sap.cds.ql.StructuredType;
import com.sap.cds.ql.cqn.CqnPredicate;
import java.lang.String;
import java.util.function.Function;
import javax.annotation.processing.Generated;

/**
 * Aspect for a code list with name and description
 *
 * See https://cap.cloud.sap/docs/cds/common#aspect-codelist
 */
@CdsName("TravelService.SupplementType")
@Generated(
    value = "cds-maven-plugin",
    date = "2025-05-08T06:34:56.366350Z",
    comments = "com.sap.cds:cds-maven-plugin:3.9.1 / com.sap.cds:cds4j-api:3.9.1"
)
public interface SupplementType_ extends StructuredType<SupplementType_> {
  String CDS_NAME = "TravelService.SupplementType";

  ElementRef<String> name();

  ElementRef<String> descr();

  ElementRef<String> code();

  SupplementTypeTexts_ texts();

  SupplementTypeTexts_ texts(Function<SupplementTypeTexts_, CqnPredicate> filter);

  SupplementTypeTexts_ localized();

  SupplementTypeTexts_ localized(Function<SupplementTypeTexts_, CqnPredicate> filter);
}
