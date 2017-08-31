package me.inspiringbits.cleanscene.ServiceImpl;

import me.inspiringbits.cleanscene.Mapper.VolunteerActivityMapper;
import me.inspiringbits.cleanscene.Model.BasicMessage;
import me.inspiringbits.cleanscene.Model.VolunteeringActivity;
import me.inspiringbits.cleanscene.Service.VolunteerService;
import me.inspiringbits.cleanscene.Tools.DateTimeTool;
import org.springframework.stereotype.Component;

/**
 * Created by IvanJian on 2017/8/29.
 */
@Component
public class VolunteerServiceImpl implements VolunteerService {

    final VolunteerActivityMapper volunteerActivityMapper;


    public VolunteerServiceImpl(VolunteerActivityMapper volunteerActivityMapper) {
        this.volunteerActivityMapper = volunteerActivityMapper;
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
            //TODO:check the start date time of this activity (start date should be before after join date)
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
    public BasicMessage dropOutFromActivity(int userId, int volunteeringActivityId) {
        try {
            volunteerActivityMapper.dropOutFromActivity(userId,volunteeringActivityId);
            //TODO:if there is no member, set the state to close
            BasicMessage message=new BasicMessage();
            message.setStatus(true);
            message.setCode("200");
            return message;
        } catch (Exception e){
            BasicMessage message=new BasicMessage();
            message.setStatus(false);
            message.setCode(BasicMessage.DROP_OUT_FAILED);
            message.setContent("You are not the member of this activity.");
            return message;
        }
    }

    @Override
    public BasicMessage createVolunteerActivity(VolunteeringActivity volunteeringActivity) {
        /**
         * TODO: set create date and time; set the status to open
         */
        try
        {
            volunteeringActivity.setCreatedDate(DateTimeTool.getCurrentDate());
            volunteeringActivity.setCreatedTime(DateTimeTool.getCurrentTime());
            volunteeringActivity.setStatus(volunteeringActivity.STATUS_OPEN);
            volunteerActivityMapper.createVolunteerActivity(volunteeringActivity);
        }catch (Exception e)
        {
            BasicMessage message=new BasicMessage();
            message.setStatus(false);
            message.setCode("444");
            message.setContent("Failed Creating activity.");
            return message;
        }
        return null;
    }
}
