package pameas.rtls.api.manager.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import pameas.rtls.api.manager.model.LocationServiceDTO;
import static pameas.rtls.api.manager.utils.Constants.LOCATION_SERVICE_URL;

import java.util.List;
import java.util.Map;

@Slf4j
@Service
public class LocationDataService {

    //get geofence from location service
    public Map<String, String> getGeofence(LocationServiceDTO locationServiceDTO) {
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.postForObject(LOCATION_SERVICE_URL +"/getGeofence", locationServiceDTO, Map.class);
    }

    //get distance from location service
    public String getDistance(LocationServiceDTO locationServiceDTO) {
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.postForObject(LOCATION_SERVICE_URL +"/getDistance", locationServiceDTO, String.class);
    }

    //get passenger speed from location service
    public String getPassengerSpeed(List<LocationServiceDTO> locationServiceDTO) {
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.postForObject(LOCATION_SERVICE_URL +"/getPassengerSpeed", locationServiceDTO, String.class);
    }
}
