package cds.gen.sap.common;

import com.sap.cds.CdsData;
import com.sap.cds.Struct;
import com.sap.cds.ql.CdsName;
import java.lang.Object;
import java.lang.String;
import java.util.Map;
import javax.annotation.processing.Generated;

/**
 * Aspect for a code list with name and description
 *
 * See https://cap.cloud.sap/docs/cds/common#aspect-codelist
 */
@CdsName("sap.common.CodeList")
@Generated(
    value = "cds-maven-plugin",
    date = "2025-05-08T06:34:56.366350Z",
    comments = "com.sap.cds:cds-maven-plugin:3.9.1 / com.sap.cds:cds4j-api:3.9.1"
)
public interface CodeList extends CdsData {
  String NAME = "name";

  String DESCR = "descr";

  String name();

  CodeList name(String name);

  String descr();

  CodeList descr(String descr);

  static CodeList create() {
    return Struct.create(CodeList.class);
  }

  static CodeList of(Map<String, Object> map) {
    return Struct.access(map).as(CodeList.class);
  }
}
