package pameas.rtls.api.manager.restController;

import com.mashape.unirest.http.exceptions.UnirestException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import pameas.rtls.api.manager.model.DeviceInfo;
import pameas.rtls.api.manager.model.LocationDTO;
import pameas.rtls.api.manager.model.LocationServiceDTO;
import pameas.rtls.api.manager.model.PameasPerson;
import pameas.rtls.api.manager.service.DbProxyService;
import pameas.rtls.api.manager.service.LocationDataService;
import pameas.rtls.api.manager.service.RegistrationService;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Slf4j
@RestController
public class LocationDataController {

    private DbProxyService dbProxyService;
    private LocationDataService locationDataService;
    private RegistrationService registrationService;

    @Autowired
    LocationDataController(DbProxyService dbProxyService, LocationDataService locationDataService, RegistrationService registrationService){
        this.dbProxyService = dbProxyService;
        this.locationDataService = locationDataService;
        this.registrationService = registrationService;
    }

    @PostMapping("/addPersonAndDevice")
    public void addPersonAndDevice(@RequestBody LocationDTO locationDTO) throws UnirestException {
        if (Boolean.TRUE.equals(locationDTO.getIsNewPerson())) {
            registrationService.addPersonFull(locationDTO.getLocationTO().getMacAddress(),
                    locationDTO.getLocationTO().getHashedMacAddress());
        }
    }

    @PostMapping("/saveLocationData")
    public void saveLocationData(@RequestBody LocationDTO locationDTO) throws UnirestException {
        List<PameasPerson> persons = dbProxyService.getPassengerDetails();
        persons.forEach(pameasPerson -> {
            try {
                Optional<DeviceInfo> device = pameasPerson.getNetworkInfo().getDeviceInfoList().stream()
                        .filter(x -> x.getMacAddress().equals(locationDTO.getLocationTO().getMacAddress())).findAny();
                if(device.isPresent()){
                    locationDTO.getLocationTO().setHashedMacAddress(device.get().getHashedMacAddress());
                    dbProxyService.saveLocationData(locationDTO.getLocationTO());
                }

            } catch (UnirestException | IOException | InterruptedException e) {
                log.error(e.getMessage());
            }
        });
    }

    @PostMapping("/getGeofence")
    public Map<String, String> getGeofence(@RequestBody LocationServiceDTO dto) {
        return locationDataService.getGeofence(dto);
    }

    @PostMapping("/getDistance")
    public String getDistance(@RequestBody LocationServiceDTO dto) {
        return locationDataService.getDistance(dto);
    }

    @PostMapping("/getPassengerSpeed")
    public String getPassengerSpeed(@RequestBody List<LocationServiceDTO> dto) {
        return locationDataService.getPassengerSpeed(dto);
    }

}
