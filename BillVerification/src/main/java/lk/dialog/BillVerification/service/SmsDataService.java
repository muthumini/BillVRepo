package lk.dialog.BillVerification.service;

import lk.dialog.BillVerification.repository.OperatorRepository;
import lk.dialog.BillVerification.rest.resources.CustomSMSStatusResource;
import lk.dialog.BillVerification.rest.resources.SmsVerifyResource;
import lk.dialog.BillVerification.util.ServiceException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import lk.dialog.BillVerification.model.SmsData;
import lk.dialog.BillVerification.repository.InvoiceRepository;
import lk.dialog.BillVerification.repository.SmsDataRepository;

import javax.transaction.Transactional;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * @author Sasini_08765
 */

@Service("SmsDataService")
public class SmsDataService {

	private static final Logger logger = LoggerFactory.getLogger(SmsDataService.class);

	@Autowired
	SmsDataRepository smsDataRepository;
	
	@Autowired
	InvoiceRepository invoiceRepository;

	@Autowired
	OperatorRepository operatorRepository;

	//------------------------------retrieve sms data by ID---------------------------------
	public SmsData findById(long id) {
		try{
			logger.debug("Find sms data by id : {}", id);
			return smsDataRepository.findOne(id);
		}catch(Exception e){
			logger.error("Failed to retrieve sms data by id : {}", id);
			throw new ServiceException("Cannot return SmsData");
		}
	}

	//----------------------------Create new Sms data entry---------------------------------
	@Transactional
	public SmsData addOrUpdateSmsData(SmsData smsData) throws ServiceException{
		try{
			logger.debug("Add or update sms data invoice of invoice number : {} and document : id {}", smsData.getInvoice()
					.getInvoiceNumber(), smsData.getInvoice().getDocumentId());
			invoiceRepository.save(smsData.getInvoice());

			logger.debug("Add or update sms data of invoice number : {} and document id : {}", smsData.getInvoice()
					.getInvoiceNumber(), smsData.getInvoice().getDocumentId());
			return smsDataRepository.save(smsData);
		}catch(Exception e){
			logger.error("Failed to add or update sms data invoice of invoice number : {} and document id : {}", smsData
					.getInvoice().getInvoiceNumber(), smsData.getInvoice().getDocumentId());
			throw new ServiceException("Error occurred while creating voice data entry for : {}", e, smsData.getId());
		}		
	}
	//----------------find past records of sms data by operator code---------------------------------------
	public Page<SmsData> findPastRecordsByOpCode(String operatorCode) {
		try {
			logger.debug("Find past records of sms data by operator of code : {} ", operatorCode);
			return smsDataRepository.findSmsData(operatorCode,
					new PageRequest(0, 12, Sort.Direction.DESC, "id"));
		} catch (Exception ex) {
			logger.error("Failed to retrieve past records of sms data by operator of code : {}", operatorCode);
			throw new ServiceException(ex);
		}
	}
	//-------------------find all sms invoices of an operator in between a time period------------
	public Page<SmsData> findInvoicesByOpAndDuration(String op, String eventType, String from, String to,
													 int pageNo, int pageSize, String sortDir, String sortCol){
		try {
			PageRequest pageRequest = new PageRequest(pageNo, pageSize, Sort.Direction.fromString(sortDir), sortCol);
			Date fromDate = new SimpleDateFormat("yyyy-MM-dd").parse(from);
			Date toDate = new SimpleDateFormat("yyyy-MM-dd").parse(to);
			logger.debug("Find past records of sms data by operator of code : {} and event type of : {}", op, eventType);
			return smsDataRepository.findAllSMSOfOperator(op, eventType, fromDate, toDate, pageRequest);
		} catch (Exception ex){
			logger.error("Failed to retrieve past records of sms data by operator of code : {} and event type of : {}", op, eventType);
			throw new ServiceException("Cannot find sms data with the conditions");
		}
	}

