package me.inspiringbits.cleanscene.Mapper;

import me.inspiringbits.cleanscene.Model.VolunteeringActivity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * Created by IvanJian on 2017/8/29.
 */
@Mapper
public interface VolunteerActivityMapper {
    VolunteeringActivity getVolunteerActivityById(@Param("volunteeringActivityId") int volunteeringActivityId);
    void joinVolunteering(@Param("userId") int userId, @Param("volunteeringActivityId") int volunteeringActivityId);
    void dropOutFromActivity(@Param("userId") int userId, @Param("volunteeringActivityId") int volunteeringActivityId);
}
