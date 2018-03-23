package lk.dialog.BillVerification.service;

import lk.dialog.BillVerification.model.VoiceData;
import lk.dialog.BillVerification.repository.InvoiceRepository;
import lk.dialog.BillVerification.repository.OperatorRepository;
import lk.dialog.BillVerification.repository.VoiceDataRepository;
import lk.dialog.BillVerification.rest.resources.CustomVoiceStatusResource;
import lk.dialog.BillVerification.rest.resources.VoiceVerifyResource;
import lk.dialog.BillVerification.util.ServiceException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * @author Sasini_08765
 */

@Service("VoiceDataService")
public class VoiceDataService {

    private static final Logger logger = LoggerFactory.getLogger(VoiceDataService.class);

    @Autowired
    VoiceDataRepository voiceDataRepository;

    @Autowired
    InvoiceRepository invoiceRepository;

    @Autowired
    OperatorRepository operatorRepository;

    //------------------------retrieve voice data-----------------------------------------
    public VoiceData findById(long id) {
        try{
            logger.debug("Find voice data by id : {}", id);
            return voiceDataRepository.findOne(id);
        }catch(Exception e){
            logger.error("Failed to retrieve voice data by id : {}", id);
            throw new ServiceException("Cannot return VoiceData");
        }
    }
    //-------------------find all voice invoices of an operator in between a time period------------
    public Page<VoiceData> findInvoicesByOpAndDuration(String op, String et, String from, String to,
                                                       int pageNo, int pageSize, String sortDir, String sortCol){
        try {
            Date fromDate = new SimpleDateFormat("yyyy-MM-dd").parse(from);
            Date toDate = new SimpleDateFormat("yyyy-MM-dd").parse(to);
            logger.debug("Find past records of voice data by operator of code : {} and event type of : {}", op, et);
            return voiceDataRepository.findAllVoiceOfOperator(op, et, fromDate, toDate,
                                        new PageRequest(pageNo, pageSize, Sort.Direction.fromString(sortDir), sortCol));
        } catch (Exception ex){
            logger.error("Failed to retrieve past records of voice data by operator of code : {} and event type of : {}", op, et);
            throw new ServiceException("Cannot find voice data with the conditions");
        }
    }

    //----------------------------find voice data by invoice number-----------------------------------
    public VoiceData findVoiceDataByInvoiceNUmber(String invoiceNumber){
        try {
            logger.debug("Find voice data by invoice number : {}", invoiceNumber);
            return voiceDataRepository.findFirstByInvoice_InvoiceNumber(invoiceNumber);
        }catch (Exception e){
            logger.error("Failed to retrieve voice data by invoice number : {}", invoiceNumber);
            throw new ServiceException("Cannot find data");
        }
    }
    //----------------find past records of voice data by operator code---------------------------------------
    public Page<VoiceData> findPastRecordsByOpCode(String operatorCode) {
        try {
            logger.debug("Find past records of voice data by operator of code : {} ", operatorCode);
            return voiceDataRepository.findVoiceData(operatorCode,
                    new PageRequest(0, 12, Sort.Direction.DESC, "id"));
        } catch (Exception ex) {
            logger.error("Failed to retrieve past records of voice data by operator of code : {}", operatorCode);
            throw new ServiceException(ex);
        }
    }
    //----------------------------Create new Voice data entry---------------------------------
    @Transactional
    public VoiceData addOrUpdateVoiceData(VoiceData voiceData) throws ServiceException{
        try{
            //SLAService slaService = new SLAService();
            //voiceData.getInvoice().getSla().setSlaAchieved(slaService.calculateSLA(voiceData.getInvoice()));
            logger.debug("Add or update voice data invoice of invoice number : {} ", voiceData.getInvoice().getInvoiceNumber());
            invoiceRepository.save(voiceData.getInvoice());
            logger.debug("Add or update voice data of invoice number : {} ", voiceData.getInvoice().getInvoiceNumber());
            return voiceDataRepository.save(voiceData);
        }catch(Exception e){
            logger.error("Failed to add or update voice data invoice of invoice number : {}", voiceData.getInvoice().getInvoiceNumber());
            throw new ServiceException("Error occurred while creating voice data entry for : {}", e, voiceData.getId());
        }
    }

