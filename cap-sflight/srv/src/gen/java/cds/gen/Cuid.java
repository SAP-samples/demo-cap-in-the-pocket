package cds.gen;

import com.sap.cds.CdsData;
import com.sap.cds.Struct;
import com.sap.cds.ql.CdsName;
import java.lang.Object;
import java.lang.String;
import java.util.Map;
import javax.annotation.processing.Generated;

/**
 * Aspect for entities with canonical universal IDs
 *
 * See https://cap.cloud.sap/docs/cds/common#aspect-cuid
 */
@CdsName("cuid")
@Generated(
    value = "cds-maven-plugin",
    date = "2025-05-08T06:34:56.366350Z",
    comments = "com.sap.cds:cds-maven-plugin:3.9.1 / com.sap.cds:cds4j-api:3.9.1"
)
public interface Cuid extends CdsData {
  String ID = "ID";

  @CdsName(ID)
  String id();

  @CdsName(ID)
  Cuid id(String id);

  static Cuid create() {
    return Struct.create(Cuid.class);
  }

  static Cuid of(Map<String, Object> map) {
    return Struct.access(map).as(Cuid.class);
  }
}
