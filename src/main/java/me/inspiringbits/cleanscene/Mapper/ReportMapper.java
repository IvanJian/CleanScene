package me.inspiringbits.cleanscene.Mapper;

import me.inspiringbits.cleanscene.Model.Report;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by Abdulkareem on 2017/8/7.
 */
@Mapper
public interface ReportMapper {
    Report selectById(Integer reportId);
    List<Report> selectAll();
    Report createReport(@Param("rid") Integer rid, @Param("rating") String rating, @Param("source") String source,
                        @Param("type") String type, @Param("lat") Double lat, @Param("longit") Double longit,
                        @Param("description") String description, @Param("photo") String photo,
                        @Param("locationName") String locationName, @Param("hasMore") boolean hasMore,
                        @Param("deviceId") String deviceId, @Param("userId") Integer userId,
                        @Param("date") String date, @Param("time") String time);
}
