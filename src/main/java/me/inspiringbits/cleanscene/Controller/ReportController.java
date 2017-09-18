package me.inspiringbits.cleanscene.Controller;

import me.inspiringbits.cleanscene.Mapper.ReportMapper;
import me.inspiringbits.cleanscene.Mapper.UserMapper;
import me.inspiringbits.cleanscene.Model.BasicMessage;
import me.inspiringbits.cleanscene.Model.NumOfReportResult;
import me.inspiringbits.cleanscene.Model.Report;
import me.inspiringbits.cleanscene.Model.User;
import me.inspiringbits.cleanscene.Service.ReportService;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.MediaType;

import javax.annotation.Resource;
import java.security.Timestamp;
import java.util.Calendar;
import java.text.SimpleDateFormat;

import java.util.List;
import java.util.TimeZone;

/**
 * Created by Abdulkareem on 2017/8/9.
 */
@RestController
@CrossOrigin
public class ReportController {

    final ReportMapper reportMapper;
    final UserMapper userMapper;
    BasicMessage basicMessage = new BasicMessage();
    final ReportService reportService ;

    public ReportController(ReportMapper reportMapper, UserMapper userMapper, ReportService reportService) {
        this.reportMapper = reportMapper;
        this.userMapper = userMapper;
        this.reportService = reportService;
    }

    @RequestMapping("/report/{id}")
    public @ResponseBody
    Report getReportById(@PathVariable Integer id) {
        return reportMapper.selectById(id);
    }

    @RequestMapping("/report/all")
    public @ResponseBody
    List<Report> getAllReports() {
        List<Report> reports = reportMapper.selectAll();
        return reports;
    }

    @RequestMapping(value = "/report/create",method = RequestMethod.POST)
    @ResponseBody
    public BasicMessage createReport(@RequestBody Report report){
        report.setStatus(Report.STATUS_UNRESOLVED);
        return reportService.saveReport(report);
    }

    @RequestMapping(value = "/report/num/postcode")
    public List<NumOfReportResult> getReportNumByPostcode(){
        return reportMapper.numOfReportInPostcode();
    }
}
