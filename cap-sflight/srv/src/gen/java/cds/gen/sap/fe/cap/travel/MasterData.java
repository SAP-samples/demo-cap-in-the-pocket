package cds.gen.sap.fe.cap.travel;

import com.sap.cds.CdsData;
import com.sap.cds.Struct;
import com.sap.cds.ql.CdsName;
import java.lang.Object;
import java.lang.String;
import java.util.Map;
import javax.annotation.processing.Generated;

@CdsName("sap.fe.cap.travel.MasterData")
@Generated(
    value = "cds-maven-plugin",
    date = "2025-05-08T06:34:56.366350Z",
    comments = "com.sap.cds:cds-maven-plugin:3.9.1 / com.sap.cds:cds4j-api:3.9.1"
)
public interface MasterData extends CdsData {
  static MasterData create() {
    return Struct.create(MasterData.class);
  }

  static MasterData of(Map<String, Object> map) {
    return Struct.access(map).as(MasterData.class);
  }
}
