package com.jczb.checkpoint.model;

import java.io.Serializable;

public class AppDown implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -2333382289470943084L;

	public   int id;
	
	public   String name;
	
	/**
	 * 安标证书记录创建日期
	 */
	public   String begin_date;
	/**
	 * 安标证书编码
	 */
	public   String  anbiao_code;
	/**
	 * 发证日期
	 */
	public   String  provide_date;
	/**
	 * 有效期
	 */
	public   String  term_validity;
	/**
	 * 持证人
	 */
	public   String  holder_person;
	/**
	 * 注册地址
	 */
	public   String  registered_address;
	/**
	 * 生产单位
	 */
	public   String  production_unit_id;
	/**
	 * 生产地址
	 */
	public   String  production_address;
	/**
	 * 产品名称
	 */
	public   String  product_name;
	/**
	 * 规格型号
	 */
	public   String  product_model;
	/**
	 * 标准和要求
	 */
	public   String  standard;
	/**
	 * 适用范围
	 */
	public   String  scope_application;
	/**
	 * 备注
	 */
	public   String  remark;
	/**
	 * 状态
	 */
	public   String  state;
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
	public String getBegin_date() {
		return begin_date;
	}
	public void setBegin_date(String begin_date) {
		this.begin_date = begin_date;
	}
	public String getAnbiao_code() {
		return anbiao_code;
	}
	public void setAnbiao_code(String anbiao_code) {
		this.anbiao_code = anbiao_code;
	}
	public String getProvide_date() {
		return provide_date;
	}
	public void setProvide_date(String provide_date) {
		this.provide_date = provide_date;
	}
	public String getTerm_validity() {
		return term_validity;
	}
	public void setTerm_validity(String term_validity) {
		this.term_validity = term_validity;
	}
	public String getHolder_person() {
		return holder_person;
	}
	public void setHolder_person(String holder_person) {
		this.holder_person = holder_person;
	}
	public String getRegistered_address() {
		return registered_address;
	}
	public void setRegistered_address(String registered_address) {
		this.registered_address = registered_address;
	}
	public String getProduction_unit_id() {
		return production_unit_id;
	}
	public void setProduction_unit_id(String production_unit_id) {
		this.production_unit_id = production_unit_id;
	}
	public String getProduction_address() {
		return production_address;
	}
	public void setProduction_address(String production_address) {
		this.production_address = production_address;
	}
	public String getProduct_name() {
		return product_name;
	}
	public void setProduct_name(String product_name) {
		this.product_name = product_name;
	}
	public String getProduct_model() {
		return product_model;
	}
	public void setProduct_model(String product_model) {
		this.product_model = product_model;
	}
	public String getStandard() {
		return standard;
	}
	public void setStandard(String standard) {
		this.standard = standard;
	}
	public String getScope_application() {
		return scope_application;
	}
	public void setScope_application(String scope_application) {
		this.scope_application = scope_application;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
}
