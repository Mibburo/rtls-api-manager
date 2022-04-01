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
public class Person {

    private String name;
    private String surname;
    private String gender;
    private String identifier;
    private String age;
    private List<Passenger> conenctedPassengers;
    private String ticketNumber;
    private String email;
    private String role;
    private String isCrew;
    private String embarkation_port;
    private String disembarkation_port;
    private String postal_address;
    private String emergency_contact_details;
    private String country_of_residence;
    private String medical_condnitions;
    private String mobility_issues;
    private String pregnency_data;
    private String emergency_duty;
    private List<DutySchedule> duty_schedule;
    private List<String> preferred_language;
    private String in_position;
    private String assignment_status;
    private String assigned_muster_station;

}
