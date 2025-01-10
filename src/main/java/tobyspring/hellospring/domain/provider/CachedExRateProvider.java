package tobyspring.hellospring.domain.provider;

import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDateTime;

public class CachedExRateProvider implements ExRateProvider {

    private final ExRateProvider target;
    private BigDecimal cachedExRate;
    private LocalDateTime cacheExpiryTime;

    public CachedExRateProvider(final ExRateProvider exRateProvider) {
        this.target = exRateProvider;
    }

    @Override
    public BigDecimal getExRate(final String currency) throws IOException {
        if (cachedExRate == null || cacheExpiryTime.isBefore(LocalDateTime.now())) {
            cachedExRate = this.target.getExRate(currency);
            cacheExpiryTime = LocalDateTime.now().plusSeconds(3);
        }
        return cachedExRate;
    }
}
