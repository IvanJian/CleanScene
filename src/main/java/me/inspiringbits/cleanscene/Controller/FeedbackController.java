package me.inspiringbits.cleanscene.Controller;

import me.inspiringbits.cleanscene.Model.BasicMessage;
import me.inspiringbits.cleanscene.Model.Feedback;
import me.inspiringbits.cleanscene.Model.FeedbackId;
import me.inspiringbits.cleanscene.Model.Report;
import me.inspiringbits.cleanscene.Service.ReportService;
import me.inspiringbits.cleanscene.Tools.DateTimeTool;
import org.springframework.web.bind.annotation.*;

/**
 * Created by IvanJian on 2017/9/11.
 */
@RestController
public class FeedbackController {

    ReportService reportService;

    public FeedbackController(ReportService reportService) {
        this.reportService = reportService;
    }

    @RequestMapping(value = "/report/feedback", method = RequestMethod.POST)
    public @ResponseBody BasicMessage createFeedback(@RequestBody Feedback feedback){
        Report report = reportService.getById(feedback.getFeedbackId().getReportId());
        if (report.getStatus().equals(Report.STATUS_CLOSED) || report.getStatus().equals(Report.STATUS_RESOLVED)){
            return new BasicMessage(BasicMessage.FEEDBACK_FAILED,false,"Report is closed");
        }
        try {
            feedback.setDate(DateTimeTool.getCurrentDate());
            reportService.createFeedback(feedback);
        } catch (Exception e){
            return new BasicMessage(BasicMessage.FEEDBACK_FAILED,false,"You can only give feedback once");
        }
        return new BasicMessage(BasicMessage.SUCCESS,true,null);
    }

    @RequestMapping("/report/testf")
    public void test(){
       Feedback feedback=new Feedback();
       feedback.setDate(DateTimeTool.getCurrentDate());
       feedback.setContent(Feedback.CONTENT_POSITIVE);
       FeedbackId feedbackId = new FeedbackId();
       feedbackId.setReportId(60);
       feedbackId.setUserId(13);
       feedback.setFeedbackId(feedbackId);
       reportService.createFeedback(feedback);
    }

}
