package com.zhuhua.iot.parser;

public interface IParserExpression {
	public String interpret(String context);
	public void onParserDatatEvent(IDataParserEvent event);
}
