package christmas.View;


import christmas.Validator.Validate;

public class InputManager {
    public static int getDate(){
        while (true) {
            try {
                String input = InputView.readDay();
                Validate.validateDate(input);
                return Integer.parseInt(input);
            } catch (IllegalArgumentException error) {
                System.out.println(error.getMessage());
            }
        }
    }

    public static String getOrder(){
        while (true){
            try{
                String input = InputView.readOrder();
                Validate.validateOrder(input);
                return input;
            }catch (IllegalArgumentException error){
                System.out.println(error.getMessage());
            }
        }
    }
}
