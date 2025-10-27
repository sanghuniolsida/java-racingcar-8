package racingcar.domain;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class AttemptCountTest {

    @Test
    @DisplayName("정수 생성자: 1 이상은 허용")
    void int_ctor_accepts_positive_numbers() {
        assertThat(new AttemptCount(1).value()).isEqualTo(1);
        assertThat(new AttemptCount(5).value()).isEqualTo(5);
    }

    @Test
    @DisplayName("정수 생성자: 0 또는 음수는 예외")
    void int_ctor_throws_when_zero_or_negative() {
        assertThatThrownBy(() -> new AttemptCount(0))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("1 이상의 정수");
        assertThatThrownBy(() -> new AttemptCount(-3))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("문자열 팩토리: null/빈 문자열/공백만은 예외")
    void from_string_throws_on_null_or_blank() {
        assertThatThrownBy(() -> AttemptCount.fromString(null))
                .isInstanceOf(IllegalArgumentException.class);

        assertThatThrownBy(() -> AttemptCount.fromString(""))
                .isInstanceOf(IllegalArgumentException.class);

        assertThatThrownBy(() -> AttemptCount.fromString("    "))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("문자열 팩토리: 숫자가 아니면 예외")
    void from_string_throws_when_not_number() {
        assertThatThrownBy(() -> AttemptCount.fromString("abc"))
                .isInstanceOf(IllegalArgumentException.class);
        assertThatThrownBy(() -> AttemptCount.fromString("1a"))
                .isInstanceOf(IllegalArgumentException.class);
        assertThatThrownBy(() -> AttemptCount.fromString("-2"))
                .isInstanceOf(IllegalArgumentException.class);
        assertThatThrownBy(() -> AttemptCount.fromString("+3"))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("문자열 팩토리: 앞뒤 공백은 허용하고 숫자만 파싱")
    void from_string_accepts_spaces_and_parses_number() {
        assertThat(AttemptCount.fromString(" 5 ").value()).isEqualTo(5);
        assertThat(AttemptCount.fromString("01").value()).isEqualTo(1);
    }

    @Test
    @DisplayName("문자열 팩토리: 0 또는 음수는 예외")
    void from_string_throws_when_zero_or_negative() {
        assertThatThrownBy(() -> AttemptCount.fromString("0"))
                .isInstanceOf(IllegalArgumentException.class);
        assertThatThrownBy(() -> AttemptCount.fromString("-1"))
                .isInstanceOf(IllegalArgumentException.class);
    }
}