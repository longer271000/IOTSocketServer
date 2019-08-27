package com.zhuhua.iot.parser;

import java.util.List;

import com.zhuhua.iot.config.dictionary.DataResultInfo;
import com.zhuhua.iot.parser.weather.DataWeatherParser;

public class DataParser implements IParserExpression{

	static DataParser instance;
	IDataParserEvent event;
	public static DataParser getInstance() {
		if(instance==null) {
			instance=new DataParser();
			
		}
		return instance;
	}

	@Override
	public String interpret(String context) {
		// TODO Auto-generated method stub
		
		IParserExpression parser=new DataWeatherParser();
		parser.onParserDatatEvent(event);
		parser.interpret(context);
		return null;
	}

	@Override
	public void onParserDatatEvent(IDataParserEvent event) {
		// TODO Auto-generated method stub
		this.event=event;
	}
	

}
