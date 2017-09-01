package me.inspiringbits.cleanscene.Controller;

import me.inspiringbits.cleanscene.Mapper.VolunteerActivityMapper;
import me.inspiringbits.cleanscene.Mapper.VolunteerMapper;
import me.inspiringbits.cleanscene.Model.*;
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

    //return all vol activities
    @RequestMapping("/volunteer/activity/all")
    public @ResponseBody
    List<VolunteeringActivity> getAllVolunteeringActivity() {
        List<VolunteeringActivity> Volunteers = volunteerActivityMapper.getAllVolunteerActivity();
        return Volunteers;
    }
    //return all vol activities for a specific user
    @RequestMapping("/volunteer/activity/user/{id}")
    public @ResponseBody
    List<VolunteeringActivity> getAllVolunteerActivityForUser(@PathVariable("id") int userId){
        return volunteerService.getAllVolunteerActivityForUser(userId);
    }
    //create v activity
    @RequestMapping(value = "/volunteer/activity/create",method = RequestMethod.POST)
    @ResponseBody
    public BasicMessage createVolunteeringActivity(@RequestBody VolunteeringActivity volunteeringActivity)
    {
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

    @RequestMapping(value = "/volunteer/activity/join/{volunteeringActivityId}")
    @ResponseBody BasicMessage joinVolunteeringActivityAnonymous(@PathVariable("volunteeringActivityId") int volunteeringActivityId){
        return volunteerService.joinVolunteeringActivityAnonymous(volunteeringActivityId);
    }

    @RequestMapping(value = "/volunteer/activity/dropout/{volunteeringActivityId}/{userId}")
    @ResponseBody BasicMessage dropOutFromActivity(@PathVariable("volunteeringActivityId") int volunteeringActivityId, @PathVariable("userId") int userId){
        return volunteerService.dropOutFromActivity(userId,volunteeringActivityId);
    }


    //Find the locations with more than 4 reports to recomend for a user (Return: V recom model)
    @RequestMapping("/volunteer/activity/recommend")
    public @ResponseBody
    VolunteeringRecommendation getVolunteerRecommendation() {
        return volunteerService.getHighReportLocation();
    }

}
