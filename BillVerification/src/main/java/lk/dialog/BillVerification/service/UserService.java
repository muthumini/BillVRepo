package lk.dialog.BillVerification.service;

import lk.dialog.BillVerification.model.Role;
import lk.dialog.BillVerification.model.User;
import lk.dialog.BillVerification.repository.UserRepository;
import lk.dialog.BillVerification.rest.resources.PasswordResetResource;
import lk.dialog.BillVerification.util.BVAUtils;
import lk.dialog.BillVerification.util.ServiceException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

/**
 * @author Sasini_08765
 */

@Service
public class UserService {

    private static final Logger logger = LoggerFactory.getLogger(UserProfileService.class);

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserService userService;

    public User findUserByUsername(String username) {
        try {
            logger.debug("Find user by username : {}", username);
            return userRepository.findByUsername(username);
        } catch (Exception ex) {
            logger.error("Failed to retrieve user by username : {}", username);
            throw new ServiceException("Failed to retrieve user", ex);
        }
    }

    public Role findRoleByUsername(String username) {
        try {
            logger.debug("Find user role by username : {}", username);
            return userRepository.findByUsername(username).getRole();
        }catch (Exception e){
            logger.error("Failed to retrieve user role by username : {}", username);
            throw new ServiceException("Failed loading user role by username", e);
        }
    }

    @Transactional
    public boolean resetPassword(PasswordResetResource passwordResetResource, String username) {
        try {
            logger.debug("Reset password by user : {}", username);
            logger.debug("Encode old password user : {} provides", username);
            passwordResetResource.setOldPwd(BVAUtils.getInstance().encode(passwordResetResource.getOldPwd()));

            logger.debug("Find currently logged user : {} details", username);
            User user = userRepository.findByUsername(username);

            if (passwordResetResource.getOldPwd().equals(user.getPassword())) {
                logger.debug("Set new password to the user : {}", username);
                user.setPassword(BVAUtils.getInstance().encode(passwordResetResource.getNewPwd()));
                logger.debug("Save updated password for the user : {}", username);
                userRepository.save(user);
            }else {
                logger.error("User entered old password does not match with database");
                throw new Exception("Old password is wrong");
            }

            return true;

        }catch (Exception e) {
            logger.error("Failed to reset password by User : {}", username);
            throw new ServiceException("Failed to reset password.", e);
        }
    }
}
