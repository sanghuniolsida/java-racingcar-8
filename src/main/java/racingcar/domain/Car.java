package racingcar.domain;

import java.util.Objects;

public final class Car {
    private final Name carName;
    private int position = 0;

    public Car(Name carName) {
        this.carName = Objects.requireNonNull(carName, "자동차 이름은 공백이 아니어야 한다.");
    }

    public void moveIf(boolean shouldMove) {
        if (shouldMove) {
            position++;
        }
    }

    public String toProgressLine() {
        return carName.value() + " : " + "-".repeat(position);
    }

    public int position() { return position; }

    public String nameValue() { return carName.value(); }
}
