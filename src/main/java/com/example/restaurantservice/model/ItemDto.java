package com.example.restaurantservice.model;



import  com.example.restaurantservice.model.ItemType;

public class ItemDto {

	private String itemName;

	private String itemDesc;

	private Long price;
	
	private ItemType itemType;

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public String getItemDesc() {
		return itemDesc;
	}

	public void setItemDesc(String itemDesc) {
		this.itemDesc = itemDesc;
	}

	public Long getPrice() {
		return price;
	}

	public void setPrice(Long price) {
		this.price = price;
	}

	public ItemType getItemType() {
		return itemType;
	}

	public void setItemType(ItemType itemType) {
		this.itemType = itemType;
	}

	@Override
	public String toString() {
		return "ItemDto [itemName=" + itemName + ", itemDesc=" + itemDesc + ", price=" + price + ", itemType="
				+ itemType + "]";
	}

	public ItemDto(String itemName, String itemDesc, Long price, ItemType itemType) {
		super();
		this.itemName = itemName;
		this.itemDesc = itemDesc;
		this.price = price;
		this.itemType = itemType;
	}

	public ItemDto() {
		super();
	}
	
	
}
