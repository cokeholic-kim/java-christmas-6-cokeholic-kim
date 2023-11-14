package christmas.Model;

import java.util.Arrays;

public enum Menu {
    MUSHROOM_SOUP("appetizer", "양송이수프", 6_000),
    TAPAS("appetizer", "타파스", 5_500),
    CAESAR_SALAD("appetizer", "시저샐러드", 8_000),
    TBONE_STEAK("main", "티본스테이크", 55_000),
    BARBECUE_RIB("main", "바비큐립", 54_000),
    SEAFOOD_PASTA("main", "해산물파스타", 35_000),
    CHRISTMAS_PASTA("main", "크리스마스파스타", 25_000),
    CHOCOLATE_CAKE("dessert", "초코케이크", 15_000),
    ICECREAM("dessert", "아이스크림", 5_000),
    ZERO_COKE("drink", "제로콜라", 3_000),
    RED_WINE("drink", "레드와인", 60_000),
    CHAMPAGNE("drink", "샴페인", 25_000),
    NONE("", "", 0);

    private final String category;
    private final String name;
    private final int price;

    Menu(String category, String name, int price) {
        this.category = category;
        this.name = name;
        this.price = price;
    }

    public static Menu returnMenu(String orderedMenu) {
        return Arrays.stream(Menu.values())
                .filter(menu -> isContainedMenu(menu,orderedMenu))
                .findFirst()
                .orElse(NONE);
    }

    public String getCategory() {
        return category;
    }

    public int getPrice(){
        return price;
    }
    public String getName(){return name;}

    private static boolean isContainedMenu(Menu menu, String menuName) {
        return menuName.equals(menu.name);
    }
}
