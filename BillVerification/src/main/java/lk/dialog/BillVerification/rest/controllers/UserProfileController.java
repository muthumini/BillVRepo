package lk.dialog.BillVerification.rest.controllers;

import lk.dialog.BillVerification.model.UserProfile;
import lk.dialog.BillVerification.rest.resources.CustomMessageResource;
import lk.dialog.BillVerification.util.BVAUtils;
import lk.dialog.BillVerification.util.ServiceException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import lk.dialog.BillVerification.rest.resources.UserProfileResource;
import lk.dialog.BillVerification.service.UserProfileService;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Sasini_08765
 */

@RestController
@RequestMapping("/userProfile")
public class UserProfileController extends AbstractRestController{

	private static final Logger logger = LoggerFactory.getLogger(UserProfileController.class);

	@Autowired
	UserProfileService userProfileService;

	//----------------------------Create new user profile---------------------------
	@PreAuthorize("hasRole('ADMIN')")
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<?> createUserProfile(@RequestBody UserProfileResource userProfileResource) throws Exception{
		logger.info("Create user profile of username : {}, emp id : {}", userProfileResource.getUser().getUsername(),
				userProfileResource.getEmpID());
		try {
			userProfileResource.getUser().setPassword(BVAUtils.getInstance().encode(userProfileResource.getUser().getPassword()));
			userProfileService.addOrUpdateUserProfile(userProfileResource.toUserProfile());
			return new ResponseEntity<CustomMessageResource>(new CustomMessageResource("User profile Successfully Created"), HttpStatus.OK);

		} catch (ServiceException e) {
			return handleServiceException(e);
		}
	}
	
	//-----------------------------Remove user profile---------------------------------
	/*@PreAuthorize("hasRole('ADMIN')")
	@RequestMapping(value = "/remove/{id}", method = RequestMethod.DELETE)
		public ResponseEntity<?> removeUserProfile(@PathVariable("id")long id){
			try{
				userProfileService.removeUser(id);
				return new ResponseEntity<CustomMessageResource>(new CustomMessageResource("User profile deleted successfully"), HttpStatus.OK);
			}catch(ServiceException e) {
				return handleServiceException(e);
			}
		}*/

		//---------------------------Update user profile---------------------------------
		@PreAuthorize("hasRole('ADMIN')")
		@RequestMapping(method = RequestMethod.PUT)
		public ResponseEntity<?> updateUserProfile(@RequestBody UserProfileResource userProfileResource) {
			logger.info("Update user profile of username : {}, emp id : {}", userProfileResource.getUser().getUsername(),
					userProfileResource.getEmpID());
			try {
				 userProfileService.addOrUpdateUserProfile(
						userProfileResource.toUserProfile(userProfileService.findById(userProfileResource.getId())));
				return new ResponseEntity<CustomMessageResource>(new CustomMessageResource("User profile Successfully Updated"), HttpStatus.OK);
			} catch (ServiceException e) {
				return handleServiceException(e);
			}
		}

	//-------------------view user profile details by id-----------------------------------------------------
	@PreAuthorize("hasRole('ADMIN')")
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<?> listUserProfiles() {
		logger.info("List user profiles");
		try {
			List<UserProfile> userProfiles = userProfileService.findUserProfiles();
			return new ResponseEntity<>(userProfiles.stream().map(UserProfileResource::new).collect(Collectors.toList()), HttpStatus.OK);
		}catch (ServiceException se) {
			return handleServiceException(se);
		}
	}
}
