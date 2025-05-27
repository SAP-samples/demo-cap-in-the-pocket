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
 * Code list for languages
 *
 * See https://cap.cloud.sap/docs/cds/common#entity-languages
 */
@CdsName("sap.common.Languages")
@Generated(
    value = "cds-maven-plugin",
    date = "2025-05-08T06:34:56.366350Z",
    comments = "com.sap.cds:cds-maven-plugin:3.9.1 / com.sap.cds:cds4j-api:3.9.1"
)
public interface Languages extends CdsData {
  String NAME = "name";

  String DESCR = "descr";

  String CODE = "code";

  String TEXTS = "texts";

  String LOCALIZED = "localized";

  String name();

  Languages name(String name);

  String descr();

  Languages descr(String descr);

  /**
   * Type for a language code
   */
  String code();

  /**
   * Type for a language code
   */
  Languages code(String code);

  List<LanguagesTexts> texts();

  Languages texts(List<? extends Map<String, ?>> texts);

  LanguagesTexts localized();

  Languages localized(Map<String, ?> localized);

  Languages_ ref();

  static Languages create() {
    return Struct.create(Languages.class);
  }

  static Languages of(Map<String, Object> map) {
    return Struct.access(map).as(Languages.class);
  }

  static Languages create(String code) {
    Map<String, Object> keys = new HashMap<>();
    keys.put(CODE, code);
    return Struct.access(keys).as(Languages.class);
  }
}
