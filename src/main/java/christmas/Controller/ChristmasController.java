package christmas.Controller;

import christmas.Model.EventManager;
import christmas.Model.Recipt;
import christmas.View.InputManager;
import christmas.View.OutputView;

public class ChristmasController {
    public static void start() {
        int day = askDate();
        Recipt recipt = new Recipt(InputManager.getOrder());
        OutputView.printEventPreView(day);
        outPut(recipt, new EventManager(recipt, day));
    }

    private static int askDate() {
        OutputView.printWelcome();
        return InputManager.getDate();
    }

    private static void outPut(Recipt recipt, EventManager event) {
        outPutRecipt(recipt);
        outPutEvent(event);
    }

    private static void outPutRecipt(Recipt recipt) {
        OutputView.printOrderMenu(recipt.getOrder());
        OutputView.printTotalPrice(recipt.calculateTotal());
    }

    private static void outPutEvent(EventManager event) {
        OutputView.printGiving(event.getGiveaway());
        OutputView.printBenefitList(event.getEvent());
        OutputView.printBenefitPrice(event.calculateTotalEventPrice());
        OutputView.printExpectedPrice(event.calculateExpectedPrice());
        OutputView.printBadge(event.giveBadge());
    }
}