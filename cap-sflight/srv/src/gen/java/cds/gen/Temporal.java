package cds.gen;

import com.sap.cds.CdsData;
import com.sap.cds.Struct;
import com.sap.cds.ql.CdsName;
import java.lang.Object;
import java.lang.String;
import java.time.Instant;
import java.util.Map;
import javax.annotation.processing.Generated;

/**
 * Aspect for entities with temporal data
 *
 * See https://cap.cloud.sap/docs/cds/common#aspect-temporal
 */
@CdsName("temporal")
@Generated(
    value = "cds-maven-plugin",
    date = "2025-05-08T06:34:56.366350Z",
    comments = "com.sap.cds:cds-maven-plugin:3.9.1 / com.sap.cds:cds4j-api:3.9.1"
)
public interface Temporal extends CdsData {
  String VALID_FROM = "validFrom";

  String VALID_TO = "validTo";

  Instant validFrom();

  Temporal validFrom(Instant validFrom);

  Instant validTo();

  Temporal validTo(Instant validTo);

  static Temporal create() {
    return Struct.create(Temporal.class);
  }

  static Temporal of(Map<String, Object> map) {
    return Struct.access(map).as(Temporal.class);
  }
}
