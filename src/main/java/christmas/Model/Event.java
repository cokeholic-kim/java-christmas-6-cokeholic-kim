package christmas.Model;

import java.text.NumberFormat;
import java.util.Locale;

public interface Event {
    default String formatPrice(int price) {
        NumberFormat numberFormat = NumberFormat.getNumberInstance(Locale.getDefault());
        return numberFormat.format(price);
    }

    int getPrice();

    void EventStringBuild(StringBuilder sb);
}
