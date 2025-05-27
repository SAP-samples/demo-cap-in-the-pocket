package cds.gen.sap.fe.cap.travel;

import com.sap.cds.ql.CdsName;
import java.lang.String;
import javax.annotation.processing.Generated;

@CdsName("sap.fe.cap.travel.BookingStatusCode")
@Generated(
    value = "cds-maven-plugin",
    date = "2025-05-08T06:34:56.366350Z",
    comments = "com.sap.cds:cds-maven-plugin:3.9.1 / com.sap.cds:cds4j-api:3.9.1"
)
public final class BookingStatusCode {
  public static final String NEW = "N";

  public static final String BOOKED = "B";

  public static final String CANCELED = "X";

  private BookingStatusCode() {
  }
}
