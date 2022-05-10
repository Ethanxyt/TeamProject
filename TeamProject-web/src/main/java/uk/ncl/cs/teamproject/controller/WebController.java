package uk.ncl.cs.teamproject.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import uk.ncl.cs.teamproject.common.Result;
import uk.ncl.cs.teamproject.model.User;
import uk.ncl.cs.teamproject.request.web.LoginRequest;
import uk.ncl.cs.teamproject.request.web.ResetPasswordRequest;
import uk.ncl.cs.teamproject.request.web.SetPasswordRequest;
import uk.ncl.cs.teamproject.service.LoginService;
import uk.ncl.cs.teamproject.service.RegistrationService;
import uk.ncl.cs.teamproject.service.UserInfoService;


@RestController
@RequestMapping("/web/*")
public class WebController {

    @Autowired
    LoginService loginService;
    @Autowired
    RegistrationService registrationService;
    @Autowired
    UserInfoService userInfoService;

    /**
     * Login
     * @return
     */
    @PostMapping("login")
    public Result login(@RequestBody LoginRequest loginRequest) {
        return loginService.login(loginRequest.getEmail(), loginRequest.getPassword());
    }

    /**
     * Registration
     * @return
     */

    @PostMapping("registration")
    public Result registration(@RequestBody User user){
        return registrationService.registration(user);
    }

    /**
     * Change password
     * @return
     */

    @PostMapping("setPsw")
    public Result setPassword(@RequestBody SetPasswordRequest setPasswordRequest){
        return userInfoService.setPassword(setPasswordRequest.getEmail(), setPasswordRequest.getOldPassword(),setPasswordRequest.getNewPassword());
    }

    /**
     * Forgot your password
     * @return
     */
    @PostMapping("resetPsw")
    public Result resetPassword(@RequestBody ResetPasswordRequest resetPasswordRequest){
        return userInfoService.resetPassword(resetPasswordRequest.getEmail(), resetPasswordRequest.getphoneNumber(),resetPasswordRequest.getNewPassword());
    }
}