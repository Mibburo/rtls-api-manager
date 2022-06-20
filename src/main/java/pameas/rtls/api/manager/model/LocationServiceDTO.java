package pameas.rtls.api.manager.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@ToString
@Getter
@Setter
@NoArgsConstructor
public class LocationServiceDTO {

    @JsonProperty("xCoord")
    private String xCoord;
    @JsonProperty("yCoord")
    private String yCoord;
    @JsonProperty("xCoord2")
    private String xCoord2;
    @JsonProperty("yCoord2")
    private String yCoord2;
    private String deck;
    private String timestamp;
    private String musterStationId;
}
