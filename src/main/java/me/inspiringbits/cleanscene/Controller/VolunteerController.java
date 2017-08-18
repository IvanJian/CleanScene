package me.inspiringbits.cleanscene.Controller;

import me.inspiringbits.cleanscene.Mapper.VolunteerMapper;
import me.inspiringbits.cleanscene.Model.Report;
import me.inspiringbits.cleanscene.Model.Volunteer;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by Abdulkareem on 2017/8/9.
 */
@RestController
public class VolunteerController {

    final VolunteerMapper volunteerMapper;

    public VolunteerController(VolunteerMapper volunteerMapper) {
        this.volunteerMapper = volunteerMapper;
    }

    @RequestMapping("/volunteer/all")
    public @ResponseBody
    List<Volunteer> getAllVolunteerGroups() {
        List<Volunteer> Volunteers = volunteerMapper.getAllVolunteerGroups();
        return Volunteers;
    }



}
