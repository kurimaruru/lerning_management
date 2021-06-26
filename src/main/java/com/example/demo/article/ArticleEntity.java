package com.example.demo.article;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name="article")
public class ArticleEntity extends AbstractEntity{

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column
	private Integer id;
	
	@Column
	@NotNull(message="タイトルが未入力です")
	@Size(max=50,message="50文字以下で入力してください")
	private String title;
	
	@Column
	@Size(max=200,message="ファイルパスは200文字以下で入力してください")
	private String file_path;
	
	@Column(length=1000)
	@NotNull(message="本文が未入力です")
	@Size(max=1000,message="1000文字以下で入力してください")
	private String body;
	
	@Column
	@Size(max=200,message="200文字以下のURLを入力してください")
	private String url;
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	

	public String getFile_path() {
		return file_path;
	}

	public void setFile_path(String file_path) {
		this.file_path = file_path;
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
