package christmas.Model;

import java.util.Arrays;

public enum Badge {
    NONE(0, "없음"),
    STAR(5000, "별"),
    TREE(10000, "트리"),
    SANTA(20000, "산타");

    private final int minimumPrice;
    private final String badgeName;

    Badge(int minimumPrice, String badgeName) {
        this.minimumPrice = minimumPrice;
        this.badgeName = badgeName;
    }

    public String getBadgeName() {
        return badgeName;
    }

    public static Badge returnBadge(int price) {
        return Arrays.stream(Badge.values())
                .sorted((b1, b2) -> Integer.compare(b2.minimumPrice, b1.minimumPrice))
                .filter(badge -> badge.minimumPrice <= price)
                .findFirst()
                .orElse(NONE);
    }
}
