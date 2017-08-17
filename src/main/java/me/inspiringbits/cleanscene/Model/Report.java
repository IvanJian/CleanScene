package me.inspiringbits.cleanscene.Model;

/**
 * Created by Abdulkareem on 2017/8/7.
 */
public class Report
{
    private Integer reportId;
    private String rating;
    private String source;
    private String type;
    private Double Latitude;
    private Double Longitude;
    private String description;
    private String photo;
    private String locationName;
    private Boolean hasMoreDetail;
    private String deviceId;
    private String Date;
    private String Time;



    public Report() {
    }

    public Integer getReportId() {
        return reportId;
    }

    public void setReportId(Integer reportId) {
        this.reportId = reportId;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Double getLatitude() {
        return Latitude;
    }

    public void setLatitude(Double latitude) {
        Latitude = latitude;
    }

    public Double getLongitude() {
        return Longitude;
    }

    public void setLongitude(Double longitude) {
        Longitude = longitude;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getLocationName() {
        return locationName;
    }

    public void setLocationName(String locationName) {
        this.locationName = locationName;
    }

    public Boolean isHasMoreDetail() {
        return hasMoreDetail;
    }

    public void setHasMoreDetail(Boolean hasMoreDetail) {
        this.hasMoreDetail = hasMoreDetail;
    }
    public String getDate()
    {
        return Date;
    }
    public void setDate(String ndate)
    {
        Date = ndate;
    }

    public String getTime()
    {
        return Time;
    }
    public void setTime(String ntime)
    {
        Time = ntime;
    }

}
