package pameas.rtls.api.manager.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.github.javafaker.Faker;
import com.mashape.unirest.http.exceptions.UnirestException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import pameas.rtls.api.manager.model.Device;
import pameas.rtls.api.manager.model.DutySchedule;
import pameas.rtls.api.manager.model.Passenger;
import pameas.rtls.api.manager.model.Person;

import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.io.IOException;
import java.net.URI;

import java.net.http.HttpResponse;
import java.util.*;
import static pameas.rtls.api.manager.utils.Constants.DBPROXY_URL;

//used for emulation
@Slf4j
@Service
public class RegistrationService {
    Random random = new Random();

    public String addPerson() throws UnirestException, IOException, InterruptedException {

        String accessToken = TokenService.getAccessToken();
        Person person = generatePerson();
        ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();

        String json = ow.writeValueAsString(person);
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(DBPROXY_URL +"/addPerson/"))
                .header("Content-Type", "application/json")
                .header("Authorization", "Bearer "+accessToken)
                .method("POST", HttpRequest.BodyPublishers.ofString(json))
                .build();
        HttpResponse<String> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
        return person.getIdentifier();

    }

    public void addDevice(String macAddress, String hashedMacAddress, String identifier) throws UnirestException, IOException, InterruptedException {
        String accessToken = TokenService.getAccessToken();
        Device device = generateDevice(macAddress, hashedMacAddress, identifier);
        ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
        String json = ow.writeValueAsString(device);
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(DBPROXY_URL +"/addDevice/"))
                .header("Content-Type", "application/json")
                .header("Authorization", "Bearer "+accessToken)
                .method("POST", HttpRequest.BodyPublishers.ofString(json))
                .build();
        HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
    }

    private Person generatePerson(){
        Person person = new Person();
        Faker faker = new Faker();
        person.setName(faker.name().firstName());
        person.setSurname(faker.name().lastName());
        person.setGender(generateGender());
        person.setIdentifier(UUID.randomUUID().toString());
        person.setAge(String.valueOf(generateAge()));
        person.setConenctedPassengers(generatePassengers());
        person.setTicketNumber(UUID.randomUUID().toString());
        person.setEmail(person.getName() + person.getSurname() + "@email.com");
        person.setRole("captain");
        person.setIsCrew("true");
        person.setMedical_condnitions("non");
        person.setPreferred_language(Arrays.asList("EN", "ES"));
        person.setEmbarkation_port("Piraeus");
        person.setDisembarkation_port("Chania");
        person.setCountry_of_residence("Greece");
        person.setMobility_issues("none");
        person.setPregnency_data("none");
        person.setEmergency_duty("none");
        person.setPostal_address(String.valueOf(random.ints(10000, 99999).findFirst().getAsInt()));
        person.setEmergency_contact_details(faker.name().fullName());
        person.setDuty_schedule(generateDutySchedule("2022-03-28T14:44:39.673Z", "2022-03-28T14:44:39.673Z"));
        person.setIn_position("true");
        person.setAssignment_status("ASSIGNED");
        person.setAssigned_muster_station("reception");

        return person;
    }

    private Device generateDevice(String macAddress, String hashedMacAddress, String identifier){
        Device device = new Device();
        device.setIdentifier(identifier);
        device.setMacAddress(macAddress);
        device.setHashedMacAddress(hashedMacAddress);
        device.setImsi(String.valueOf(Math.random() * 100000000000000L).replace(".", ""));
        device.setImei(String.valueOf(Math.random() * 100000000000000L).replace(".", ""));
        device.setMsisdn("MSISDN");
        device.setMessagingAppClientId(hashedMacAddress);
        return device;
    }

    private List<DutySchedule> generateDutySchedule(String start, String end){
        DutySchedule schedule = new DutySchedule();
        schedule.setDutyStartDateTime(start);
        schedule.setDutyEndDateTime(end);

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

    private List<Passenger> generatePassengers(){
        List<Passenger> passengers = new ArrayList<>();
        return passengers;
    }
}
