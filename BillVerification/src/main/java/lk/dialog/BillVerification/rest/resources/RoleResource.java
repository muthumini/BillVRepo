package lk.dialog.BillVerification.rest.resources;

import lk.dialog.BillVerification.model.Role;

/**
 * @author Sasini_08765
 */

public class RoleResource {
	private Long id;
	private String role;

	public RoleResource(){

	}

	public RoleResource(Role role){
		this.id = role.getId();
		this.role = role.getRole();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public Role toRole(){
		Role userRole = new Role();
		userRole.setId(id);
		userRole.setRole(role);

		return userRole;
	}
}
