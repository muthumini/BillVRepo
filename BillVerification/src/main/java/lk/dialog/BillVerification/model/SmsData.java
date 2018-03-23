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
@Table(name = "SMS_Data")
public class SmsData {

	@Id
	@Column(name = "Id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column(name = "Operator_No_of_sms")
	Long operatorNoOfSms;

	@Column(name = "Operator_Sms_cost")
	Double operatorSmsCost;

	@Column(name = "Dialog_No_of_sms")
	Long dialogNoOfSms;

	@Column(name = "Dialog_Sms_cost")
	Double dialogSmsCost;

	@Column(name = "Sms_Qty_Difference")
	Long smsQtyDiff;

	@Column(name = "Sms_Qty_Diff_Percentage")
	Double smsQtyPercentage;

	@Column(name = "Sms_Cost_Difference")
	Long smsCostDiff;

	@Column(name = "Sms_Cost_Diff_Percentage")
	Long smsCostDiffPercentage;

	@OneToOne
	@JoinColumn(name = "SMS_Invoice")
	private Invoice invoice;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getOperatorNoOfSms() {
		return operatorNoOfSms;
	}

	public void setOperatorNoOfSms(Long operatorNoOfSms) {
		this.operatorNoOfSms = operatorNoOfSms;
	}

	public Double getOperatorSmsCost() {
		return operatorSmsCost;
	}

	public void setOperatorSmsCost(Double operatorSmsCost) {
		this.operatorSmsCost = operatorSmsCost;
	}

	public Long getDialogNoOfSms() {
		return dialogNoOfSms;
	}

	public void setDialogNoOfSms(Long dialogNoOfSms) {
		this.dialogNoOfSms = dialogNoOfSms;
	}

	public Double getDialogSmsCost() {
		return dialogSmsCost;
	}

	public void setDialogSmsCost(Double dialogSmsCost) {
		this.dialogSmsCost = dialogSmsCost;
	}

	public Long getSmsQtyDiff() {
		return smsQtyDiff;
	}

	public void setSmsQtyDiff(Long smsQtyDiff) {
		this.smsQtyDiff = smsQtyDiff;
	}

	public Double getSmsQtyPercentage() {
		return smsQtyPercentage;
	}

	public void setSmsQtyPercentage(Double smsQtyPercentage) {
		this.smsQtyPercentage = smsQtyPercentage;
	}

	public Long getSmsCostDiff() {
		return smsCostDiff;
	}

	public void setSmsCostDiff(Long smsCostDiff) {
		this.smsCostDiff = smsCostDiff;
	}

	public Long getSmsCostDiffPercentage() {
		return smsCostDiffPercentage;
	}

	public void setSmsCostDiffPercentage(Long smsCostDiffPercentage) {
		this.smsCostDiffPercentage = smsCostDiffPercentage;
	}

	public Invoice getInvoice() {
		return invoice;
	}

	public void setInvoice(Invoice invoice) {
		this.invoice = invoice;
	}

}
