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
public class LoginServiceImpl implements LoginService {

    @Autowired
    UserDao userDao;


    @Override
    public JSONResult login(String email, String passwordMd5) {
        User user = userDao.findByEmail(email);
        if (ConditionUtil.isNotNull(user)) {
            if (!user.getPassword().equals(SignUtil.getMD5(passwordMd5))) {
                return JSONResult.isFail("Password error!");
            } else {
                return JSONResult.isOk().put("info", user);
            }
        }
        return JSONResult.isFail("E-mail does not exist！");
    }

}
