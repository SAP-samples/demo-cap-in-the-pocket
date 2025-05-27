package cds.gen.travelservice;

import com.sap.cds.CdsData;
import com.sap.cds.Struct;
import com.sap.cds.ql.CdsName;
import java.lang.Boolean;
import java.lang.Object;
import java.lang.String;
import java.time.Instant;
import java.util.HashMap;
import java.util.Map;
import javax.annotation.processing.Generated;

@CdsName("TravelService.DraftAdministrativeData")
@Generated(
    value = "cds-maven-plugin",
    date = "2025-05-08T06:34:56.366350Z",
    comments = "com.sap.cds:cds-maven-plugin:3.9.1 / com.sap.cds:cds4j-api:3.9.1"
)
public interface DraftAdministrativeData extends CdsData {
  String DRAFT_UUID = "DraftUUID";

  String CREATION_DATE_TIME = "CreationDateTime";

  String CREATED_BY_USER = "CreatedByUser";

  String DRAFT_IS_CREATED_BY_ME = "DraftIsCreatedByMe";

  String LAST_CHANGE_DATE_TIME = "LastChangeDateTime";

  String LAST_CHANGED_BY_USER = "LastChangedByUser";

  String IN_PROCESS_BY_USER = "InProcessByUser";

  String DRAFT_IS_PROCESSED_BY_ME = "DraftIsProcessedByMe";

  @CdsName(DRAFT_UUID)
  String draftUUID();

  @CdsName(DRAFT_UUID)
  DraftAdministrativeData draftUUID(String draftUUID);

  @CdsName(CREATION_DATE_TIME)
  Instant creationDateTime();

  @CdsName(CREATION_DATE_TIME)
  DraftAdministrativeData creationDateTime(Instant creationDateTime);

  @CdsName(CREATED_BY_USER)
  String createdByUser();

  @CdsName(CREATED_BY_USER)
  DraftAdministrativeData createdByUser(String createdByUser);

  @CdsName(DRAFT_IS_CREATED_BY_ME)
  Boolean draftIsCreatedByMe();

  @CdsName(DRAFT_IS_CREATED_BY_ME)
  DraftAdministrativeData draftIsCreatedByMe(Boolean draftIsCreatedByMe);

  @CdsName(LAST_CHANGE_DATE_TIME)
  Instant lastChangeDateTime();

  @CdsName(LAST_CHANGE_DATE_TIME)
  DraftAdministrativeData lastChangeDateTime(Instant lastChangeDateTime);

  @CdsName(LAST_CHANGED_BY_USER)
  String lastChangedByUser();

  @CdsName(LAST_CHANGED_BY_USER)
  DraftAdministrativeData lastChangedByUser(String lastChangedByUser);

  @CdsName(IN_PROCESS_BY_USER)
  String inProcessByUser();

  @CdsName(IN_PROCESS_BY_USER)
  DraftAdministrativeData inProcessByUser(String inProcessByUser);

  @CdsName(DRAFT_IS_PROCESSED_BY_ME)
  Boolean draftIsProcessedByMe();

  @CdsName(DRAFT_IS_PROCESSED_BY_ME)
  DraftAdministrativeData draftIsProcessedByMe(Boolean draftIsProcessedByMe);

  DraftAdministrativeData_ ref();

  static DraftAdministrativeData create() {
    return Struct.create(DraftAdministrativeData.class);
  }

  static DraftAdministrativeData of(Map<String, Object> map) {
    return Struct.access(map).as(DraftAdministrativeData.class);
  }

  static DraftAdministrativeData create(String draftUUID) {
    Map<String, Object> keys = new HashMap<>();
    keys.put(DRAFT_UUID, draftUUID);
    return Struct.access(keys).as(DraftAdministrativeData.class);
  }
}
