package racingcar.controller;

import static org.assertj.core.api.Assertions.assertThat;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.List;
import org.junit.jupiter.api.*;

import racingcar.view.InputView;
import racingcar.view.OutputView;

class GameControllerTest {

    private final ByteArrayOutputStream capturedOut = new ByteArrayOutputStream();
    private PrintStream originalOut;

    @BeforeEach
    void setUp() {
        originalOut = System.out;
        System.setOut(new PrintStream(capturedOut));
    }

    @AfterEach
    void tearDown() {
        System.setOut(originalOut);
    }

    @Test
    @DisplayName("2대 자동차, 2라운드 진행: 라운드별 출력과 최종 우승자 출력")
    void run_two_cars_two_rounds() {
        InputView fakeInput = new InputView() {
            @Override public String readCarNamesCsv() { return "pobi,woni"; }
            @Override public String readAttemptCount() { return "2"; }
        };

        OutputView outputView = new OutputView();

        Deque<List<Boolean>> flags = new ArrayDeque<>();
        flags.add(List.of(true, false));
        flags.add(List.of(false, true));

        GameController controller = new GameController(fakeInput, outputView) {
            @Override
            protected List<Boolean> generateMoveFlags(int carCount) {
                return flags.removeFirst();
            }
        };

        controller.startGame();

        String nl = System.lineSeparator();
        String expected =
                nl + "실행 결과" + nl +
                        "pobi : -" + nl +
                        "woni : " + nl +
                        nl +
                        "pobi : -" + nl +
                        "woni : -" + nl +
                        nl +
                        "최종 우승자 : pobi,woni" + nl;

        assertThat(capturedOut.toString()).isEqualTo(expected);
    }
}