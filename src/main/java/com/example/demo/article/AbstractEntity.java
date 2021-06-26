package com.example.demo.article;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;

@MappedSuperclass
public class AbstractEntity {

	@Column(name="created_datetime")
	private Date createdDatetime;
	
	@Column(name = "updated_datetime")
	private Date updateDatetime;
	
	@PrePersist
	public void onPrePersist() {
		setCreatedDatetime(new Date());
	}
	
	@PreUpdate
	public void onPreUpdate() {
		setUpdateDatetime(new Date());
	}

	public Date getUpdateDatetime() {
		return updateDatetime;
	}

	public void setUpdateDatetime(Date updateDatetime) {
		this.updateDatetime = updateDatetime;
	}

	public Date getCreatedDatetime() {
		return createdDatetime;
	}

	public void setCreatedDatetime(Date createdDatetime) {
		this.createdDatetime = createdDatetime;
	}

}
