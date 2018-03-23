package lk.dialog.BillVerification.rest.resources;

import lk.dialog.BillVerification.model.SmsData;

/**
 * @author Sasini_08765
 */

public class SmsDataResource {
	private Long id;
	private long operatorNoOfSms;
	private double operatorSmsCost;
	private long dialogNoOfSms;
	private double dialogSmsCost;
	private long smsQtyDiff;
	private double smsQtyPercentage;
	private long smsCostDiff;
	private long smsCostDiffPercentage;

	private InvoiceResource invoiceResource;

	public SmsDataResource(){

	}
	public SmsDataResource(SmsData smsData) {
		this.id = smsData.getId();
		this.operatorNoOfSms = smsData.getOperatorNoOfSms();
		this.operatorSmsCost = smsData.getOperatorSmsCost();
		this.dialogNoOfSms = smsData.getDialogNoOfSms();
		this.dialogSmsCost = smsData.getDialogSmsCost();
		this.smsQtyDiff = smsData.getSmsQtyDiff();
		this.smsQtyPercentage = smsData.getSmsQtyPercentage();
		this.smsCostDiff = smsData.getSmsCostDiff();
		this.smsCostDiffPercentage = smsData.getSmsCostDiffPercentage();

		if(smsData.getInvoice() != null){
			this.invoiceResource = new InvoiceResource(smsData.getInvoice());
		}
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}

	public long getOperatorNoOfSms() {
		return operatorNoOfSms;
	}

	public void setOperatorNoOfSms(long operatorNoOfSms) {
		this.operatorNoOfSms = operatorNoOfSms;
	}

	public double getOperatorSmsCost() {
		return operatorSmsCost;
	}

	public void setOperatorSmsCost(double operatorSmsCost) {
		this.operatorSmsCost = operatorSmsCost;
	}

	public long getDialogNoOfSms() {
		return dialogNoOfSms;
	}

	public void setDialogNoOfSms(long dialogNoOfSms) {
		this.dialogNoOfSms = dialogNoOfSms;
	}

	public double getDialogSmsCost() {
		return dialogSmsCost;
	}

	public void setDialogSmsCost(double dialogSmsCost) {
		this.dialogSmsCost = dialogSmsCost;
	}

	public long getSmsQtyDiff() {
		return smsQtyDiff;
	}

	public void setSmsQtyDiff(long smsQtyDiff) {
		this.smsQtyDiff = smsQtyDiff;
	}

	public double getSmsQtyPercentage() {
		return smsQtyPercentage;
	}

	public void setSmsQtyPercentage(double smsQtyPercentage) {
		this.smsQtyPercentage = smsQtyPercentage;
	}

	public long getSmsCostDiff() {
		return smsCostDiff;
	}

	public void setSmsCostDiff(long smsCostDiff) {
		this.smsCostDiff = smsCostDiff;
	}

	public long getSmsCostDiffPercentage() {
		return smsCostDiffPercentage;
	}

	public void setSmsCostDiffPercentage(long smsCostDiffPercentage) {
		this.smsCostDiffPercentage = smsCostDiffPercentage;
	}

	public InvoiceResource getInvoiceResource() {
            return invoiceResource;
        }
        public void setInvoiceResource(InvoiceResource invoiceResource) {
            this.invoiceResource = invoiceResource;
        }

	public SmsData toSmsData() {
		return toSmsData(new SmsData());
	}
	public SmsData toSmsData(SmsData smsData) {
		smsData.setId(id);
		smsData.setOperatorNoOfSms(operatorNoOfSms);
		smsData.setOperatorSmsCost(operatorSmsCost);
		smsData.setDialogNoOfSms(dialogNoOfSms);
		smsData.setDialogSmsCost(dialogSmsCost);
		smsData.setSmsQtyDiff(smsQtyDiff);
		smsData.setSmsQtyPercentage(smsQtyPercentage);
		smsData.setSmsCostDiff(smsCostDiff);
		smsData.setSmsCostDiffPercentage(smsCostDiffPercentage);
		if(invoiceResource != null){
			smsData.setInvoice(invoiceResource.toInvoice());
		}

		return smsData;
	}

}
