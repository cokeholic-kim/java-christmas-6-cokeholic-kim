package christmas.Model;

import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;

public class Recipt {
    private final Map<Menu,Integer> recipt;

    public Recipt(String orders) {
        recipt = Arrays.stream(orders.split(",", -1))
                .collect(Collectors.toMap(
                   order -> Menu.returnMenu(order.split("-")[0]),
                   order -> Integer.parseInt(order.split("-")[1])
                ));
    }

    public int CalculateTotal(){
        return recipt.entrySet().stream()
                .mapToInt(recipt -> recipt.getKey().getPrice() * recipt.getValue())
                .sum();
    }
}
