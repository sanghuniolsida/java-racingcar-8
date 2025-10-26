package racingcar.parser;

import java.util.ArrayList;
import java.util.List;
import racingcar.domain.Name;

public final class NameParser {
    private NameParser() {}

    public static List<Name> parse(String csv) {
        if (csv == null || csv.isBlank()) {
            throw new IllegalArgumentException("자동차 이름을 입력해 주세요.");
        }
        String[] tokens = csv.split(",", -1);
        if (tokens.length == 0) {
            throw new IllegalArgumentException("자동차 이름을 입력해 주세요.");
        }
        List<Name> names = new ArrayList<>(tokens.length);
        for (String token : tokens) {
            if (token.isEmpty()) {
                throw new IllegalArgumentException("이름 사이에 빈 항목이 있습니다.");
            }
            names.add(new Name(token));
        }
        return names;
    }
}
