package tobyspring.hellospring.domain.exrate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.stream.Collectors;
import tobyspring.hellospring.domain.payment.ExRateProvider;
import tobyspring.hellospring.dto.ExRateData;

//@Component
public class WebApiExRateProvider implements ExRateProvider {

    private static final String URL = "https://open.er-api.com/v6/latest/";
    private static final String KRW = "KRW";

    @Override
    public BigDecimal getExRate(final String currency) {
        String url = URL + currency;
        URI uri = null;
        try {
            uri = new URI(url);
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }

        String response;
        try {
            HttpURLConnection connection = (HttpURLConnection) uri.toURL().openConnection();
            try (BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream()))) {
                response = br.lines().collect(Collectors.joining());
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        try {
            ObjectMapper mapper = new ObjectMapper();
            ExRateData data = mapper.readValue(response, ExRateData.class);
            return data.rates().get(KRW);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
