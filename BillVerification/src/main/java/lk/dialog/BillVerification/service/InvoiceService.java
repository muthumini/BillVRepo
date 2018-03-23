package lk.dialog.BillVerification.service;

import lk.dialog.BillVerification.model.Invoice;
import lk.dialog.BillVerification.repository.InvoiceRepository;
import lk.dialog.BillVerification.util.ServiceException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

/**
 * @author Sasini_08765
 */

@Service("InvoiceService")
public class InvoiceService {

	private static final Logger logger = LoggerFactory.getLogger(InvoiceService.class);

	@Autowired
	InvoiceRepository invoiceRepository;

	public List<Invoice> findInvoices() {
		try {
			logger.debug("Find all invoices");
			return invoiceRepository.findAll();
		} catch (Exception ex) {
			logger.error("Failed to retrieve all invoices");
			throw new ServiceException("List invoices failed", ex);
		}
	}

	public List<Invoice> findInvoicesByOperator(String operatorCode) {
		try {
			logger.debug("Find invoices with operator code : {}", operatorCode);
			return invoiceRepository.findAllByOperator_Code(operatorCode);
		} catch (Exception e) {
			logger.error("Failed to retrieve invoices of operator : {}", operatorCode);
			throw new ServiceException("List invoices by operator failed");
		}
	}

	public Invoice findByInNo(String invNo) {
		try {
			logger.debug("Find invoice with invoice no : {}", invNo);
			return invoiceRepository.findInvoiceByInvoiceNumber(invNo);
		} catch (Exception e) {
			logger.error("Failed to retrieve invoice of invoice no : {}", invNo);
			throw new ServiceException("Find invoice from invoice number failed", e);
		}
	}

	public Invoice findByDocId(String docId) {
		try {
			logger.debug("Find invoice with document id : {} ", docId);
			return invoiceRepository.findInvoiceByDocumentId(docId);
		} catch (Exception e) {
			logger.error("Failed to retrieve invoice of document id : {}", docId);
			throw new ServiceException(e);
		}
	}

	//-----------------find invoice details for report generate-------------------
	public Invoice findInvoiceForReport(String docId, String invNo) {
		try {
			logger.debug("Find invoice with document id : {} or invoice no : {}", docId, invNo);
			return invoiceRepository.findInvoiceByDocumentIdOrInvoiceNumber(docId, invNo);
		} catch (Exception e) {
			logger.error("Failed to retrieve invoice");
			throw new ServiceException("No data found for the invoice", e);
		}
	}

	public Invoice findLastInvoice() {
		try {
			logger.debug("Retrieving last invoice");
			return invoiceRepository.findFirstByOrderByIdDesc();
		} catch (Exception e) {
			logger.error("Failed to retrieve last invoice");
			throw new ServiceException("Failed to retrieve last invoice", e);
		}
	}

	@Transactional
	public Invoice addOrUpdateInvoice(Invoice invoice) {
		try {
			logger.debug("Saving invoice with document id : {}", invoice.getDocumentId());
			return invoiceRepository.save(invoice);
		} catch (Exception e) {
			logger.error("Failed to save invoice with document id : {}", invoice.getDocumentId());
			throw new ServiceException("Failed to save invoice with document id : {}", e, invoice.getDocumentId());
		}
	}

}
