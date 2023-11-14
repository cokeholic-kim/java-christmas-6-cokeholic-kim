package christmas.Model;

import java.text.NumberFormat;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

public class Event {
    private final Recipt recipt;
    private boolean eventStatus = false;
    private final int YEAR = 2023;
    private final int MONTH = 12;
    private final int date;

    public Event(Recipt recipt, int date) {
        this.recipt = recipt;
        this.date = date;
        if (recipt.calculateTotal() > 10000) {
            this.eventStatus = true;
        }
    }

    public int calCulateDdayEvent() {
        if (date > 25 || !eventStatus) {
            return 0;
        }
        return (date - 1) * 100 + 1000;
    }

    public int dateDiscountEvent() {
//        - [ ] 평일 할인 이벤트
//                - 일요일 ~ 목요일
//                - [ ] 디저트메뉴 1개당 2023원 할인
//        - [ ] 주말 할인 이벤트
//                - 금요일,토요일
//                -[ ] 메인메뉴 1개당 2023원 할인
        int dayNumber = getDateNumber();
        if (calculateWeekDay(dayNumber) && eventStatus) {
            return YEAR * recipt.countCategory("dessert");
        }
        if (calculateWeekendDay(dayNumber) && eventStatus) {
            return YEAR * recipt.countCategory("main");
        }
        return 0;
    }

    public int specialDiscountEvent() {
        List<Integer> specialDays = Arrays.asList(3, 10, 17, 24, 25, 31);
        if (specialDays.contains(date) && eventStatus) {
            return 1000;
        }
        return 0;
    }

    public boolean giveawayEvent() {
        return recipt.calculateTotal() >= 120000;
    }

    public int giveawayEvnetPrice() {
        if (giveawayEvent() && eventStatus) {
            return 25000;
        }
        return 0;
    }

    public String getGiveaway() {
        if (giveawayEvent()) {
            return "샴페인 1개";
        }
        return "없음";
    }

    //어떤 이벤트가 있는지 알아야 출력이가능 없는 이벤트는 출력하지않는다.
    //이벤트의 해당사항이 하나도 없는 경우는 없음을 출력한다.
    //이벤트를 계산하고 있으므로 각 이벤트 계산식을 사용해서 0이면 없음 0이 아니면 이벤트가 있는걸로 판단.
    //TODO:: 아래 getEvent메서드 분리필요 ,, 각 이벤트를 모델로 분리고려.
    public String getEvent() {
        if (calculateTotalEventPrice() <= 0) {
            return "없음";
        }
        //여기서부터 해당하는 이벤트만 출력
        StringBuilder sb = new StringBuilder();
        dDayEventBuilder(sb);
        dateEvebtBuilder(sb);
        specialEventBuilder(sb);
        giveAwayBuilder(sb);
        return sb.toString();
    }

    private void dDayEventBuilder(StringBuilder sb) {
        if (calCulateDdayEvent() > 0) {
            sb.append("크리스마스 디데이 할인: -" + formatPrice(calCulateDdayEvent()) + "원\n");
        }
    }

    private void dateEvebtBuilder(StringBuilder sb) {
        if (dateDiscountEvent() > 0) {
            //평일 , 주말 따로출력,,
            if (calculateWeekDay(getDateNumber())) {
                sb.append("평일 할인: -" + formatPrice(dateDiscountEvent()) + "원\n");
            }
            if (calculateWeekendDay(getDateNumber())) {
                sb.append("주말 할인: -" + formatPrice(dateDiscountEvent()) + "원\n");
            }
        }
    }

    private void specialEventBuilder(StringBuilder sb) {
        if (specialDiscountEvent() > 0) {
            sb.append("특별 할인: -" + formatPrice(specialDiscountEvent()) + "원\n");
        }
    }

    private void giveAwayBuilder(StringBuilder sb) {
        if (giveawayEvent()) {
            sb.append("증정 이벤트: -" + formatPrice(giveawayEvnetPrice()) + "원\n");
        }
    }

    private String formatPrice(int price) {
        NumberFormat numberFormat = NumberFormat.getNumberInstance(Locale.getDefault());
        return numberFormat.format(price);
    }

    public int calculateTotalEventPrice() {
        if (!eventStatus) {
            return 0;
        }
        int dDayEvent = calCulateDdayEvent();
        int dateEvent = dateDiscountEvent();
        int specialEvent = specialDiscountEvent();
        int giveawayEvent = giveawayEvnetPrice();
        return dDayEvent + dateEvent + specialEvent + giveawayEvent;
    }

    public Badge giveBadge() {
        return Badge.returnBadge(calculateTotalEventPrice());
    }

    private boolean calculateWeekDay(int dayNumber) {
        return (1 <= dayNumber && dayNumber <= 4) || dayNumber == 7;
    }

    private boolean calculateWeekendDay(int dayNumber) {
        return 5 <= dayNumber && dayNumber <= 6;
    }

    private int getDateNumber() {
        return LocalDate.of(YEAR, MONTH, date).getDayOfWeek().getValue();
    }
}
