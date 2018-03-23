package lk.dialog.BillVerification.rest.controllers;

import lk.dialog.BillVerification.rest.resources.RoleResource;
import lk.dialog.BillVerification.service.RoleService;
import lk.dialog.BillVerification.util.ServiceException;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.stream.Collectors;

/**
 * @author Sasini_08765
 */

@RestController
@RequestMapping("/role")
public class RoleController extends AbstractRestController {

    private static final org.slf4j.Logger logger = LoggerFactory.getLogger(RoleController.class);

    @Autowired
    RoleService roleService;

    @RequestMapping(method = RequestMethod.GET)
    ResponseEntity getRoles() {
        logger.info("Find roles");
        try {
            return new ResponseEntity<>(roleService.getRoles().stream().map(RoleResource::new).collect(Collectors.toList()), HttpStatus.OK);
        } catch (ServiceException se) {
            return handleServiceException(se);
        }
    }
}
