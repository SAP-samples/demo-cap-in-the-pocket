package cds.gen.travelservice;

import com.sap.cds.CdsData;
import com.sap.cds.Struct;
import com.sap.cds.ql.CdsName;
import java.lang.Object;
import java.lang.String;
import java.util.Map;
import javax.annotation.processing.Generated;

@CdsName("TravelService.BookingStatus.texts")
@Generated(
    value = "cds-maven-plugin",
    date = "2025-05-08T06:34:56.366350Z",
    comments = "com.sap.cds:cds-maven-plugin:3.9.1 / com.sap.cds:cds4j-api:3.9.1"
)
public interface BookingStatusTexts extends CdsData {
  String LOCALE = "locale";

  String NAME = "name";

  String DESCR = "descr";

  String CODE = "code";

  /**
   * Type for a language code
   */
  String locale();

  /**
   * Type for a language code
   */
  BookingStatusTexts locale(String locale);

  String name();

  BookingStatusTexts name(String name);

  String descr();

  BookingStatusTexts descr(String descr);

  String code();

  BookingStatusTexts code(String code);

  BookingStatusTexts_ ref();

  static BookingStatusTexts create() {
    return Struct.create(BookingStatusTexts.class);
  }

  static BookingStatusTexts of(Map<String, Object> map) {
    return Struct.access(map).as(BookingStatusTexts.class);
  }
}
