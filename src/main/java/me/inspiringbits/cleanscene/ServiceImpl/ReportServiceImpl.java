package me.inspiringbits.cleanscene.ServiceImpl;

import me.inspiringbits.cleanscene.Model.BasicMessage;
import me.inspiringbits.cleanscene.Service.ReportService;
import org.springframework.stereotype.Component;
import sun.misc.BASE64Decoder;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by IvanJian on 2017/8/16.
 */
@Component
public class ReportServiceImpl implements ReportService {

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
            return new BasicMessage("200",true,path);
        } catch (IOException e) {
            e.printStackTrace();
            return new BasicMessage("400",false,"Error");
        }
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
