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
public class UserInfoServiceImpl  implements UserInfoService{

    @Autowired
    UserDao userDao;

    @Override
    public JSONResult setPassword(String email, String oldPassword, String newPassword) {
        User user = userDao.findByEmail(email);
        if (ConditionUtil.isNotNull(user)){
            if (!SignUtil.getMD5(oldPassword).equals(user.getPassword())){
              return JSONResult.isFail("Wrong Password");
            }
            if(oldPassword.equals(newPassword)){
            return JSONResult.isFail("The old password cannot be the same as the new password!");
            } else {
                user.setPassword(SignUtil.getMD5(newPassword));
                userDao.save(user);
               return JSONResult.isOk();
            }
        }
        return JSONResult.isFail("Account does not exist!");
    }

    @Override
    public JSONResult resetPassword(String email, String phoneNumber, String newPassword) {
        User user = userDao.findByEmail(email);
        if (ConditionUtil.isNotNull(user)){
            if (!phoneNumber.equals(user.getPhoneNumber())){
                return JSONResult.isFail("Wrong phone number!");
            }
            else {
                user.setPassword(SignUtil.getMD5(newPassword));
                userDao.save(user);
                return JSONResult.isOk();
            }
        }
        return JSONResult.isFail("Account does not exist!");
    }
}
