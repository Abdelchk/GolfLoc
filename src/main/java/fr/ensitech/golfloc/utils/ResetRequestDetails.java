package fr.ensitech.golfloc.utils;

import java.sql.Timestamp;

public class ResetRequestDetails {
    private int userId;
    private String resetToken;
    private Timestamp expirationTime;
    
	public ResetRequestDetails() {
	}
	
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public String getResetToken() {
		return resetToken;
	}
	public void setResetToken(String resetToken) {
		this.resetToken = resetToken;
	}
	public Timestamp getExpirationTime() {
		return expirationTime;
	}
	public void setExpirationTime(Timestamp expirationTime) {
		this.expirationTime = expirationTime;
	}
}