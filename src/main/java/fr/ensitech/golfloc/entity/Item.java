package fr.ensitech.golfloc.entity;

import fr.ensitech.golfloc.enums.Flexibility;
import fr.ensitech.golfloc.enums.Gender;
import fr.ensitech.golfloc.enums.MainHand;

public class Item {
	private Integer id;
	private String name;
	private String brand;
	private Gender gender;
	private MainHand mainHand;
	private Flexibility flexibility;
	private String description;
	private Float price;
	private int discount;
	private int stock;
	private Integer categoryId;
	private String isSellable;
	
	public Item() {
	}

	public Item(Integer id, String name, String brand, Gender gender, MainHand mainHand, Flexibility flexibility,
			String description, Float price, int discount, int stock, Integer categoryId, String isSellable) {
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

	public Gender getGender() {
		return gender;
	}

	public void setGender(Gender gender) {
		this.gender = gender;
	}

	public MainHand getMainHand() {
		return mainHand;
	}

	public void setMainHand(MainHand mainHand) {
		this.mainHand = mainHand;
	}

	public Flexibility getFlexibility() {
		return flexibility;
	}

	public void setFlexibility(Flexibility flexibility) {
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

	public String getIsSellable() {
		return isSellable;
	}

	public void setIsSellable(String isSellable) {
		this.isSellable = isSellable;
	}
	
	
}
