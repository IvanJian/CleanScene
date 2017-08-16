package me.inspiringbits.cleanscene.Controller;

import me.inspiringbits.cleanscene.Model.BasicMessage;
import me.inspiringbits.cleanscene.Service.ReportService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import sun.misc.BASE64Decoder;

import javax.annotation.Resource;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by IvanJian on 2017/8/16.
 */
@RestController
public class UploadingController {
    @Resource
    private ReportService reportService;

    @RequestMapping(value = "/upload/image",method = RequestMethod.POST,consumes = MediaType.ALL_VALUE)
    public BasicMessage uploadImg(@RequestBody BasicMessage msg){
        String encodedImage=msg.getContent();
        return reportService.saveEncodedImage(encodedImage);
    }


}
