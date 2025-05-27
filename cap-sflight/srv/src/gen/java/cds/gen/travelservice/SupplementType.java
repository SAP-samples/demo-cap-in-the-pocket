package cds.gen.travelservice;

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
@CdsName("TravelService.SupplementType")
@Generated(
    value = "cds-maven-plugin",
    date = "2025-05-08T06:34:56.366350Z",
    comments = "com.sap.cds:cds-maven-plugin:3.9.1 / com.sap.cds:cds4j-api:3.9.1"
)
public interface SupplementType extends CdsData {
  String NAME = "name";

  String DESCR = "descr";

  String CODE = "code";

  String TEXTS = "texts";

  String LOCALIZED = "localized";

  String name();

  SupplementType name(String name);

  String descr();

  SupplementType descr(String descr);

  String code();

  SupplementType code(String code);

  List<SupplementTypeTexts> texts();

  SupplementType texts(List<? extends Map<String, ?>> texts);

  SupplementTypeTexts localized();

  SupplementType localized(Map<String, ?> localized);

  SupplementType_ ref();

  static SupplementType create() {
    return Struct.create(SupplementType.class);
  }

  static SupplementType of(Map<String, Object> map) {
    return Struct.access(map).as(SupplementType.class);
  }

  static SupplementType create(String code) {
    Map<String, Object> keys = new HashMap<>();
    keys.put(CODE, code);
    return Struct.access(keys).as(SupplementType.class);
  }
}
