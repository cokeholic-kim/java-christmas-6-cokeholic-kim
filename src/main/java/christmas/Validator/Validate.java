package christmas.Validator;

import static christmas.ErrorMessage.INVALID_ORDER_MESSAGE;

import christmas.Menu;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.Map;

public class Validate {

    private static final int MINIMUM_QUANTITY = 1;
    private static final int MAXIMAL_QUANTITY = 20;
    public static void validateOrder(String input) {
        //검증순서는 가장 실수가 많을것같다고 생각되는부분부터 검증시도.
        checkMenuAvailability(input);
        checkMenuCount(input);
        //        메뉴 형식이 예시와 다른 경우, "[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요."라는 에러 메시지를 보여 준다.
        //        메뉴가 중복된 경우(e.g. 시저샐러드-1,시저샐러드-1), "[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요."라는 에러 메시지를 보여 준다.
        //        음료만 주문하는 경우
    }

    private static void checkMenuCount(String input) {
        //메뉴의 개수가 1미만인 경우, "[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요."라는 에러 메시지를 보여 준다.
        //메뉴가 20개를 초과하는 경우
        int count = countMenuQuantity(orderMapper(input));
        if(count > MAXIMAL_QUANTITY || count < MINIMUM_QUANTITY){
            throw new IllegalArgumentException(INVALID_ORDER_MESSAGE);
        }
    }



    private static void checkMenuAvailability(String orders) {
        //메뉴에 없는 메뉴를 입력하는 경우, "[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요."라는 에러 메시지를 보여 준다.
        Map<String, Integer> orderMenu = orderMapper(orders);
        orderMenu.keySet().stream().forEach(menuName -> {

            if (Menu.returnMenu(menuName) == Menu.NONE) {
                throw new IllegalArgumentException(INVALID_ORDER_MESSAGE);
            }
        });
    }

    //TODO:이 메서드의 적합한위치 고려하여 이후에 이동
    //맵에 Menu와 갯수를 매핑하는형태로 변경 고려. - > 입력에대한 검증을한후에 메뉴를 맵에 매핑하는게 좋을것같음,
    //이 메서드는 검증을 하기유용한형태로 변경해준다는 점에 집중.
    //주문을 종이에받아적고 검증한후에 pos에 입력한다는 개념으로 접근.
    public static Map<String, Integer> orderMapper(String orders) {
        Map<String, Integer> menu = new LinkedHashMap<>();
        Arrays.stream(orders.split(",", -1)).forEach(order -> {
            String[] menuInfo = order.split("-");
            menu.put(menuInfo[0], Integer.parseInt(menuInfo[1]));
        });
        return menu;
    }

    private static int countMenuQuantity(Map<String,Integer> mapppedOrder){
        return mapppedOrder.values().stream()
                .mapToInt(Integer::intValue)
                .sum();
    }

}
