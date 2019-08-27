package com.zhuhua.iot.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketAddress;
import java.util.List;

import com.zhuhua.iot.config.dictionary.DataResultInfo;
import com.zhuhua.iot.db.dal.WeatherDal;
import com.zhuhua.iot.parser.DataParser;
import com.zhuhua.iot.parser.IDataParserEvent;
import com.zhuhua.iot.server.cache.DataSocketCache;
import com.zhuhua.iot.server.listener.HeDaSocketListener;
import com.zhuhua.iot.server.listener.HeDaSocketOperateEvent;
import com.zhuhua.iot.server.listener.IHeDaSocketDataEvent;
import com.zhuhua.iot.server.listener.IHeDaSocketListener;
import com.zhuhua.iot.server.model.HeDaSocket;
/****
 * TCP socket 主服务监听连接
 * ***/
public class SocketTCPServer {
	
    ServerSocket server=null;
	Thread server_thread;
	boolean isRun=true;
    public void start() {
    	try {
        	System.out.println("====start=====");

    		initSocket();
    		server_thread.start();
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    
    
    private void initSocket(){
    	  try{
              //创建一个ServerSocket在端口4700监听客户请求
          	System.out.println("====initSocket=====");

          	//监听8888端口号
              server=new ServerSocket(8888);
              server_thread=new Thread(new Runnable() {

				@Override
				public void run() {
					// TODO Auto-generated method stub
					runSocketListener();
				}
            	  
              });
          }catch(Exception e){
              e.printStackTrace();//出错，打印出错信息
          }
        

    }
    private void runSocketListener() {
  		 while(isRun){  
			 try {
				 Socket socket=null;
	            	System.out.println("====run===1==");

	              //使用accept()阻塞等待客户请求，有客户
	              socket=server.accept();//请求到来则产生一个Socket对象，并继续执行
	            	System.out.println("====run===2==");
	              	
	            //监听客户端连接，创建hedasocket 
            	HeDaSocket hedaSocket=new HeDaSocket();
            	hedaSocket.set_name("socket-"+DataSocketCache.getSize()+1);
            	hedaSocket.set_socket(socket);
            	IHeDaSocketListener hedaSocketListener=new HeDaSocketListener(hedaSocket);
            	hedaSocketListener.setOperaterEvent(new HeDaSocketOperateEvent() {

					@Override
					public void close(String name) {
						// TODO Auto-generated method stub
						//socket连接异常断开连接，清除所在socket
						DataSocketCache.remove(name);
					}
            		
            	});
            	hedaSocketListener.setDataEvent(new IHeDaSocketDataEvent() {

					@Override
					public void receiveData(String data) {
						// TODO Auto-generated method stub
						//获取socket接受数据

						DataParser.getInstance().onParserDatatEvent(new IDataParserEvent() {

							@Override
							public void onExpressionData(List<DataResultInfo> dataList) {
								// TODO Auto-generated method stub
								WeatherDal.getInstance().save(dataList);
							}
							
							
						});
						DataParser.getInstance().interpret(data);

					}
            		
            		
            	});
            	//开始执行与客户的握手接受数据
            	hedaSocketListener.start();
            	//加入socket池管理
            	DataSocketCache.add(hedaSocketListener);

			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		 }
    }
  
    public void close() {
        try {
        	isRun=false;
			server.close();
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} //关闭ServerSocket

    }
}
