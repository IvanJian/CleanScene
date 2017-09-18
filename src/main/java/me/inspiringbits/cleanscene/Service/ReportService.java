package me.inspiringbits.cleanscene.Service;

import me.inspiringbits.cleanscene.Model.BasicMessage;
import me.inspiringbits.cleanscene.Model.Feedback;
import me.inspiringbits.cleanscene.Model.FeedbackId;
import me.inspiringbits.cleanscene.Model.Report;

/**
 * Created by IvanJian on 2017/8/16.
 */
public interface ReportService {

    abstract BasicMessage saveEncodedImage(String encodedImage);
    abstract BasicMessage saveReport(Report report);
    abstract Boolean checkFeedbackId(FeedbackId feedbackId);
    abstract Report getById(Integer reportId);
    abstract void createFeedback(Feedback feedback);
    abstract void changeReportStatus(Integer reportId, String status);
    abstract Integer getFeedbackCount(Integer reportId, String content);
}
