package me.inspiringbits.cleanscene.Service;

import me.inspiringbits.cleanscene.Model.BasicMessage;

/**
 * Created by IvanJian on 2017/8/29.
 */
public interface VolunteerService {
    abstract BasicMessage joinVolunteeringActivity(int volunteeringActivityId,int userId);
    abstract BasicMessage dropOutFromActivity(int userId, int volunteeringActivityId);
}
