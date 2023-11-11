package christmas.Validator;

import static christmas.ErrorMessage.ERROR_PREFIX;
import static christmas.ErrorMessage.INVALID_ORDER_MESSAGE;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Stream;
import org.assertj.core.api.Assert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
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

    @Test
    @DisplayName("메뉴에 없는 메뉴를 입력하는 경우")
//    @ValueSource(strings  = {
//            "티본스테이크-1,바비큐립-1,초코케이크-2,다이어트콜라-1"
//    })
    void validateOrderTest() {
        String input = "티본스테이크-1,바비큐립-1,초코케이크-2,다이어트콜라-1";
        assertThatThrownBy(() -> {
            Validate.validateOrder(input);
        }).isInstanceOf(IllegalArgumentException.class)
                .hasMessage(INVALID_ORDER_MESSAGE);
//                .hasMessageStartingWith(ERROR_PREFIX);
    }
}
