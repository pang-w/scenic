package com.itmaoo.scenic.robot.entity.po;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="zd_ciyu")
public class Ciyu {
	@Id
    @GeneratedValue(strategy= GenerationType.AUTO)
	private int id;
	private String ciyu;
	private Boolean mingci;
	private Boolean dongci;
	private Boolean xingci;
	private Boolean fuci;
	
	private Boolean liangci;
	private Boolean nishengci;
	private Boolean jiegouzhuci;
	private Boolean zhuci;
	
	private Boolean binglielianci;
	private Boolean lianci;
	private Boolean jieci;
	private Boolean daici;
	
	private Boolean yiwenci;
	private Boolean shuci;
	private Boolean chengyu;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getCiyu() {
		return ciyu;
	}
	public void setCiyu(String ciyu) {
		this.ciyu = ciyu;
	}
	public Boolean getMingci() {
		return mingci;
	}
	public void setMingci(Boolean mingci) {
		this.mingci = mingci;
	}
	public Boolean getDongci() {
		return dongci;
	}
	public void setDongci(Boolean dongci) {
		this.dongci = dongci;
	}
	public Boolean getXingci() {
		return xingci;
	}
	public void setXingci(Boolean xingci) {
		this.xingci = xingci;
	}
	public Boolean getFuci() {
		return fuci;
	}
	public void setFuci(Boolean fuci) {
		this.fuci = fuci;
	}
	public Boolean getLiangci() {
		return liangci;
	}
	public void setLiangci(Boolean liangci) {
		this.liangci = liangci;
	}
	public Boolean getNishengci() {
		return nishengci;
	}
	public void setNishengci(Boolean nishengci) {
		this.nishengci = nishengci;
	}
	public Boolean getJiegouzhuci() {
		return jiegouzhuci;
	}
	public void setJiegouzhuci(Boolean jiegouzhuci) {
		this.jiegouzhuci = jiegouzhuci;
	}
	public Boolean getZhuci() {
		return zhuci;
	}
	public void setZhuci(Boolean zhuci) {
		this.zhuci = zhuci;
	}
	public Boolean getBinglielianci() {
		return binglielianci;
	}
	public void setBinglielianci(Boolean binglielianci) {
		this.binglielianci = binglielianci;
	}
	public Boolean getLianci() {
		return lianci;
	}
	public void setLianci(Boolean lianci) {
		this.lianci = lianci;
	}
	public Boolean getJieci() {
		return jieci;
	}
	public void setJieci(Boolean jieci) {
		this.jieci = jieci;
	}
	public Boolean getDaici() {
		return daici;
	}
	public void setDaici(Boolean daici) {
		this.daici = daici;
	}
	public Boolean getYiwenci() {
		return yiwenci;
	}
	public void setYiwenci(Boolean yiwenci) {
		this.yiwenci = yiwenci;
	}
	public Boolean getShuci() {
		return shuci;
	}
	public void setShuci(Boolean shuci) {
		this.shuci = shuci;
	}
	public Boolean getChengyu() {
		return chengyu;
	}
	public void setChengyu(Boolean chengyu) {
		this.chengyu = chengyu;
	}


}
