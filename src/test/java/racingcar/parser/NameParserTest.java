package racingcar.parser;

import static org.assertj.core.api.Assertions.*;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import racingcar.domain.Name;

class NameParserTest {

    @Test
    @DisplayName("null/빈 문자열/공백만 입력이면 예외")
    void throws_on_null_or_blank() {
        assertThatThrownBy(() -> NameParser.parse(null))
                .isInstanceOf(IllegalArgumentException.class);
        assertThatThrownBy(() -> NameParser.parse(""))
                .isInstanceOf(IllegalArgumentException.class);
        assertThatThrownBy(() -> NameParser.parse("   "))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("빈 토큰이 하나라도 있으면 예외(선두/중간/후미)")
    void throws_on_empty_token() {
        assertThatThrownBy(() -> NameParser.parse(",pobi"))
                .isInstanceOf(IllegalArgumentException.class);
        assertThatThrownBy(() -> NameParser.parse("pobi,,jun"))
                .isInstanceOf(IllegalArgumentException.class);
        assertThatThrownBy(() -> NameParser.parse("pobi,"))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("정상 CSV는 입력 순서대로 Name 리스트로 변환한다")
    void returns_names_in_order() {
        List<Name> names = NameParser.parse("pobi,woni,jun");
        assertThat(names).extracting(Name::value)
                .containsExactly("pobi", "woni", "jun");
    }

    @Test
    @DisplayName("토큰은 공백을 포함한 그대로 Name으로 검증된다")
    void tokens_are_passed_as_is_to_name() {
        assertThatThrownBy(() -> NameParser.parse(" po bi ,woni"))
                .isInstanceOf(IllegalArgumentException.class);

        List<Name> ok = NameParser.parse("pobi ,woni");
        assertThat(ok).extracting(Name::value)
                .containsExactly("pobi ", "woni");
    }
}
