package lk.dialog.BillVerification.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import lk.dialog.BillVerification.model.SmsData;

import java.util.Date;
import java.util.List;

/**
 * @author Sasini_08765
 */

@RepositoryRestResource
public interface SmsDataRepository extends JpaRepository<SmsData, Long>{

    @Query(value = "SELECT smsData FROM #{#entityName} " +
            "smsData JOIN smsData.invoice invoice JOIN invoice.operator operator WHERE operator.code = :code" )
    Page<SmsData> findSmsData(@Param("code") String Code, Pageable pageable);

    SmsData findFirstByInvoice_InvoiceNumber(String invoiceNumber);

    /*String FIND_SMS_RECORDS = "select * from sms_data sd " +
            "inner Join invoice inv on sd.sms_invoice = inv.id " +
            "inner JOIN operator op on inv.invoice_operator = op.id WHERE " +
            "op.operator_code = ?1 AND inv.event_type = ?2 AND inv.from_date >= ?3 AND inv.to_date <= ?4";*/
    String FIND_SMS_RECORDS = "SELECT smsdata FROM SmsData smsdata JOIN smsdata.invoice inv JOIN inv.operator op " +
            "WHERE op.code = ?1 AND inv.eventType = ?2 AND inv.from >= ?3 AND inv.to <= ?4";
    @Query(value = FIND_SMS_RECORDS)
    Page<SmsData> findAllSMSOfOperator(String code, String eventType, Date from, Date to, Pageable pageable);
}
