package com.zhuhua.iot.config.dictionary;

import java.util.ArrayList;
import java.util.List;

public class DataResultWeather {

	public List<DataResultInfo> DataList=new ArrayList<DataResultInfo>();

	public DataResultWeather() {

		//���ٵ�ַ
		DataList.add(new DataResultInfo("����",0,2,"",""));
		//����
		DataList.add(new DataResultInfo("����",2,2,"",""));

		//�¶�
		DataList.add(new DataResultInfo("�¶�",4,2,"",""));

		//��ѹ
		DataList.add(new DataResultInfo("��ѹ",5,2,"",""));
		//��ѹ
		DataList.add(new DataResultInfo("����",10,2,"",""));
		DataList.add(new DataResultInfo("����",12,2,"",""));
		
		DataList.add(new DataResultInfo("ʪ��",16,2,"",""));

		
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
