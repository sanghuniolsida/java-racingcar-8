package racingcar.parser;

import java.util.ArrayList;
import java.util.List;
import racingcar.domain.Name;

public final class NameParser {
    private NameParser() {}

    public static List<Name> parse(String carNamesCsv) {
        validateInput(carNamesCsv);
        String[] nameTokens = splitByComma(carNamesCsv);
        return convertToNames(nameTokens);
    }

    private static void validateInput(String input) {
        if (input == null || input.isBlank()) {
            throw new IllegalArgumentException("자동차 이름을 입력해 주세요.");
        }
    }

    private static String[] splitByComma(String input) {
        String[] tokens = input.split(",", -1);
        if (tokens.length == 0) {
            throw new IllegalArgumentException("자동차 이름을 입력해 주세요.");
        }
        return tokens;
    }

    private static List<Name> convertToNames(String[] tokens) {
        List<Name> names = new ArrayList<>(tokens.length);
        for (String token : tokens) {
            validateToken(token);
            names.add(new Name(token));
        }
        return names;
    }

    private static void validateToken(String token) {
        if (token.isEmpty()) {
            throw new IllegalArgumentException("이름 사이에 빈 항목이 있습니다.");
        }
    }
}