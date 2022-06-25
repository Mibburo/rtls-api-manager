package pameas.rtls.api.manager.model;

import lombok.*;
import pameas.rtls.api.manager.model.DeviceInfo;

import java.io.Serializable;
import java.util.List;


@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class NetworkInfo implements Serializable {


    private List<DeviceInfo> deviceInfoList;
    private String messagingAppClientId;


}