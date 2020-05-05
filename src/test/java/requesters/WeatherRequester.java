package requesters;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import model.Response;
import org.springframework.web.client.RestTemplate;

public class WeatherRequester {
    private final String PREFIX = "https://samples.openweathermap.org/data/2.5/weather?q=London,uk";
    private final String POSTFIX = "&appid=439d4b804bc8187953eb36d2a8c26a02";

    public Response requestWeather(String city, String country) throws JsonProcessingException {
        RestTemplate restTemplate = new RestTemplate();
        String url = PREFIX + city + "," + country.toLowerCase() + POSTFIX;

        String jsonToParse = restTemplate.getForEntity(url, String.class).getBody();

        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readValue(jsonToParse, Response.class);
    }
}
