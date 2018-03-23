package lk.dialog.BillVerification.model;

import javax.persistence.*;

/**
 * @author Sasini_08765
 */

@Entity
@Table(name="Operator")
public class Operator {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "Id")
	Long id;
	@Column(name="Operator_name")
	String name;
	@Column(name="Operator_code", unique = true)
	String code;
	@Column(name="Operator_addr_no")
	String no;
	@Column(name="Operator_street")
	String street;
	@Column(name="Operator_city")
	String city;
	@Column(name="Operator_country")
	String country;
	@Column(name="Operator_countryCode")
	String countryCode;
	@Column(name = "Billing_Cycle")
	String billingCycle;
	@Column(name="Dispute_percentage")
	Double disputePercentage;
	@Column(name="Dispute_cost")
	Double disputeCost;

	/*@OneToMany(cascade = CascadeType.REMOVE, fetch = FetchType.EAGER, mappedBy = "operator")
	private List<Invoice> invoices;*/

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getNo() {
		return no;
	}
	public void setNo(String no) {
		this.no = no;
	}
	public String getStreet() {
		return street;
	}
	public void setStreet(String street) {
		this.street = street;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public String getCountryCode() {
		return countryCode;
	}
	public void setCountryCode(String countryCode) {
		this.countryCode = countryCode;
	}
	public String getBillingCycle() { return billingCycle; }
	public void setBillingCycle(String billingCycle) {	this.billingCycle = billingCycle; }
	public Double getDisputePercentage() {
		return disputePercentage;
	}
	public void setDisputePercentage(Double disputePercentage) {
		this.disputePercentage = disputePercentage;
	}
	public Double getDisputeCost() {
		return disputeCost;
	}
	public void setDisputeCost(Double disputeCost) {
		this.disputeCost = disputeCost;
	}

	/*public List<Invoice> getInvoices() {
		return invoices;
	}

	public void setInvoices(List<Invoice> invoices) {
		this.invoices = invoices;
	}*/
}
