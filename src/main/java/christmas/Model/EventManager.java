package christmas.Model;

import java.util.LinkedHashMap;
import java.util.Map;


public class EventManager {
    private final Recipt recipt;
    private final OrderDate orderDate;
    private final Map<EventName, Event> events = new LinkedHashMap<>();

    public EventManager(Recipt recipt, int date) {
        this.recipt = recipt;
        this.orderDate = new OrderDate(date);
        if (recipt.calculateTotal() > 10000) {
            events.put(EventName.DDAY_EVENT, new DdayEvent(recipt, this.orderDate));
            events.put(EventName.DATE_EVENT, new DateEvent(recipt, this.orderDate));
            events.put(EventName.SPECIAL_EVENT, new SpecialEvent(recipt, this.orderDate));
            events.put(EventName.GIVEAWAY_EVENT, new GiveawayEvent(recipt, this.orderDate));
        }
    }

    public String getEvent() {
        StringBuilder sb = new StringBuilder();
        if (calculateTotalEventPrice() == 0) {
            sb.append("없음\n");
            return sb.toString();
        }
        events.values()
                .forEach(event -> event.EventStringBuild(sb));
        return sb.toString();
    }

    public int getEventPrice(EventName eventName) {
        try {
            return events.get(eventName).getPrice();
        } catch (NullPointerException ex) {
            return 0;
        }
    }

    public String getGiveaway() {
        if (events.isEmpty()) {
            return "없음";
        }
        if (events.get(EventName.GIVEAWAY_EVENT).getPrice() > 0) {
            return "샴페인 1개";
        }
        return "없음";
    }

    public int calculateTotalEventPrice() {
        if (events.isEmpty()) {
            return 0;
        }
        return -events.values().stream()
                .mapToInt(Event::getPrice)
                .sum();
    }

    public int calculateExpectedPrice() {
        return recipt.calculateTotal() + (calculateTotalEventPrice() + getEventPrice(EventName.GIVEAWAY_EVENT));
    }

    public Badge giveBadge() {
        return Badge.returnBadge(calculateTotalEventPrice());
    }

}
