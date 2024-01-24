package fr.ensitech.golfloc.entity;

public class Item {
	private Integer id;
	private String name;
	private String brand;
	private String gender;
	private String mainHand;
	private String flexibility;
	private String description;
	private Float price;
	private int discount;
	private int stock;
	private Integer categoryId;
	private boolean isSellable;
	
	public Item() {
	}

	public Item(Integer id, String name, String brand, String gender, String mainHand, String flexibility,
			String description, Float price, int discount, int stock, Integer categoryId, boolean isSellable) {
		this.id = id;
		this.name = name;
		this.brand = brand;
		this.gender = gender;
		this.mainHand = mainHand;
		this.flexibility = flexibility;
		this.description = description;
		this.price = price;
		this.discount = discount;
		this.stock = stock;
		this.categoryId = categoryId;
		this.isSellable = isSellable;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getMainHand() {
		return mainHand;
	}

	public void setMainHand(String mainHand) {
		this.mainHand = mainHand;
	}

	public String getFlexibility() {
		return flexibility;
	}

	public void setFlexibility(String flexibility) {
		this.flexibility = flexibility;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Float getPrice() {
		return price;
	}

	public void setPrice(Float price) {
		this.price = price;
	}

	public int getDiscount() {
		return discount;
	}

	public void setDiscount(int discount) {
		this.discount = discount;
	}

	public int getStock() {
		return stock;
	}

	public void setStock(int stock) {
		this.stock = stock;
	}

	public Integer getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(Integer categoryId) {
		this.categoryId = categoryId;
	}

	public boolean getIsSellable() {
		return isSellable;
	}

	public void setIsSellable(boolean isSellable) {
		this.isSellable = isSellable;
	}
	
	
}
