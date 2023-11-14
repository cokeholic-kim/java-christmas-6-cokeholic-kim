package christmas.Model;

public class DateEvent implements Event {
    private final Recipt recipt;
    private final OrderDate orderDate;

    public DateEvent(Recipt recipt, OrderDate orderDate) {
        this.recipt = recipt;
        this.orderDate = orderDate;
    }

    public int getPrice() {
        if (orderDate.isWeekDay()) {
            return orderDate.getYEAR() * recipt.countCategory("dessert");
        }
        if (orderDate.isWeekendDay()) {
            return orderDate.getYEAR() * recipt.countCategory("main");
        }
        return 0;
    }

    public void EventStringBuild(StringBuilder sb) {
        if (getPrice() > 0) {
            if (orderDate.isWeekDay()) {
                sb.append("평일 할인: -").append(formatPrice(getPrice())).append("원\n");
            }
            if (orderDate.isWeekendDay()) {
                sb.append("주말 할인: -").append(formatPrice(getPrice())).append("원\n");
            }
        }
    }
}
