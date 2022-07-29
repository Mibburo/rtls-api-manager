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
import java.util.concurrent.TimeUnit;

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
            //String identifier = registrationService.addPerson();

            TimeUnit.SECONDS.sleep(3);

            List<PameasPerson> persons = dbProxyService.getPassengerDetails();


           /*persons.forEach(pameasPerson -> {

                   try {
                       registrationService.addDevice("78:D3:AD:62:57:43","94b98a622f7befa426b665dc6ee128c1",pameasPerson.getPersonalInfo().getPersonalId());
                   } catch (UnirestException | IOException | InterruptedException e) {
                       log.error(e.getLocalizedMessage());
                   }

           });*/

            TimeUnit.SECONDS.sleep(4);
            persons = dbProxyService.getPassengerDetails();
            persons.forEach(pameasPerson -> {
                String hashedMac = pameasPerson.getNetworkInfo().getDeviceInfoList().get(0).getHashedMacAddress();
                String mac = pameasPerson.getNetworkInfo().getDeviceInfoList().get(0).getMacAddress();
                //String mac = "0D:FE:A3:56:C2:90";
                //String hashedMac = "2c25f560bd8d75a1bff2ce8070718b1c";
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

        } catch (UnirestException | InterruptedException e) {
           log.error(e.getLocalizedMessage());
        }
    }

    @Test
    public void testGetAll() throws UnirestException {
        List<PameasPerson> persons = dbProxyService.getAll();
        log.info("persons :{}", persons);
    }

}
