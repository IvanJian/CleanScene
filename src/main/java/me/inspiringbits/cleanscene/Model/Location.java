package me.inspiringbits.cleanscene.Model;

public class Location {
    private String lName;
    private Double latitude;
    private Double longitude;

    public Location()
    {}
    public Location(String lname, Double lat, Double longi) {
        this.lName = lname;
        this.latitude= lat;
        this.longitude = longi;
    }

    public String getlName() {
        return lName;
    }
    public Double getLat(){
        return latitude;
    }
    public Double getLong(){ return longitude; }
    public void setlName(String lName){
        this.lName = lName;
    }
    public void setLat(Double lat){
        this.latitude = lat;
    }
    public void setLong(Double longi){
        this.longitude = longi;
    }
}
