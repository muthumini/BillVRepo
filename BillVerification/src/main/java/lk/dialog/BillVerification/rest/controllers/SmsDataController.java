package lk.dialog.BillVerification.rest.controllers;

import lk.dialog.BillVerification.model.SmsData;
import lk.dialog.BillVerification.model.VoiceData;
import lk.dialog.BillVerification.rest.resources.*;
import lk.dialog.BillVerification.util.ServiceException;
import lk.dialog.BillVerification.util.TimeRetriever;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import lk.dialog.BillVerification.service.SmsDataService;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Sasini_08765
 */

@RestController
@RequestMapping("/smsdata")
public class SmsDataController extends AbstractRestController{

    private static final org.slf4j.Logger logger = LoggerFactory.getLogger(SmsDataController.class);

    @Autowired
	SmsDataService smsDataService;

    //----------------------------find last 12 records of sms data--------------------------
    @PreAuthorize("hasAnyRole('ADMIN','ReadWrite')")
    @RequestMapping(value = "/{operatorCode}", method = RequestMethod.GET)
        public ResponseEntity<?> listPastDataByOperatorCode(@PathVariable("operatorCode") String operatorCode){
        logger.info("Find past sms data of the operator : {}", operatorCode);
        try {
                List<SmsData> smsData = smsDataService.findPastRecordsByOpCode(operatorCode).getContent();
                return new ResponseEntity<>(smsData.stream().map(SmsDataResource::new).collect(Collectors.toList()), HttpStatus.OK);
            }catch (ServiceException se){
                return handleServiceException(se);
            }
        }

        //---------------------------------------------------------------------------------
        @PreAuthorize("hasAnyRole('ADMIN','ReadWrite')")
        @RequestMapping(value = "view/{invoiceNumber}", method = RequestMethod.GET)
        public ResponseEntity<?> findSmsdataByInvoiceNumber(@PathVariable("invoiceNumber")String invoiceNumber){
            logger.info("Find sms data of the invoice number : {}", invoiceNumber);
            try {
                return new ResponseEntity<>(new SmsDataResource(smsDataService.findSmsDataByInvoiceNUmber(invoiceNumber)), HttpStatus.OK);
            }catch (ServiceException se){
                return handleServiceException(se);
            }
        }

        //----------------list sms data invoices by operator code and time period------------------
        @PreAuthorize("hasAnyRole('ADMIN','ReadWrite')")
        @RequestMapping(value = "view/{operatorCode}/{eventtype}/{from}/{to}", method = RequestMethod.GET)
        public ResponseEntity<?> listSmsDataByOpCodeAndTime(@PathVariable("operatorCode")String operatorCode,
                                                            @PathVariable("eventtype")String eventtype,
                                                            @PathVariable("from")String from,
                                                            @PathVariable("to")String to,
                                                            @RequestParam("pageNo")int pageNo,
                                                            @RequestParam("pageSize") int pageSize,
                                                            @RequestParam("sortDir")String sortDir,
                                                            @RequestParam("sortCol")String sortCol){
            logger.info("Find sms data of operator : {} and event type of {}", operatorCode, eventtype);
            try {
            PaginatedSmsDataResource paginatedSmsDataResource = new PaginatedSmsDataResource();
            Page<SmsData> smsData = smsDataService.findInvoicesByOpAndDuration(operatorCode, eventtype, from, to,
                                                                                pageNo, pageSize, sortDir, sortCol);

            List<SmsData> smsDataList = smsData.getContent();
            long total = smsData.getTotalElements();
            List<SmsDataResource> smsDataResources = smsDataList.stream().map(SmsDataResource::new).collect(Collectors.toList());

            paginatedSmsDataResource.setTotal(total);
            paginatedSmsDataResource.setSmsData(smsDataResources);

            return new ResponseEntity<PaginatedSmsDataResource>(paginatedSmsDataResource, HttpStatus.OK);

        }catch (ServiceException se){
            return handleServiceException(se);
        }
    }
        //----------------------------Create new sms data entry---------------------------
        @PreAuthorize("hasAnyRole('ADMIN','ReadWrite')")
        @RequestMapping(method = RequestMethod.POST)
        public ResponseEntity<?> newSmsDataEntry(@RequestBody SmsDataResource smsDataResource) {
            logger.info("Create new sms data entry with document id {}, of operator: {} and event type : {}", smsDataResource
                    .getInvoiceResource().getDocumentId(), smsDataResource.getInvoiceResource()
                    .getOperatorResource().getCode(), smsDataResource.getInvoiceResource().getEventType());
            try {
                TimeRetriever timeRetriever = new TimeRetriever();
		        smsDataResource.getInvoiceResource().setVerified(timeRetriever.retrieveSystemDate());
		        smsDataService.addOrUpdateSmsData(smsDataResource.toSmsData());
		        return new ResponseEntity<CustomMessageResource>(new CustomMessageResource("New Invoice data entry successful"), HttpStatus.OK);
            } catch (ServiceException se) {
		        return handleServiceException(se);
            }
        }

        //------------------------------update sms data invoice--------------------------------------
        @PreAuthorize("hasAnyRole('ADMIN','ReadWrite','ReadOnly')")
        @RequestMapping(method = RequestMethod.PUT)
        public ResponseEntity<?> updateVoiceData(@RequestBody SmsDataResource smsDataResource) {
            try {
                logger.info("Update sms data entry with document id {}, of operator: {} and event type : {}", smsDataResource
                        .getInvoiceResource().getDocumentId(), smsDataResource.getInvoiceResource()
                        .getOperatorResource().getCode(), smsDataResource.getInvoiceResource().getEventType());
                smsDataService.addOrUpdateSmsData(
                        smsDataResource.toSmsData(smsDataService.findById(smsDataResource.getId())));
                return new ResponseEntity<CustomMessageResource>(new CustomMessageResource("Invoice updated successfully"),HttpStatus.OK);
            }catch (ServiceException se){
                return handleServiceException(se);
            }
        }

		//-------------------------verify sms data according to the standard data-----------------------
        @PreAuthorize("hasAnyRole('ADMIN','ReadWrite')")
        @RequestMapping(value = "/verify", method = RequestMethod.POST)
		public ResponseEntity<?> verifySmsData(@RequestBody SmsVerifyResource smsVerifyResource){
            logger.info("Verify Sms data entry of operator: {} and event type : {}", smsVerifyResource
                    .getOperatorCode(), smsVerifyResource.getEventType());
			try {
				CustomSMSStatusResource customSMSStatusResource = smsDataService.verifySmsData(smsVerifyResource);
                return new ResponseEntity<CustomSMSStatusResource>(customSMSStatusResource, HttpStatus.OK);
            }catch (ServiceException se) {
				return handleServiceException(se);
		}

    }
}
