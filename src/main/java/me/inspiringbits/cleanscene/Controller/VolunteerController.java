package me.inspiringbits.cleanscene.Controller;

import me.inspiringbits.cleanscene.Mapper.VolunteerActivityMapper;
import me.inspiringbits.cleanscene.Mapper.VolunteerMapper;
import me.inspiringbits.cleanscene.Model.BasicMessage;
import me.inspiringbits.cleanscene.Model.Volunteer;
import me.inspiringbits.cleanscene.Model.VolunteeringActivity;
import me.inspiringbits.cleanscene.Service.VolunteerService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by Abdulkareem on 2017/8/9.
 */
@RestController
public class VolunteerController {

    final VolunteerMapper volunteerMapper;
    final VolunteerActivityMapper volunteerActivityMapper;
    VolunteerService volunteerService;

    public VolunteerController(VolunteerMapper volunteerMapper, VolunteerActivityMapper volunteerActivityMapper, VolunteerService volunteerService) {
        this.volunteerMapper = volunteerMapper;
        this.volunteerActivityMapper = volunteerActivityMapper;
        this.volunteerService = volunteerService;
    }

    @RequestMapping("/volunteer/all")
    public @ResponseBody
    List<Volunteer> getAllVolunteerGroups() {
        List<Volunteer> Volunteers = volunteerMapper.getAllVolunteerGroups();
        return Volunteers;
    }

    //return al vol activities
    @RequestMapping("/volunteer/activity/all")
    public @ResponseBody
    List<VolunteeringActivity> getAllVolunteeringActivity() {
        List<VolunteeringActivity> Volunteers = volunteerActivityMapper.getAllVolunteerActivity();
        return Volunteers;
    }
    //create v activity
    @RequestMapping("/volunteer/activity/create")
    public @ResponseBody BasicMessage createActivity(@RequestBody VolunteeringActivity volunteeringActivity){
        return volunteerService.createVolunteerActivity(volunteeringActivity);
    }

    @RequestMapping("/volunteer/activity/{id}")
    public @ResponseBody
    VolunteeringActivity getVolunteeringActivityById(@PathVariable("id") int volunteeringActivityId){
        return volunteerActivityMapper.getVolunteerActivityById(volunteeringActivityId);
    }

    @RequestMapping(value = "/volunteer/activity/join/{volunteeringActivityId}/{userId}")
    @ResponseBody BasicMessage joinVolunteeringActivity(@PathVariable("volunteeringActivityId") int volunteeringActivityId, @PathVariable("userId") int userId){
        return volunteerService.joinVolunteeringActivity(volunteeringActivityId,userId);
    }

    @RequestMapping(value = "/volunteer/activity/dropout/{volunteeringActivityId}/{userId}")
    @ResponseBody BasicMessage dropOutFromActivity(@PathVariable("volunteeringActivityId") int volunteeringActivityId, @PathVariable("userId") int userId){
        return volunteerService.dropOutFromActivity(userId,volunteeringActivityId);
    }


}
