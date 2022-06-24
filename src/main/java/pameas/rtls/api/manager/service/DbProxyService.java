package pameas.rtls.api.manager.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.mashape.unirest.http.exceptions.UnirestException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import pameas.rtls.api.manager.model.LocationTO;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import static pameas.rtls.api.manager.utils.Constants.DBPROXY_URL;

@Slf4j
@Service
public class DbProxyService {

    //send to db proxy
    public void saveLocationData(LocationTO locationTO) throws UnirestException, IOException, InterruptedException {
        String accessToken = TokenService.getAccessToken();

        ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
        String json = ow.writeValueAsString(locationTO);
        log.info("gggggggggggggggggg json :{}", json);
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(DBPROXY_URL +"/addLocation"))
                .header("Content-Type", "application/json")
                .header("Authorization", "Bearer "+accessToken)
                .method("POST", HttpRequest.BodyPublishers.ofString(json))
                .build();
        HttpResponse<String> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
        log.info("ffffffffffffffffffffff response :{}", response);
    }
}
