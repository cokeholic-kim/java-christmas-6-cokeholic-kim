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
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;

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

    @DisplayName("메뉴의 입력값 검증")
    @ParameterizedTest
    @CsvSource({
            "notContainedMenu1 =티본스테이크-1,바비큐립-1,초코케이크-2,제로콜라-1",
            "notContainedMenu2 =다이어트콜라-1,바베큐립-1,초코케이크-2,제로콜라-1",
            "notContainedMenu3 =해잔물파스타-2,레드와인-1,초코케이크-1",
            "MenuCountWrong1 =티본스테이크-0,바베큐립-0,시저샐러드-0,제로콜라-0",
            "MenuCountWrong2 =티본스테이크-19,바베큐립-2",
            "MenuCountWrong3 =바베큐립-19,시저샐러드-1,제로콜라-3",
            "MenuFormWrong1 =티본스테이크/0,바베큐립-0",
            "MenuFormWrong2 =시저샐러드--0,제로콜라-0",
            "MenuFormWrong3 =시저샐러드-0개,제로콜라-11개",
            "MenuRedundant =티본스테이크-1,티본스테이크-2",
            "MenuOnlyDrink =제로콜라-1,레드와인-2"
    })
    void testMenuOrderValidate(String order){
        assertThatThrownBy(() -> Validate.validateOrder(order)).isInstanceOf(IllegalArgumentException.class)
                .hasMessage(INVALID_ORDER_MESSAGE)
                .hasMessageStartingWith(ERROR_PREFIX);
    }
}
