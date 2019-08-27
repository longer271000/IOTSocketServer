package com.zhuhua.iot.db.dal;

import java.util.List;

import com.zhuhua.iot.config.dictionary.DataResultInfo;
import com.zhuhua.iot.server.utils.IdWorker;

public class WeatherDal {

	static WeatherDal instance;
	public static WeatherDal getInstance() {
		if(instance==null) {
			instance=new WeatherDal();
		}
		return instance;
	}
	
	public boolean save(List<DataResultInfo> dataList) {
		  IdWorker worker = new IdWorker(1,1,1);
		  Long datetime=System.currentTimeMillis();
		  WeatherItemDb itemDb=new WeatherItemDb();
		  WeatherDb infoDb=new WeatherDb();
		  String info_id=worker.nextId()+"";
		  String info_did="";
		  String station_name="";
		  infoDb.add(info_id, info_did, station_name, datetime);
		  
		for(DataResultInfo info:dataList) {
			
			String id=worker.nextId()+"";
			String  fid=info_id; 
			String item=info.name;
			String  value=info.data;
			itemDb.add(id, fid, item, value, datetime);
		}
		
		return false;
		
	}
}
