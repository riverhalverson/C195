package halverson.c195.entities;

/** This method holds the row object for the reports page to display the sum of apts in each country */
public class CountrySumRow {
    private String country;
    private int count;

    /** This method is the main class object for the country sum
     * @param country the country to sum appointments for
     * @param count the sum
     */
    public CountrySumRow(String country, int count){
        this.country = country;
        this.count = count;
    }
    /** This method gets the country for this object type
     * @return the country
     */
    public String getCountry() {
        return country;
    }
    /** This method gets the count for the country
     * @return the count
     */
    public int getCount(){
        return count;
    }
}
