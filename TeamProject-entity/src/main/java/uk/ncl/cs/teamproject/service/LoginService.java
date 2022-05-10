package uk.ncl.cs.teamproject.service;

import uk.ncl.cs.teamproject.common.JSONResult;

/**
 * Login Management Services
 * @author yantaoxu
 */
public interface LoginService {

    /**
     * Login encryption
     * @param email
     * @param passwordMd5 md5 encryption of password
     * @return
     */
    JSONResult login(String email, String passwordMd5);



}
