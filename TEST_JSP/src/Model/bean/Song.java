package Model.bean;

import java.sql.Date;

public class Song {
	private int id;
	private String name;
	private String preview;
	private String detail;
	private Date datecreate;
	private int id_cat;
	private String picutre;
	public int counter ;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPreview() {
		return preview;
	}
	public void setPreview(String preview) {
		this.preview = preview;
	}
	public String getDetail() {
		return detail;
	}
	public void setDetail(String detail) {
		this.detail = detail;
	}
	public Date getDatecreate() {
		return datecreate;
	}
	public void setDatecreate(Date datecreate) {
		this.datecreate = datecreate;
	}
	public int getId_cat() {
		return id_cat;
	}
	public void setId_cat(int id_cat) {
		this.id_cat = id_cat;
	}
	public String getPicutre() {
		return picutre;
	}
	public void setPicutre(String picutre) {
		this.picutre = picutre;
	}
	public int getCounter() {
		return counter;
	}
	public void setCounter(int counter) {
		this.counter = counter;
	}
	public Song(int id, String name, String preview, String detail, Date datecreate, int id_cat, String picutre, int counter) {
		super();
		this.id = id;
		this.name = name;
		this.preview = preview;
		this.detail = detail;
		this.datecreate = datecreate;
		this.id_cat = id_cat;
		this.picutre = picutre;
		this.counter = counter;
	}
	
}
