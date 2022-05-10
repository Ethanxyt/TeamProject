package uk.ncl.cs.teamproject.service;

import uk.ncl.cs.teamproject.common.JSONResult;

/**
 * User Information config Management Services
 * @author yantao xu
 */
public interface UserInfoService {
    /**
     * Password change service, requires correct email, correct old password, different old and new passwords
     * @param email
     * @param oldPassword
     * @param newPassword
     * @return
     */
    JSONResult setPassword(String email,String oldPassword,String newPassword);

    /**
     * Forgotten password service, requires correct phone and email
     * @param email
     * @param phoneNumebr
     * @param newPassword
     * @return
     */
    JSONResult resetPassword(String email,String phoneNumebr,String newPassword);
}
