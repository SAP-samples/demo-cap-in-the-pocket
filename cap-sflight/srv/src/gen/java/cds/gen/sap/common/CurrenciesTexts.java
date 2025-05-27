package cds.gen.sap.common;

import com.sap.cds.CdsData;
import com.sap.cds.Struct;
import com.sap.cds.ql.CdsName;
import java.lang.Object;
import java.lang.String;
import java.util.Map;
import javax.annotation.processing.Generated;

@CdsName("sap.common.Currencies.texts")
@Generated(
    value = "cds-maven-plugin",
    date = "2025-05-08T06:34:56.366350Z",
    comments = "com.sap.cds:cds-maven-plugin:3.9.1 / com.sap.cds:cds4j-api:3.9.1"
)
public interface CurrenciesTexts extends CdsData {
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
  CurrenciesTexts locale(String locale);

  String name();

  CurrenciesTexts name(String name);

  String descr();

  CurrenciesTexts descr(String descr);

  String code();

  CurrenciesTexts code(String code);

  CurrenciesTexts_ ref();

  static CurrenciesTexts create() {
    return Struct.create(CurrenciesTexts.class);
  }

  static CurrenciesTexts of(Map<String, Object> map) {
    return Struct.access(map).as(CurrenciesTexts.class);
  }
}
