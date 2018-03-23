package lk.dialog.BillVerification.rest.controllers;

import lk.dialog.BillVerification.rest.resources.CustomMessageResource;
import lk.dialog.BillVerification.rest.resources.PasswordResetResource;
import lk.dialog.BillVerification.rest.resources.RoleResource;
import lk.dialog.BillVerification.rest.resources.UserResource;
import lk.dialog.BillVerification.service.UserService;
import lk.dialog.BillVerification.util.ServiceException;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.security.GeneralSecurityException;

/**
 * @author Sasini_08765
 */

@RestController
@RequestMapping("/user")
public class UserController extends AbstractRestController {
    private static final org.slf4j.Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    UserService userService;

    @RequestMapping(value = "/role", method = RequestMethod.GET)
    public ResponseEntity<?> findRoleByUsername(@AuthenticationPrincipal(expression = "username")String userName){
        logger.info("Find the role of user : {}", userName);
        try{
            return new ResponseEntity<>(new RoleResource(userService.findRoleByUsername(userName)), HttpStatus.OK);
        }catch (ServiceException se) {
            return handleServiceException(se);
        }
    }

    @RequestMapping(value = "/reset", method = RequestMethod.POST)
    public ResponseEntity<?> restPassword(@RequestBody PasswordResetResource passwordResetResource,
                                          @AuthenticationPrincipal(expression = "username")String userName) throws GeneralSecurityException{
        logger.info("Trying to reset password user {}", userName);
        try {
            userService.resetPassword(passwordResetResource, userName);
            return new ResponseEntity<CustomMessageResource>(new CustomMessageResource("Password Successfully Updated"), HttpStatus.OK);
        }catch (ServiceException se) {
            return handleServiceException(se);
        }
    }
}
