package model;

import java.sql.Date;

public class User {
	private int UserID;
	
	private String Name;
	private String NRIC;
	private Date DOB;
	private String Email;
	private String MobileNumber;
	private String Role;
	private String Password;
	private boolean FirstLogin;
	private String status;
	
	private String SecurityQuestion = new String();
	private String SecurityAnswer   = new String();
	
	private int loginAttempts;
	
	public int getUserID() {
		return UserID;
	}
	public void setUserID(int userID) {
		UserID = userID;
	}
	public String getName() {
		return Name;
	}
	public void setName(String name) {
		Name = name;
	}
	public String getNRIC() {
		return NRIC;
	}
	public void setNRIC(String nRIC) {
		NRIC = nRIC;
	}
	public Date getDOB() {
		return DOB;
	}
	public void setDOB(Date _DOB) {
		DOB = _DOB;
	}
	public String getEmail() {
		return Email;
	}
	public void setEmail(String email) {
		Email = email;
	}
	public String getMobileNumber() {
		return MobileNumber;
	}
	public void setMobileNumber(String mobileNumber) {
		MobileNumber = mobileNumber;
	}
	public boolean isFirstLogin() {
		return FirstLogin;
	}
	public void setFirstLogin(boolean firstLogin) {
		FirstLogin = firstLogin;
	}
	public String getSecurityQuestion() {
		return SecurityQuestion;
	}
	public void setSecurityQuestion(String securityQuestion) {
		SecurityQuestion = securityQuestion;
	}
	public String getSecurityAnswer() {
		return SecurityAnswer;
	}
	public void setSecurityAnswer(String securityAnswer) {
		SecurityAnswer = securityAnswer;
	}
	public String getRole() {
		return Role;
	}
	public void setRole(String role) {
		Role = role;
	}
	public String getPassword() {
		return Password;
	}
	public void setPassword(String password) {
		Password = password;
	}
	public boolean verifyPassword(String password) {
		return password.equals(Password);
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public int getLoginAttempts() {
		return loginAttempts;
	}
	public void setLoginAttempts(int loginAttempts) {
		this.loginAttempts = loginAttempts;
	}
}
