package cds.gen.sap.fe.cap.travel;

import com.sap.cds.CdsData;
import com.sap.cds.Struct;
import com.sap.cds.ql.CdsName;
import java.lang.Object;
import java.lang.String;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.processing.Generated;

/**
 * Aspect for a code list with name and description
 *
 * See https://cap.cloud.sap/docs/cds/common#aspect-codelist
 */
@CdsName("sap.fe.cap.travel.BookingStatus")
@Generated(
    value = "cds-maven-plugin",
    date = "2025-05-08T06:34:56.366350Z",
    comments = "com.sap.cds:cds-maven-plugin:3.9.1 / com.sap.cds:cds4j-api:3.9.1"
)
public interface BookingStatus extends CdsData {
  String NAME = "name";

  String DESCR = "descr";

  String CODE = "code";

  String TEXTS = "texts";

  String LOCALIZED = "localized";

  String name();

  BookingStatus name(String name);

  String descr();

  BookingStatus descr(String descr);

  String code();

  BookingStatus code(String code);

  List<BookingStatusTexts> texts();

  BookingStatus texts(List<? extends Map<String, ?>> texts);

  BookingStatusTexts localized();

  BookingStatus localized(Map<String, ?> localized);

  BookingStatus_ ref();

  static BookingStatus create() {
    return Struct.create(BookingStatus.class);
  }

  static BookingStatus of(Map<String, Object> map) {
    return Struct.access(map).as(BookingStatus.class);
  }

  static BookingStatus create(String code) {
    Map<String, Object> keys = new HashMap<>();
    keys.put(CODE, code);
    return Struct.access(keys).as(BookingStatus.class);
  }
}
