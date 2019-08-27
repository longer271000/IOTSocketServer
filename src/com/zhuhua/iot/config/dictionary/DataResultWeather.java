package com.zhuhua.iot.config.dictionary;

import java.util.ArrayList;
import java.util.List;

public class DataResultWeather {

	public List<DataResultInfo> DataList=new ArrayList<DataResultInfo>();

	public DataResultWeather() {

		//风速地址
		DataList.add(new DataResultInfo("风速",0,2,"",""));
		//雨量
		DataList.add(new DataResultInfo("雨量",2,2,"",""));

		//温度
		DataList.add(new DataResultInfo("温度",4,2,"",""));

		//气压
		DataList.add(new DataResultInfo("气压",5,2,"",""));
		//气压
		DataList.add(new DataResultInfo("辐射",10,2,"",""));
		DataList.add(new DataResultInfo("风向",12,2,"",""));
		
		DataList.add(new DataResultInfo("湿度",16,2,"",""));

		
	}
	public void add(DataResultInfo info) {
		DataList.add(info);
		
	}
	public void add(int index,DataResultInfo info) {
		DataList.add(index,info);
		
	}
	public void remove(int index) {
		DataList.remove(index);
	}
	
	

}
