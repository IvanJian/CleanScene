package me.inspiringbits.cleanscene.Mapper;

import me.inspiringbits.cleanscene.Model.Feedback;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * Created by IvanJian on 2017/9/11.
 */
@Mapper
public interface FeedbackMapper {
    void createFeedback(Feedback feedback);
    Integer getFeedbackCount(@Param("reportId") Integer reportId);
    void changeReportStatus(@Param("reportId") Integer reportId, @Param("status") String status);
}
