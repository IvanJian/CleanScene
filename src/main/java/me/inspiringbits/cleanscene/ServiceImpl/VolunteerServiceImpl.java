package me.inspiringbits.cleanscene.ServiceImpl;

import me.inspiringbits.cleanscene.Mapper.VolunteerActivityMapper;
import me.inspiringbits.cleanscene.Model.BasicMessage;
import me.inspiringbits.cleanscene.Service.VolunteerService;
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
            volunteerActivityMapper.joinVolunteering(userId,volunteeringActivityId);
            BasicMessage message=new BasicMessage();
            message.setStatus(true);
            message.setCode("200");
            return message;
        } catch (Exception e){
            BasicMessage message=new BasicMessage();
            message.setStatus(false);
            message.setCode(BasicMessage.JOIN_VOLUNTEERING_FAILED);
            message.setContent("You are already the member of this activity.");
            return message;
        }
    }

    @Override
    public BasicMessage dropOutFromActivity(int userId, int volunteeringActivityId) {
        try {
            volunteerActivityMapper.dropOutFromActivity(userId,volunteeringActivityId);
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
}
