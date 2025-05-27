package cds.gen.sap.common;

import com.sap.cds.CdsData;
import com.sap.cds.Struct;
import com.sap.cds.ql.CdsName;
import java.lang.Object;
import java.lang.String;
import java.util.Map;
import javax.annotation.processing.Generated;

@CdsName("sap.common.Languages.texts")
@Generated(
    value = "cds-maven-plugin",
    date = "2025-05-08T06:34:56.366350Z",
    comments = "com.sap.cds:cds-maven-plugin:3.9.1 / com.sap.cds:cds4j-api:3.9.1"
)
public interface LanguagesTexts extends CdsData {
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
  LanguagesTexts locale(String locale);

  String name();

  LanguagesTexts name(String name);

  String descr();

  LanguagesTexts descr(String descr);

  /**
   * Type for a language code
   */
  String code();

  /**
   * Type for a language code
   */
  LanguagesTexts code(String code);

  LanguagesTexts_ ref();

  static LanguagesTexts create() {
    return Struct.create(LanguagesTexts.class);
  }

  static LanguagesTexts of(Map<String, Object> map) {
    return Struct.access(map).as(LanguagesTexts.class);
  }
}
