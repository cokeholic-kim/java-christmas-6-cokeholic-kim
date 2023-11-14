package christmas.Validator;

import static christmas.ErrorMessage.INVALID_DATE_MESSAGE;
import static christmas.ErrorMessage.INVALID_ORDER_MESSAGE;

import christmas.Model.Menu;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.regex.Pattern;

public class Validate {
    private static final int MINIMUM_QUANTITY = 1;
    private static final int MAXIMAM_QUANTITY = 20;
    private static final Pattern ORDER_PATTERN = Pattern.compile("\\b[가-힣]+-\\d+\\b");

    private static final int MINIMUN_DATE = 1;
    private static final int MAXIMAM_DATE = 31;

    public static void validateOrder(String input) {
        checkMenuForm(input);
        checkMenuRedundant(input);
        checkMenuAvailability(input);
        checkMenuCount(input);
        checkMenuOnlyDrink(input);
    }

    public static void validateDate(String input) {
        checkDateNotNumber(input);
        checkDateOutOfRange(input);
    }

    private static void checkDateOutOfRange(String input) {
        int date = Integer.parseInt(input);
        if (MINIMUN_DATE > date || MAXIMAM_DATE < date) {
            throw new IllegalArgumentException(INVALID_DATE_MESSAGE);
        }
    }

    private static void checkDateNotNumber(String input) {
        if (!input.matches("\\d+")) {
            throw new IllegalArgumentException(INVALID_DATE_MESSAGE);
        }
    }

    private static void checkMenuOnlyDrink(String input) {
        boolean hasNonDrinkMenu = orderMapper(input).keySet().stream()
                .map(Menu::returnMenu)
                .anyMatch(menu -> !menu.getCategory().equalsIgnoreCase("drink"));
        if (!hasNonDrinkMenu) {
            throw new IllegalArgumentException(INVALID_ORDER_MESSAGE);
        }
    }

    private static void checkMenuRedundant(String input) {
        int beforeSize = input.split(",").length;
        int removeRedundantSize = orderMapper(input).size();
        if (beforeSize > removeRedundantSize) {
            throw new IllegalArgumentException(INVALID_ORDER_MESSAGE);
        }
    }

    private static void checkMenuForm(String input) {
        Arrays.stream(input.split(",")).forEach(menu -> {
            if (!ORDER_PATTERN.matcher(menu).matches()) {
                throw new IllegalArgumentException(INVALID_ORDER_MESSAGE);
            }
        });
    }

    private static void checkMenuCount(String input) {
        orderMapper(input).values()
                .forEach(count -> {
                    if (count > MAXIMAM_QUANTITY || count < MINIMUM_QUANTITY) {
                        throw new IllegalArgumentException(INVALID_ORDER_MESSAGE);
                    }
                });
    }


    private static void checkMenuAvailability(String orders) {
        Map<String, Integer> orderMenu = orderMapper(orders);
        orderMenu.keySet().forEach(menuName -> {
            if (Menu.returnMenu(menuName) == Menu.NONE) {
                throw new IllegalArgumentException(INVALID_ORDER_MESSAGE);
            }
        });
    }

    public static Map<String, Integer> orderMapper(String orders) {
        Map<String, Integer> menu = new LinkedHashMap<>();
        Arrays.stream(orders.split(",", -1)).forEach(order -> {
            String[] menuInfo = order.split("-");
            menu.put(menuInfo[0], Integer.parseInt(menuInfo[1]));
        });
        return menu;
    }

    private static int countMenuQuantity(Map<String, Integer> mapppedOrder) {
        return mapppedOrder.values().stream()
                .mapToInt(Integer::intValue)
                .sum();
    }
}
