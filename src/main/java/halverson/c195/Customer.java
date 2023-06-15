package halverson.c195;

public class Customer {
    private int customerId;
    private String name;
    private String address;
    private int postalCode;
    private int phoneNumber;
    private String stateProvince;


    public Customer(int customerId, String name, String address, int postalCode, int phoneNumber, String stateProvince){
        this.customerId = customerId;
        this.name = name;
        this.address = address;
        this.postalCode = postalCode;
        this.phoneNumber = phoneNumber;
        this.stateProvince = stateProvince;
    }

    public int getId(){
        return customerId;
    }

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

    public int getPostalCode(){
        return postalCode;
    }

    public void setPostalCode(int postalCode){
        this.postalCode = postalCode;
    }

    public int getPhoneNumber(){
        return phoneNumber;
    }

    public void setPhoneNumber(int phoneNumber){
        this.phoneNumber = phoneNumber;
    }

    public String getStateProvince(){
        return stateProvince;
    }

    public void setStateProvince(String stateProvince){
        this.stateProvince = stateProvince;
    }
}

