package racingcar.domain;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class NameTest {

    @Test
    @DisplayName("이름이 null 또는 공백만 포함하면 예외")
    void blank_name_throws_exception() {
        assertThatThrownBy(() -> new Name(null))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("자동차 이름은 1~5자여야 합니다");

        assertThatThrownBy(() -> new Name(""))
                .isInstanceOf(IllegalArgumentException.class);

        assertThatThrownBy(() -> new Name("     ")) // 공백만
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("이름 길이는 공백 포함하여 1~5자 허용")
    void name_length_includes_spaces_and_allows_up_to_5() {
        // 길이 1~5 (공백 포함) → 허용
        assertThat(new Name("p").value()).isEqualTo("p");
        assertThat(new Name("po bi").value()).isEqualTo("po bi");
        assertThat(new Name("pobi ").value()).isEqualTo("pobi ");
        assertThat(new Name("pobi ").value()).isEqualTo("pobi ");
    }

    @Test
    @DisplayName("이름 길이가 6자를 넘으면(공백 포함) 예외")
    void name_length_over_5_throws_exception() {
        assertThatThrownBy(() -> new Name("abcdef"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("자동차 이름은 1~5자여야 합니다");

        assertThatThrownBy(() -> new Name("po  bi"))
                .isInstanceOf(IllegalArgumentException.class);

        assertThatThrownBy(() -> new Name("  pobi"))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("동등성/문자열 표현 확인")
    void equality_and_toString() {
        Name a = new Name("pobi");
        Name b = new Name("pobi");
        assertThat(a).isEqualTo(b);
        assertThat(a.hashCode()).isEqualTo(b.hashCode());
        assertThat(a.toString()).isEqualTo("pobi");
    }
}