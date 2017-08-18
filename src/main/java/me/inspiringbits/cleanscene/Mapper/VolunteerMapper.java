package me.inspiringbits.cleanscene.Mapper;

import me.inspiringbits.cleanscene.Model.Volunteer;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface VolunteerMapper {
    List<Volunteer> getAllVolunteerGroups ();
}
