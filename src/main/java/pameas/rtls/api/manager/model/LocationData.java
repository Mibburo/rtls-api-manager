package pameas.rtls.api.manager.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@ToString
@Getter
@Setter
@NoArgsConstructor
public class LocationData {

    private String macAddress;
    private String hashedMacAddress;
    private Geofence geofence;
    private Location location;

}
