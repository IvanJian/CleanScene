package me.inspiringbits.cleanscene.Mapper;

import me.inspiringbits.cleanscene.Model.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserMapper {
    User selectByUserId (Integer userId);
    void createUser(User user);
    List<User> findByFacebookId(String facebookId);
}
