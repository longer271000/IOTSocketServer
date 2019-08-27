package com.zhuhua.iot.parser;

import java.util.List;

import com.zhuhua.iot.config.dictionary.DataResultInfo;

public interface IDataParserEvent {

	public void onExpressionData(List<DataResultInfo> dataList);
}
