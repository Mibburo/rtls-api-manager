package pameas.rtls.api.manager.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@JsonIgnoreProperties(ignoreUnknown = true)
public class AddDevicePersonTO {

    private String identifier;
    private String macAddress;
    private String hashedMacAddress;
    private String imsi;
    private String msisdn;
    private String imei;
    private String messagingAppClientId;
}
