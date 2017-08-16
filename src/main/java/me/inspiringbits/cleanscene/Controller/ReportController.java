package me.inspiringbits.cleanscene.Controller;

import me.inspiringbits.cleanscene.Mapper.ReportMapper;
import me.inspiringbits.cleanscene.Mapper.UserMapper;
import me.inspiringbits.cleanscene.Model.Report;
import me.inspiringbits.cleanscene.Model.User;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by IvanJian on 2017/8/9.
 */
@RestController
public class ReportController {

    final ReportMapper reportMapper;
    final UserMapper userMapper;

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




}
