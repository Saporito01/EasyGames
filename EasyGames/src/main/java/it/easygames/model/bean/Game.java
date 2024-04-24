package it.easygames.model.bean;

import java.io.Serializable;

public class Game implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	public Game() {
		id = "";
		name = "";
		desc = "";
		platf = "";
		qt = 0;
		price = 0.0f;
	}

	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
	
	public String getPlatf() {
		return platf;
	}
	public void setPlatf(String platf) {
		this.platf = platf;
	}
	
	public int getQt() {
		return qt;
	}
	public void setQt(int qt) {
		this.qt = qt;
	}
	
	public float getPrice() {
		return price;
	}
	public void setPrice(float price) {
		this.price = price;
	}


	private String id;
	private String name;
	private String desc;
	private String platf;
	private int qt;
	private float price;
}
