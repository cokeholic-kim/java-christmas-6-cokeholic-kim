package christmas;

import static christmas.Menu.BARBECUE_RIB;
import static christmas.Menu.CAESAR_SALAD;
import static christmas.Menu.CHAMPAGNE;
import static christmas.Menu.CHOCOLATE_CAKE;
import static christmas.Menu.CHRISTMAS_PASTA;
import static christmas.Menu.ICECREAM;
import static christmas.Menu.MUSHROOM_SOUP;
import static christmas.Menu.NONE;
import static christmas.Menu.RED_WINE;
import static christmas.Menu.SEAFOOD_PASTA;
import static christmas.Menu.TAPAS;
import static christmas.Menu.TBONE_STEAK;
import static christmas.Menu.ZERO_COKE;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class MenuTest {

    @DisplayName("문자열로입력한 메뉴를 Enum의 객체로 반환")
    @ParameterizedTest
    @MethodSource("menuParameter")
    void returnMenuTest(String input,Menu expected) {
        assertThat(
                Menu.returnMenu(input)
        ).isEqualTo(expected);
    }

    static Stream<Arguments> menuParameter(){
        return Stream.of(
                Arguments.of("양송이수프",MUSHROOM_SOUP),
                Arguments.of("타파스",TAPAS),
                Arguments.of("시저샐러드",CAESAR_SALAD),
                Arguments.of("티본스테이크",TBONE_STEAK),
                Arguments.of("바베큐립",BARBECUE_RIB),
                Arguments.of("해산물파스타",SEAFOOD_PASTA),
                Arguments.of("크리스마스파스타",CHRISTMAS_PASTA),
                Arguments.of("초코케이크",CHOCOLATE_CAKE),
                Arguments.of("아이스크림",ICECREAM),
                Arguments.of("제로콜라",ZERO_COKE),
                Arguments.of("레드와인",RED_WINE),
                Arguments.of("샴페인",CHAMPAGNE),
                Arguments.of("다이어트콜라",NONE),
                Arguments.of("아무런 값이나 입력함",NONE)
        );
    }
}