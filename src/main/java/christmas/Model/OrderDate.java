package christmas.Model;

import java.time.LocalDate;

public class OrderDate {
    private final int DATE;
    private final int YEAR = 2023;
    private final int MONTH = 12;

    public OrderDate(int date) {
        this.DATE = date;
    }

    public int getDATE() {
        return DATE;
    }

    public int getYEAR() {
        return YEAR;
    }

    public int getMONTH() {
        return MONTH;
    }

    public int getDateNumber() {
        return LocalDate.of(YEAR, MONTH, DATE).getDayOfWeek().getValue();
    }
    public boolean isWeekDay() {
        return (1 <= getDateNumber() && getDateNumber() <= 4) || getDateNumber() == 7;
    }

    public boolean isWeekendDay() {
        return 5 <= getDateNumber() && getDateNumber() <= 6;
    }
}
