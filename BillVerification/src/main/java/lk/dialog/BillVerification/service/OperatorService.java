package lk.dialog.BillVerification.service;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lk.dialog.BillVerification.model.Operator;
import lk.dialog.BillVerification.repository.InvoiceRepository;
import lk.dialog.BillVerification.repository.OperatorRepository;
import lk.dialog.BillVerification.rest.resources.OperatorResource;
import lk.dialog.BillVerification.util.ServiceException;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Sasini_08765
 */

@Service("OperatorService")
public class OperatorService {

	private static final Logger logger = LoggerFactory.getLogger(OperatorService.class);

	@Autowired 
	OperatorRepository operatorRepository;
	
	@Autowired
	InvoiceRepository invoiceRepository;
	
	public Operator findById(long id) {
		try{
			logger.debug("Find operator by operator id : {}", id);
			return operatorRepository.findOne(id);
		}catch(Exception e){
			logger.error("Failed to retrieve operator of operator id : {}", id);
			throw new ServiceException("Error occurred while retrieving operator with id : {}", e, id);
		}
	}

	public Operator findByOperatorCode(String opCode){
		try {
			logger.debug("Find operator by operator code : {}", opCode);
			return operatorRepository.findByCode(opCode);
		}catch (Exception e){
			logger.error("Failed to retrieve operator of operator code : {}", opCode);
			throw new ServiceException("Error occurred when retrieving operator by code : {}", e, opCode);
		}
	}

	public OperatorResource findByName(String name) {
		try{
			logger.debug("Find operator by operator name : {}", name);
			Operator operator = operatorRepository.findOperatorByName(name);
			OperatorResource operatorResource = new OperatorResource(operator);
			return operatorResource;
		}catch(Exception e){
			logger.error("Failed to retrieve operator of operator name : {}", name);
			throw new ServiceException("Error occurred when retrieving operator by name : {}", name);
		}
	}

	public List<Operator> findOperators() {
		try {
			logger.debug("List operators");
			return operatorRepository.findAll();
		} catch (Exception ex){
			logger.error("Failed to list operators");
			throw new ServiceException("List operators failed", ex);
		}
	}

	public boolean isUserExist(OperatorResource operator) throws Exception {
	        try {
				return findByName(operator.getName())!=null;
			} catch (Exception e) {
				throw new Exception(e);
			}
	}
	
//----------------------------Create new operator---------------------------------
@Transactional
public Operator addOrUpdateOperator(Operator operator) throws ServiceException{
		try{
			logger.debug("Add or update operator of operator code : {}", operator.getCode());
			return operatorRepository.save(operator);
		}catch(Exception e){
			logger.error("Failed to add or update operator of code: {}", operator.getCode());
			throw new ServiceException("Operator save failed", e);
		}		
}

//----------------------------Delete existing operator----------------------------
@Transactional
public void removeOperator(Long id) throws ServiceException{
	try{
		logger.debug("Remove operator of operator id : {}", id);
		operatorRepository.delete(id);
	}catch(Exception e){
		logger.error("Failed to remove operator of operator id : {}", id);
		throw new ServiceException(e);
	}
}

}
