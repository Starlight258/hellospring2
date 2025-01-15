package tobyspring.hellospring.api;

import com.fasterxml.jackson.core.JsonProcessingException;
import java.io.IOException;
import java.math.BigDecimal;
import java.net.URI;
import java.net.URISyntaxException;
import tobyspring.hellospring.api.executor.ApiExecutor;
import tobyspring.hellospring.api.executor.HttpClientApiExecutor;
import tobyspring.hellospring.api.extractor.ErApiExRateExtractor;
import tobyspring.hellospring.api.extractor.ExRateExtractor;

public class ApiTemplate {

    private final ApiExecutor apiExecutor;
    private final ExRateExtractor exRateExtractor;

    public ApiTemplate() {
        this.apiExecutor = new HttpClientApiExecutor();
        this.exRateExtractor = new ErApiExRateExtractor();
    }

    public ApiTemplate(final ApiExecutor apiExecutor, final ExRateExtractor exRateExtractor) {
        this.apiExecutor = apiExecutor;
        this.exRateExtractor = exRateExtractor;
    }

    public BigDecimal getExRate(final String url) {
        return this.getExRate(url, this.apiExecutor, this.exRateExtractor);
    }

    public BigDecimal getExRate(final String url, final ApiExecutor apiExecutor) {
        return this.getExRate(url, apiExecutor, this.exRateExtractor);
    }

    public BigDecimal getExRate(final String url, final ExRateExtractor exRateExtractor) {
        return this.getExRate(url, this.apiExecutor, exRateExtractor);
    }

    public BigDecimal getExRate(final String url, final ApiExecutor apiExecutor,
                                final ExRateExtractor exRateExtractor) {
        URI uri = null;
        try {
            uri = new URI(url);
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }

        String response;
        try {
            response = apiExecutor.execute(uri);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        try {
            return exRateExtractor.extractExRate(response);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