    //-----------------------Verify voice data---------------------------------------------
    public CustomVoiceStatusResource verifyVoiceData(VoiceVerifyResource voiceVerifyResource){

        double timeDiff = 0.0;
        double costDiff = 0.0;
        double noOfCallsDiff = 0.0;
        double timePercentage = 0.0;
        double costPercentage = 0.0;
        double callsDiffPercentage = 0.0;

        Double operatorPercentage ;
        Double operatorCost ;

        CustomVoiceStatusResource customVoiceStatusResource = new CustomVoiceStatusResource();

        /*try {
            operatorPercentage = operatorRepository.findByCode(voiceVerifyResource.getOperatorCode()).getDisputePercentage();
            operatorCost = operatorRepository.findByCode(voiceVerifyResource.getOperatorCode()).getDisputeCost();
        }catch (Exception ex){
            throw new ServiceException("Operator : {} not exiting", ex, voiceVerifyResource.getOperatorCode());
        }*/
        try {
            logger.debug("Verify voice data of operator of code : {}", voiceVerifyResource.getOperatorCode());

            operatorPercentage = operatorRepository.findByCode(voiceVerifyResource.getOperatorCode()).getDisputePercentage();
            operatorCost = operatorRepository.findByCode(voiceVerifyResource.getOperatorCode()).getDisputeCost();

            if(voiceVerifyResource.getEventType().equals("Termination") || voiceVerifyResource.getEventType().equals("ILAC&IC")){

                logger.debug("Verify voice data of event type : {}", voiceVerifyResource.getEventType());

                timeDiff = voiceVerifyResource.getNoOfMinutesOfOperator() - voiceVerifyResource.getNoOfMinutesOfDialog();
                timePercentage = (timeDiff/voiceVerifyResource.getNoOfMinutesOfDialog())*100;
                costDiff = voiceVerifyResource.getVoiceCostOfOperator()- voiceVerifyResource.getVoiceCostOfDialog() ;
                costPercentage = (costDiff/voiceVerifyResource.getVoiceCostOfDialog())*100;
                noOfCallsDiff = voiceVerifyResource.getNumberOfCallsOfOperator() - voiceVerifyResource.getNumberOfCallsOfDialog();
                callsDiffPercentage = (noOfCallsDiff/voiceVerifyResource.getNumberOfCallsOfDialog())*100;

            }else if (voiceVerifyResource.getEventType().equals("Origination")) {

                logger.debug("Verify voice data of event type : {}", voiceVerifyResource.getEventType());

                timeDiff = voiceVerifyResource.getNoOfMinutesOfDialog() - voiceVerifyResource.getNoOfMinutesOfOperator();
                timePercentage = (timeDiff / voiceVerifyResource.getNoOfMinutesOfDialog()) * 100;
                costDiff = voiceVerifyResource.getVoiceCostOfDialog() - voiceVerifyResource.getVoiceCostOfOperator();
                costPercentage = (costDiff / voiceVerifyResource.getVoiceCostOfDialog()) * 100;
                noOfCallsDiff = voiceVerifyResource.getNumberOfCallsOfDialog() - voiceVerifyResource.getNumberOfCallsOfOperator();
                callsDiffPercentage = (noOfCallsDiff / voiceVerifyResource.getNumberOfCallsOfDialog()) * 100;
            }

            logger.trace("time diff : {} time percentage : {} cost ddiff : {} cost percentage : {} ", timeDiff,
                    timePercentage, costDiff, costPercentage);

            if (((-1*operatorPercentage) <= timePercentage) && (timePercentage <= operatorPercentage)){
                    customVoiceStatusResource.setStatus("OK");
            } else if (((-1*operatorCost) <= costDiff) && (costDiff <= operatorCost )) {
                        customVoiceStatusResource.setStatus("OK");
            }else {
                    customVoiceStatusResource.setStatus("DISPUTED");
            }

            logger.debug("Verified voice data status : {}", customVoiceStatusResource.getStatus());

            customVoiceStatusResource.setTimeDiff(Math.round(timeDiff*100D)/100D);
            customVoiceStatusResource.setTimePercentage(Math.round(timePercentage*100D)/100D);
            customVoiceStatusResource.setCostDiff(Math.round(costDiff*100D)/100D);
            customVoiceStatusResource.setCostPercentage(Math.round(costPercentage*100D)/100D);
            customVoiceStatusResource.setNoOfCallsDiff(Math.round(noOfCallsDiff*100D)/100D);
            customVoiceStatusResource.setCallsDiffPercentage(Math.round(callsDiffPercentage*100D)/100D);

            return customVoiceStatusResource;
        }catch (Exception e) {
            logger.error("Voice data not verified of operator : {}", voiceVerifyResource.getOperatorCode());
            throw new ServiceException("Error occurred while verifying voice data ", e);
        }
    }
}
