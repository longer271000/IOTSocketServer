package com.zhuhua.iot.parser.weather;

import java.util.List;

import com.zhuhua.iot.config.dictionary.DataResultInfo;
import com.zhuhua.iot.config.dictionary.DataResultWeather;
import com.zhuhua.iot.parser.AbstractDataParser;
import com.zhuhua.iot.parser.IParserExpression;

/****
 * 匹配天气数据
 * ***/
public class DataWeatherParser extends AbstractDataParser implements IParserExpression {

	String TAG="";
	@Override
	public String interpret(String content) {
		// TODO Auto-generated method stub
		//获取16进制数据
		
		//获取数据头
		String str_hex_header_device_data=content.substring(this.header_device_index,header_device_index+header_device_len);
		String str_hex_header_option_data=content.substring(this.header_option_index,this.header_option_index+this.header_option_len);

		//获取数据长度
		String str_hex_data_len=content.substring(this.header_data_index,this.header_data_index+this.header_data_len);

		int data_len=Integer.valueOf(str_hex_data_len,16);
    	System.out.println(TAG+"====interpret=====16*content-len:"+data_len);

    	//截取数据
		String str_hex_data=content.substring(header_device_len+header_option_len+header_data_len,data_len);
		DataResultWeather weater_data=new DataResultWeather();
		List<DataResultInfo> DataList=weater_data.DataList;
    	System.out.println(TAG+"====interpret=====16*content:"+str_hex_data);

    	//循环匹配天气数据
		for(int i=0;i<DataList.size();i++) {
			DataResultInfo info=DataList.get(i);
			String str_weather_hex_data=str_hex_data.substring(info.index,info.index+info.len);
        	System.out.println(TAG+"====interpret=====["+info.index+","+info.len+"]16*str_data:"+str_weather_hex_data);
			if(!str_hex_data.trim().equals(""))
			{
				String str_weather_data=Integer.valueOf(str_weather_hex_data,16).toString();
	        	System.out.println(TAG+"====interpret=====10*str_data-"+info.name+":"+str_weather_data);
	        	info.data=str_weather_data;
	        	info.data_hex=str_weather_hex_data;
	        	DataList.remove(i);
	        	DataList.add(i, info);
			}


		}
		if(event!=null && DataList!=null && DataList.size()>0) {
			event.onExpressionData(DataList);
		}
		
		return "";
	}
	
	protected void getChildData(String context, int len) {
		// TODO Auto-generated method stub
		
	}

	
}
