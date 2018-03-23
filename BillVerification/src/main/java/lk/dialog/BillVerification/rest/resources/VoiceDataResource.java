package lk.dialog.BillVerification.rest.resources;

import lk.dialog.BillVerification.model.VoiceData;

/**
 * @author Sasini_08765
 */

public class VoiceDataResource {

	private Long id;
	private double operatorMinutesOfVoice;
	private double dialogMinutesOfVoice;
	private double operatorVoiceCost;
	private double dialogVoiceCost;
	private double operatorNoOfCalls;
	private double dialogNoOfCalls;

	private double minutesDifference;
	private double minutesDiffPercentage;
	private double voiceCostDifference;
	private double voiceCostPercentage;
	private double noOfCallsDifference;
	private double noOfCallsDiffPercentage;

	private InvoiceResource invoiceResource;

	public VoiceDataResource(){

	}

	public VoiceDataResource(VoiceData voiceData) {
		this.id = voiceData.getId();
		this.operatorMinutesOfVoice = voiceData.getOperatorMinutesOfVoice();
		this.operatorVoiceCost = voiceData.getOperatorVoiceCost();
		this.operatorNoOfCalls = voiceData.getOperatorNoOfCalls();
		this.dialogMinutesOfVoice = voiceData.getDialogMinutesOfVoice();
		this.dialogVoiceCost = voiceData.getDialogVoiceCost();
		this.dialogNoOfCalls = voiceData.getDialogNoOfCalls();
		this.minutesDifference = voiceData.getMinutesDifference();
		this.minutesDiffPercentage = voiceData.getMinutesDiffPercentage();
		this.voiceCostDifference = voiceData.getVoiceCostDifference();
		this.voiceCostPercentage = voiceData.getVoiceCostPercentage();
		this.noOfCallsDifference = voiceData.getNoOfCallsDifference();
		this.noOfCallsDiffPercentage = voiceData.getNoOfCallsDiffPercentage();

		if(voiceData.getInvoice() != null){
			this.invoiceResource = new InvoiceResource(voiceData.getInvoice());
		}
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public double getOperatorMinutesOfVoice() {
		return operatorMinutesOfVoice;
	}

	public void setOperatorMinutesOfVoice(double operatorMinutesOfVoice) {
		this.operatorMinutesOfVoice = operatorMinutesOfVoice;
	}

	public double getDialogMinutesOfVoice() {
		return dialogMinutesOfVoice;
	}

	public void setDialogMinutesOfVoice(double dialogMinutesOfVoice) {
		this.dialogMinutesOfVoice = dialogMinutesOfVoice;
	}

	public double getOperatorVoiceCost() {
		return operatorVoiceCost;
	}

	public void setOperatorVoiceCost(double operatorVoiceCost) {
		this.operatorVoiceCost = operatorVoiceCost;
	}

	public double getDialogVoiceCost() {
		return dialogVoiceCost;
	}

	public void setDialogVoiceCost(double dialogVoiceCost) {
		this.dialogVoiceCost = dialogVoiceCost;
	}

	public double getOperatorNoOfCalls() {
		return operatorNoOfCalls;
	}

	public void setOperatorNoOfCalls(double operatorNoOfCalls) {
		this.operatorNoOfCalls = operatorNoOfCalls;
	}

	public double getDialogNoOfCalls() {
		return dialogNoOfCalls;
	}

	public void setDialogNoOfCalls(double dialogNoOfCalls) {
		this.dialogNoOfCalls = dialogNoOfCalls;
	}

	public double getMinutesDifference() {
		return minutesDifference;
	}

	public void setMinutesDifference(double minutesDifference) {
		this.minutesDifference = minutesDifference;
	}

	public double getMinutesDiffPercentage() {
		return minutesDiffPercentage;
	}

	public void setMinutesDiffPercentage(double minutesDiffPercentage) {
		this.minutesDiffPercentage = minutesDiffPercentage;
	}

	public double getVoiceCostDifference() {
		return voiceCostDifference;
	}

	public void setVoiceCostDifference(double voiceCostDifference) {
		this.voiceCostDifference = voiceCostDifference;
	}

	public double getVoiceCostPercentage() {
		return voiceCostPercentage;
	}

	public void setVoiceCostPercentage(double voiceCostPercentage) {
		this.voiceCostPercentage = voiceCostPercentage;
	}

	public double getNoOfCallsDifference() {
		return noOfCallsDifference;
	}

	public void setNoOfCallsDifference(double noOfCallsDifference) {
		this.noOfCallsDifference = noOfCallsDifference;
	}

	public double getNoOfCallsDiffPercentage() {
		return noOfCallsDiffPercentage;
	}

	public void setNoOfCallsDiffPercentage(double noOfCallsDiffPercentage) {
		this.noOfCallsDiffPercentage = noOfCallsDiffPercentage;
	}

	public InvoiceResource getInvoiceResource() {
		return invoiceResource;
	}
	public void setInvoiceResource(InvoiceResource invoiceResource) {
		this.invoiceResource = invoiceResource;
	}

	public VoiceData toVoiceData() {
		return toVoiceData(new VoiceData());
	}

	public VoiceData toVoiceData(VoiceData voiceData) {
		voiceData.setId(id);
		voiceData.setDialogMinutesOfVoice(dialogMinutesOfVoice);
		voiceData.setDialogNoOfCalls(dialogNoOfCalls);
		voiceData.setDialogVoiceCost(dialogVoiceCost);
		voiceData.setOperatorMinutesOfVoice(operatorMinutesOfVoice);
		voiceData.setOperatorNoOfCalls(operatorNoOfCalls);
		voiceData.setOperatorVoiceCost(operatorVoiceCost);
		voiceData.setMinutesDifference(minutesDifference);
		voiceData.setMinutesDiffPercentage(minutesDiffPercentage);
		voiceData.setVoiceCostDifference(voiceCostDifference);
		voiceData.setVoiceCostPercentage(voiceCostPercentage);
		voiceData.setNoOfCallsDifference(noOfCallsDifference);
		voiceData.setNoOfCallsDiffPercentage(noOfCallsDiffPercentage);

		if(invoiceResource != null){
			voiceData.setInvoice(invoiceResource.toInvoice());
		}

		return voiceData;
	}

}
