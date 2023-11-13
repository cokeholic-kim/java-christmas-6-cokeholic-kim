package christmas.Model;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ReciptTest {

    Recipt recipt = new Recipt("티본스테이크-1,바비큐립-1,초코케이크-2,제로콜라-1");

    @Test
    @DisplayName("주문한 금액의 총합을 계산")
    void calculateTotal() {
        assertThat(
         recipt.calculateTotal()
        ).isEqualTo(142000);
    }

    @Test
    @DisplayName("디저트의 갯수를 카운트")
    void countDessertTest(){
        assertThat(
                recipt.countCategory("dessert")
        ).isEqualTo(2);
    }

    @Test
    @DisplayName("메인의 갯수를 카운트")
    void countMainTest(){
        assertThat(
                recipt.countCategory("main")
        ).isEqualTo(2);
    }
}