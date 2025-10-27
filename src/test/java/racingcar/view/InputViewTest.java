package racingcar.view;

import static org.assertj.core.api.Assertions.assertThat;

import camp.nextstep.edu.missionutils.Console;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class InputViewTest {

    private PrintStream originalOut;
    private final ByteArrayOutputStream out = new ByteArrayOutputStream();

    @BeforeEach
    void setUp() {
        originalOut = System.out;
        System.setOut(new PrintStream(out));
    }

    @AfterEach
    void tearDown() {
        System.setOut(originalOut);
        Console.close();
    }

    @Test
    @DisplayName("자동차 이름 프롬프트를 출력하고 한 줄을 입력받는다")
    void readCarNamesCsv() {
        System.setIn(new ByteArrayInputStream("pobi,woni,jun\n".getBytes()));
        InputView view = new InputView();

        String carNamesCsv = view.readCarNamesCsv();

        String sep = System.lineSeparator();
        assertThat(out.toString()).isEqualTo("경주할 자동차 이름을 입력하세요.(이름은 쉼표(,) 기준으로 구분)" + sep);
        assertThat(carNamesCsv).isEqualTo("pobi,woni,jun");
    }

    @Test
    @DisplayName("시도 횟수 프롬프트를 출력하고 한 줄을 입력받는다")
    void readAttemptCount() {
        System.setIn(new ByteArrayInputStream("5\n".getBytes()));
        InputView view = new InputView();

        String rawAttempt = view.readAttemptCount();

        String sep = System.lineSeparator();
        assertThat(out.toString()).isEqualTo("시도할 횟수는 몇 회인가요?" + sep);
        assertThat(rawAttempt).isEqualTo("5");
    }
}