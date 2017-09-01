package me.inspiringbits.cleanscene.Controller;

import me.inspiringbits.cleanscene.Mapper.UserMapper;
import me.inspiringbits.cleanscene.Model.BasicMessage;
import me.inspiringbits.cleanscene.Model.Report;
import me.inspiringbits.cleanscene.Model.User;
import me.inspiringbits.cleanscene.Service.UserService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * Created by Abdulkareem on 2017/8/9.
 */
@RestController
public class UserController {

    final UserMapper userMapper;
    UserService userService;

    public UserController(UserMapper userMapper, UserService userService) {
        this.userMapper = userMapper;
        this.userService = userService;
    }

    @RequestMapping("/user/{id}")
    public @ResponseBody
    User getUserByID(@PathVariable Integer id) {
        return userMapper.selectByUserId(id);
    }

    @RequestMapping("/user/reports/{id}")
    public @ResponseBody
    BasicMessage getUserReports(@PathVariable Integer id) {
        return userService.getUserReports(id);
    }

    @RequestMapping("/user/activities/{id}")
    public @ResponseBody
    BasicMessage getUserActivities(@PathVariable Integer id) {
        return userService.getUserActivities(id);
    }

    @RequestMapping(value = "/user/create", method = RequestMethod.POST)
    public @ResponseBody
    BasicMessage createUser(@RequestBody User user){
        return userService.createUser(user);
    }
}
