package pameas.rtls.api.manager.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonAppend;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@ToString
@Getter
@Setter
@NoArgsConstructor
public class Location {

    private String errorLevel;
    private String isAssociated;
    private String campusId;
    private String buildingId;
    private String floorId;
    private String hashedMacAddress;
    private String geofenceId;
    private List<String> geofenceNames;
    private String timestamp;

    @JsonProperty("xLocation")
    private String xLocation;

    @JsonProperty("yLocation")
    private String yLocation;
}
