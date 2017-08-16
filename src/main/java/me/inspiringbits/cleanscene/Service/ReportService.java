package me.inspiringbits.cleanscene.Service;

import me.inspiringbits.cleanscene.Model.BasicMessage;

/**
 * Created by IvanJian on 2017/8/16.
 */
public interface ReportService {

    abstract BasicMessage saveEncodedImage(String encodedImage);
}
