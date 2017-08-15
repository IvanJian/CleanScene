package me.inspiringbits.cleanscene.Model;

/**
 * Created by Ivan on 2017/8/12.
 */

public class ReportModel {
    private Integer reportId;
    private String rating;
    private String source;
    private String type;
    private double Latitude;
    private double Longitude;
    private String description;
    private String photo;
    private String locationName;
    private boolean hasMoreDetail;
    private String deviceId;


    public ReportModel() {
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

    public double getLatitude() {
        return Latitude;
    }

    public void setLatitude(double latitude) {
        Latitude = latitude;
    }

    public double getLongitude() {
        return Longitude;
    }

    public void setLongitude(double longitude) {
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

    public boolean hasMoreDetail() {
        return hasMoreDetail;
    }

    public void setHasMoreDetail(boolean hasMoreDetail) {
        this.hasMoreDetail = hasMoreDetail;
    }
}
