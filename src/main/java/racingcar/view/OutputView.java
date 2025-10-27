package racingcar.view;

import java.util.List;

public final class OutputView {

    public void printStart() {
        System.out.println();
        System.out.println("실행 결과");
    }

    public void printProgress(List<String> progressLines) {
        for (String line : progressLines) {
            System.out.println(line);
        }
        System.out.println();
    }

    public void printWinners(String winnersCsv) {
        System.out.println("최종 우승자 : " + winnersCsv);
    }
}