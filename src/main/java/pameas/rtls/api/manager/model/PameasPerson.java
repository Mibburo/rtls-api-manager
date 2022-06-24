package pameas.rtls.api.manager.model;


import lombok.*;

import java.io.Serializable;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class PameasPerson implements Serializable {

    private String id;

    Personalinfo personalInfo;

    NetworkInfo networkInfo;

    LocationInfo locationInfo;
}
