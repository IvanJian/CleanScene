package me.inspiringbits.cleanscene.ServiceImpl;

import me.inspiringbits.cleanscene.Mapper.UserMapper;
import me.inspiringbits.cleanscene.Model.BasicMessage;
import me.inspiringbits.cleanscene.Model.User;
import me.inspiringbits.cleanscene.Service.UserService;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by IvanJian on 2017/8/29.
 */
@Component
public class UserServiceImpl implements UserService{

    final UserMapper userMapper;
    BasicMessage basicMessage=new BasicMessage();

    public UserServiceImpl(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    @Override
    public BasicMessage createUser(User user) {
        if ("".equals(user.getFacebookId())){
            basicMessage.setStatus(false);
            basicMessage.setCode(BasicMessage.NO_FACEBOOK_ID);
            basicMessage.setContent("Can't get facebook ID.");
            return basicMessage;
        }
        List<User> users=userMapper.findByFacebookId(user.getFacebookId());
        if (users.size()!=0){
            basicMessage.setCode(BasicMessage.SUCCESS);
            basicMessage.setStatus(true);
            basicMessage.setContent(users.get(0).getUserId().toString());
            return basicMessage;
        }
        userMapper.createUser(user);
        basicMessage.setContent(user.getUserId().toString());
        return basicMessage;
    }
}
