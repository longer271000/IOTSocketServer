package com.zhuhua.iot.config.dictionary;

import java.io.Serializable;

/***
 * 协议数据基础类
 * ***/
public class DataResultInfo implements Serializable {

	public DataResultInfo(String name,int index,int len,String data_hex,String data) {
		this.name=name;
		this.data_hex=data_hex;
		this.data=data;
		this.len=len;
		this.index=index;
	}
	public String name="";
	public int index=0;

	public int len=0;

	public String data="";
	public String data_hex="";

}
