package me.inspiringbits.cleanscene.ServiceImpl;

import me.inspiringbits.cleanscene.Mapper.LocationMapper;
import me.inspiringbits.cleanscene.Mapper.VolunteerActivityMapper;
import me.inspiringbits.cleanscene.Model.BasicMessage;
import me.inspiringbits.cleanscene.Model.Location;
import me.inspiringbits.cleanscene.Model.VolunteeringActivity;
import me.inspiringbits.cleanscene.Model.VolunteeringRecommendation;
import me.inspiringbits.cleanscene.Service.VolunteerService;
import me.inspiringbits.cleanscene.Tools.DateTimeTool;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by IvanJian on 2017/8/29.
 */
@Component
public class VolunteerServiceImpl implements VolunteerService {

    final VolunteerActivityMapper volunteerActivityMapper;
    final LocationMapper locationMapper;

    public VolunteerServiceImpl(VolunteerActivityMapper volunteerActivityMapper, LocationMapper locationMapper) {
        this.volunteerActivityMapper = volunteerActivityMapper;
        this.locationMapper = locationMapper;
    }

    @Override
    public BasicMessage joinVolunteeringActivity(int volunteeringActivityId, int userId) {
        try {
            VolunteeringActivity volunteeringActivity=volunteerActivityMapper.getVolunteerActivityById(volunteeringActivityId);
            if (volunteeringActivity.getStatus().equals(VolunteeringActivity.STATUS_CLOSE)){
                BasicMessage message=new BasicMessage();
                message.setStatus(false);
                message.setContent("This activity is closed.");
                message.setCode(BasicMessage.ACTIVITY_CLOSED);
                return message;
            }
            VolunteeringActivity vola = volunteerActivityMapper.getVolunteerActivityById(volunteeringActivityId);
            if(DateTimeTool.compareDateTime(vola.getActivityDate(),vola.getFromTime()
                    ,DateTimeTool.getCurrentDate(), DateTimeTool.getCurrentTime()).equals(DateTimeTool.AFTER)) {
                volunteerActivityMapper.joinVolunteering(userId, volunteeringActivityId);
                BasicMessage message = new BasicMessage();
                message.setStatus(true);
                message.setCode("200");
                return message;
            }
            BasicMessage message=new BasicMessage();
            message.setStatus(false);
            message.setCode("444");
            return message;
        } catch (Exception e){
            BasicMessage message=new BasicMessage();
            message.setStatus(false);
            message.setCode(BasicMessage.JOIN_VOLUNTEERING_FAILED);
            message.setContent("You are already a member of this activity.");
            return message;
        }
    }


    @Override
    public BasicMessage joinVolunteeringActivityAnonymous(int volunteeringActivityId)
    {
        VolunteeringActivity volunteeringActivity=volunteerActivityMapper.getVolunteerActivityById(volunteeringActivityId);
        if (volunteeringActivity.getStatus().equals(VolunteeringActivity.STATUS_CLOSE)){
            BasicMessage message=new BasicMessage();
            message.setStatus(false);
            message.setContent("This activity is closed.");
            message.setCode(BasicMessage.ACTIVITY_CLOSED);
            return message;
        }
        try {
            if (DateTimeTool.compareDateTime(volunteeringActivity.getActivityDate(), volunteeringActivity.getFromTime()
                    , DateTimeTool.getCurrentDate(), DateTimeTool.getCurrentTime()).equals(DateTimeTool.AFTER))
            {
                volunteerActivityMapper.joinVolunteeringjoinVolunteeringAnonymous(volunteeringActivityId);
                BasicMessage message = new BasicMessage();
                message.setStatus(true);
                message.setCode("200");
                return message;
            }
            BasicMessage message=new BasicMessage();
            message.setStatus(false);
            message.setCode("444");
            return message;
        }catch (Exception e)
        {
            BasicMessage message=new BasicMessage();
            message.setStatus(false);
            message.setCode(BasicMessage.JOIN_VOLUNTEERING_FAILED);
            message.setContent("You are already a member of this activity.");
            return message;
        }
    }


    @Override
    public BasicMessage dropOutFromActivity(int userId, int volunteeringActivityId) {
        try {
            volunteerActivityMapper.dropOutFromActivity(userId,volunteeringActivityId);
            if (volunteerActivityMapper.getVolunteerActivityMembersCount(userId,volunteeringActivityId) == 0)
            {
                volunteerActivityMapper.closeVolunteeringActivity(volunteeringActivityId);
            }
            BasicMessage message = new BasicMessage();
            message.setStatus(true);
            message.setCode("200");
            return message;
        } catch (Exception e){
            BasicMessage message=new BasicMessage();
            message.setStatus(false);
            message.setCode(BasicMessage.DROP_OUT_FAILED);
            message.setContent("You are not a member of this activity.");
            return message;
        }
    }

    @Override
    public BasicMessage createVolunteerActivity(VolunteeringActivity volunteeringActivity) {
        try
        {
            volunteeringActivity.setCreatedDate(DateTimeTool.getCurrentDate());
            volunteeringActivity.setCreatedTime(DateTimeTool.getCurrentTime());
            volunteeringActivity.setStatus(volunteeringActivity.STATUS_OPEN);
            volunteerActivityMapper.createVolunteerActivity(volunteeringActivity);
            BasicMessage message=new BasicMessage();
            message.setCode("200");
            message.setStatus(true);
            message.setContent(volunteeringActivity.getVolunteeringActivityId().toString());
            return message;
        }catch (Exception e)
        {
            BasicMessage message=new BasicMessage();
            message.setStatus(false);
            message.setCode("444");
            message.setContent("Failed Creating activity.");
            return message;
        }
    }

    @Override
    public List<VolunteeringActivity> getAllVolunteerActivityForUser(int userId) {

        return volunteerActivityMapper.getAllVolunteerActivityForUser(userId);

    }

    @Override
    public VolunteeringRecommendation getHighReportLocation(){
        List<String> highestReport = volunteerActivityMapper.getHighReportLocation();
        VolunteeringRecommendation volunteeringRecommendation = new VolunteeringRecommendation();
        if (highestReport.size() == 0) {
            volunteeringRecommendation.setTitle(null);
            return volunteeringRecommendation;
        }
        if (highestReport.size() == 1){
                if (highestReport.get(0).equals("N/A"))
                {
                    volunteeringRecommendation.setTitle(null);
                }
        }
        for (String hir:highestReport)
        {
            if (!hir.equals("N/A"))
            {
                volunteeringRecommendation.setTitle("There are numerous reports in " + hir +" would you like" +
                        " to create a volunteering activity there? ");
                Location location = locationMapper.getLocationById(hir);
                volunteeringRecommendation.setLatitude(location.getLat());
                volunteeringRecommendation.setLongitude(location.getLong());
            }

        }
        return volunteeringRecommendation;
    }
}
