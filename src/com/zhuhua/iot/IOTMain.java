package com.zhuhua.iot;

import com.zhuhua.iot.server.SocketTCPServer;

public class IOTMain {

	public static void main(String[] args) {
		SocketTCPServer tcp_server=new SocketTCPServer();
		tcp_server.start();
	}
}
