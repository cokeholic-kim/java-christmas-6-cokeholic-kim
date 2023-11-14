package christmas.Model;

public enum EventName {
    DDAY_EVENT("크리스마스 디데이 할인"),
    DATE_EVENT ("요일별 할인"),
    SPECIAL_EVENT ("특별 할인"),
    GIVEAWAY_EVENT ("증정 이벤트");

    private final String eventName;

    EventName(String eventName) {
        this.eventName = eventName;
    }
}
