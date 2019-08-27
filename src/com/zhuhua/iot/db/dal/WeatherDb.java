package com.zhuhua.iot.db.dal;

import com.zhuhua.iot.db.DBHerpel;

public class WeatherDb {

	public static void add(String id,String did,String station_name,long datetime) {
		
		String strSQL="insert into davis_weather_info values('"+id+"','"+did+"','"+station_name+"',"+datetime+")";
		DBHerpel.execSQL(strSQL);
	}
	
}
