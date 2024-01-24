package fr.ensitech.golfloc.entity;

public class Comment {
	
	private Integer userId;
	private Integer itemId;
	private String texte;
	private int rating;
	
	public Comment() {
		
	}
	
	public Comment(Integer userId, Integer itemId, String texte, int rating) {
		this.userId = userId;
		this.itemId = itemId;
		this.texte = texte;
		this.rating = rating;
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
	
	
}
