package me.inspiringbits.cleanscene.ServiceImpl;

import me.inspiringbits.cleanscene.Mapper.FeedbackMapper;
import me.inspiringbits.cleanscene.Mapper.LocationMapper;
import me.inspiringbits.cleanscene.Mapper.ReportMapper;
import me.inspiringbits.cleanscene.Mapper.UserMapper;
import me.inspiringbits.cleanscene.Model.*;
import me.inspiringbits.cleanscene.Service.ReportService;
import org.springframework.stereotype.Component;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Base64;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

/**
 * Created by IvanJian on 2017/8/16.
 */
@Component
public class ReportServiceImpl implements ReportService {

    final ReportMapper reportMapper;
    final UserMapper userMapper;
    final LocationMapper locationMapper;
    final FeedbackMapper feedbackMapper;
    BasicMessage basicMessage = new BasicMessage();



    public ReportServiceImpl(ReportMapper reportMapper, UserMapper userMapper, LocationMapper locationMapper, FeedbackMapper feedbackMapper) {
        this.reportMapper = reportMapper;
        this.userMapper = userMapper;
        this.locationMapper = locationMapper;
        this.feedbackMapper = feedbackMapper;
    }


    @Override
    public BasicMessage saveEncodedImage(String encodedImage) {
        BufferedImage image = null;
        byte[] imageByte;
        try {
            imageByte = Base64.getMimeDecoder().decode(encodedImage);
            ByteArrayInputStream bis = new ByteArrayInputStream(imageByte);
            image = ImageIO.read(bis);
            bis.close();
            // write the image to a file
            String path="/var/www/html/images/";
            String timeStamp = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
            int num= 10000 + (int)(Math.random() * (99999 - 10000));
            String fileName=timeStamp+num+".jpeg";
            path+=fileName;
            File outputfile = new File(path);
            writeBtesToFile(imageByte,outputfile);
            path="images/"+fileName;
            BasicMessage basicMessage=new BasicMessage();
            basicMessage.setCode("200");
            basicMessage.setContent(path);
            basicMessage.setStatus(true);
            return basicMessage;
        } catch (IOException e) {
            e.printStackTrace();
            return new BasicMessage("400",false,"Error");
        }
    }

    @Override
    public BasicMessage saveReport(Report report) {
        System.out.println(report.getReportId()+" "+report.getUserId());
        List<Location> locations = locationMapper.getLocations();
        if(!validate(report)){
            basicMessage.setCode("444");
            basicMessage.setContent("Error, Please check your entries.");
            basicMessage.setStatus(false);
            return basicMessage;
        }
        if(!report.isHasMoreDetail())
        {
            report.setRating("N/A");
            report.setSource("N/A");
            report.setType("N/A");
            report.setDescription("N/A");
        }
        report.setLocationName("N/A");
        double dist = 100;
        for (Location l : locations) {
            if (l.getLat() != null && l.getLong() != null) {
                if (dist > this.distance(report.getLatitude(), report.getLongitude(), l.getLat(), l.getLong(), 'K')) {
                    dist = this.distance(report.getLatitude(), report.getLongitude(), l.getLat(), l.getLong(), 'K');
                    if (dist <= 2) {
                        report.setLocationName(l.getlName());
                    }
                }
            }
        }
        try {
            SimpleDateFormat sdfd = new SimpleDateFormat("yyyy-MM-dd");
            //String timeStamp = new SimpleDateFormat("yyyy-MM-dd").format(new java.util.Date());
            sdfd.setTimeZone(TimeZone.getTimeZone("GMT+10"));
            String timeStamp = sdfd.format(new java.util.Date());
            report.setDate(timeStamp);
            SimpleDateFormat sdft = new SimpleDateFormat("HH:mm:ss");
            sdft.setTimeZone(TimeZone.getTimeZone("GMT+10"));
            String timeStamp2 = sdft.format(new java.util.Date());
            //String timeStamp2 = new SimpleDateFormat("HH:mm:ss").format(new java.util.Date());
            report.setTime(timeStamp2);
        } catch (Exception e) {
            basicMessage.setCode("444");
            basicMessage.setContent("Error Collecting Server Time and Date.");
            basicMessage.setStatus(false);
            return basicMessage;
        }
        try {
            reportMapper.insertReport(report, report.getRating(), report.getSource(), report.getType(),
                    report.getLatitude(), report.getLongitude(), report.getDescription(), report.getPhoto(), report.getLocationName(),
                    report.isHasMoreDetail(), report.getDeviceId(), report.getUserId(), report.getDate(), report.getTime(),report.getStatus(),report.getPostcode());
            basicMessage.setCode("200");
            basicMessage.setContent(report.getReportId().toString());
            basicMessage.setStatus(true);
        } catch (Exception e) {
            e.printStackTrace();
            basicMessage.setCode("444");
            basicMessage.setContent(e.getMessage());
            basicMessage.setStatus(false);
            return basicMessage;
        }
        return basicMessage;

    }

    @Override
    public Boolean checkFeedbackId(FeedbackId feedbackId) {

        return null;
    }

    @Override
    public Report getById(Integer reportId) {
        return reportMapper.selectById(reportId);
    }

    @Override
    public void createFeedback(Feedback feedback) {
        feedbackMapper.createFeedback(feedback);
        Integer positive = getFeedbackCount(feedback.getFeedbackId().getReportId(),Feedback.CONTENT_POSITIVE);
        Integer negative = getFeedbackCount(feedback.getFeedbackId().getReportId(),Feedback.CONTENT_NEGATIVE);
        if (positive - negative >=3){
            changeReportStatus(feedback.getFeedbackId().getReportId(),Report.STATUS_RESOLVED);
        }
    }

    @Override
    public void changeReportStatus(Integer reportId, String status) {
        feedbackMapper.changeReportStatus(reportId,status);
    }

    @Override
    public Integer getFeedbackCount(Integer reportId, String content) {
        return feedbackMapper.getFeedbackCount(reportId,content);
    }

    private boolean validate(Report report) {
        if (report.getPhoto() == "" || report.getPhoto() == null){
            return false;
        }
        if (report.getLongitude() == null || report.getLatitude() == null){
            return false;
        }
        if (report.isHasMoreDetail() != false && report.isHasMoreDetail() != true){
            return false;
        }

        return true;
    }

    private void writeBtesToFile(byte[] bytes, File file) {
        OutputStream out;
        try {
            out = new FileOutputStream(file);
            out.write(bytes);
            out.flush();
            out.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /*
    * Source: https://dzone.com/articles/distance-calculation-using-3
    * */
    private double distance(double lat1, double lon1, double lat2, double lon2, char unit) {
        double theta = lon1 - lon2;
        double dist = Math.sin(deg2rad(lat1)) * Math.sin(deg2rad(lat2)) + Math.cos(deg2rad(lat1)) * Math.cos(deg2rad(lat2)) * Math.cos(deg2rad(theta));
        dist = Math.acos(dist);
        dist = rad2deg(dist);
        dist = dist * 60 * 1.1515;
        if (unit == 'K') {
            dist = dist * 1.609344;
        } else if (unit == 'N') {
            dist = dist * 0.8684;
        }
        return (dist);
    }

    /*:::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::*/
    /*::  This function converts decimal degrees to radians             :*/
    /*:::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::*/
    private double deg2rad(double deg) {
        return (deg * Math.PI / 180.0);
    }

    /*:::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::*/
    /*::  This function converts radians to decimal degrees             :*/
    /*:::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::*/
    private double rad2deg(double rad) {
        return (rad * 180.0 / Math.PI);
    }

}
