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
        EventManager event = new EventManager(new Recipt("아이스크림-1"), 25);
        assertThat(
                event.getEventPrice(EventName.DDAY_EVENT)
        ).isEqualTo(0);
    }

    @Test
    @DisplayName("크리스마스때까지 100원씩증가하는이벤트 총금액이 만원이상")
    void calculateDdayEventTrueTest() {
        EventManager event = new EventManager(new Recipt("아이스크림-4"), 25);
        assertThat(
                event.getEventPrice(EventName.DDAY_EVENT)
        ).isEqualTo(3400);
    }

    @Test
    @DisplayName("주말할인금액 테스트")
    void calculateWeekendDiscount() {
        EventManager event = new EventManager(new Recipt("티본스테이크-2,해산물파스타-3,아이스크림-4"), 8);
        assertThat(
                event.getEventPrice(EventName.DATE_EVENT)
        ).isEqualTo(10115);
    }

    @Test
    @DisplayName("평일할인금액 테스트")
    void calculateWeekdayDiscount() {
        EventManager event = new EventManager(new Recipt("티본스테이크-2,해산물파스타-3,아이스크림-4"), 7);
        assertThat(
                event.getEventPrice(EventName.DATE_EVENT)
        ).isEqualTo(8092);
    }

    @ParameterizedTest
    @DisplayName("특별할인금액 테스트")
    @ValueSource(ints = {3, 10, 17, 24, 25, 31})
    void calculateSpecialDiscountEvent(int date) {
        EventManager event = new EventManager(new Recipt("티본스테이크-2,해산물파스타-3,아이스크림-4"), date);
        assertThat(
                event.getEventPrice(EventName.SPECIAL_EVENT)
        ).isEqualTo(1000);
    }

    @Test
    @DisplayName("증정품 이벤트")
    void giveawayEvnetTest() {
        EventManager event = new EventManager(new Recipt("티본스테이크-2,해산물파스타-3,아이스크림-4"), 23);
        assertThat(
                event.getEventPrice(EventName.GIVEAWAY_EVENT)
        ).isEqualTo(25000);
    }

    @Test
    @DisplayName("증정품 이벤트 해당없음")
    void giveawayEvnetPriceTest() {
        EventManager event = new EventManager(new Recipt("티본스테이크-1"), 25);
        assertThat(
                event.getEventPrice(EventName.GIVEAWAY_EVENT)
        ).isEqualTo(0);
    }

    @Test
    @DisplayName("총 할인금액 테스트")
    void calculateTotalEventPriceTest() {
        EventManager event = new EventManager(new Recipt("티본스테이크-1,바비큐립-1,초코케이크-2,제로콜라-1"), 3);
        assertThat(
                event.calculateTotalEventPrice()
        ).isEqualTo(31246);
    }

    @Test
    @DisplayName("증정품 뱃지 테스트")
    void giveBadgeTest() {
        EventManager event = new EventManager(new Recipt("초코케이크-1"), 3);
        assertThat(
                event.giveBadge()
        ).isEqualTo(Badge.NONE);
    }


    @Test
    @DisplayName("예상결제금액 테스트")
    void expectedPriceTest(){
        EventManager event = new EventManager(new Recipt("티본스테이크-1,바비큐립-1,초코케이크-2,제로콜라-1"),3);
        assertThat(
                event.calculateExpectedPrice()
        ).isEqualTo(135754);
    }

    @Test
    @DisplayName("예상결제금액 테스트2")
    void expectedPriceTest2(){
        EventManager event = new EventManager(new Recipt("타파스-1,제로콜라-1"),26);
        assertThat(
                event.calculateExpectedPrice()
        ).isEqualTo(8500);
    }

}