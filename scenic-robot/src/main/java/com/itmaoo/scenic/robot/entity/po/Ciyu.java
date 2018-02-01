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

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		Ciyu ciyu1 = (Ciyu) o;

		if (id != ciyu1.id) return false;
		if (ciyu != null ? !ciyu.equals(ciyu1.ciyu) : ciyu1.ciyu != null) return false;
		if (mingci != null ? !mingci.equals(ciyu1.mingci) : ciyu1.mingci != null) return false;
		if (dongci != null ? !dongci.equals(ciyu1.dongci) : ciyu1.dongci != null) return false;
		if (xingci != null ? !xingci.equals(ciyu1.xingci) : ciyu1.xingci != null) return false;
		if (fuci != null ? !fuci.equals(ciyu1.fuci) : ciyu1.fuci != null) return false;
		if (liangci != null ? !liangci.equals(ciyu1.liangci) : ciyu1.liangci != null) return false;
		if (nishengci != null ? !nishengci.equals(ciyu1.nishengci) : ciyu1.nishengci != null) return false;
		if (jiegouzhuci != null ? !jiegouzhuci.equals(ciyu1.jiegouzhuci) : ciyu1.jiegouzhuci != null) return false;
		if (zhuci != null ? !zhuci.equals(ciyu1.zhuci) : ciyu1.zhuci != null) return false;
		if (binglielianci != null ? !binglielianci.equals(ciyu1.binglielianci) : ciyu1.binglielianci != null)
			return false;
		if (lianci != null ? !lianci.equals(ciyu1.lianci) : ciyu1.lianci != null) return false;
		if (jieci != null ? !jieci.equals(ciyu1.jieci) : ciyu1.jieci != null) return false;
		if (daici != null ? !daici.equals(ciyu1.daici) : ciyu1.daici != null) return false;
		if (yiwenci != null ? !yiwenci.equals(ciyu1.yiwenci) : ciyu1.yiwenci != null) return false;
		if (shuci != null ? !shuci.equals(ciyu1.shuci) : ciyu1.shuci != null) return false;
		return chengyu != null ? chengyu.equals(ciyu1.chengyu) : ciyu1.chengyu == null;
	}

	@Override
	public int hashCode() {
		int result = id;
		result = 31 * result + (ciyu != null ? ciyu.hashCode() : 0);
		result = 31 * result + (mingci != null ? mingci.hashCode() : 0);
		result = 31 * result + (dongci != null ? dongci.hashCode() : 0);
		result = 31 * result + (xingci != null ? xingci.hashCode() : 0);
		result = 31 * result + (fuci != null ? fuci.hashCode() : 0);
		result = 31 * result + (liangci != null ? liangci.hashCode() : 0);
		result = 31 * result + (nishengci != null ? nishengci.hashCode() : 0);
		result = 31 * result + (jiegouzhuci != null ? jiegouzhuci.hashCode() : 0);
		result = 31 * result + (zhuci != null ? zhuci.hashCode() : 0);
		result = 31 * result + (binglielianci != null ? binglielianci.hashCode() : 0);
		result = 31 * result + (lianci != null ? lianci.hashCode() : 0);
		result = 31 * result + (jieci != null ? jieci.hashCode() : 0);
		result = 31 * result + (daici != null ? daici.hashCode() : 0);
		result = 31 * result + (yiwenci != null ? yiwenci.hashCode() : 0);
		result = 31 * result + (shuci != null ? shuci.hashCode() : 0);
		result = 31 * result + (chengyu != null ? chengyu.hashCode() : 0);
		return result;
	}
}
