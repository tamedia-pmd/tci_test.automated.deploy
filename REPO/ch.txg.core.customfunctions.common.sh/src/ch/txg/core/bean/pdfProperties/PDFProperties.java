package ch.txg.core.bean.pdfProperties;

import java.io.Serializable;

public class PDFProperties implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -3559081505570827815L;
	private String author;
	private String title;
	private String object;
	private String keywords;
	
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getObject() {
		return object;
	}
	public void setObject(String object) {
		this.object = object;
	}
	public String getKeywords() {
		return keywords;
	}
	public void setKeywords(String keywords) {
		this.keywords = keywords;
	}
	
}
