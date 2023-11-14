package christmas.Model;

import java.util.Arrays;
import java.util.List;

public class SpecialEvent implements Event {
    private final Recipt recipt;
    private final OrderDate orderDate;

    public SpecialEvent(Recipt recipt, OrderDate orderDate) {
        this.recipt = recipt;
        this.orderDate = orderDate;
    }

    public int getPrice() {
        List<Integer> specialDays = Arrays.asList(3, 10, 17, 24, 25, 31);
        if (specialDays.contains(orderDate.getDATE())) {
            return 1000;
        }
        return 0;
    }

    public void EventStringBuild(StringBuilder sb) {
        if (getPrice() > 0) {
            sb.append("특별 할인: -").append(formatPrice(getPrice())).append("원\n");
        }
    }
}
