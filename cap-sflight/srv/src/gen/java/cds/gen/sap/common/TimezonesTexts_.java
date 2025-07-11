package cds.gen.sap.common;

import com.sap.cds.ql.CdsName;
import com.sap.cds.ql.ElementRef;
import com.sap.cds.ql.StructuredType;
import java.lang.String;
import javax.annotation.processing.Generated;

@CdsName("sap.common.Timezones.texts")
@Generated(
    value = "cds-maven-plugin",
    date = "2025-05-08T06:34:56.366350Z",
    comments = "com.sap.cds:cds-maven-plugin:3.9.1 / com.sap.cds:cds4j-api:3.9.1"
)
public interface TimezonesTexts_ extends StructuredType<TimezonesTexts_> {
  String CDS_NAME = "sap.common.Timezones.texts";

  ElementRef<String> locale();

  ElementRef<String> name();

  ElementRef<String> descr();

  ElementRef<String> code();
}
