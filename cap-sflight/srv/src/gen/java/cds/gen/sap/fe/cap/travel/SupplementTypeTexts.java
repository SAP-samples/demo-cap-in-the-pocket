package cds.gen.sap.fe.cap.travel;

import com.sap.cds.CdsData;
import com.sap.cds.Struct;
import com.sap.cds.ql.CdsName;
import java.lang.Object;
import java.lang.String;
import java.util.Map;
import javax.annotation.processing.Generated;

@CdsName("sap.fe.cap.travel.SupplementType.texts")
@Generated(
    value = "cds-maven-plugin",
    date = "2025-05-08T06:34:56.366350Z",
    comments = "com.sap.cds:cds-maven-plugin:3.9.1 / com.sap.cds:cds4j-api:3.9.1"
)
public interface SupplementTypeTexts extends CdsData {
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
  SupplementTypeTexts locale(String locale);

  String name();

  SupplementTypeTexts name(String name);

  String descr();

  SupplementTypeTexts descr(String descr);

  String code();

  SupplementTypeTexts code(String code);

  SupplementTypeTexts_ ref();

  static SupplementTypeTexts create() {
    return Struct.create(SupplementTypeTexts.class);
  }

  static SupplementTypeTexts of(Map<String, Object> map) {
    return Struct.access(map).as(SupplementTypeTexts.class);
  }
}
