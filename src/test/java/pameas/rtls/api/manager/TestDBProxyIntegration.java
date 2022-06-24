package pameas.rtls.api.manager;

import com.mashape.unirest.http.exceptions.UnirestException;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import pameas.rtls.api.manager.model.LocationTO;
import pameas.rtls.api.manager.model.PameasPerson;
import pameas.rtls.api.manager.model.UserGeofenceUnit;
import pameas.rtls.api.manager.model.UserLocationUnit;
import pameas.rtls.api.manager.service.DbProxyService;
import pameas.rtls.api.manager.service.RegistrationService;

import java.io.IOException;
import java.util.List;

@SpringBootTest
@Slf4j
public class TestDBProxyIntegration {


    @Autowired
    RegistrationService registrationService;

    @Autowired
    DbProxyService dbProxyService;
    @Test
    public void testRegistration(){

        try {
            String identifier = registrationService.addPerson();
            registrationService.addDevice("","", identifier);
            List<PameasPerson> persons = dbProxyService.getPassengerDetails();
           persons.forEach(pameasPerson -> {

                   try {
                       registrationService.addDevice("12312","has123",pameasPerson.getId());
                   } catch (UnirestException | IOException | InterruptedException e) {
                       log.error(e.getLocalizedMessage());
                   }

           });


            persons.forEach(pameasPerson -> {
                String hashedMac = pameasPerson.getNetworkInfo().getDeviceInfoList().get(0).getHashedMacAddress();
                String mac = pameasPerson.getNetworkInfo().getDeviceInfoList().get(0).getMacAddress();
                LocationTO locationTO = new LocationTO();
                UserLocationUnit userLocationUnit = new UserLocationUnit();
                userLocationUnit.setXLocation("x");
                userLocationUnit.setYLocation("y");
                userLocationUnit.setBuildingId("building");
                userLocationUnit.setCampusId("campus");
                userLocationUnit.setBuildingId("building");
                userLocationUnit.setGeofenceId("geo");
                locationTO.setLocation(userLocationUnit);
                UserGeofenceUnit userGeofenceUnit = new UserGeofenceUnit();
                userGeofenceUnit.setDeck("7");
                locationTO.setGeofence(userGeofenceUnit);


                locationTO.setHashedMacAddress(hashedMac);
                locationTO.setMacAddress(mac);

                try {
                    dbProxyService.saveLocationData(locationTO);
                } catch (UnirestException | IOException | InterruptedException e) {
                    log.error(e.getLocalizedMessage());
                }

            });

        } catch (UnirestException | IOException | InterruptedException e) {
           log.error(e.getLocalizedMessage());
        }
    }

}
