package cds.gen.sap.common;

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
 * Code list for time zones
 *
 * See https://cap.cloud.sap/docs/cds/common#entity-timezones
 */
@CdsName("sap.common.Timezones")
@Generated(
    value = "cds-maven-plugin",
    date = "2025-05-08T06:34:56.366350Z",
    comments = "com.sap.cds:cds-maven-plugin:3.9.1 / com.sap.cds:cds4j-api:3.9.1"
)
public interface Timezones extends CdsData {
  String NAME = "name";

  String DESCR = "descr";

  String CODE = "code";

  String TEXTS = "texts";

  String LOCALIZED = "localized";

  String name();

  Timezones name(String name);

  String descr();

  Timezones descr(String descr);

  String code();

  Timezones code(String code);

  List<TimezonesTexts> texts();

  Timezones texts(List<? extends Map<String, ?>> texts);

  TimezonesTexts localized();

  Timezones localized(Map<String, ?> localized);

  Timezones_ ref();

  static Timezones create() {
    return Struct.create(Timezones.class);
  }

  static Timezones of(Map<String, Object> map) {
    return Struct.access(map).as(Timezones.class);
  }

  static Timezones create(String code) {
    Map<String, Object> keys = new HashMap<>();
    keys.put(CODE, code);
    return Struct.access(keys).as(Timezones.class);
  }
}
