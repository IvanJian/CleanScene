package me.inspiringbits.cleanscene.Controller;

import me.inspiringbits.cleanscene.Model.BasicMessage;
import me.inspiringbits.cleanscene.Service.ReportService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * Created by IvanJian on 2017/8/16.
 */
@RestController
public class UploadingController {
    @Resource
    private ReportService reportService;

    @RequestMapping(value = "/upload/image",method = RequestMethod.POST,consumes = MediaType.ALL_VALUE)
    @ResponseBody
    public BasicMessage uploadImg(@RequestBody BasicMessage msg){
        String encodedImage=msg.getContent();
        return reportService.saveEncodedImage(encodedImage);
    }


}
