package com.jczb.checkpoint.model;

import java.io.Serializable;
import java.util.List;

/**
 * 安标证书下载模型
 * @author 星尘
 * @date 2015-03-27
 */

public class DownCertificate implements Serializable {

	private static final long serialVersionUID = 6970841560479035453L;

	public DownCertificate(){
		super();
	}

	public   String id;
	
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
	 * 有效期开始日期
	 * 跟发证日期可能不一样
	 */
	public   String  term_validity;
	
	/**
	 * 有效期结束日期
	 */
	public String term_validity_end;
	
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
	
	List<AnbiaoScanningImg> downScanning;
	
	List<DownCertificate_realtion> downCertificate_realtion;
	
	public List<AnbiaoScanningImg> getDownScanning() {
		return downScanning;
	}


	public void setDownScanning(List<AnbiaoScanningImg> downScanning) {
		this.downScanning = downScanning;
	}

	
	public List<DownCertificate_realtion> getDownCertificate_realtion() {
		return downCertificate_realtion;
	}


	public void setDownCertificate_realtion(
			List<DownCertificate_realtion> downCertificate_realtion) {
		this.downCertificate_realtion = downCertificate_realtion;
	}

	public String getTerm_validity_end() {
		return term_validity_end;
	}


	public void setTerm_validity_end(String term_validity_end) {
		this.term_validity_end = term_validity_end;
	}
	public String getId() {
		return id;
	}


	public void setId(String id) {
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


	@Override
	public String toString() {
		return "DownCertificate [id=" + id + ", name=" + name + ", begin_date="
				+ begin_date + ", anbiao_code=" + anbiao_code
				+ ", provide_date=" + provide_date + ", term_validity="
				+ term_validity + ", term_validity_end=" + term_validity_end
				+ ", holder_person=" + holder_person + ", registered_address="
				+ registered_address + ", production_unit_id="
				+ production_unit_id + ", production_address="
				+ production_address + ", product_name=" + product_name
				+ ", product_model=" + product_model + ", standard=" + standard
				+ ", scope_application=" + scope_application + ", remark="
				+ remark + ", state=" + state + ", downScanning="
				+ downScanning + ", downCertificate_realtion="
				+ downCertificate_realtion + "]";
	}


	

}
