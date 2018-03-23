package lk.dialog.BillVerification.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author Sasini_08765
 */

@Entity
@Table(name = "Role")
public class Role {

	@Id
	@Column(name = "Id")
	private Long id;
	@Column(name = "Role")
	String role;

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

}
