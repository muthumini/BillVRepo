package lk.dialog.BillVerification.rest.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import lk.dialog.BillVerification.rest.resources.CustomMessageResource;
import lk.dialog.BillVerification.util.ServiceException;

/**
 * @author Sasini_08765
 */

public abstract class AbstractRestController {

	public ResponseEntity<?> handleServiceException(ServiceException se){
		CustomMessageResource customMessageResource = new CustomMessageResource(se.getMessage());
		return new ResponseEntity<CustomMessageResource>(customMessageResource, HttpStatus.INTERNAL_SERVER_ERROR);

	}
}
