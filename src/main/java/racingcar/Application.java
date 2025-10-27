package racingcar;

import camp.nextstep.edu.missionutils.Console;
import racingcar.controller.GameController;
import racingcar.view.InputView;
import racingcar.view.OutputView;

public class Application {
    public static void main(String[] args) {
        try {
            GameController controller = new GameController(new InputView(), new OutputView());
            controller.startGame();
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            throw e;
        } finally {
            Console.close();
        }
    }
}
