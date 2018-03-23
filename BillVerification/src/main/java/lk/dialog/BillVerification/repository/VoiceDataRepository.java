package lk.dialog.BillVerification.repository;

import lk.dialog.BillVerification.model.VoiceData;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;

/**
 * @author Sasini_08765
 */

public interface VoiceDataRepository extends JpaRepository<VoiceData, Long>{

    @Query(value = "SELECT voiceData FROM #{#entityName} " +
            "voiceData JOIN voiceData.invoice invoice JOIN invoice.operator operator WHERE operator.code = :code" )
    Page<VoiceData> findVoiceData(@Param("code") String Code, Pageable pageable);

    VoiceData findFirstByInvoice_InvoiceNumber(String invoiceNumber);

    /*String FIND_VOICE_RECORDS = "select * from voice_data vc " +
            "inner Join invoice inv on vc.dialog_voice_invoice = inv.id\n" +
            "inner JOIN operator op on inv.invoice_operator = op.id WHERE\n" +
            "op.operator_code = ?1 AND inv.event_type = ?2 AND inv.from_date >= ?3 AND inv.to_date <= ?4";*/

    String FIND_VOICE_RECORDS = "SELECT voicedata FROM VoiceData voicedata JOIN voicedata.invoice inv JOIN inv.operator op " +
            "WHERE op.code = ?1 AND inv.eventType = ?2 AND inv.from >= ?3 AND inv.to <= ?4";

    @Query(value = FIND_VOICE_RECORDS)
    Page<VoiceData> findAllVoiceOfOperator(String code, String eventType, Date from, Date to, Pageable pageable);
}
