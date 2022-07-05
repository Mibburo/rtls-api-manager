package pameas.rtls.api.manager.service;

import com.github.javafaker.Faker;
import com.mashape.unirest.http.exceptions.UnirestException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import pameas.rtls.api.manager.model.*;

import java.util.*;
import static pameas.rtls.api.manager.utils.Constants.DBPROXY_URL;

//used for emulation
@Slf4j
@Service
public class RegistrationService {
    Random random = new Random();

    public String addPersonFull(String macAddress, String hashedMacAddress) throws UnirestException {

        String accessToken = TokenService.getAccessToken();
        PersonFullTO personFull = generatePersonFull(macAddress, hashedMacAddress);

        RestTemplate restTemplate = new RestTemplate();
        org.springframework.http.HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization",  "Bearer "+accessToken);

        HttpEntity<PersonFullTO> request = new HttpEntity<>(personFull, headers);
        restTemplate.postForObject(DBPROXY_URL +"/addPerson2ES/", request, String.class);
        return personFull.getIdentifier();

    }

    private PersonFullTO generatePersonFull(String macAddress, String hashedMacAddress){
        PersonFullTO personFull = new PersonFullTO();
        Faker faker = new Faker();
        personFull.setName(faker.name().firstName());
        personFull.setSurname(faker.name().lastName());
        personFull.setGender(generateGender());
        personFull.setIdentifier(UUID.randomUUID().toString());
        personFull.setAge(String.valueOf(generateAge()));
        personFull.setConnectedPassengers(generatePassengers());
        personFull.setTicketNumber(UUID.randomUUID().toString());
        personFull.setEmail(personFull.getName() + personFull.getSurname() + "@email.com");
        personFull.setRole("passenger");
        personFull.setCrew(false);
        personFull.setMedicalCondition("");
        personFull.setPreferredLanguage(new String[]{"EN"});
        personFull.setEmbarkationPort("Piraeus");
        personFull.setDisembarkationPort("Chania");
        personFull.setCountryOfResidence("Greece");
        personFull.setMobilityIssues("");
        personFull.setPrengencyData("");
        personFull.setEmergencyDuty("");
        personFull.setPostalAddress(String.valueOf(random.ints(10000, 99999).findFirst().getAsInt()));
        personFull.setEmergencyContact(faker.name().fullName());
        personFull.setDutySchedule(new ArrayList<>());
        personFull.setInPosition(false);
        personFull.setAssignmentStatus(Personalinfo.AssignmentStatus.UNASSIGNED);
        personFull.setAssignedMusteringStation("7BG6");
        personFull.setMessagingAppClientId(hashedMacAddress);
        personFull.setDeviceInfoList(generateDeviceInfo(macAddress, hashedMacAddress));
        return personFull;
    }

    private List<DeviceInfo> generateDeviceInfo(String macAddress, String hashedMacAddress){
        DeviceInfo deviceInfo = new DeviceInfo();
        deviceInfo.setMacAddress(macAddress);
        deviceInfo.setHashedMacAddress(hashedMacAddress);
        deviceInfo.setImsi(String.valueOf(Math.random() * 100000000000000L).replace(".", ""));
        deviceInfo.setImei(String.valueOf(Math.random() * 100000000000000L).replace(".", ""));
        deviceInfo.setMsisdn("MSISDN");

        return new ArrayList<>(Arrays.asList(deviceInfo));
    }

    private List<DutySchedule> generateDutySchedule(String start, String end){
        DutySchedule schedule = new DutySchedule();
//        schedule.setDutyStartDateTime(start);
//        schedule.setDutyEndDateTime(end);
        schedule.setDutyStartDateTime(null);
        schedule.setDutyEndDateTime(null);

        List<DutySchedule> dutySchedules = new ArrayList<>();
        dutySchedules.add(schedule);

        return dutySchedules;
    }

    private String generateGender(){
        int gend = random.ints(0, 2).findFirst().getAsInt();
        String gender = gend == 0? "female" : "male";
        return gender;
    }

    private Integer generateAge(){
        return random.ints(1, 80).findFirst().getAsInt();
    }

    private List<ConnectedPersonTO> generatePassengers(){
        List<ConnectedPersonTO> passengers = new ArrayList<>();
        return passengers;
    }
}
