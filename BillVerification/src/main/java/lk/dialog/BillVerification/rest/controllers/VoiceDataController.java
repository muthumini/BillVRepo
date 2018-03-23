package lk.dialog.BillVerification.rest.controllers;

import lk.dialog.BillVerification.model.VoiceData;
import lk.dialog.BillVerification.rest.resources.*;
import lk.dialog.BillVerification.service.VoiceDataService;
import lk.dialog.BillVerification.util.ServiceException;
import lk.dialog.BillVerification.util.TimeRetriever;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Sasini_08765
 */

@RestController
@RequestMapping("/voicedata")
public class VoiceDataController extends AbstractRestController{

    private static final Logger logger = LoggerFactory.getLogger(VoiceDataController.class);

    @Autowired
    VoiceDataService voiceDataService;

      //---------------------------------------------------------------------------------
      @PreAuthorize("hasAnyRole('ADMIN','ReadWrite')")
      @RequestMapping(value = "view/{invoiceNumber}", method = RequestMethod.GET)
        public ResponseEntity<?> findVoicedataByInvoiceNumber(@PathVariable("invoiceNumber")String invoiceNumber){
          logger.info("Find voice data of the invoice number : {}", invoiceNumber);
          try {
                return new ResponseEntity<>(new VoiceDataResource(voiceDataService.findVoiceDataByInvoiceNUmber(invoiceNumber)), HttpStatus.OK);
            }catch (ServiceException se){
                return handleServiceException(se);
            }
        }

        //----------------list voice data invoices by operator code and time period------------------
        @PreAuthorize("hasAnyRole('ADMIN','ReadWrite','ReadOnly')")
        @RequestMapping(value = "view/{operatorCode}/{eventtype}/{from}/{to}", method = RequestMethod.GET)
    public ResponseEntity<?> listVoiceDataByOpCodeAndTime(@PathVariable("operatorCode")String operatorCode,
                                                          @PathVariable("eventtype")String eventtype,
                                                          @PathVariable("from")String from,
                                                          @PathVariable("to")String to,
                                                          @RequestParam("pageNo")int pageNo,
                                                          @RequestParam("pageSize")int pageSize,
                                                          @RequestParam("sortDir")String sortDir,
                                                          @RequestParam("sortCol")String sortCol){
            logger.info("Find voice data of operator : {} and event type of {}", operatorCode, eventtype);

            try {
                PaginatedVoiceDataResource paginatedVoiceDataResource = new PaginatedVoiceDataResource();

                Page<VoiceData> voiceData = voiceDataService.findInvoicesByOpAndDuration(operatorCode, eventtype, from, to, pageNo, pageSize, sortDir, sortCol);

                List<VoiceData> voiceDataList = voiceData.getContent();
                long total = voiceData.getTotalElements();
                List<VoiceDataResource> voiceDataResources = voiceDataList.stream().map(VoiceDataResource::new).collect(Collectors.toList());

                paginatedVoiceDataResource.setTotal(total);
                paginatedVoiceDataResource.setVoiceData(voiceDataResources);

                return new ResponseEntity<PaginatedVoiceDataResource>(paginatedVoiceDataResource, HttpStatus.OK);
            }catch (ServiceException se){
                return handleServiceException(se);
            }
    }

    //----------------------------------------------------------------------------------
    @PreAuthorize("hasAnyRole('ADMIN','ReadWrite')")
    @RequestMapping(value = "/{operatorCode}", method = RequestMethod.GET)
    public ResponseEntity<?> listPastDataByOperatorCode(@PathVariable("operatorCode") String operatorCode){
        logger.info("Find past voice data of the operator : {}", operatorCode);
        try {
            List<VoiceData> voiceData = voiceDataService.findPastRecordsByOpCode(operatorCode).getContent();
            return new ResponseEntity<>(voiceData.stream().map(VoiceDataResource::new).collect(Collectors.toList()), HttpStatus.OK);
        }catch (ServiceException se){
            return handleServiceException(se);
        }
    }

    //----------------------------Create new voice data entry---------------------------
    @PreAuthorize("hasAnyRole('ADMIN','ReadWrite')")
    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<?> newVoiceDataEntry(@RequestBody VoiceDataResource voiceDataResource){
        logger.info("Create new voice data entry with document id {}, of operator: {} and event type : {}", voiceDataResource
                .getInvoiceResource().getDocumentId(), voiceDataResource.getInvoiceResource()
                .getOperatorResource().getCode(), voiceDataResource.getInvoiceResource().getEventType());
        try {
            TimeRetriever timeRetriever = new TimeRetriever();
            voiceDataResource.getInvoiceResource().setVerified(timeRetriever.retrieveSystemDate());
            voiceDataService.addOrUpdateVoiceData(voiceDataResource.toVoiceData());
            return new ResponseEntity<CustomMessageResource>(new CustomMessageResource("New Invoice data entry successful"), HttpStatus.OK);
        }catch(ServiceException se) {
            return handleServiceException(se);
        }
    }

    @PreAuthorize("hasAnyRole('ADMIN','ReadWrite','ReadOnly')")
    @RequestMapping(method = RequestMethod.PUT)
    public ResponseEntity<?> updateVoiceData(@RequestBody VoiceDataResource voiceDataResource) {
        logger.info("Update voice data entry with document id {}, of operator: {} and event type : {}", voiceDataResource
                .getInvoiceResource().getDocumentId(), voiceDataResource.getInvoiceResource()
                .getOperatorResource().getCode(), voiceDataResource.getInvoiceResource().getEventType());
        try {
            voiceDataService.addOrUpdateVoiceData(
                    voiceDataResource.toVoiceData(voiceDataService.findById(voiceDataResource.getId())));
            return new ResponseEntity<CustomMessageResource>(new CustomMessageResource("Invoice updated successfully"),HttpStatus.OK);
        }catch (ServiceException se){
            return handleServiceException(se);
        }
    }

    //-------------------------verify voice data according to the standard data-----------------------
    @PreAuthorize("hasAnyRole('ADMIN','ReadWrite')")
    @RequestMapping(value = "/verify", method = RequestMethod.POST)
    public ResponseEntity<?> verifyVoiceData(@RequestBody VoiceVerifyResource voiceVerifyResource){
        logger.info("Verify voice data entry of operator: {} and event type : {}", voiceVerifyResource
                .getOperatorCode(), voiceVerifyResource.getEventType());
        try {
            CustomVoiceStatusResource customVoiceStatusResource = voiceDataService.verifyVoiceData(voiceVerifyResource);
            System.out.println(""+customVoiceStatusResource.getStatus());
            return new ResponseEntity<CustomVoiceStatusResource>(customVoiceStatusResource, HttpStatus.OK);

        }catch (ServiceException se) {
            return handleServiceException(se);
        }
    }
}
