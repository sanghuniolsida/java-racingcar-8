package racingcar.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public final class Cars {
    private final List<Car> racingCars;

    public Cars(List<Car> racingCars) {
        if (racingCars == null || racingCars.isEmpty()) {
            throw new IllegalArgumentException("경주할 자동차가 한 대 이상 있어야 합니다.");
        }
        this.racingCars = List.copyOf(racingCars);
    }

    public void moveAllOnce(List<Boolean> shouldMoves) {
        Objects.requireNonNull(shouldMoves, "shouldMoves must not be null");
        if (shouldMoves.size() != racingCars.size()) {
            throw new IllegalArgumentException("전진 여부의 개수와 자동차 수가 일치해야 합니다.");
        }
        for (int i = 0; i < racingCars.size(); i++) {
            boolean shouldMove = Boolean.TRUE.equals(shouldMoves.get(i));
            racingCars.get(i).moveIf(shouldMove);
        }
    }

    public List<String> progressLines() {
        List<String> lines = new ArrayList<>(racingCars.size());
        for (Car car : racingCars) {
            lines.add(car.toProgressLine());
        }
        return Collections.unmodifiableList(lines);
    }

    public List<String> findWinnerNames() {
        int max = 0;
        for (Car car : racingCars) {
            if (car.position() > max) {
                max = car.position();
            }
        }
        List<String> winners = new ArrayList<>();
        for (Car car : racingCars) {
            if (car.position() == max) {
                winners.add(car.nameValue());
            }
        }
        return Collections.unmodifiableList(winners);
    }
    // winnersCsv는 '수상자 목록'을 CSV(Comma Separated Values)의미한다.
    public String winnersCsv() {
        return String.join(",", findWinnerNames());
    }

    public List<Car> cars() {
        return racingCars;
    }
}