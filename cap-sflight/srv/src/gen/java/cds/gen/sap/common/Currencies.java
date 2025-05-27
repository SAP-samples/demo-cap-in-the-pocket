package cds.gen.sap.common;

import com.sap.cds.CdsData;
import com.sap.cds.Struct;
import com.sap.cds.ql.CdsName;
import java.lang.Integer;
import java.lang.Object;
import java.lang.Short;
import java.lang.String;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.processing.Generated;

/**
 * Code list for currencies
 *
 * See https://cap.cloud.sap/docs/cds/common#entity-currencies
 */
@CdsName("sap.common.Currencies")
@Generated(
    value = "cds-maven-plugin",
    date = "2025-05-08T06:34:56.366350Z",
    comments = "com.sap.cds:cds-maven-plugin:3.9.1 / com.sap.cds:cds4j-api:3.9.1"
)
public interface Currencies extends CdsData {
  String NAME = "name";

  String DESCR = "descr";

  String CODE = "code";

  String SYMBOL = "symbol";

  String MINOR_UNIT = "minorUnit";

  String NUMCODE = "numcode";

  String EXPONENT = "exponent";

  String MINOR = "minor";

  String TEXTS = "texts";

  String LOCALIZED = "localized";

  String name();

  Currencies name(String name);

  String descr();

  Currencies descr(String descr);

  String code();

  Currencies code(String code);

  String symbol();

  Currencies symbol(String symbol);

  Short minorUnit();

  Currencies minorUnit(Short minorUnit);

  Integer numcode();

  Currencies numcode(Integer numcode);

  Integer exponent();

  Currencies exponent(Integer exponent);

  String minor();

  Currencies minor(String minor);

  List<CurrenciesTexts> texts();

  Currencies texts(List<? extends Map<String, ?>> texts);

  CurrenciesTexts localized();

  Currencies localized(Map<String, ?> localized);

  Currencies_ ref();

  static Currencies create() {
    return Struct.create(Currencies.class);
  }

  static Currencies of(Map<String, Object> map) {
    return Struct.access(map).as(Currencies.class);
  }

  static Currencies create(String code) {
    Map<String, Object> keys = new HashMap<>();
    keys.put(CODE, code);
    return Struct.access(keys).as(Currencies.class);
  }
}
