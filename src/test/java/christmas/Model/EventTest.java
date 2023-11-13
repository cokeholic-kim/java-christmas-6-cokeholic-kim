package christmas.Model;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class EventTest {

    @Test
    @DisplayName("크리스마스때까지 100원씩증가하는이벤트 총금액이 만원미만")
    void calculateDdayEventFalseTest() {
        Event event = new Event(new Recipt("아이스크림-1"),25);
        assertThat(
                event.calCulateDdayEvent()
        ).isEqualTo(0);
    }

    @Test
    @DisplayName("크리스마스때까지 100원씩증가하는이벤트 총금액이 만원이상")
    void calculateDdayEventTrueTest() {
        Event event = new Event(new Recipt("아이스크림-4"),25);
        assertThat(
                event.calCulateDdayEvent()
        ).isEqualTo(3400);
    }

    @Test
    @DisplayName("주말할인금액 테스트")
    void calculateWeekendDiscount(){
        Event event = new Event(new Recipt("티본스테이크-2,해산물파스타-3,아이스크림-4"),9);
        assertThat(
                event.dateDiscountEvent()
        ).isEqualTo(10115);
    }

    @Test
    @DisplayName("평일할인금액 테스트")
    void calculateWeekdayDiscount(){
        Event event = new Event(new Recipt("티본스테이크-2,해산물파스타-3,아이스크림-4"),7);
        assertThat(
                event.dateDiscountEvent()
        ).isEqualTo(8092);
    }

    @ParameterizedTest
    @DisplayName("특별할인금액 테스트")
    @ValueSource(ints = {3,10,17,24,25,31})
    void calculateSpecialDiscountEvent(int date){
        Event event = new Event(new Recipt("티본스테이크-2,해산물파스타-3,아이스크림-4"),date);
        assertThat(
                event.specialDiscountEvent()
        ).isEqualTo(1000);
    }

    @Test
    @DisplayName("증정품 이벤트")
    void giveawayEvnetTest(){
        Event event = new Event(new Recipt("티본스테이크-2,해산물파스타-3,아이스크림-4"),23);
        assertThat(
                event.giveawayEvent()
        ).isEqualTo(true);
        assertThat(
                event.giveawayEvnetPrice()
        ).isEqualTo(25000);
    }

    @Test
    @DisplayName("증정품 이벤트 해당없음")
    void giveawayEvnetPriceTest(){
        Event event = new Event(new Recipt("티본스테이크-1"),25);
        assertThat(
                event.giveawayEvent()
        ).isEqualTo(false);
        assertThat(
                event.giveawayEvnetPrice()
        ).isEqualTo(0);
    }

    @Test
    @DisplayName("총 할인금액 테스트")
    void calculateTotalEventPriceTest(){
        Event event = new Event(new Recipt("티본스테이크-1,바비큐립-1,초코케이크-2,제로콜라-1"),3);
        assertThat(
                event.calculateTotalEventPrice()
        ).isEqualTo(31246);
    }

    @Test
    @DisplayName("증정품 뱃지 테스트")
    void giveBadgeTest(){
        Event event = new Event(new Recipt("티본스테이크-1,바비큐립-1,초코케이크-2,제로콜라-1"),3);
        assertThat(
                event.giveBadge()
        ).isEqualTo(Badge.SANTA);
    }
}