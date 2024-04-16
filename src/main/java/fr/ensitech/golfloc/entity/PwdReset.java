package fr.ensitech.golfloc.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.xml.bind.annotation.XmlRootElement;

@Entity(name = "pwd_reset")
@XmlRootElement
public class PwdReset implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@ManyToOne
	@JoinColumn(name = "user_id", nullable = false)
	private User user;
	
	@Column(name = "token", nullable = false)
	private String token;
	
	@Column(name = "expiration_time", nullable = false, length = 45)
	private String expirationTime;
	
	public PwdReset() {
		
	}
	
	public PwdReset(Integer id, User user, String token, String expirationTime) {
		this.id = id;
		this.user = user;
		this.token = token;
		this.expirationTime = expirationTime;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getExpirationTime() {
		return expirationTime;
	}

	public void setExpirationTime(String expirationTime) {
		this.expirationTime = expirationTime;
	}

	@Override
	public String toString() {
		return "PwdReset [id=" + id + ", user=" + user + ", token=" + token + ", expirationTime=" + expirationTime
				+ "]";
	}
	
}
