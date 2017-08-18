package me.inspiringbits.cleanscene.Model;

public class Volunteer {
    private String vName;
    private String Address;
    private String Suburb;

    public Volunteer()
    {}
    public Volunteer(String vName, String Address, String Suburb) {
        this.vName = vName;
        this.Address= Address;
        this.Suburb = Suburb;
    }

    public String getlName() {
        return vName;
    }
    public String getAddress(){
        return Address;
    }
    public String getSuburb(){ return Suburb; }
    public void setlName(String vName){
        this.vName = vName;
    }
    public void setAddress(String Address){
        this.Address = Address;
    }
    public void setSuburb(String Suburb){
        this.Suburb = Suburb;
    }
}
