package pameas.rtls.api.manager.model;

import lombok.*;

import java.util.List;

@ToString
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class LocationTO {

    private String macAddress;
    private String hashedMacAddress;
    private UserGeofenceUnit geofence;
    private UserLocationUnit location;
    private String saturation;
    private String heartBeat;
}
