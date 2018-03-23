package lk.dialog.BillVerification.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 * @author Sasini_08765
 */

@Entity
@Table(name="Voice_Data")
public class VoiceData {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column(name = "Operator_Minutes")
	Double operatorMinutesOfVoice;

	@Column(name = "Dialog_Minutes")
	Double dialogMinutesOfVoice;

	@Column(name = "Operator_Voice_cost")
	Double operatorVoiceCost;

	@Column(name = "Dialog_Voice_cost")
	Double dialogVoiceCost;

	@Column(name = "Operator_No_Of_Calls")
	Double operatorNoOfCalls;

	@Column(name = "Dialog_No_Of_Calls")
	Double dialogNoOfCalls;

	@Column(name = "Minutes_Difference")
	Double minutesDifference;

	@Column(name = "Min_Diff_Percentage")
	Double minutesDiffPercentage;

	@Column(name = "Voice_Cost_Difference")
	Double voiceCostDifference;

	@Column(name = "Voice_Cost_Percentage")
	Double voiceCostPercentage;

	@Column(name = "No_Calls_Difference")
	Double noOfCallsDifference;

	@Column(name = "No_Calls_Percentage")
	Double noOfCallsDiffPercentage;

	@OneToOne
	@JoinColumn(name = "Dialog_Voice_Invoice")
	private Invoice invoice;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Double getOperatorMinutesOfVoice() {
		return operatorMinutesOfVoice;
	}

	public void setOperatorMinutesOfVoice(Double operatorMinutesOfVoice) {
		this.operatorMinutesOfVoice = operatorMinutesOfVoice;
	}

	public Double getDialogMinutesOfVoice() {
		return dialogMinutesOfVoice;
	}

	public void setDialogMinutesOfVoice(Double dialogMinutesOfVoice) {
		this.dialogMinutesOfVoice = dialogMinutesOfVoice;
	}

	public Double getOperatorVoiceCost() {
		return operatorVoiceCost;
	}

	public void setOperatorVoiceCost(Double operatorVoiceCost) {
		this.operatorVoiceCost = operatorVoiceCost;
	}

	public Double getDialogVoiceCost() {
		return dialogVoiceCost;
	}

	public void setDialogVoiceCost(Double dialogVoiceCost) {
		this.dialogVoiceCost = dialogVoiceCost;
	}

	public Double getOperatorNoOfCalls() {
		return operatorNoOfCalls;
	}

	public void setOperatorNoOfCalls(Double operatorNoOfCalls) {
		this.operatorNoOfCalls = operatorNoOfCalls;
	}

	public Double getDialogNoOfCalls() {
		return dialogNoOfCalls;
	}

	public void setDialogNoOfCalls(Double dialogNoOfCalls) {
		this.dialogNoOfCalls = dialogNoOfCalls;
	}

	public Double getMinutesDifference() {
		return minutesDifference;
	}

	public void setMinutesDifference(Double minutesDifference) {
		this.minutesDifference = minutesDifference;
	}

	public Double getMinutesDiffPercentage() {
		return minutesDiffPercentage;
	}

	public void setMinutesDiffPercentage(Double minutesDiffPercentage) {
		this.minutesDiffPercentage = minutesDiffPercentage;
	}

	public Double getVoiceCostDifference() {
		return voiceCostDifference;
	}

	public void setVoiceCostDifference(Double voiceCostDifference) {
		this.voiceCostDifference = voiceCostDifference;
	}

	public Double getVoiceCostPercentage() {
		return voiceCostPercentage;
	}

	public void setVoiceCostPercentage(Double getVoiceCostPercentage) {
		this.voiceCostPercentage = getVoiceCostPercentage;
	}

	public Double getNoOfCallsDifference() {
		return noOfCallsDifference;
	}

	public void setNoOfCallsDifference(Double noOfCallsDifference) {
		this.noOfCallsDifference = noOfCallsDifference;
	}

	public Double getNoOfCallsDiffPercentage() {
		return noOfCallsDiffPercentage;
	}

	public void setNoOfCallsDiffPercentage(Double noOfCallsDiffPercentage) {
		this.noOfCallsDiffPercentage = noOfCallsDiffPercentage;
	}

	public Invoice getInvoice() {
		return invoice;
	}
	public void setInvoice(Invoice invoice) {
		this.invoice = invoice;
	}

}
