package com.zhuhua.iot.server.listener;

public interface IHeDaSocketListener {

	public void runListener();
	public void start();

	public void send(String data);
	public void setOperaterEvent(HeDaSocketOperateEvent _operaterEvent) ;
	public void setDataEvent(IHeDaSocketDataEvent _dataEvent);
}
