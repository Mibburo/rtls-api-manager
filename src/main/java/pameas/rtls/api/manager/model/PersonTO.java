package pameas.rtls.api.manager.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@ToString
@Getter
@Setter
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class PersonTO {

    private String name;
    private String surname;
    private String gender;
    private String identifier;
    private String age;
    private List<ConnectedPersonTO> connectedPassengers;
    @JsonProperty("embarkation_port")
    private String  embarkationPort;
    @JsonProperty("disembarkation_port")
    private  String disembarkationPort;
    private String ticketNumber;

    private String email;
    @JsonProperty("postal_address")
    private String postalAddress;
    @JsonProperty("emergency_contact_details")
    private String emergencyContact;
    @JsonProperty("country_of_residence")
    private String countryOfResidence;

    @JsonProperty("medical_condnitions")
    private String medicalCondition;
    @JsonProperty("mobility_issues")
    private String mobilityIssues;
    @JsonProperty("pregnency_data")
    private String prengencyData;
    @JsonProperty("is_crew")
    private boolean isCrew;
    private String role;
    @JsonProperty("emergency_duty")
    private String emergencyDuty;

    @JsonProperty("duty_schedule")
    private List<DutySchedule> dutySchedule;

    @JsonProperty("preferred_language")
    private String[] preferredLanguage;

    // New additions
    @JsonProperty("in_position")
    private boolean inPosition;
    @JsonProperty("assignment_status")
    private Personalinfo.AssignmentStatus assignmentStatus;
    @JsonProperty("assigned_muster_station")
    private String assignedMusteringStation;

}
