package christmas.Model;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

public class Event {
    private final Recipt recipt;
    private boolean eventStatus = false;
    private final int YEAR = 2023;
    private final int MONTH = 12;
    private final int date;
    Event(Recipt recipt,int date){
        this.recipt = recipt;
        this.date = date;
        if(recipt.calculateTotal() > 10000) this.eventStatus = true;
    }
    public int calCulateDdayEvent(){
        if(date > 25 || !eventStatus){
            return 0;
        }
        return (date - 1) * 100 + 1000;
    }

    public int dateDiscountEvent(){
//        - [ ] 평일 할인 이벤트
//                - 일요일 ~ 목요일
//                - [ ] 디저트메뉴 1개당 2023원 할인
//        - [ ] 주말 할인 이벤트
//                - 금요일,토요일
//                -[ ] 메인메뉴 1개당 2023원 할인
        int dayNumber = getDateNumber();
        if(calculateWeekDay(dayNumber) && eventStatus){
            return YEAR * recipt.countCategory("dessert");
        }
        if(calculateWeekendDay(dayNumber) && eventStatus){
            return YEAR * recipt.countCategory("main");
        }
        return 0;
    }

    public int specialDiscountEvent(){
        List<Integer> specialDays = Arrays.asList(3,10,17,24,25,31) ;
        if(specialDays.contains(date) && eventStatus){
            return 1000;
        }
        return 0;
    }

    public boolean giveawayEvent(){
        return recipt.calculateTotal() >= 120000;
    }

    public int giveawayEvnetPrice(){
        if(giveawayEvent() && eventStatus) return 25000;
        return 0;
    }

    public int calculateTotalEventPrice(){
        if(!eventStatus) return 0;
        int dDayEvent = calCulateDdayEvent();
        int dateEvent = dateDiscountEvent();
        int specialEvent = specialDiscountEvent();
        int giveawayEvent = giveawayEvnetPrice();
        return dDayEvent + dateEvent + specialEvent + giveawayEvent;
    }
    public Badge giveBadge(){
        return Badge.returnBadge(calculateTotalEventPrice());
    }

    private boolean calculateWeekDay(int dayNumber){
        return (1 <= dayNumber && dayNumber <= 4) || dayNumber == 7;
    }
    private boolean calculateWeekendDay(int dayNumber){
        return 5 <= dayNumber && dayNumber <= 6;
    }
    private int getDateNumber(){
        return LocalDate.of(YEAR,MONTH,date).getDayOfWeek().getValue();
    }
}
