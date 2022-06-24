package pameas.rtls.api.manager.model;

import lombok.*;

import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class LocationInfo implements Serializable {
    private List<UserGeofenceUnit> geofenceHistory;
    private List<UserLocationUnit> locationHistory;
}
