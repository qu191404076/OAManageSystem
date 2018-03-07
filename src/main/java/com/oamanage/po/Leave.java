package com.oamanage.po;

import java.util.Date;

/**
 * 请假信息
 * @author Administrator
 *
 */
public class Leave {
	private int id;
	private String userName; //请假人姓名
	private String reasonType; //请假类型
	private String leaveStart; //请假开始时间
	private String leaveEnd; //请假结束时间
	private String reason; //请假理由
	private String state;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getReasonType() {
		return reasonType;
	}
	public void setReasonType(String reasonType) {
		this.reasonType = reasonType;
	}
	public String getLeaveStart() {
		return leaveStart;
	}
	public void setLeaveStart(String leaveStart) {
		this.leaveStart = leaveStart;
	}
	public String getLeaveEnd() {
		return leaveEnd;
	}
	public void setLeaveEnd(String leaveEnd) {
		this.leaveEnd = leaveEnd;
	}
	public String getReason() {
		return reason;
	}
	public void setReason(String reason) {
		this.reason = reason;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	@Override
	public String toString() {
		return "Leave [id=" + id + ", userName=" + userName + ", reasonType="
				+ reasonType + ", leaveStart=" + leaveStart + ", leaveEnd="
				+ leaveEnd + ", reason=" + reason + ", state=" + state + "]";
	}
	
	
}
