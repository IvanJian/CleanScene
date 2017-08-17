package me.inspiringbits.cleanscene.Service;

import me.inspiringbits.cleanscene.Model.BasicMessage;
import me.inspiringbits.cleanscene.Model.Report;

/**
 * Created by IvanJian on 2017/8/16.
 */
public interface ReportService {

    abstract BasicMessage saveEncodedImage(String encodedImage);
    abstract BasicMessage saveReport(Report report);
}
