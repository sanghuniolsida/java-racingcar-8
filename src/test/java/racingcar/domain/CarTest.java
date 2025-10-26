package racingcar.domain;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class CarTest {

    @Test
    @DisplayName("shouldMove가 true면 Car는 전진한다")
    void car_moves_when_true() {
        Car car = new Car(new Name("pobi"));
        car.moveIf(true);
        assertThat(car.position()).isEqualTo(1);
    }

    @Test
    @DisplayName("shouldMove가 false면 Car는 멈춘다")
    void car_stops_when_false() {
        Car car = new Car(new Name("pobi"));
        car.moveIf(false);
        assertThat(car.position()).isZero();
    }

    @Test
    @DisplayName("라운드가 달라도 전진/멈춤 판단은 독립적으로 수행된다")
    void move_decision_is_independent_each_round() {
        Car car = new Car(new Name("woni"));
        car.moveIf(true);
        car.moveIf(false);
        car.moveIf(true);
        assertThat(car.position()).isEqualTo(2);
    }

    @Test
    @DisplayName("진행도 문자열은 '이름 : ---' 형식이다")
    void progress_line_format() {
        Car car = new Car(new Name("jun"));
        car.moveIf(true);
        car.moveIf(true);
        assertThat(car.toProgressLine()).isEqualTo("jun : --");
    }
}