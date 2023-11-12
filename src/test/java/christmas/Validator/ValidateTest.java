package christmas.Validator;

import static christmas.ErrorMessage.ERROR_PREFIX;
import static christmas.ErrorMessage.INVALID_ORDER_MESSAGE;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.Map;
import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

class ValidateTest {

    @ParameterizedTest
    @DisplayName("문자열의 주문을 입력받아서 맵형식의 메뉴로 리턴")
    @MethodSource("orderParameter")
    void orderMapperTest(String input, Map<String, Integer> expected) {
        assertThat(
                Validate.orderMapper(input)
        ).isEqualTo(expected);
    }

    static Stream<Arguments> orderParameter() {
        return Stream.of(
                Arguments.of("해산물파스타-2,레드와인-1,초코케이크-1", Map.of("해산물파스타", 2, "레드와인", 1, "초코케이크", 1)),
                Arguments.of("타파스-1,제로콜라-1", Map.of("타파스", 1, "제로콜라", 1)),
                Arguments.of("티본스테이크-1,바비큐립-1,초코케이크-2,제로콜라-1", Map.of("티본스테이크", 1, "바비큐립", 1, "초코케이크", 2, "제로콜라", 1))
        );
    }

    @DisplayName("메뉴에 없는 메뉴를 입력하는 경우")
    @ParameterizedTest
    @ValueSource(strings = {
            "티본스테이크-1,바비큐립-1,초코케이크-2,제로콜라-1",
            "다이어트콜라-1,바베큐립-1,초코케이크-2,제로콜라-1",
            "해잔물파스타-2,레드와인-1,초코케이크-1"})
    void validateOrderTest(String input) {
        assertThatThrownBy(() -> Validate.validateOrder(input)).isInstanceOf(IllegalArgumentException.class)
                .hasMessage(INVALID_ORDER_MESSAGE)
                .hasMessageStartingWith(ERROR_PREFIX);
    }

    //TODO : 테스트에 들어가는 파라미터만다르고 동작은 일치하므로 이후에 파라미터와 기대값 으로 나누어서 하나로 합치기
    @DisplayName("메뉴의 갯수가 1개이상 20개이하가 아닌 경우")
    @ParameterizedTest
    @ValueSource(strings = {
            "티본스테이크-0,바베큐립-0,시저샐러드-0,제로콜라-0",
            "티본스테이크-19,바베큐립-2",
            "바베큐립-19,시저샐러드-1,제로콜라-3",
    })
    void validateOrderCountTest(String input) {
        assertThatThrownBy(() -> Validate.validateOrder(input)).isInstanceOf(IllegalArgumentException.class)
                .hasMessage(INVALID_ORDER_MESSAGE)
                .hasMessageStartingWith(ERROR_PREFIX);
    }

    @DisplayName("메뉴의 입력형식이 예시와 다른경우")
    @ParameterizedTest
    @ValueSource(strings = {
            "티본스테이크/1,바베큐립-5",
            "시저샐러드--2,제로콜라-4",
            "시저샐러드-3개,제로콜라-11개",
            ""
    })
    void validateCheckFormTest(String input) {
        assertThatThrownBy(() -> Validate.validateOrder(input)).isInstanceOf(IllegalArgumentException.class)
                .hasMessage(INVALID_ORDER_MESSAGE)
                .hasMessageStartingWith(ERROR_PREFIX);
    }

    @DisplayName("메뉴가 중복된 경우")
    @ParameterizedTest
    @ValueSource(strings = {
            "티본스테이크-1,티본스테이크-2"
    })
    void validateCheckMenuRedundant(String input) {
        assertThatThrownBy(() -> Validate.validateOrder(input)).isInstanceOf(IllegalArgumentException.class)
                .hasMessage(INVALID_ORDER_MESSAGE)
                .hasMessageStartingWith(ERROR_PREFIX);
    }

    @DisplayName("음료만 주문하는경우")
    @ParameterizedTest
    @ValueSource(strings = {
            "제로콜라-1,레드와인-2"
    })
    void validateCheckMenuOnlyDrink(String input) {
        assertThatThrownBy(() -> Validate.validateOrder(input)).isInstanceOf(IllegalArgumentException.class)
                .hasMessage(INVALID_ORDER_MESSAGE)
                .hasMessageStartingWith(ERROR_PREFIX);
    }
}
