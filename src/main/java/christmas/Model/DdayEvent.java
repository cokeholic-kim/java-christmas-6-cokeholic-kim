package christmas.Model;

public class DdayEvent implements Event {
    private final Recipt recipt;
    private final OrderDate orderDate;

    public DdayEvent(Recipt recipt, OrderDate orderDate) {
        this.recipt = recipt;
        this.orderDate = orderDate;
    }

    public int getPrice() {
        if (orderDate.getDATE() > 25) {
            return 0;
        }
        return (orderDate.getDATE() - 1) * 100 + 1000;
    }

    public void EventStringBuild(StringBuilder sb) {
        if (getPrice() > 0) {
            sb.append("크리스마스 디데이 할인: -").append(formatPrice(getPrice())).append("원\n");
        }
    }
}
