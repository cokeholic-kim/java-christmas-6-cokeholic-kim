package christmas.Model;

import java.util.Arrays;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

public class Recipt {
    private final Map<Menu, Integer> recipt;

    public Recipt(String orders) {
        recipt = Arrays.stream(orders.split(",", -1))
                .collect(Collectors.toMap(
                        order -> Menu.returnMenu(order.split("-")[0]),
                        order -> Integer.parseInt(order.split("-")[1])
                ));
    }

    public Map<Menu, Integer> getRecipt() {
        return recipt;
    }

    public int countCategory(String category) {
        return recipt.entrySet().stream()
                .filter(entry -> Objects.equals(entry.getKey().getCategory(), category))
                .mapToInt(Map.Entry::getValue)
                .sum();
    }

    public int calculateTotal() {
        return recipt.entrySet().stream()
                .mapToInt(recipt -> recipt.getKey().getPrice() * recipt.getValue())
                .sum();
    }

    public String getOrder() {
        return recipt.entrySet().stream()
                .map(order -> order.getKey().getName() + " " + order.getValue() + "개")
                .collect(Collectors.joining("\n"));
    }
}
