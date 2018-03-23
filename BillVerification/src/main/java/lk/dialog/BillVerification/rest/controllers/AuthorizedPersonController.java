package lk.dialog.BillVerification.rest.controllers;


import lk.dialog.BillVerification.model.UserProfile;
import lk.dialog.BillVerification.rest.resources.InvoiceResource;
import lk.dialog.BillVerification.rest.resources.UserProfileResource;
import lk.dialog.BillVerification.service.UserProfileService;
import lk.dialog.BillVerification.util.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import lk.dialog.BillVerification.service.AuthorizedPersonService;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Sasini_08765
 */

@RestController
@RequestMapping(value="/authorizedperson")
public class AuthorizedPersonController extends AbstractRestController{
	
	@Autowired
	AuthorizedPersonService authorizedPersonService;

	@Autowired
	UserProfileService userProfileService;

	//-----------------retrieve operator list-------------------
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<?> listAuthorizedPersons() {
		try {
			List<UserProfile> authorizedPeople = authorizedPersonService.findAuthorizedPersons();
			List<UserProfileResource> authorizedPeopleList = authorizedPeople.stream().map(UserProfileResource::new).collect(Collectors.toList());
			return new ResponseEntity<>(authorizedPeople.stream().map(UserProfileResource::new).collect(Collectors.toList()), HttpStatus.OK);
		}catch (ServiceException se) {
			return handleServiceException(se);
		}
	}

	//--------find authorized person of last invoice---------------------------
	@RequestMapping(value = "/last", method = RequestMethod.GET)
	public ResponseEntity<?> findAuthorizedPersonOfLastInvoice(){
		try {
			return new ResponseEntity<>(new InvoiceResource(authorizedPersonService.findLastInvoice()), HttpStatus.OK);
		}catch (ServiceException se){
			return handleServiceException(se);
		}
	}
}
