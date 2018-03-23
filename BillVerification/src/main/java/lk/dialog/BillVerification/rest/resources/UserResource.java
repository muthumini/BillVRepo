package lk.dialog.BillVerification.rest.resources;

import lk.dialog.BillVerification.model.User;

/**
 * @author Sasini_08765
 */

public class UserResource {

	private Long id;
	private String username;
	private String password;
	private RoleResource role;

	//private List<InvoiceResource> invoiceResources;

	public UserResource(){

	}
	public UserResource(User user) {
		this.id = user.getId();
		this.username = user.getUsername();
		this.password = user.getPassword();
		if(user.getRole() != null){
			this.role = new RoleResource(user.getRole());
		}
	}

public User toUser() {
		return toUser(new User());
}

	public User toUser(User user) {

		user.setId(id);
		user.setUsername(username);
		user.setPassword(password);
		if(role != null){
			user.setRole(role.toRole());
		}
		/*if(invoiceResources != null){
			user.setInvoices(invoiceResources.stream().map(InvoiceResource::toInvoice).collect(Collectors.toList()));
		}*/

		return user;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String userName) {
		this.username = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}

	public RoleResource getRole() {
		return role;
	}

	public void setRole(RoleResource role) {
		this.role = role;
	}
/*public List<InvoiceResource> getInvoiceResources() {
		return invoiceResources;
	}
	public void setInvoiceResources(List<InvoiceResource> invoiceResources) {
		this.invoiceResources = invoiceResources;
	}*/

}
