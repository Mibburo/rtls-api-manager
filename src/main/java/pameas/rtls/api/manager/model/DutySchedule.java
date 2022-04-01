package pameas.rtls.api.manager.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@ToString
@Getter
@Setter
@NoArgsConstructor
public class DutySchedule {

    private String dutyStartDateTime;
    private String dutyEndDateTime;
}