package halverson.c195;

public class Customer {
    private int customerId;
    private String name;
    private String address;
    private String postalCode;
    private String phoneNumber;
    private int divisionId;


    public Customer(int customerId, String name, String address, String postalCode, String phoneNumber, int divisionId){
        this.customerId = customerId;
        this.name = name;
        this.address = address;
        this.postalCode = postalCode;
        this.phoneNumber = phoneNumber;
        this.divisionId = divisionId;
    }

    public int getId(){ return customerId; }

    public void setId(int id){
        this.customerId = id;
    }

    public String getName(){
        return name;
    }

    public void setName(String name){
        this.name = name;
    }

    public String getAddress(){
        return address;
    }

    public void setAddress(String address){
        this.address = address;
    }

    public String getPostalCode(){ return postalCode; }

    public void setPostalCode(String postalCode){
        this.postalCode = postalCode;
    }

    public String getPhoneNumber(){
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber){
        this.phoneNumber = phoneNumber;
    }

    public int getDivisionId(){
        return divisionId;
    }

    public void setStateProvince(int divisionId){
        this.divisionId = divisionId;
    }
}

