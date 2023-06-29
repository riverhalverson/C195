package halverson.c195;

import java.time.Month;

public class MonthlyApptRow {
    private Month month;
    int count;

    public MonthlyApptRow(Month month, int count){
        this.month = month;
        this.count = count;
    }
    public Month getMonth(){
        return month;
    }
    public int getCount(){
        return count;
    }
}
