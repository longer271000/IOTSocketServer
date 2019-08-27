package com.zhuhua.iot.server.model;

import java.io.Serializable;
import java.net.Socket;

/***
 * 自定义Socket对象
 * **/
public class HeDaSocket implements Serializable {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	String _name;
	Socket _socket;
	public String get_name() {
		return _name;
	}
	public void set_name(String _name) {
		this._name = _name;
	}
	public Socket get_socket() {
		return _socket;
	}
	public void set_socket(Socket _socket) {
		this._socket = _socket;
	}
	
	
}
