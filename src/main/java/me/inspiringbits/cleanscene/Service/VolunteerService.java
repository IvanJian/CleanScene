package me.inspiringbits.cleanscene.Service;

import me.inspiringbits.cleanscene.Model.BasicMessage;
import me.inspiringbits.cleanscene.Model.VolunteeringActivity;
import me.inspiringbits.cleanscene.Model.VolunteeringRecommendation;

import java.util.List;

/**
 * Created by IvanJian on 2017/8/29.
 */
public interface VolunteerService {
    abstract BasicMessage joinVolunteeringActivity(int volunteeringActivityId,int userId);
    abstract BasicMessage dropOutFromActivity(int userId, int volunteeringActivityId);
    abstract BasicMessage createVolunteerActivity(VolunteeringActivity volunteeringActivity);
    abstract BasicMessage joinVolunteeringActivityAnonymous(int volunteeringActivityId);
    abstract List<VolunteeringActivity> getAllVolunteerActivityForUser(int userId);
    abstract VolunteeringRecommendation getHighReportLocation();
}
