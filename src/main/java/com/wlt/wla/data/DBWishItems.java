package com.wlt.wla.data;

public class DBWishItems {
	private String name;
	private int id;
	private float price;
	private int group;
	private int user_id;
	private String cat_name;
	private int priority;
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

}
