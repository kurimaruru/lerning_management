package com.example.demo.pile;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name="target")
public class TargetEntity {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column
	private Integer id;
	
	@Column(unique=true)
	@NotNull
	private Integer month;

	@Column
	@NotNull(message="少なくとも１つは目標を設定してください")
	@Size(max=20,message="20文字以内で入力してください")
	private String month_target1;
	
	@Column
	@Size(max=20,message="20文字以内で入力してください")
	private String month_target2;
	
	@Column
	@Size(max=20,message="20文字以内で入力してください")
	private String month_target3;
	
	@Column
	private Double time_target;
	
	@Column
	private String state;
	
	@Column
	private String state2;
	
	@Column
	private String state3;

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

	public String getMonth_target1() {
		return month_target1;
	}

	public void setMonth_target1(String month_target1) {
		this.month_target1 = month_target1;
	}

	public String getMonth_target2() {
		return month_target2;
	}

	public void setMonth_target2(String month_target2) {
		this.month_target2 = month_target2;
	}

	public String getMonth_target3() {
		return month_target3;
	}

	public void setMonth_target3(String month_target3) {
		this.month_target3 = month_target3;
	}

	public Double getTime_target() {
		return time_target;
	}

	public void setTime_target(Double time_target) {
		this.time_target = time_target;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}
	
	
	public String getState2() {
		return state2;
	}

	public void setState2(String state2) {
		this.state2 = state2;
	}

	public String getState3() {
		return state3;
	}

	public void setState3(String state3) {
		this.state3 = state3;
	}
}






