package racingcar.domain;

import static org.assertj.core.api.Assertions.*;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class CarsTest {

    private Car car(String name) {
        return new Car(new Name(name));
    }

    @Test
    @DisplayName("라운드에서 true인 자동차만 전진한다")
    void move_all_once_advances_only_trues() {
        Cars cars = new Cars(List.of(car("pobi"), car("woni"), car("jun")));

        cars.moveAllOnce(List.of(true, false, true));

        assertThat(cars.progressLines()).containsExactly(
                "pobi : -",
                "woni : ",
                "jun : -"
        );

        assertThat(cars.cars().get(0).position()).isEqualTo(1);
        assertThat(cars.cars().get(1).position()).isEqualTo(0);
        assertThat(cars.cars().get(2).position()).isEqualTo(1);
    }

    @Test
    @DisplayName("단독 우승자를 계산한다")
    void find_single_winner() {
        Cars cars = new Cars(List.of(car("pobi"), car("woni"), car("jun")));

        cars.moveAllOnce(List.of(true, false, false));
        cars.moveAllOnce(List.of(true, false, false));

        assertThat(cars.findWinnerNames()).containsExactly("pobi");
        assertThat(cars.winnersCsv()).isEqualTo("pobi");
    }

    @Test
    @DisplayName("공동 우승자를 계산한다")
    void find_tied_winners() {
        Cars cars = new Cars(List.of(car("pobi"), car("woni"), car("jun")));

        cars.moveAllOnce(List.of(true, true, true));
        cars.moveAllOnce(List.of(true, true, false));

        assertThat(cars.findWinnerNames()).containsExactly("pobi", "woni");
        assertThat(cars.winnersCsv()).isEqualTo("pobi,woni");
    }

    @Test
    @DisplayName("모든 진행도 라인을 반환한다 ('이름 : ---' 형식)")
    void progress_lines_format() {
        Cars cars = new Cars(List.of(car("pobi"), car("woni")));
        cars.moveAllOnce(List.of(true, true));
        cars.moveAllOnce(List.of(false, true));

        assertThat(cars.progressLines()).containsExactly(
                "pobi : -",
                "woni : --"
        );
    }
}