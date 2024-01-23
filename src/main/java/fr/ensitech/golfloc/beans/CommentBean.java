package fr.ensitech.golfloc.beans;

import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.application.FacesMessage;

@ManagedBean(name = "commentbean")
@SessionScoped
public class CommentBean implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Integer userId;
	private Integer itemId;
	private String texte;
	private int rating;
	
	public CommentBean() {
		
	}
	
	public Integer getUserId() {
		return userId;
	}
	
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	
	public Integer getItemId() {
		return itemId;
	}
	
	public void setItemId(Integer itemId) {
		this.itemId = itemId;
	}
	
	public String getTexte() {
		return texte;
	}
	
	public void setTexte(String texte) {
		this.texte = texte;
	}
	
	public int getRating() {
		return rating;
	}
	
	public void setRating(int rating) {
		this.rating = rating;
	}
	
	// Méthodes de Gestion
	
	public String createComment() {
		return null;
	}
	
	public String deleteComment() {
		return null;
	}
	
	public String updateComment() {
		return null;
	}
	
	public String getComments() {
		return null;
	}
}
