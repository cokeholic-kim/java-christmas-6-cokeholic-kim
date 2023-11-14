package christmas.Model;

public class GiveawayEvent implements Event {
    private final Recipt recipt;
    private final OrderDate orderDate;

    public GiveawayEvent(Recipt recipt, OrderDate orderDate) {
        this.recipt = recipt;
        this.orderDate = orderDate;
    }

    public boolean giveawayEvent() {
        return recipt.calculateTotal() >= 120000;
    }

    public int getPrice() {
        if (giveawayEvent()) {
            return 25000;
        }
        return 0;
    }

    public void EventStringBuild(StringBuilder sb) {
        if (giveawayEvent()) {
            sb.append("증정 이벤트: -").append(formatPrice(getPrice())).append("원\n");
        }
    }
}
