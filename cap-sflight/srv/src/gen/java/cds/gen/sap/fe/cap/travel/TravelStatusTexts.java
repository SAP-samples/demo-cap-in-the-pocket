package cds.gen.sap.fe.cap.travel;

import com.sap.cds.CdsData;
import com.sap.cds.Struct;
import com.sap.cds.ql.CdsName;
import java.lang.Object;
import java.lang.String;
import java.util.Map;
import javax.annotation.processing.Generated;

@CdsName("sap.fe.cap.travel.TravelStatus.texts")
@Generated(
    value = "cds-maven-plugin",
    date = "2025-05-08T06:34:56.366350Z",
    comments = "com.sap.cds:cds-maven-plugin:3.9.1 / com.sap.cds:cds4j-api:3.9.1"
)
public interface TravelStatusTexts extends CdsData {
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
  TravelStatusTexts locale(String locale);

  String name();

  TravelStatusTexts name(String name);

  String descr();

  TravelStatusTexts descr(String descr);

  String code();

  TravelStatusTexts code(String code);

  TravelStatusTexts_ ref();

  static TravelStatusTexts create() {
    return Struct.create(TravelStatusTexts.class);
  }

  static TravelStatusTexts of(Map<String, Object> map) {
    return Struct.access(map).as(TravelStatusTexts.class);
  }
}
