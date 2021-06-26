package com.example.demo.article;

import javax.persistence.Column;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.web.multipart.MultipartFile;

public class UploadFile {
	@Column
	@NotNull(message="タイトルが未入力です")
	@Size(max=50,message="50文字以下で入力してください")
	private String title;
	private MultipartFile file;
	@Column(length=1000)
	@NotNull(message="本文が未入力です")
	@Size(max=1000,message="1000文字以下で入力してください")
	private String body;
	@Column
	@Size(max=200,message="200文字以下のURLを入力してください")
	private String url;
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public MultipartFile getFile() {
		return file;
	}
	public void setFile(MultipartFile file) {
		this.file = file;
	}
	public String getBody() {
		return body;
	}
	public void setBody(String body) {
		this.body = body;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	
}
