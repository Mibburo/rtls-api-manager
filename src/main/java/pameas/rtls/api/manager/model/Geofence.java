package pameas.rtls.api.manager.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@ToString
@Getter
@Setter
@NoArgsConstructor
public class Geofence {

    private String gfEvent;
    private String gfId;
    private String gfName;
    private String macAddress;
    private String isAssociated;
    private String dwellTime;
    private String hashedMacAddress;
    private String timestamp;
    private String deck;
}
