package christmas.View;

import christmas.Model.Badge;

public class OutputView {
    public static void printWelcome(){
        System.out.print("안녕하세요! 우테코 식당 12월 이벤트 플래너입니다.");
    }

    public static void printAskVisitDate(){
        System.out.println("12월 중 식당 예상 방문 날짜는 언제인가요? (숫자만 입력해 주세요!)");
    }

    public static void printAskOrder(){
        System.out.println("주문하실 메뉴를 메뉴와 개수를 알려 주세요. (e.g. 해산물파스타-2,레드와인-1,초코케이크-1)");
    }

    public static void printEventPreView(int date){
        System.out.printf("12월 %d일에 우테코 식당에서 받을 이벤트 혜택 미리 보기!%n%n",date);
    }

    public static void printOrderMenu(String menu){
        System.out.println("<주문 메뉴>");
        System.out.println(menu);
        System.out.println();
    }

    public static void printTotalPrice(int price){
        System.out.println("<할인 전 총주문 금액>");
        System.out.printf("%,d원",price);
        System.out.println();
        System.out.println();
    }

    public static void printGiving(String giving){
        System.out.println("<증정 메뉴>");
        System.out.println(giving);
        System.out.println();
    }

    public static void printBenefitList(String benefit){
        System.out.println("<혜택 내역>");
        System.out.println(benefit);
        System.out.println();
    }

    public static void printBenefitPrice(int price){
        System.out.println("<총혜택 금액>");
        System.out.printf("-%,d원",price);
        System.out.println();
        System.out.println();
    }

    public static void printExpectedPrice(int expected){
        System.out.println("<할인 후 예상 결제 금액>");
        System.out.println(expected + "원");
        System.out.println();
    }

    public static  void printBadge(Badge badge){
        System.out.println("<12월 이벤트 배지>");
        System.out.println(badge.getBadgeName());
    }
}
