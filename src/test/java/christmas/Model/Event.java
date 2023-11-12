package christmas.Model;

public class Event {
    private final Recipt recipt;
    private boolean eventStatus = false;

    Event(Recipt recipt){
        this.recipt = recipt;
        if(recipt.CalculateTotal() > 10000) this.eventStatus = true;
    }
    public int calCulateDdayEvent(int date){
        if(date > 25 || !eventStatus){
            return 0;
        }
        return (date - 1) * 100 + 1000;
    }

}
