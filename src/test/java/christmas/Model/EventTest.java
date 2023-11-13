package christmas.Model;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

class EventTest {

    @Test
    @DisplayName("크리스마스때까지 100원씩증가하는이벤트 총금액이 만원미만")
    void calculateDdayEventFalseTest() {
        Event event = new Event(new Recipt("아이스크림-1"));
        int date = 25;
        assertThat(
                event.calCulateDdayEvent(date)
        ).isEqualTo(0);
    }

    @Test
    @DisplayName("크리스마스때까지 100원씩증가하는이벤트 총금액이 만원이상")
    void calculateDdayEventTrueTest() {
        Event event = new Event(new Recipt("아이스크림-4"));
        int date = 25;
        assertThat(
                event.calCulateDdayEvent(date)
        ).isEqualTo(3400);
    }

    @Test
    @DisplayName("주말할인금액 테스트")
    void calculateWeekendDiscount(){
        Event event = new Event(new Recipt("티본스테이크-2,해산물파스타-3,아이스크림-4"));
        int date = 9; // 주말 8,9
        assertThat(
                event.dateDiscountEvent(date)
        ).isEqualTo(10115);
    }

    @Test
    @DisplayName("평일할인금액 테스트")
    void calculateWeekdayDiscount(){
        Event event = new Event(new Recipt("티본스테이크-2,해산물파스타-3,아이스크림-4"));
        int date = 7; // 주말 7,8
        assertThat(
                event.dateDiscountEvent(date)
        ).isEqualTo(8092);
    }

    @ParameterizedTest
    @DisplayName("특별할인금액 테스트")
    @ValueSource(ints = {3,10,17,24,25,31})
    void calculateSpecialDiscountEvent(int date){
        Event event = new Event(new Recipt("티본스테이크-2,해산물파스타-3,아이스크림-4"));
        assertThat(
                event.specialDiscountEvent(date)
        ).isEqualTo(1000);
    }

    @Test
    @DisplayName("증정품 이벤트")
    void giveawayEvnetTest(){
        Event event = new Event(new Recipt("티본스테이크-2,해산물파스타-3,아이스크림-4"));
        assertThat(
                event.giveawayEvent()
        ).isEqualTo(true);
        assertThat(
                event.giveawayEvnetPrice()
        ).isEqualTo(20000);
    }

    @Test
    @DisplayName("증정품 이벤트 해당없음")
    void giveawayEvnetPriceTest(){
        Event event = new Event(new Recipt("티본스테이크-1"));
        assertThat(
                event.giveawayEvent()
        ).isEqualTo(false);
        assertThat(
                event.giveawayEvnetPrice()
        ).isEqualTo(0);
    }
}