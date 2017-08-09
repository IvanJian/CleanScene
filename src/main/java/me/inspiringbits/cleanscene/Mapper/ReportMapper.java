package me.inspiringbits.cleanscene.Mapper;

import me.inspiringbits.cleanscene.Model.Report;
import org.apache.ibatis.annotations.Mapper;

/**
 * Created by IvanJian on 2017/8/7.
 */
@Mapper
public interface ReportMapper {
    Report selectById(Integer reportId);
}
