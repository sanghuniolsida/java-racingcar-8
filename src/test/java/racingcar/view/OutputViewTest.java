package racingcar.view;

import static org.assertj.core.api.Assertions.assertThat;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.List;
import org.junit.jupiter.api.*;

class OutputViewTest {

    private final ByteArrayOutputStream out = new ByteArrayOutputStream();
    private PrintStream originalOut;

    @BeforeEach
    void setUp() {
        originalOut = System.out;
        System.setOut(new PrintStream(out));
    }

    @AfterEach
    void tearDown() {
        System.setOut(originalOut);
    }

    @Test
    @DisplayName("printStart는 공백 줄 이후 '실행 결과'를 출력한다")
    void printStart() {
        OutputView view = new OutputView();
        view.printStart();
        assertThat(out.toString())
                .isEqualTo(System.lineSeparator() + "실행 결과" + System.lineSeparator());
    }

    @Test
    @DisplayName("printProgress는 라인들을 모두 출력하고 공백 줄을 출력한다")
    void printProgress() {
        OutputView view = new OutputView();
        view.printProgress(List.of("pobi : -", "woni : --"));

        String sep = System.lineSeparator();
        String expected = "pobi : -" + sep + "woni : --" + sep + sep;
        assertThat(out.toString()).isEqualTo(expected);
    }

    @Test
    @DisplayName("printWinners는 '최종 우승자 : {csv}'를 출력한다")
    void printWinners() {
        OutputView view = new OutputView();
        view.printWinners("pobi,woni");
        assertThat(out.toString()).isEqualTo("최종 우승자 : pobi,woni" + System.lineSeparator());
    }
}