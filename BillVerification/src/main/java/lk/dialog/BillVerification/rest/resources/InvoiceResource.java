package lk.dialog.BillVerification.rest.resources;

import java.util.Date;

import lk.dialog.BillVerification.model.Invoice;

/**
 * @author Sasini_08765
 */

public class InvoiceResource {

	private Long id;
	private String documentId;
	private String invoiceNumber;
	private Date from;
	private Date to;
	private Date received;
	private Date verified;
	private String status;
	private String eventType;
	private String callsType;
	private String dataSource;
	private String comment;

	private OperatorResource operatorResource;
	private UserResource authorizedPersonResource;
	private UserResource userResource;
	private SLAResource slaResource;
	//private SmsDataResource smsDataResource;
	//private VoiceDataResource voiceDataResource;

	public InvoiceResource(){

	}

	public InvoiceResource(Invoice invoice){
			this.id = invoice.getId();
			this.documentId = invoice.getDocumentId();
			this.invoiceNumber = invoice.getInvoiceNumber();
			this.from = invoice.getFrom();
			this.to = invoice.getTo();
			this.received = invoice.getReceived();
			this.verified = invoice.getVerified();
			this.status = invoice.getStatus();
			this.eventType = invoice.getEventType();
			this.callsType = invoice.getCallsType();
			this.dataSource = invoice.getDataSource();
			this.comment = invoice.getComment();

		if(invoice.getOperator() != null){
			this.operatorResource = new OperatorResource(invoice.getOperator());
		}
		if(invoice.getUser() != null){
			this.userResource = new UserResource(invoice.getUser());
		}

		if (invoice.getAuthorizedPerson() != null) {
			this.authorizedPersonResource = new UserResource(invoice.getAuthorizedPerson());
		}
		if (invoice.getSla() != null) {
			this.slaResource = new SLAResource(invoice.getSla());
		}

		/*if(invoice.getSmsData() != null){
			this.smsDataResource = new SmsDataResource(invoice.getSmsData());
		}
		if(invoice.getVoiceData() != null){
			this.voiceDataResource = new VoiceDataResource(invoice.getVoiceData());
		}*/
		}

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}

	public String getDocumentId() {
		return documentId;
	}

	public void setDocumentId(String documentId) {
		this.documentId = documentId;
	}

	public String getInvoiceNumber() {
		return invoiceNumber;
	}
	public void setInvoiceNumber(String invoiceNumber) {
		this.invoiceNumber = invoiceNumber;
	}
	public Date getFrom() {
		return from;
	}
	public void setFrom(Date from) {
		this.from = from;
	}
	public Date getTo() {
		return to;
	}
	public void setTo(Date to) {
		this.to = to;
	}
	public Date getReceived() {
		return received;
	}
	public void setReceived(Date received) {
		this.received = received;
	}
	public Date getVerified() {
		return verified;
	}
	public void setVerified(Date verified) {
		this.verified = verified;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getEventType() {
		return eventType;
	}
	public void setEventType(String eventType) {
		this.eventType = eventType;
	}
	public String getCallsType() {
		return callsType;
	}
	public void setCallsType(String callsType) {
		this.callsType = callsType;
	}
	public String getDataSource() {
		return dataSource;
	}
	public void setDataSource(String dataSource) {
		this.dataSource = dataSource;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public OperatorResource getOperatorResource() {
		return operatorResource;
	}

	public void setOperatorResource(OperatorResource operatorResource) {
		this.operatorResource = operatorResource;
	}

	public UserResource getAuthorizedPersonResource() {
		return authorizedPersonResource;
	}
	public void setAuthorizedPersonResource(UserResource authorizedPersonResource) {
		this.authorizedPersonResource = authorizedPersonResource;
	}
	public UserResource getUserResource() {
		return userResource;
	}

	public void setUserResource(UserResource userResource) {
		this.userResource = userResource;
	}

	public SLAResource getSlaResource() {
		return slaResource;
	}

	public void setSlaResource(SLAResource slaResource) {
		this.slaResource = slaResource;
	}
	/*	public SmsDataResource getSmsDataResource() {
		return smsDataResource;
	}
	public void setSmsDataResource(SmsDataResource smsDataResource) {
		this.smsDataResource = smsDataResource;
	}
	public VoiceDataResource getVoiceDataResource() {
		return voiceDataResource;
	}
	public void setVoiceDataResource(VoiceDataResource voiceDataResource) {
		this.voiceDataResource = voiceDataResource;
	}*/

	public Invoice toInvoice(){
		return toInvoice(new Invoice());
	}
	public Invoice toInvoice(Invoice invoice) {
		invoice.setId(id);
		invoice.setDocumentId(documentId);
		invoice.setInvoiceNumber(invoiceNumber);
		invoice.setFrom(from);
		invoice.setTo(to);
		invoice.setReceived(received);
		invoice.setVerified(verified);
		invoice.setStatus(status);
		invoice.setEventType(eventType);
		invoice.setCallsType(callsType);
		invoice.setDataSource(dataSource);
		invoice.setComment(comment);
		if(operatorResource != null){
			invoice.setOperator(operatorResource.toOperator());
		}
		if(authorizedPersonResource != null){
			invoice.setAuthorizedPerson(authorizedPersonResource.toUser());
		}
		if(userResource != null){
			invoice.setUser(userResource.toUser());
		}
		if (slaResource != null){
			invoice.setSla(slaResource.toSLA());
		}
		/*if(smsDataResource != null){
			invoice.setSmsData(smsDataResource.toSmsData());
		}
		if(voiceDataResource != null){
			invoice.setVoiceData(voiceDataResource.toVoiceData());
		}*/
		return invoice;
	}
}
