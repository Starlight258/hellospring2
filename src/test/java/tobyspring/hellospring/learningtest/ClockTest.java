package tobyspring.hellospring.learningtest;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.Clock;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import org.junit.jupiter.api.Test;

public class ClockTest {

    @Test
    void 현재_시간을_가져온다() {
        // Given
        Clock clock = Clock.systemDefaultZone();

        // When
        LocalDateTime dateTime = LocalDateTime.now(clock);
        LocalDateTime comparedDateTime = LocalDateTime.now(clock);

        // Then
        assertThat(comparedDateTime).isAfter(dateTime);
    }

    @Test
    void 시간을_고정한다() {
        // Given
        Clock clock = Clock.fixed(Instant.now(), ZoneId.systemDefault());

        // When
        LocalDateTime dateTime = LocalDateTime.now(clock);
        LocalDateTime comparedDateTime = LocalDateTime.now(clock);

        // Then
        assertThat(comparedDateTime).isEqualTo(dateTime);
    }
}
