package racingcar.rule;

import camp.nextstep.edu.missionutils.Randoms;

public final class RandomMoveRule {
    private static final int RANDOM_MIN_INCLUSIVE = 0;
    private static final int RANDOM_MAX_INCLUSIVE = 9;
    private static final int MOVE_THRESHOLD = 4;

    private RandomMoveRule() { }

    public static boolean isMovable(int randomValue) {
        return randomValue >= MOVE_THRESHOLD;
    }

    public static boolean shouldMove() {
        int randomValue = Randoms.pickNumberInRange(RANDOM_MIN_INCLUSIVE, RANDOM_MAX_INCLUSIVE);
        return isMovable(randomValue);
    }
}