package lk.dialog.BillVerification.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import lk.dialog.BillVerification.model.Invoice;

import java.util.List;

/**
 * @author Sasini_08765
 */

@RepositoryRestResource
public interface InvoiceRepository extends JpaRepository<Invoice, Long>{

    Invoice findInvoiceByInvoiceNumber(String invoiceNumber);

    List<Invoice> findAllByOperator_Code(String operatorCode);

    Invoice findInvoiceByDocumentIdOrInvoiceNumber(String docId, String invNo);

    Invoice findFirstByOrderByIdDesc();

    Invoice findInvoiceByDocumentId(String docId);
}
