package com.zhuhua.iot.parser;

public abstract class AbstractDataParser {

	public int header_device_len=2;
	public int header_device_index=0;
	public int header_option_len=2;
	public int header_option_index=2;
	public int header_data_len=4;
	public int header_data_index=4;
	public String data;

	public IDataParserEvent event;

	public IDataParserEvent getEvent() {
		return event;
	}

	public void onParserDatatEvent(IDataParserEvent event) {
		this.event = event;
	}
	
	
}
