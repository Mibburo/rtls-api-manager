package pameas.rtls.api.manager.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.mashape.unirest.http.exceptions.UnirestException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import pameas.rtls.api.manager.model.LocationTO;
import pameas.rtls.api.manager.model.PameasPerson;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Arrays;
import java.util.List;

import static pameas.rtls.api.manager.utils.Constants.DBPROXY_URL;

@Slf4j
@Service
public class DbProxyService {

    //send to db proxy
    public void saveLocationData(LocationTO locationTO) throws UnirestException, IOException, InterruptedException {
        String accessToken = TokenService.getAccessToken();

        ObjectWriter ow = new ObjectMapper()
                .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false).writer().withDefaultPrettyPrinter();
        String json = ow.writeValueAsString(locationTO);
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(DBPROXY_URL +"/addLocationAndHealth"))
                .header("Content-Type", "application/json")
                .header("Authorization", "Bearer "+accessToken)
                .method("POST", HttpRequest.BodyPublishers.ofString(json))
                .build();
        HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
    }


    public List<PameasPerson> getPassengerDetails() throws UnirestException {
        String uri = URI.create(DBPROXY_URL) + "/getPassengers";

        HttpHeaders headers = new HttpHeaders();
        String bearer = "Bearer " +  TokenService.getAccessToken();
        headers.set("Authorization", bearer);

        HttpEntity<Void> requestEntity = new HttpEntity<>(headers);
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> responseString = restTemplate.exchange(
                uri, HttpMethod.GET, requestEntity, String.class);
        ObjectMapper mapper = new ObjectMapper()
                .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

        try {
            return Arrays.asList(mapper.readValue(responseString.getBody(), PameasPerson[].class));
        } catch (JsonProcessingException e) {
            log.error(e.getMessage());
        }

        return null;
    }

    public List<PameasPerson> getAll() throws UnirestException {

        String uri = URI.create(DBPROXY_URL) + "/getAll";

        HttpHeaders headers = new HttpHeaders();
        String bearer = "Bearer " +  TokenService.getAccessToken();
        headers.set("Authorization", bearer);

        HttpEntity request = new HttpEntity(headers);
        RestTemplate restTemplate = new RestTemplate();

        ResponseEntity<String> response = restTemplate.exchange(uri, HttpMethod.GET, request, String.class);

        ObjectMapper mapper = new ObjectMapper()
                .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

        try {
            return Arrays.asList(mapper.readValue(response.getBody(), PameasPerson[].class));
        } catch (JsonProcessingException e) {
            log.error(e.getMessage());
        }

        return null;
    }


}
