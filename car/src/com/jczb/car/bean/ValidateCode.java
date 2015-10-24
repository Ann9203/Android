package com.jczb.car.bean;

/**
 * 验证码实体类
 * @author 吴利昌
 * @date 2015-8-27 下午5:18:24
 */
public class ValidateCode {

	/**json串返回结果*/
	private String result;
	
	/**返回的验证码*/
	private String code;

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

}
