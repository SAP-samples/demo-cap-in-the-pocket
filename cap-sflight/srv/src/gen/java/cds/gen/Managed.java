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
 * Aspect to capture changes by user and name
 *
 * See https://cap.cloud.sap/docs/cds/common#aspect-managed
 */
@CdsName("managed")
@Generated(
    value = "cds-maven-plugin",
    date = "2025-05-08T06:34:56.366350Z",
    comments = "com.sap.cds:cds-maven-plugin:3.9.1 / com.sap.cds:cds4j-api:3.9.1"
)
public interface Managed extends CdsData {
  String CREATED_AT = "createdAt";

  String CREATED_BY = "createdBy";

  String MODIFIED_AT = "modifiedAt";

  String MODIFIED_BY = "modifiedBy";

  Instant createdAt();

  Managed createdAt(Instant createdAt);

  /**
   * Canonical user ID
   */
  String createdBy();

  /**
   * Canonical user ID
   */
  Managed createdBy(String createdBy);

  Instant modifiedAt();

  Managed modifiedAt(Instant modifiedAt);

  /**
   * Canonical user ID
   */
  String modifiedBy();

  /**
   * Canonical user ID
   */
  Managed modifiedBy(String modifiedBy);

  static Managed create() {
    return Struct.create(Managed.class);
  }

  static Managed of(Map<String, Object> map) {
    return Struct.access(map).as(Managed.class);
  }
}
