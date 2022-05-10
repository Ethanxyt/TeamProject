package uk.ncl.cs.teamproject.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uk.ncl.cs.teamproject.common.JSONResult;
import uk.ncl.cs.teamproject.model.User;
import uk.ncl.cs.teamproject.repo.UserDao;
import uk.ncl.cs.teamproject.util.ConditionUtil;
import uk.ncl.cs.teamproject.util.SignUtil;

/**
 * @author yantao xu
 */
@Service
public class RegistrationServiceImpl implements RegistrationService{

    @Autowired
    UserDao userDao;
    @Override
    public JSONResult registration(User user) {
        User user1 = userDao.findByEmail(user.getEmail());
        if (ConditionUtil.isNotNull(user1)) {
            return JSONResult.isFail("This email address is already registered");
        }
        String passwordMD5 = SignUtil.getMD5(user.getPassword());
        user.setPassword(passwordMD5);
        userDao.save(user);
        return JSONResult.isOk();
    }
}

