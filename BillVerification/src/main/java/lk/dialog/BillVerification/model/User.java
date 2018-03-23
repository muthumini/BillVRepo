package lk.dialog.BillVerification.model;

import javax.persistence.*;

/**
 * @author Sasini_08765
 */

@Entity
@Table(name="User")
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="ID")
	private Long id;

	@Column(name = "User_name")
	String username;

	@Column(name = "Password")
	String password;

	@ManyToOne
	@JoinColumn(name = "User_role")
	private Role role;

	/*@OneToMany(cascade = CascadeType.REMOVE, fetch = FetchType.EAGER, mappedBy = "user")
	private List<Invoice> invoices;*/

	@OneToOne(cascade = CascadeType.REMOVE, fetch = FetchType.EAGER, mappedBy = "user")
	private UserProfile userProfile;

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

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

/*	public List<Invoice> getInvoices() {
		return invoices;
	}

	public void setInvoices(List<Invoice> invoices) {
		this.invoices = invoices;
	}*/

	public UserProfile getUserProfile() {
		return userProfile;
	}

	public void setUserProfile(UserProfile userProfile) {
		this.userProfile = userProfile;
	}

}
