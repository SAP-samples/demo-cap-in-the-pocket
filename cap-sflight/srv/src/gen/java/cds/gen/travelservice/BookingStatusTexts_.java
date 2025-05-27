package cds.gen.travelservice;

import com.sap.cds.ql.CdsName;
import com.sap.cds.ql.ElementRef;
import com.sap.cds.ql.StructuredType;
import java.lang.String;
import javax.annotation.processing.Generated;

@CdsName("TravelService.BookingStatus.texts")
@Generated(
    value = "cds-maven-plugin",
    date = "2025-05-08T06:34:56.366350Z",
    comments = "com.sap.cds:cds-maven-plugin:3.9.1 / com.sap.cds:cds4j-api:3.9.1"
)
public interface BookingStatusTexts_ extends StructuredType<BookingStatusTexts_> {
  String CDS_NAME = "TravelService.BookingStatus.texts";

  ElementRef<String> locale();

  ElementRef<String> name();

  ElementRef<String> descr();

  ElementRef<String> code();
}
