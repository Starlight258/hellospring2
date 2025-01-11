package tobyspring.hellospring.domain.sort;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class SortTest {

    private Sort sort;

    @BeforeEach
    void setUp() {
        sort = new Sort();
        System.out.println(sort);
    }

    @Test
    @DisplayName("정렬이 올바르게 수행된다.")
    void 정렬이_올바르게_수행된다() {
        // Given

        // When
        List<String> list = sort.sortByLength(Arrays.asList("ab", "b"));

        // Then
        assertThat(list).isEqualTo(List.of("b", "ab"));
    }

    @Test
    @DisplayName("3개의 아이템이 정렬된다.")
    void 세개의_아이템이_정렬된다() {
        // Given

        // When
        List<String> list = sort.sortByLength(Arrays.asList("ab", "ccc", "b"));

        // Then
        assertThat(list).isEqualTo(List.of("b", "ab", "ccc"));
    }

    @Test
    @DisplayName("이미 정렬된 아이템을 정렬한다.")
    void 이미_정렬된_아이템을_정렬한다() {
        // Given

        // When
        List<String> list = sort.sortByLength(Arrays.asList("b", "ab", "ccc"));

        // Then
        assertThat(list).isEqualTo(List.of("b", "ab", "ccc"));
    }
}
