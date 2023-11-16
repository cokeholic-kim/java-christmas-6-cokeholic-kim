package christmas.View;

import camp.nextstep.edu.missionutils.Console;

public class InputView {
    public static String readOrder() {
        OutputView.printAskOrder();
        return Console.readLine();
    }

    public static String readDay() {
        OutputView.printAskVisitDate();
        return Console.readLine();
    }
}
