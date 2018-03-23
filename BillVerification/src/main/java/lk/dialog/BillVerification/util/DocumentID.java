package lk.dialog.BillVerification.util;

import lk.dialog.BillVerification.model.Invoice;
import org.springframework.stereotype.Component;
import java.text.SimpleDateFormat;
import java.util.Calendar;

@Component
public class DocumentID {

    private static final String DOCUMENT_ID = "DIALOG-";
    private static final SimpleDateFormat SDF = new SimpleDateFormat("YYYY/MM/DD HH:mm:ss");

    public String generateDocumentId(Invoice invoice){

        try {
            String dateString = SDF.format(Calendar.getInstance().getTime());

            if (invoice == null){
                return DOCUMENT_ID.concat(dateString.substring(0, 4).concat("-")
                        .concat(dateString.substring(5, 7))
                        .concat("_").concat("1"));
            }

            String lastDocId = invoice.getDocumentId();
            Long lastDocNumber = Long.parseLong(lastDocId.toString().substring(lastDocId.indexOf("_")+1));

            String lastInvoiceYear = invoice.getDocumentId().substring(7, 11);
            String currentYear = dateString.substring(0, 4);

            if (lastInvoiceYear.equals(currentYear)) {
                return lastDocId.substring(0, lastDocId.indexOf("_") + 1) + (lastDocNumber + 1);
            } else {
                return DOCUMENT_ID.concat(dateString.substring(0, 4)
                        .concat("-")
                        .concat(dateString.substring(5, 7))
                        .concat("_")
                        .concat("1"));
            }

        }catch (Exception e){
            throw new ServiceException("Document ID generation failed ", e);
        }

    }
}
