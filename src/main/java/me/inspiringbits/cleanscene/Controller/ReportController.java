package me.inspiringbits.cleanscene.Controller;

import me.inspiringbits.cleanscene.Mapper.ReportMapper;
import me.inspiringbits.cleanscene.Model.Report;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by IvanJian on 2017/8/9.
 */
@RestController
public class ReportController {

    final ReportMapper reportMapper;

    public ReportController(ReportMapper reportMapper) {
        this.reportMapper = reportMapper;
    }

    @RequestMapping("/report/{id}")
    public @ResponseBody
    Report getReportById(@PathVariable Integer id){
        return reportMapper.selectById(id);
    }

    @RequestMapping("hello")
    public String hello(){
        return "Hello";
    }


}
