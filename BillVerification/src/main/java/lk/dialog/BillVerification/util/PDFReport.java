package lk.dialog.BillVerification.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;


import lk.dialog.BillVerification.model.Invoice;
import lk.dialog.BillVerification.model.Operator;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;

/**
 * @author Sasini_08765
 */

public class PDFReport {

    static Format formatter = new SimpleDateFormat("yyyy-MM-dd");

    public static byte[] generateJasperReport(Invoice invoice) throws JRException, IOException {

        ArrayList<Invoice> InvoiceList = new ArrayList<Invoice>();
        InvoiceList.add(invoice);

        File initialFile = new File("/apps/dialog/BillVerification/report/in/dataDocInvoice.jrxml");
        InputStream inputStream = new FileInputStream(initialFile);

        String outputFile = "/apps/dialog/BillVerification/report/out/invoiceDocument.pdf";

        File outputPdf = new File(outputFile);
		outputPdf.createNewFile();
		JasperDesign jd = JRXmlLoader.load(inputStream);
		JasperReport jr = JasperCompileManager.compileReport(jd);
		HashMap<String, Object> reportParams = new HashMap<String, Object>();
		Operator opt = invoice.getOperator();
		if (invoice.getVoiceData() != null) {
			reportParams.put("sammary", "Minutes Summary");
			reportParams.put("subHeader", "Minutes");
			reportParams.put("asPerDialog", Double.toString(invoice.getVoiceData().getDialogMinutesOfVoice()));
			reportParams.put("asPerCarrier", Double.toString(invoice.getVoiceData().getOperatorMinutesOfVoice()));
			reportParams.put("diff", Double.toString(invoice.getVoiceData().getMinutesDifference()));
			reportParams.put("diffPercentage", Double.toString(invoice.getVoiceData().getMinutesDiffPercentage()));

		} else {
			reportParams.put("sammary", "SMS Summary");
			reportParams.put("subHeader", "SMS");
			reportParams.put("asPerDialog", Long.toString(invoice.getSmsData().getDialogNoOfSms()));
			reportParams.put("asPerCarrier", Long.toString(invoice.getSmsData().getOperatorNoOfSms()));
			reportParams.put("diff", Long.toString(invoice.getSmsData().getSmsQtyDiff()));
			reportParams.put("diffPercentage", Double.toString(invoice.getSmsData().getSmsQtyPercentage()));
		}
		String verifiedDate = formatter.format(invoice.getVerified());
		String fromDate = formatter.format(invoice.getFrom());
		String toDate = formatter.format(invoice.getTo());
		reportParams.put("confirmedDate", verifiedDate);
		reportParams.put("fromDate", fromDate);
		reportParams.put("toDate", toDate);
		//Handle this correctly
		reportParams.put("operatorName", opt.getName());
		reportParams.put("authorizedPerson", invoice.getAuthorizedPerson().getUserProfile().getFirstname()+" "+invoice.getAuthorizedPerson().getUserProfile().getLastname());
		reportParams.put("operatorCode", opt.getCode());
		String streetWithStrNo = null;
		if (opt.getNo() != null && !"".equals(opt.getNo())) {
			streetWithStrNo = opt.getNo() + ", ";
		}
		if (opt.getStreet() != null && !"".equals(opt.getStreet())) {
			streetWithStrNo = streetWithStrNo + opt.getStreet() + ",";
		}
		reportParams.put("streetWithStrNo", streetWithStrNo);
		if (opt.getCity() != null && !"".equals(opt.getCity())) {
			reportParams.put("city", opt.getCity() + ",");
		} else {
			reportParams.put("city", null);
		}
		reportParams.put("country", opt.getCountry());
		reportParams.put("disputePct", Double.toString(opt.getDisputePercentage()));
		reportParams.put("profileName","Dasun Perera");
//		reportParams.put("profileName", invoice.getUser().getUserProfile().getFirstname() + " "
//				+ invoice.getUser().getUserProfile().getLastname());

		JasperPrint jasperPrint = JasperFillManager.fillReport(jr, reportParams,
				new JRBeanCollectionDataSource(InvoiceList));

		JasperExportManager.exportReportToPdfStream(jasperPrint, new FileOutputStream(outputPdf));
		Path path = outputPdf.toPath();
		byte[] data = Files.readAllBytes(path);
		System.out.println("Partial Rollover Report Temp Path : " + outputFile);
		return data;
    }

}
