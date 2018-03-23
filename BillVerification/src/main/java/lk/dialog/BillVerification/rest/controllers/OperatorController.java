package lk.dialog.BillVerification.rest.controllers;

import lk.dialog.BillVerification.model.Operator;
import lk.dialog.BillVerification.rest.resources.CustomMessageResource;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import lk.dialog.BillVerification.rest.resources.OperatorResource;
import lk.dialog.BillVerification.service.OperatorService;
import lk.dialog.BillVerification.util.ServiceException;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Sasini_08765
 */

@RestController
@RequestMapping("/operator")
public class OperatorController extends AbstractRestController {

    @Autowired
    OperatorService operatorService;

    private static final org.slf4j.Logger logger = LoggerFactory.getLogger(OperatorController.class);

    //----------------------------Retrieve Operator by Id--------------------
    @PreAuthorize("hasAnyRole('ADMIN','ReadWrite')")
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<?> viewOperatorById(@PathVariable("id") long id) {
        logger.info("Find operator of id : {}", id);
        try {
                return new ResponseEntity<>(new OperatorResource(operatorService.findById(id)), HttpStatus.OK);
        } catch (ServiceException e) {
            return handleServiceException(e);
        }
    }

    @PreAuthorize("hasAnyRole('ADMIN','ReadWrite')")
    @RequestMapping(value = "code/{opCode}", method = RequestMethod.GET)
    public ResponseEntity<?> findOperatorByCode(@PathVariable("opCode") String opCode){
        logger.info("Find operator of operator code : {}", opCode);
        try {
            return new ResponseEntity<>(new OperatorResource(operatorService.findByOperatorCode(opCode)), HttpStatus.OK);
        }catch (ServiceException e){
            return handleServiceException(e);
        }
    }

    //-----------------retrieve operator list-------------------
    @PreAuthorize("hasAnyRole('ADMIN','ReadWrite','ReadOnly')")
    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<?> listOperators() { 
        logger.info("List operators");
        try {
            List<Operator> operators = operatorService.findOperators();
           return new ResponseEntity<>(operators.stream().map(OperatorResource::new).collect(Collectors.toList()), HttpStatus.OK);
        }catch (ServiceException se) {
            return handleServiceException(se);
        }
    }

    //----------------------------Create new operator---------------------------
    @PreAuthorize("hasAnyRole('ADMIN','ReadWrite')")
    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<?> createOperator(@RequestBody OperatorResource operatorResource) {
        logger.info("Create operator of operator code {} and operator name {}", operatorResource.getCode(),
                operatorResource.getName());
        try {
            operatorService.addOrUpdateOperator(operatorResource.toOperator());
            return new ResponseEntity<CustomMessageResource>(new CustomMessageResource("Operator Successfully Created"), HttpStatus.OK);
        } catch (ServiceException e) {
            return handleServiceException(e);
        }
    }

    //----------------------------update operator------------------------------------
    @PreAuthorize("hasAnyRole('ADMIN','ReadWrite')")
    @RequestMapping(method = RequestMethod.PUT)
    public ResponseEntity<?> updateOperator(@RequestBody OperatorResource operatorResource) {
        logger.info("Update operator of operator code {} and operator name {}", operatorResource.getCode(),
                operatorResource.getName());
        try {
            operatorService.addOrUpdateOperator(
                    operatorResource.toOperator(operatorService.findById(operatorResource.getId())));
            return new ResponseEntity<CustomMessageResource>(new CustomMessageResource("Operator Successfully Updated"), HttpStatus.OK);
        } catch (ServiceException e) {
            return handleServiceException(e);
        }
    }

    //-----------------------------Remove operator---------------------------------
    /*@PreAuthorize("hasAnyRole('ADMIN','ReadWrite')")
    @RequestMapping(value = "/remove/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<?> removeOperator(@PathVariable("id") long id) {
        logger.info("Remove operator of operator id {}", id);
        try {
            operatorService.removeOperator(id);
            return new ResponseEntity<CustomMessageResource>(new CustomMessageResource("Operator deleted successfully"), HttpStatus.OK);
        } catch (ServiceException e) {
            return handleServiceException(e);
        }
    }*/
}
