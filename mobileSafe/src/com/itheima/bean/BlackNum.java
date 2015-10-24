package com.itheima.bean;

public class BlackNum {
	private String blacknum;
	private String mode;
	public String getBlacknum() {
		return blacknum;
	}
	public void setBlacknum(String blacknum) {
		this.blacknum = blacknum;
	}
	public String getMode() {
		
		return mode;
	}
	public void setMode(String mode) {
		//判断一下mode的作用
				if("".equals(mode)){
					mode="0";
				}else{
					this.mode=mode;
				}
		this.mode = mode;
	}
	public BlackNum(String blacknum,String mode){
			this.blacknum=blacknum;
			this.mode=mode;
	}
	@Override
	public String toString() {
		return "BlackNum [blacknum=" + blacknum + ", mode=" + mode + "]";
	}
	
}
