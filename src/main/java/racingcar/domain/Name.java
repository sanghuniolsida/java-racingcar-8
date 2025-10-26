package racingcar.domain;

import java.util.Objects;

public final class Name {
    private static final int MAX_NAME_LENGTH = 5;

    private final String value;

    public Name(String rawName) {
        this.value = validate(rawName);
    }

    private String validate(String rawName) {
        if (rawName == null || rawName.isBlank()) {
            throw new IllegalArgumentException("자동차 이름은 1~5자여야 합니다: 빈 값");
        }
        if (rawName.length() > MAX_NAME_LENGTH) {
            throw new IllegalArgumentException(
                    String.format("자동차 이름은 1~5자여야 합니다: '%s'", rawName)
            );
        }
        return rawName;
    }

    public String value() { return value; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Name other)) return false;
        return value.equals(other.value);
    }

    @Override
    public int hashCode() { return Objects.hash(value); }

    @Override
    public String toString() { return value; }
}