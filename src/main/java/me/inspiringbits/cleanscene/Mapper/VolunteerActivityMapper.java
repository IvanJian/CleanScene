package me.inspiringbits.cleanscene.Mapper;

import me.inspiringbits.cleanscene.Model.VolunteeringActivity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by IvanJian on 2017/8/29.
 */
@Mapper
public interface VolunteerActivityMapper {
    VolunteeringActivity getVolunteerActivityById(@Param("volunteeringActivityId") int volunteeringActivityId);
    void joinVolunteering(@Param("userId") int userId, @Param("volunteeringActivityId") int volunteeringActivityId);
    void joinVolunteeringjoinVolunteeringAnonymous(@Param("volunteeringActivityId") int volunteeringActivityId);
    void dropOutFromActivity(@Param("userId") int userId, @Param("volunteeringActivityId") int volunteeringActivityId);
    void createVolunteerActivity(VolunteeringActivity volunteeringActivity);
    List<VolunteeringActivity> getAllVolunteerActivity();
    int getVolunteerActivityMembersCount(@Param("volunteeringActivityId") int volunteeringActivityId);
    void closeVolunteeringActivity(@Param("volunteeringActivityId") int volunteeringActivityId);
    List<VolunteeringActivity> getAllVolunteerActivityForUser(@Param("userId") int userId);
    List<String> getHighReportLocation();
}
