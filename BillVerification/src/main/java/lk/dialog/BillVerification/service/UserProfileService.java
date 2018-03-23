package lk.dialog.BillVerification.service;

import lk.dialog.BillVerification.util.ServiceException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lk.dialog.BillVerification.model.User;
import lk.dialog.BillVerification.model.UserProfile;
import lk.dialog.BillVerification.repository.UserProfileRepository;
import lk.dialog.BillVerification.repository.UserRepository;

import javax.transaction.Transactional;
import java.util.List;

/**
 * @author Sasini_08765
 */

@Service("UserProfileService")
public class UserProfileService {

	private static final Logger logger = LoggerFactory.getLogger(UserProfileService.class);

	@Autowired
	UserRepository userRepository;
	
	@Autowired 
	UserProfileRepository userProfileRepository;
	
	//----------------------------Create new user profile---------------------------------
	@Transactional
	public UserProfile addOrUpdateUserProfile(UserProfile userProfile) throws ServiceException{
			try{
				logger.debug("Add or update user with username : {}", userProfile.getUser().getUsername());
				userRepository.save(userProfile.getUser());
				logger.debug("Add or update user profile with username : {} and employee no : {}", userProfile.getUser()
						.getUsername(), userProfile.getEmpID());
				return userProfileRepository.save(userProfile);
			}catch(Exception e){
				logger.error("Failed to add or update user of username : {}", userProfile.getUser().getUsername());
				throw new ServiceException("User save failed",e);
			}		
	}

	//----------------------Find user by id------------------------------------
	public UserProfile findById(long id) {
		try{
			logger.debug("Find user profile by id : {}", id);
			return userProfileRepository.findOne(id);
		}catch(Exception e){
			logger.error("Failed to retrieve user profile of id : {}", id);
			throw new ServiceException("Error occurred while retrieving userProfile with id : {}", e, id);
		}
	}

	//---------------------------list all user profiles--------------------------------
	public List<UserProfile> findUserProfiles() {
		try {
			logger.debug("List user profiles");
			return userProfileRepository.findAll();
		} catch (Exception ex){
			logger.error("Failed to retrieve user profiles");
			throw new ServiceException("Couldn't load user profile list", ex);
		}
	}

	/*public void removeUser(Long id) throws ServiceException{
		try {
				userProfileRepository.delete(id);
				
				User user = userRepository.findOne(id);
				if(user != null){
					userRepository.delete(id);
			}
			}catch(Exception e){
				throw new ServiceException("Remove user profile failed",e);
			}
	}*/
}
