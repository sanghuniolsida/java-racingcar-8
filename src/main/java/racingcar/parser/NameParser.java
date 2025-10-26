package racingcar.parser;

import java.util.ArrayList;
import java.util.List;
import racingcar.domain.Name;

public final class NameParser {
    private NameParser() {}

    public static List<Name> parse(String carNamesCsv) {
        if (carNamesCsv == null || carNamesCsv.isBlank()) {
            throw new IllegalArgumentException("자동차 이름을 입력해 주세요.");
        }
        String[] nameTokens = carNamesCsv.split(",", -1);
        if (nameTokens.length == 0) {
            throw new IllegalArgumentException("자동차 이름을 입력해 주세요.");
        }
        List<Name> parsedNames = new ArrayList<>(nameTokens.length);
        for (String token : nameTokens) {
            if (token.isEmpty()) {
                throw new IllegalArgumentException("이름 사이에 빈 항목이 있습니다.");
            }
            parsedNames.add(new Name(token));
        }
        return parsedNames;
    }
}