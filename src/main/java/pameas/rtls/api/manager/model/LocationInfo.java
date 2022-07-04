package pameas.rtls.api.manager.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class LocationInfo implements Serializable {

    private String speed;
    private List<UserGeofenceUnit> geofenceHistory;
    private List<UserLocationUnit> locationHistory;
}
