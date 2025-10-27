package racingcar.domain;

public final class AttemptCount {
    private final int value;

    public AttemptCount(int value) {
        if (value < 1) throw new IllegalArgumentException("시도 횟수는 1 이상의 정수여야 합니다.");
        this.value = value;
    }

    public static AttemptCount fromString(String raw) {
        if (raw == null || raw.isBlank()) {
            throw new IllegalArgumentException("시도 횟수는 1 이상의 정수여야 합니다: 빈 값");
        }
        String normalized = raw.strip();
        if (!normalized.chars().allMatch(Character::isDigit)) {
            throw new IllegalArgumentException(String.format("시도 횟수는 숫자만 입력해야 합니다: '%s'", raw));
        }
        int parsed;
        try { parsed = Integer.parseInt(normalized); }
        catch (NumberFormatException e) {
            throw new IllegalArgumentException(String.format("시도 횟수 해석 실패: '%s'", raw), e);
        }
        if (parsed < 1) throw new IllegalArgumentException("시도 횟수는 1 이상의 정수여야 합니다.");
        return new AttemptCount(parsed);
    }

    public int value() { return value; }
}
