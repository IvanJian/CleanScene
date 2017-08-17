package me.inspiringbits.cleanscene.ServiceImpl;

import me.inspiringbits.cleanscene.Mapper.ReportMapper;
import me.inspiringbits.cleanscene.Mapper.UserMapper;
import me.inspiringbits.cleanscene.Model.BasicMessage;
import me.inspiringbits.cleanscene.Model.Report;
import me.inspiringbits.cleanscene.Service.ReportService;
import org.springframework.stereotype.Component;
import sun.misc.BASE64Decoder;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

/**
 * Created by IvanJian on 2017/8/16.
 */
@Component
public class ReportServiceImpl implements ReportService {

    final ReportMapper reportMapper;
    final UserMapper userMapper;
    BasicMessage basicMessage = new BasicMessage();
    public ReportServiceImpl(ReportMapper reportMapper, UserMapper userMapper) {
        this.reportMapper = reportMapper;
        this.userMapper = userMapper;

    }

    @Override
    public BasicMessage saveEncodedImage(String encodedImage) {
        BufferedImage image = null;
        byte[] imageByte;
        BASE64Decoder decoder = new BASE64Decoder();
        try {
            imageByte = decoder.decodeBuffer(encodedImage);
            ByteArrayInputStream bis = new ByteArrayInputStream(imageByte);
            image = ImageIO.read(bis);
            bis.close();
            // write the image to a file
            String path="src/main/webapp/images/";
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
        if(!validate(report)){

        }
        try {
            SimpleDateFormat sdfd = new SimpleDateFormat("yyyy-MM-dd");
            //String timeStamp = new SimpleDateFormat("yyyy-MM-dd").format(new java.util.Date());
            sdfd.setTimeZone(TimeZone.getTimeZone("GMT+11"));
            String timeStamp = sdfd.format(new java.util.Date());
            report.setDate(timeStamp);
            SimpleDateFormat sdft = new SimpleDateFormat("HH:mm:ss");
            sdft.setTimeZone(TimeZone.getTimeZone("GMT+11"));
            String timeStamp2 = sdft.format(new java.util.Date());
            //String timeStamp2 = new SimpleDateFormat("HH:mm:ss").format(new java.util.Date());
            report.setTime(timeStamp2);
        }catch(Exception e){}
        try {
            reportMapper.createReport(report.getReportId(),report.getRating(),report.getSource(),report.getType(),
                    report.getLatitude(),report.getLongitude(),report.getDescription(),report.getPhoto(),report.getLocationName(),
                    report.isHasMoreDetail(),report.getDeviceId(),null, report.getDate(),report.getTime());
            basicMessage.setCode("200");
            basicMessage.setContent("Report Submitted");
            basicMessage.setStatus(true);
        }
        catch (Exception e)
        {
            basicMessage.setCode("444");
            basicMessage.setContent(e.getMessage());
            basicMessage.setStatus(false);
            return basicMessage;
        }
        return basicMessage;
    }

    private boolean validate(Report report) {
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
}
