package lk.dialog.BillVerification.rest.controllers;

import lk.dialog.BillVerification.model.Invoice;
import lk.dialog.BillVerification.rest.resources.DocumentIdResource;
import lk.dialog.BillVerification.rest.resources.InvoiceResource;
import lk.dialog.BillVerification.service.InvoiceService;
import lk.dialog.BillVerification.util.DocumentID;
import lk.dialog.BillVerification.util.EmptyResultException;
import lk.dialog.BillVerification.util.PDFReport;
import lk.dialog.BillVerification.util.ServiceException;
import net.sf.jasperreports.engine.JRException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.ByteArrayInputStream;
import java.io.IOException;

/**
 * @author Sasini_08765
 */

@RestController
@RequestMapping("/invoice")
public class InvoiceController extends AbstractRestController {

    @Autowired
    InvoiceService invoiceService;
    @Autowired
    DocumentID documentID;

    private static final Logger logger = LoggerFactory.getLogger(InvoiceController.class);

    @PreAuthorize("hasAnyRole('ADMIN','ReadWrite','ReadOnly')")
    @RequestMapping(value = "/getInvoice/{invNo}/{docId}", method = RequestMethod.GET)
    public ResponseEntity<?> findInvoiceByInvoiceOrDocId(@PathVariable("invNo") String invNo, @PathVariable("docId") String docId) {
        //  logger.info("Executing method -findInvoiceByInvoiceOrDocId - params - docId:"+docId+" invNumber"+invNo);
        logger.info("Retrieving invoice with invoice no : {}, document id : {}", invNo, docId);
        try {
            return new ResponseEntity<>(new InvoiceResource(invoiceService.findInvoiceForReport(docId, invNo)), HttpStatus.OK);
        } catch (ServiceException se) {
            return handleServiceException(se);
        }
    }

    //------------------------generate document ID---------------------------------
    @PreAuthorize("hasAnyRole('ADMIN','ReadWrite')")
    @RequestMapping(value = "/genDocId", method = RequestMethod.GET)
    public ResponseEntity<?> generateDocumentId() {
        logger.info("Generate document ID");
        try {
            return new ResponseEntity<>(new DocumentIdResource(documentID.generateDocumentId(invoiceService.findLastInvoice())), HttpStatus.OK);
        } catch (ServiceException e) {
            return handleServiceException(e);
        }
    }

    @PreAuthorize("hasAnyRole('ADMIN','ReadWrite','ReadOnly')")
    @RequestMapping(value = "/byInvoiceNumber/{invNo}", method = RequestMethod.GET)
    public ResponseEntity<?> findInvoiceByInvoiceNumber(@PathVariable("invNo") String invNo) {
        logger.info("Retrieving invoice with invoice no : {}", invNo);
        try {
            return new ResponseEntity<Object>(new InvoiceResource(invoiceService.findByInNo(invNo)), HttpStatus.OK);
        } catch (ServiceException se) {
            return handleServiceException(se);
        } catch (NullPointerException ne) {
            throw new EmptyResultException(ne.getMessage());
        }
    }

    @PreAuthorize("hasAnyRole('ADMIN','ReadWrite')")
    @RequestMapping(value = "/byDocId/{docId}", method = RequestMethod.GET)
    public ResponseEntity<?> findInvoiceByDocumentId(@PathVariable("docId") String docId) {
        logger.info("Retrieving invoice with document id : {}", docId);

        try {
            return new ResponseEntity<>(new InvoiceResource(invoiceService.findByDocId(docId)), HttpStatus.OK);
        } catch (ServiceException se) {
            return handleServiceException(se);
        }
    }


    @RequestMapping(value = "/report", method = RequestMethod.GET, produces = MediaType.APPLICATION_PDF_VALUE)
    public ResponseEntity<?> voiceDataReportWithComment(@RequestParam("docId") String docId)
            throws JRException, IOException {
        logger.info("Retrieving invoice data report with document id : {}", docId);

        try {

            Invoice invoice = invoiceService.findByDocId(docId);

            byte[] outputBytes = PDFReport.generateJasperReport(invoice);
            ByteArrayInputStream bis = new ByteArrayInputStream(outputBytes);
            HttpHeaders headers = new HttpHeaders();
            headers.add("Content-Disposition", "inline; filename=voiceDataReport.pdf");

            return ResponseEntity.ok().headers(headers).contentType(MediaType.APPLICATION_PDF)
                    .body(new InputStreamResource(bis));

        } catch (ServiceException se) {
            return handleServiceException(se);
        }
    }

    // -------------------------update invoice comment-----------------------
    @PreAuthorize("hasAnyRole('ADMIN','ReadWrite','ReadOnly')")
    @RequestMapping(method = RequestMethod.PUT)
    public ResponseEntity<?> updateUserComment(@RequestBody InvoiceResource invoiceResource) {
        logger.info("Update user comment of invoice no : {}, document id : {}", invoiceResource.getInvoiceNumber(),
                invoiceResource.getDocumentId());
        try {
            Invoice invoice = invoiceService.findInvoiceForReport(invoiceResource.getDocumentId(),
                    invoiceResource.getInvoiceNumber());
            if (invoiceResource.getComment() != null && invoiceResource.getComment() != "") {
                String newComment = invoice.getComment() + "\n" + invoiceResource.getComment();

                invoice.setComment(newComment);

                invoiceService.addOrUpdateInvoice(invoice);
            }
            invoice = invoiceService.findByDocId(invoice.getDocumentId());
            return new ResponseEntity<Object>(new InvoiceResource(invoice), HttpStatus.OK);
        } catch (ServiceException se) {
            return handleServiceException(se);
        }
    }

    @PreAuthorize("hasAnyRole('ADMIN','ReadWrite')")
    @RequestMapping(value = "/ ort", method = RequestMethod.GET, produces = MediaType.APPLICATION_PDF_VALUE)
    public ResponseEntity<?> viewReport(@RequestParam("docId") String docId,
                                        @RequestParam("invNumber") String invNumber) throws JRException, IOException {
        logger.info("View invoice report with invoice no : {}, document id : {}", invNumber, docId);
        try {
            Invoice invoice = invoiceService.findInvoiceForReport(docId, invNumber);

            byte[] outputBytes = PDFReport.generateJasperReport(invoice);
            ByteArrayInputStream bis = new ByteArrayInputStream(outputBytes);
            HttpHeaders headers = new HttpHeaders();
            headers.add("Content-Disposition", "inline; filename=voiceDataReport.pdf");

            return ResponseEntity.ok().headers(headers).contentType(MediaType.APPLICATION_PDF)
                    .body(new InputStreamResource(bis));

        } catch (ServiceException se) {
            return handleServiceException(se);
        }
    }
}
