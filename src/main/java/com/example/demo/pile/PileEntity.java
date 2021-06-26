package com.example.demo.pile;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Table(name="pile")
@Entity
public class PileEntity {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column
	private Integer id;
	
	@Column
	private Integer month;
	
	@Column
	private Integer day;
	
	@Column(length=12)
	@NotNull(message="学習時間を入力してください")
	@Max(12)
	private Double stime;

	@Column(length=100)
	@NotNull(message="一言日記を入力してください")
	@Size(max=100,message="１００字以内で入力してください")
	private String memo;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getMonth() {
		return month;
	}
	public void setMonth(Integer month) {
		this.month = month;
	}
	public Integer getDay() {
		return day;
	}
	public void setDay(Integer day) {
		this.day = day;
	}
	
	
	public Double getStime() {
		return stime;
	}
	public void setStime(Double stime) {
		this.stime = stime;
	}
	
	public String getMemo() {
		return memo;
	}
	public void setMemo(String memo) {
		this.memo = memo;
	}
	
	
}
