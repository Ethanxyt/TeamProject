package uk.ncl.cs.teamproject.service;

import uk.ncl.cs.teamproject.common.JSONResult;
import uk.ncl.cs.teamproject.model.User;

/**
 * Registration Management Services
 * @author yantao xu
 */
public interface RegistrationService {
    /**
     * Registered and encrypted services
     * @param user
     * @return
     */

    JSONResult registration(User user);
}
