package lk.dialog.BillVerification.rest.resources;

import lk.dialog.BillVerification.model.UserProfile;

/**
 * @author Sasini_08765
 */

public class UserProfileResource {

	private Long id;
	private String empID;
	private String firstname;
	private String lastname;
	private String mobile;
	private String email;

	private UserResource user;

	public UserProfileResource(){

	}

	public UserProfileResource(UserProfile userProfile){
		this.id = userProfile.getId();
		this.empID = userProfile.getEmpID();
		this.firstname = userProfile.getFirstname();
		this.lastname = userProfile.getLastname();
		this.mobile = userProfile.getMobile();
		this.email = userProfile.getEmail();
		if(userProfile.getUser() != null){
			this.user = new UserResource(userProfile.getUser());
		}
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getEmpID() {
		return empID;
	}

	public void setEmpID(String empID) {
		this.empID = empID;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public UserResource getUser() {
		return user;
	}

	public void setUser(UserResource user) {
		this.user = user;
	}

	public UserProfile toUserProfile() { return toUserProfile(new UserProfile()); }

		public UserProfile toUserProfile(UserProfile userProfile){

		userProfile.setEmpID(empID);
		userProfile.setFirstname(firstname);
		userProfile.setLastname(lastname);
		userProfile.setMobile(mobile);
		userProfile.setEmail(email);

		if(user != null){
			userProfile.setUser(user.toUser());
		}
		return userProfile;
	}
}
