package com.oamanage.po;

import java.util.List;

/**
 * 员工实体类
 * @author Administrator
 *
 */
public class Staff {
	
	private int userId;
	private String userName;
	private String loginName;
	private String loginPassword;
	private int phone;
	private String sex;
	private String email;
	private List<Role> roles;
	private int deptId;
	private String deptName;
	private int roleId;
	private String roleName;
	
	public Staff() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Staff(int userId, String userName, String loginName,
			String loginPassword, int phone, String sex, String email,
			List<Role> roles, int deptId, String deptName, int roleId,
			String roleName) {
		super();
		this.userId = userId;
		this.userName = userName;
		this.loginName = loginName;
		this.loginPassword = loginPassword;
		this.phone = phone;
		this.sex = sex;
		this.email = email;
		this.roles = roles;
		this.deptId = deptId;
		this.deptName = deptName;
		this.roleId = roleId;
		this.roleName = roleName;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getLoginName() {
		return loginName;
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}

	public String getLoginPassword() {
		return loginPassword;
	}

	public void setLoginPassword(String loginPassword) {
		this.loginPassword = loginPassword;
	}

	public int getPhone() {
		return phone;
	}

	public void setPhone(int phone) {
		this.phone = phone;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public List<Role> getRoles() {
		return roles;
	}

	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}

	public int getDeptId() {
		return deptId;
	}

	public void setDeptId(int deptId) {
		this.deptId = deptId;
	}

	public String getDeptName() {
		return deptName;
	}

	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}

	public int getRoleId() {
		return roleId;
	}

	public void setRoleId(int roleId) {
		this.roleId = roleId;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
	
	
}
