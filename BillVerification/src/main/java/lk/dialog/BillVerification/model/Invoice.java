package lk.dialog.BillVerification.model;

import java.util.Date;

import javax.persistence.*;
import javax.validation.constraints.Max;

/**
 * @author Sasini_08765
 */

@Entity
@Table(name = "Invoice")
public class Invoice {

	@Id
	@Column(name = "Id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "Document_Id", unique = true)
	String documentId;
	@Column(name = "Invoice_number", unique = true)
	String invoiceNumber;
	@Column(name = "From_date")
	Date from;
	@Column(name = "To_date")
	Date to;
	@Column(name = "Received_date")
	Date received;
	@Column(name = "Verified_date")
	Date verified;
	@Column(name = "Invoice_status")
	String status;
	@Column(name = "Event_Type")
	String eventType;
	@Column(name = "Calls_type")
	String callsType;
	@Column(name = "Data_source")
	String dataSource;
	@Column(name = "Comment")
	@Lob
	String comment;

	@ManyToOne(cascade = CascadeType.MERGE)
	@JoinColumn(name = "Invoice_Operator")
	private Operator operator;

	@OneToOne(cascade = CascadeType.MERGE)
	@JoinColumn(name = "Invoice_authorizingPerson")
	private User authorizedPerson;

	@ManyToOne(cascade = CascadeType.MERGE)
	@JoinColumn(name = "Invoice_User")
	private User user;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "Invoice_SLA")
	private SLA sla;

	@OneToOne(cascade = CascadeType.REMOVE, fetch = FetchType.EAGER, mappedBy = "invoice")
	private SmsData smsData;

	@OneToOne(cascade = CascadeType.REMOVE, fetch = FetchType.EAGER, mappedBy = "invoice")
	private VoiceData voiceData;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public Operator getOperator() {
		return operator;
	}

	public void setOperator(Operator operator) {
		this.operator = operator;
	}

	public User getAuthorizedPerson() {
		return authorizedPerson;
	}

	public void setAuthorizedPerson(User authorizedPerson) {
		this.authorizedPerson = authorizedPerson;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public SLA getSla() {
		return sla;
	}

	public void setSla(SLA sla) {
		this.sla = sla;
	}

	public SmsData getSmsData() {
		return smsData;
	}

	public void setSmsData(SmsData smsData) {
		this.smsData = smsData;
	}

	public VoiceData getVoiceData() {
		return voiceData;
	}

	public void setVoiceData(VoiceData voiceData) {
		this.voiceData = voiceData;
	}

	public String getDocumentId() {
		return documentId;
	}

	public void setDocumentId(String documentId) {
		this.documentId = documentId;
	}
}
