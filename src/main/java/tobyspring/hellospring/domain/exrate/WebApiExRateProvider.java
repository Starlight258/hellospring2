package tobyspring.hellospring.domain.exrate;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.stream.Collectors;
import tobyspring.hellospring.domain.payment.ExRateProvider;
import tobyspring.hellospring.dto.ExRateData;

//@Component
public class WebApiExRateProvider implements ExRateProvider {

    private static final String URL = "https://open.er-api.com/v6/latest/";
    private static final String KRW = "KRW";

    @Override
    public BigDecimal getExRate(final String currency) throws IOException {
        URL url = new URL(URL + currency);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        String response = br.lines().collect(Collectors.joining());
        br.close();

        ObjectMapper mapper = new ObjectMapper();
        ExRateData data = mapper.readValue(response, ExRateData.class);
        return data.rates().get(KRW);
    }
}
