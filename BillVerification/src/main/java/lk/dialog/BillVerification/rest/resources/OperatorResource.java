package lk.dialog.BillVerification.rest.resources;

import lk.dialog.BillVerification.model.Operator;

/**
 * @author Sasini_08765
 */

public class OperatorResource {

	private Long id;
	private String name;
	private String code;
	private String no;
	private String street;
	private String city;
	private String country;
	private String countryCode;
	private String billingCycle;
	private double disputePercentage;
	private double disputeCost;

	//private InvoiceResource invoiceResource;

	public OperatorResource(){

	}

	public OperatorResource(Operator operator){
		this.id = operator.getId();
		this.name = operator.getName();
		this.code = operator.getCode();
		this.no = operator.getNo();
		this.street = operator.getStreet();
		this.city = operator.getCity();
		this.country = operator.getCountry();
		this.countryCode = operator.getCountryCode();
		this.billingCycle = operator.getBillingCycle();
		this.disputePercentage = operator.getDisputePercentage();
		this.disputeCost = operator.getDisputeCost();
		/*if(operator.getInvoice() != null){
			this.invoiceResource = new InvoiceResource(operator.getInvoice());
		}*/
	}

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
	public String getCountryCode() {
		return countryCode;
	}

	public void setCountryCode(String countryCode) {
		this.countryCode = countryCode;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getBillingCycle() { return billingCycle;	}

	public void setBillingCycle(String billingCycle) {	this.billingCycle = billingCycle;	}

	public double getDisputePercentage() {
		return disputePercentage;
	}

	public void setDisputePercentage(double disputePercentage) {
		this.disputePercentage = disputePercentage;
	}

	public double getDisputeCost() {
		return disputeCost;
	}

	public void setDisputeCost(double disputeCost) {
		this.disputeCost = disputeCost;
	}


	/*public InvoiceResource getInvoiceResource() {
		return invoiceResource;
	}

	public void setInvoiceResource(InvoiceResource invoiceResource) {
		this.invoiceResource = invoiceResource;
	}*/

	public Operator toOperator(){
		return toOperator(new Operator());
	}

	public Operator toOperator(Operator operator){
		operator.setId(id);
		operator.setName(name);
		operator.setCode(code);
		operator.setNo(no);
		operator.setStreet(street);
		operator.setCity(city);
		operator.setCountry(country);
		operator.setCountryCode(countryCode);
		operator.setBillingCycle(billingCycle);
		operator.setDisputePercentage(disputePercentage);
		operator.setDisputeCost(disputeCost);
		/*if(invoiceResource != null){
			operator.setInvoice(invoiceResource.toInvoice());
		}*/
		return operator;
	}

}
