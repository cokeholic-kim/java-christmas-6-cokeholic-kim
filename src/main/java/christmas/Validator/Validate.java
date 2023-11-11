package christmas.Validator;

import static christmas.ErrorMessage.INVALID_ORDER_MESSAGE;

import christmas.Menu;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class Validate {
    public static void validateOrder(String input){
        checkMenuAvailability(input);
        //        메뉴의 개수가 1미만인 경우, "[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요."라는 에러 메시지를 보여 준다.
        //        메뉴 형식이 예시와 다른 경우, "[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요."라는 에러 메시지를 보여 준다.
        //        메뉴가 중복된 경우(e.g. 시저샐러드-1,시저샐러드-1), "[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요."라는 에러 메시지를 보여 준다.
        //        메뉴가 20개를 초과하는 경우
        //        음료만 주문하는 경우
    }

    private static void checkMenuAvailability(String orders){
//        메뉴에 없는 메뉴를 입력하는 경우, "[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요."라는 에러 메시지를 보여 준다.
        Map<String,Integer> orderMenu = orderMapper(orders);
        orderMenu.keySet().stream().forEach(menuName ->{

            if(Menu.returnMenu(menuName) == Menu.NONE){
                throw new IllegalArgumentException(INVALID_ORDER_MESSAGE);
            }
        });
    }

    //TODO:이 메서드의 적합한위치 고려하여 이후에 이동
    public static Map<String,Integer> orderMapper(String orders){
        Map<String,Integer> menu = new LinkedHashMap<>();
        Arrays.stream(orders.split(",",-1)).forEach(order -> {
            String[] menuInfo = order.split("-");
            menu.put(menuInfo[0],Integer.parseInt(menuInfo[1]));
        });
        return menu;
    }

}
