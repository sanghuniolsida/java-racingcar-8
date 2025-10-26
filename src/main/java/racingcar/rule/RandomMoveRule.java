package racingcar.rule;

import camp.nextstep.edu.missionutils.Randoms;

public final class RandomMoveRule {
    private static final int MIN = 0;
    private static final int MAX = 9;
    private static final int THRESHOLD = 4;

    private RandomMoveRule() { }

    public static boolean isMovable(int value) {
        return value >= THRESHOLD;
    }

    public static boolean shouldMove() {
        int value = Randoms.pickNumberInRange(MIN, MAX);
        return isMovable(value);
    }
}
