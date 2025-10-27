// src/main/java/racingcar/controller/GameController.java
package racingcar.controller;

import java.util.ArrayList;
import java.util.List;
import racingcar.domain.AttemptCount;
import racingcar.domain.Car;
import racingcar.domain.Cars;
import racingcar.domain.Name;
import racingcar.parser.NameParser;
import racingcar.rule.RandomMoveRule;
import racingcar.view.InputView;
import racingcar.view.OutputView;

public class GameController {

    private final InputView inputView;
    private final OutputView outputView;

    public GameController(InputView inputView, OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public void startGame() {
        List<Name> names = readNames();
        AttemptCount attemptCount = readAttemptCount();

        Cars cars = new Cars(createCars(names));

        outputView.printStart();
        playRounds(cars, attemptCount.value());
        printWinners(cars);
    }

    private List<Name> readNames() {
        String carNamesCsv = inputView.readCarNamesCsv();
        return NameParser.parse(carNamesCsv);
    }

    private AttemptCount readAttemptCount() {
        String rawAttempt = inputView.readAttemptCount();
        return AttemptCount.fromString(rawAttempt);
    }

    private List<Car> createCars(List<Name> names) {
        return names.stream().map(Car::new).toList();
    }

    private void playRounds(Cars cars, int totalRounds) {
        for (int round = 0; round < totalRounds; round++) {
            playOneRound(cars);
        }
    }

    private void playOneRound(Cars cars) {
        List<Boolean> moveFlags = generateMoveFlags(carsCount(cars));
        cars.moveAllOnce(moveFlags);
        outputView.printProgress(cars.progressLines());
    }

    protected List<Boolean> generateMoveFlags(int carCount) {
        List<Boolean> flags = new ArrayList<>(carCount);
        for (int i = 0; i < carCount; i++) {
            flags.add(RandomMoveRule.shouldMove());
        }
        return flags;
    }

    private void printWinners(Cars cars) {
        outputView.printWinners(cars.winnersCsv());
    }

    private int carsCount(Cars cars) {
        return cars.progressLines().size();
    }
}