package cds.gen.sap.fe.cap.travel;

import com.sap.cds.CdsData;
import com.sap.cds.Struct;
import com.sap.cds.ql.CdsName;
import java.lang.Boolean;
import java.lang.Object;
import java.lang.Short;
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
@CdsName("sap.fe.cap.travel.TravelStatus")
@Generated(
    value = "cds-maven-plugin",
    date = "2025-05-08T06:34:56.366350Z",
    comments = "com.sap.cds:cds-maven-plugin:3.9.1 / com.sap.cds:cds4j-api:3.9.1"
)
public interface TravelStatus extends CdsData {
  String NAME = "name";

  String DESCR = "descr";

  String CODE = "code";

  String FIELD_CONTROL = "fieldControl";

  String CREATE_DELETE_HIDDEN = "createDeleteHidden";

  String INSERT_DELETE_RESTRICTION = "insertDeleteRestriction";

  String TEXTS = "texts";

  String LOCALIZED = "localized";

  String name();

  TravelStatus name(String name);

  String descr();

  TravelStatus descr(String descr);

  String code();

  TravelStatus code(String code);

  Short fieldControl();

  TravelStatus fieldControl(Short fieldControl);

  Boolean createDeleteHidden();

  TravelStatus createDeleteHidden(Boolean createDeleteHidden);

  Boolean insertDeleteRestriction();

  TravelStatus insertDeleteRestriction(Boolean insertDeleteRestriction);

  List<TravelStatusTexts> texts();

  TravelStatus texts(List<? extends Map<String, ?>> texts);

  TravelStatusTexts localized();

  TravelStatus localized(Map<String, ?> localized);

  TravelStatus_ ref();

  static TravelStatus create() {
    return Struct.create(TravelStatus.class);
  }

  static TravelStatus of(Map<String, Object> map) {
    return Struct.access(map).as(TravelStatus.class);
  }

  static TravelStatus create(String code) {
    Map<String, Object> keys = new HashMap<>();
    keys.put(CODE, code);
    return Struct.access(keys).as(TravelStatus.class);
  }
}
