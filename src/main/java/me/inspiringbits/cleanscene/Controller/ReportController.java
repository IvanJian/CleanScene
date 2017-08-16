package me.inspiringbits.cleanscene.Controller;

import me.inspiringbits.cleanscene.Mapper.ReportMapper;
import me.inspiringbits.cleanscene.Mapper.UserMapper;
import me.inspiringbits.cleanscene.Model.BasicMessage;
import me.inspiringbits.cleanscene.Model.Report;
import me.inspiringbits.cleanscene.Model.User;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.MediaType;
import java.util.Calendar;
import java.text.SimpleDateFormat;

import java.util.List;

/**
 * Created by Abdulkareem on 2017/8/9.
 */
@RestController
public class ReportController {

    final ReportMapper reportMapper;
    final UserMapper userMapper;
    BasicMessage basicMessage;

    public ReportController(ReportMapper reportMapper, UserMapper userMapper) {
        this.reportMapper = reportMapper;
        this.userMapper = userMapper;
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

    @RequestMapping(value = "/report/create")
    public @ResponseBody
    BasicMessage createReport(@RequestBody Report report)
    {
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
        report.setTime(sdf.toString());
        SimpleDateFormat sdf1 = new SimpleDateFormat("yyy/MM/dd");
        report.setDate(sdf1.toString());
        try {
            reportMapper.createReport(report.getReportId(),report.getRating(),report.getSource(),report.getType(),
                    report.getLatitude(),report.getLongitude(),report.getDescription(),report.getPhoto(),report.getLocationName(),
                    report.hasMoreDetail(),report.getDeviceId(),null, report.getDate(),report.getTime());
            basicMessage.setCode("200");
            basicMessage.setContent("Report Submitted");
            basicMessage.setSuccess(true);
        }
        catch (Exception e)
        {
            basicMessage.setCode("444");
            basicMessage.setContent("Couldn't Submit Report");
            basicMessage.setSuccess(false);
            return basicMessage;
        }
        return basicMessage;
    }

}
