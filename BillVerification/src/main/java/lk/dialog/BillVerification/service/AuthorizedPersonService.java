package lk.dialog.BillVerification.service;

import lk.dialog.BillVerification.model.Invoice;
import lk.dialog.BillVerification.model.UserProfile;
import lk.dialog.BillVerification.repository.InvoiceRepository;
import lk.dialog.BillVerification.repository.UserProfileRepository;
import lk.dialog.BillVerification.util.ServiceException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Sasini_08765
 */

@Service("AuthorizedPersonService")
public class AuthorizedPersonService {

	private static final Logger logger = LoggerFactory.getLogger(AuthorizedPersonService.class);

	@Autowired
	UserProfileRepository userProfileRepository;

	@Autowired
	InvoiceRepository invoiceRepository;

	//------------------------------list all authorized persons-------------------------
	public List<UserProfile> findAuthorizedPersons() {
		try {
			logger.debug("Find all authorized persons");
			return userProfileRepository.findUserByRole("ReadOnly");
		} catch (Exception ex){
			logger.error("Failed to retrieve all authorized persons");
			throw new ServiceException("Error in listing authorized persons", ex);
		}
	}

	//-----------------find last invoice for retrieve authorized person of it---------------
	public Invoice findLastInvoice(){
		try {
			logger.debug("Find last invoice");
			return invoiceRepository.findFirstByOrderByIdDesc();
		}catch (Exception ex){
			logger.error("Failed to retrieve last invoice");
			throw new ServiceException("Last entry of invoice not found", ex);
		}
	}
}
