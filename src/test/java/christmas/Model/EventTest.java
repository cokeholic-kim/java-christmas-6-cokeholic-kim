package christmas.Model;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class EventTest {

    @Test
    @DisplayName("크리스마스때까지 100원씩증가하는이벤트 총금액이 만원미만")
    void calCulateDdayEventFalseTest() {
        Event event = new Event(new Recipt("아이스크림-1"));
        int date = 25;
        assertThat(
                event.calCulateDdayEvent(date)
        ).isEqualTo(0);
    }

    @Test
    @DisplayName("크리스마스때까지 100원씩증가하는이벤트 총금액이 만원이상")
    void calCulateDdayEventTrueTest() {
        Event event = new Event(new Recipt("아이스크림-4"));
        int date = 25;
        assertThat(
                event.calCulateDdayEvent(date)
        ).isEqualTo(3400);
    }
}