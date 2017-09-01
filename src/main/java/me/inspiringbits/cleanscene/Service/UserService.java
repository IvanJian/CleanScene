package me.inspiringbits.cleanscene.Service;

import me.inspiringbits.cleanscene.Model.BasicMessage;
import me.inspiringbits.cleanscene.Model.User;

/**
 * Created by IvanJian on 2017/8/29.
 */
public interface UserService {
    abstract BasicMessage createUser(User user);
    abstract BasicMessage getUserReports(int userId);
    abstract BasicMessage getUserActivities(int userId);
}
