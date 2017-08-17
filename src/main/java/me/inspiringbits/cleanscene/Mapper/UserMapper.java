package me.inspiringbits.cleanscene.Mapper;

import me.inspiringbits.cleanscene.Model.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper {
    User selectByUserId (Integer userId);
}
