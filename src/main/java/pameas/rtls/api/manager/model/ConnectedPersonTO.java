package pameas.rtls.api.manager.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@ToString
@Getter
@Setter
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class ConnectedPersonTO {

    private String name;
    private String surname;
    private String gender;
    private String age;
    private String ticketNumber;
}
