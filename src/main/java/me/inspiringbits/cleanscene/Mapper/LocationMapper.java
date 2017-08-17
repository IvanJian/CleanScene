package me.inspiringbits.cleanscene.Mapper;

import me.inspiringbits.cleanscene.Model.Location;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface LocationMapper {
    List<Location> getLocations();
}
