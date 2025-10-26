package racingcar.rule;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class RandomMoveRuleTest {

    @Test
    @DisplayName("임계값 4 미만(0~3)은 전진하지 않는다")
    void isMovable_returns_false_below_threshold() {
        assertThat(RandomMoveRule.isMovable(0)).isFalse();
        assertThat(RandomMoveRule.isMovable(1)).isFalse();
        assertThat(RandomMoveRule.isMovable(2)).isFalse();
        assertThat(RandomMoveRule.isMovable(3)).isFalse();
    }

    @Test
    @DisplayName("임계값 4 이상(4~9)은 전진한다")
    void isMovable_returns_true_at_or_above_threshold() {
        assertThat(RandomMoveRule.isMovable(4)).isTrue();
        assertThat(RandomMoveRule.isMovable(5)).isTrue();
        assertThat(RandomMoveRule.isMovable(6)).isTrue();
        assertThat(RandomMoveRule.isMovable(7)).isTrue();
        assertThat(RandomMoveRule.isMovable(8)).isTrue();
        assertThat(RandomMoveRule.isMovable(9)).isTrue();
    }
}