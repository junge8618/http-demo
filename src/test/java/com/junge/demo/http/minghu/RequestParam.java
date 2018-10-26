/**
 * 
 */
package com.junge.demo.http.minghu;

/**
 * 请求参数
 * 
 * @author "liuxj"
 *
 */
public class RequestParam {

	private String corpId;
	private String sign;
	private String noncestr;
	private String data;

	public String getCorpId() {
		return corpId;
	}

	public void setCorpId(String corpId) {
		this.corpId = corpId;
	}

	public String getSign() {
		return sign;
	}

	public void setSign(String sign) {
		this.sign = sign;
	}

	public String getNoncestr() {
		return noncestr;
	}

	public void setNoncestr(String noncestr) {
		this.noncestr = noncestr;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

}
