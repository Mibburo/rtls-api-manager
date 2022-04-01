package pameas.rtls.api.manager.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@ToString
@Getter
@Setter
@NoArgsConstructor
public class Device {

    private String identifier;
    private String macAddress;
    private String hashedMacAddress;
    private String imsi;
    private String msisdn;
    private String imei;
    private String messagingAppClientId;
}
