package com.itmaoo.scenic.robot.entity.po;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="zd_chengyu")
public class Chengyu {
	@Id
    @GeneratedValue(strategy= GenerationType.AUTO)
	private int id;
	private String chengyu;
	private String pinyin;
	private String diangu;
	private String chuchu;
	private String lizi;
	private String spinyin;
	private String start_str;
	private String end_str;
	
	public int countWords() {
		return chengyu.length();
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getChengyu() {
		return chengyu;
	}
	public void setChengyu(String chengyu) {
		this.chengyu = chengyu;
	}
	public String getPinyin() {
		return pinyin;
	}
	public void setPinyin(String pinyin) {
		this.pinyin = pinyin;
	}
	public String getDiangu() {
		return diangu;
	}
	public void setDiangu(String diangu) {
		this.diangu = diangu;
	}
	public String getChuchu() {
		return chuchu;
	}
	public void setChuchu(String chuchu) {
		this.chuchu = chuchu;
	}
	public String getLizi() {
		return lizi;
	}
	public void setLizi(String lizi) {
		this.lizi = lizi;
	}
	public String getSpinyin() {
		return spinyin;
	}
	public void setSpinyin(String spinyin) {
		this.spinyin = spinyin;
	}
	public String getStart_str() {
		return start_str;
	}
	public void setStart_str(String start_str) {
		this.start_str = start_str;
	}
	public String getEnd_str() {
		return end_str;
	}
	public void setEnd_str(String end_str) {
		this.end_str = end_str;
	}
	
	


}