	//----------------------------find sms data by invoice number-----------------------------------
	public SmsData findSmsDataByInvoiceNUmber(String invoiceNumber){
		try {
			logger.debug("Find sms data by invoice number : {}", invoiceNumber);
			return smsDataRepository.findFirstByInvoice_InvoiceNumber(invoiceNumber);
		}catch (Exception e){
			logger.error("Failed to retrieve sms data by invoice number : {}", invoiceNumber);
			throw new ServiceException("Cannot find data");
		}
	}

	public CustomSMSStatusResource verifySmsData(SmsVerifyResource smsVerifyResource){

		double smsPercentage = 0.0;
		double costPercentage = 0.0;
		double smsDiff = 0.0;
		double costDiff = 0.0;
		double operatorPercentage = 0.0;
		double operatorCost = 0.0;
		CustomSMSStatusResource customSMSStatusResource = new CustomSMSStatusResource();

		/*try{
			operatorPercentage = operatorRepository.findByCode(smsVerifyResource.getOperatorCode()).getDisputePercentage();
			operatorCost = operatorRepository.findByCode(smsVerifyResource.getOperatorCode()).getDisputeCost();
		}catch (Exception ex){
			throw new ServiceException("Operator : {} not exiting", ex, smsVerifyResource.getOperatorCode());
		}*/

		try {
			logger.debug("Verify sms data of operator of code : {} and event type : {}", smsVerifyResource.getOperatorCode(),
					smsVerifyResource.getEventType());

			operatorPercentage = operatorRepository.findByCode(smsVerifyResource.getOperatorCode()).getDisputePercentage();
			operatorCost = operatorRepository.findByCode(smsVerifyResource.getOperatorCode()).getDisputeCost();

			if(smsVerifyResource.getEventType().equals("Termination") || smsVerifyResource.getEventType().equals("ILAC&IC")){

				logger.debug("Verify sms data of event type : {}", smsVerifyResource.getEventType());

				smsDiff = smsVerifyResource.getNoOfSmsOfOperator() - smsVerifyResource.getNoOfSmsOfDialog();
				smsPercentage = (smsDiff/smsVerifyResource.getNoOfSmsOfDialog())*100;
				costDiff = smsVerifyResource.getSmsCostOfOperator() - smsVerifyResource.getSmsCostOfDialog();
				costPercentage = (costDiff/smsVerifyResource.getSmsCostOfDialog())*100;

			}else if (smsVerifyResource.getEventType().equals("Origination")) {

				logger.debug("Verify sms data of event type : {}", smsVerifyResource.getEventType());

				smsDiff = smsVerifyResource.getNoOfSmsOfDialog() - smsVerifyResource.getNoOfSmsOfOperator();
				smsPercentage = (smsDiff / smsVerifyResource.getNoOfSmsOfDialog()) * 100;
				costDiff = smsVerifyResource.getSmsCostOfDialog() - smsVerifyResource.getSmsCostOfOperator();
				costPercentage = (costDiff / smsVerifyResource.getSmsCostOfDialog()) * 100;
			}

			logger.trace("sms diff : {} sms percentage : {} cost ddiff : {} cost percentage : {} ", smsDiff,
					smsPercentage, costDiff, costPercentage);

				if (((-1*operatorPercentage) <= smsPercentage) && (smsPercentage <= operatorPercentage)) {
					customSMSStatusResource.setStatus("OK");
				}
				else if (((-1*operatorCost) <= costDiff) && (costDiff <= operatorCost) ) {
						customSMSStatusResource.setStatus("OK");
				}else {
						customSMSStatusResource.setStatus("DISPUTED");
				}

			logger.trace("Verified sms data status : {}", customSMSStatusResource.getStatus());

			customSMSStatusResource.setSmsDiff(Math.round(smsDiff*100D)/100D);
			customSMSStatusResource.setCostDiff(Math.round(costDiff*100D)/100D);
			customSMSStatusResource.setSmsPercentage(Math.round(smsPercentage*100D)/100D);
			customSMSStatusResource.setCostPercentage(Math.round(costPercentage*100D)/100D);

			return customSMSStatusResource;

		}catch (Exception e) {
			logger.error("Sms data not verified of operator : {} and event type : {}", smsVerifyResource.getOperatorCode(),
					smsVerifyResource.getEventType());
			throw new ServiceException("Error occurred while verifying sms data ", e);
		}
	}
}
