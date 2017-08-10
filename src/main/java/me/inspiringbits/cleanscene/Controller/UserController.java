package me.inspiringbits.cleanscene.Controller;

import me.inspiringbits.cleanscene.Mapper.UserMapper;
import me.inspiringbits.cleanscene.Model.Report;
import me.inspiringbits.cleanscene.Model.User;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by IvanJian on 2017/8/9.
 */
@RestController
public class UserController {

    final UserMapper userMapper;

    public UserController(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    @RequestMapping("/user/{id}")
    public @ResponseBody
    User getUserByID(@PathVariable Integer id) {
        return userMapper.selectByUserId(id);
    }

    @RequestMapping("hello")
    public String hello(){
        return "Hello";
    }

}
