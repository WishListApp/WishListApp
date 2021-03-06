package com.wlt.wla.auth.model;

import java.util.Locale;

public class DBWishItems {
	private String name;
	private int id;
	private float price;
	private String priceStr;
	private int group;
	private int user_id;
	private String cat_name;
	private int priority;
	private String priorityName;
	private String url;
	private String urlImg;

	public DBWishItems() {
	}

	public DBWishItems(String name, int id, float price, int group, int priority, String url) {
		this.name = name;
		this.id = id;
		this.price = price;
		this.group = group;
		this.priority = priority;
		this.url = url;
	}

	@Override
	public String toString() {
		return "DBWishItems{" +
				"name='" + name + '\'' +
				", id=" + id +
				", price=" + price +
				", priceStr='" + priceStr + '\'' +
				", group=" + group +
				", user_id=" + user_id +
				", cat_name='" + cat_name + '\'' +
				", priority=" + priority +
				", priorityName='" + priorityName + '\'' +
				", url='" + url + '\'' +
				", urlImg='" + urlImg + '\'' +
				", priority_name='" + priority_name + '\'' +
				'}';
	}

	public String getPriorityName() {
		return priorityName;
	}

	public void setPriorityName(String priorityName) {
		this.priorityName = priorityName;
	}


	public String getUrlImg() {
		return urlImg;
	}

	public void setUrlImg(String urlImg) {
		this.urlImg = urlImg;
	}
	
	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getPriority_name() {
		return priority_name;
	}

	public void setPriority_name(String priority_name) {
		this.priority_name = priority_name;
	}

	private String priority_name;
	
	public String getCat_name() {
		return cat_name;
	}

	public void setCat_name(String cat_name) {
		this.cat_name = cat_name;
	}

	
	public int getPriority() {
		return priority;
	}

	public void setPriority(int priority) {
		this.priority = priority;
	}


	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	public int getGroup() {
		return group;
	}

	public void setGroup(int group) {
		this.group = group;
	}

	public int getUser_id() {
		return user_id;
	}

	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}

	public String getPriceStr() {
		return priceStr;
	}

	public void setPriceStr(String priceStr) {
		this.priceStr = priceStr;
	}

}
