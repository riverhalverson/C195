package halverson.c195.entities;

import java.time.Month;

/** This class is the object for the reports page view sum of appointments by row table*/
public class MonthlyApptRow {
    private Month month;
    int count;

    /** This method is the main object for the sum of apts by month for the report window
     * @param month the month to be summed
     * @param count the sum of the appointments in the given month
     */
    public MonthlyApptRow(Month month, int count){
        this.month = month;
        this.count = count;
    }
    /** This method gets the current month
     * @return count the current month
     */
    public Month getMonth(){
        return month;
    }
    public int getCount(){
        return count;
    }
}
