package com.zhuhua.iot.db.dal;

import com.zhuhua.iot.db.DBHerpel;

public class WeatherItemDb {

	public static void add(String id,String fid,String item,String value,long datetime) {
		
		String strSQL="insert into davis_weather_info_item values('"+id+"','"+fid+"','"+item+"','"+value+"',"+datetime+")";
		DBHerpel.execSQL(strSQL);
	}
}
