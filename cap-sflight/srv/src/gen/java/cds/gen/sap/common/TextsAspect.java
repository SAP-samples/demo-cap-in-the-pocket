package cds.gen.sap.common;

import com.sap.cds.CdsData;
import com.sap.cds.Struct;
import com.sap.cds.ql.CdsName;
import java.lang.Object;
import java.lang.String;
import java.util.Map;
import javax.annotation.processing.Generated;

@CdsName("sap.common.TextsAspect")
@Generated(
    value = "cds-maven-plugin",
    date = "2025-05-08T06:34:56.366350Z",
    comments = "com.sap.cds:cds-maven-plugin:3.9.1 / com.sap.cds:cds4j-api:3.9.1"
)
public interface TextsAspect extends CdsData {
  String LOCALE = "locale";

  /**
   * Type for a language code
   */
  String locale();

  /**
   * Type for a language code
   */
  TextsAspect locale(String locale);

  static TextsAspect create() {
    return Struct.create(TextsAspect.class);
  }

  static TextsAspect of(Map<String, Object> map) {
    return Struct.access(map).as(TextsAspect.class);
  }
}
