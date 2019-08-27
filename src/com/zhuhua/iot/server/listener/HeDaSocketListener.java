package com.zhuhua.iot.server.listener;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

import com.zhuhua.iot.server.model.HeDaSocket;
import com.zhuhua.iot.server.utils.StringUtil;

public class HeDaSocketListener implements IHeDaSocketListener {

	
	  Socket socket=null;
	  HeDaSocket hedaSocket;
		Thread socket_thread;
		Thread socket_send_thread;

		OutputStream os ;
		boolean isRun=true;
		public String Name;
		String TAG="HeDaSocketListener";
		
		private HeDaSocketOperateEvent _operaterEvent;
		private IHeDaSocketDataEvent _dataEvent;
		public HeDaSocketOperateEvent getOperaterEvent() {
			return _operaterEvent;
		}

		public void setOperaterEvent(HeDaSocketOperateEvent _operaterEvent) {
			this._operaterEvent = _operaterEvent;
		}

		public IHeDaSocketDataEvent getDataEvent() {
			return _dataEvent;
		}

		public void setDataEvent(IHeDaSocketDataEvent _dataEvent) {
			this._dataEvent = _dataEvent;
		}

		public HeDaSocketListener( HeDaSocket hedaSocket) {
			this.hedaSocket=hedaSocket;
			this.Name=this.hedaSocket.get_name();
			socket=this.hedaSocket.get_socket();
			init();
		}
		
	private void init() {
		socket_thread=new Thread(new Runnable() {

			@Override
			public void run() {
				// TODO Auto-generated method stub
				runListener();
			}
			
			
		});
		socket_send_thread=new Thread(new Runnable() {

			@Override
			public void run() {
				// TODO Auto-generated method stub
				while(isRun) {
					try {
						Thread.sleep(5000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
					send("01 03 00 00 00 10 44 06");

				}
			}
			
			
		});
    	System.out.println(TAG+"====init=====");

	}
	public void start() {
    	System.out.println(TAG+"====start=====");

		if(socket_thread!=null &&!socket_thread.isAlive()) {
			socket_thread.start();
		}
		
		if(socket_send_thread!=null &&!socket_send_thread.isAlive()) {
			socket_send_thread.start();
		}
	}
	@Override
	public void runListener() {
		// TODO Auto-generated method stub
 		 while(isRun){  
			 try {
				listener();
            	System.out.println(TAG+"====runListener=====");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		 }
	}

	@Override
	public void send(String data) {
		// TODO Auto-generated method stub
    	if(socket!=null &&socket.isConnected()) {
   		 try {
   			 	//ͨ��socket������Ի�������������д����  
   			 	if(socket.isClosed())return;
   			 	os = socket.getOutputStream();  
   	        	System.out.println(TAG+"====listener=====isOutputShutdown:"+socket.isOutputShutdown());

   			  // ��ͻ��˷�����Ϣ  
   	         byte[] bytes =StringUtil.hexStringToByteArray(data);
				os.write(bytes);
            	System.out.println(TAG+"====send====="+data);
               

			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}  
    	}
	}	
	
	  
    private void listener() throws IOException {
          	String line;
        	System.out.println(TAG+"====listener=====");
            //�ڷ���������ʾ���ӵ��ϵĵ��ԡ�  
        	if(socket==null) {
            	System.out.println(TAG+"====listener=====is null:");
            	return;
        	}
        	System.out.println(TAG+"====listener=====isClosed:"+socket.isClosed());
        	System.out.println(TAG+"====listener=====isConnected:"+socket.isConnected());

        	if(socket.isClosed()) {
            	close();
            	return;
        	}
        	if(socket.isConnected())
        	{
        		

	            System.out.println(socket.getInetAddress().getHostAddress()+"�������ˣ�");  
	            //ͨ��socket������Ի����������������ȡ�û�����  
	            InputStream is=socket.getInputStream();  
	        	System.out.println(TAG+"====listener=====isInputShutdown:"+socket.isInputShutdown());
	        	System.out.println(TAG+"====listener=====InputStream-is:"+is.available());

	            //��ȡ����  
	            int len=0;  
	            byte[] buf=new byte[1024];  
	            try
	            {
		            while ((len=is.read(buf))!=-1) {  
		                //ֱ�Ӱѻ�õ����ݴ�ӡ����  
		                System.out.println("���������յ��ͻ��˵����ݣ�"+new String(buf,0,len));  
		                socket_event(buf);
		            }  	
	            }catch(Exception ext){
	            	ext.printStackTrace();
	            	if(ext.toString().contains("Connection reset")) {
		            	close();
	            	}
	            }

	            if(os!=null)
	            os.close(); //�ر�Socket�����
	          is.close(); //�ر�Socket������
        	} 
    }
    private void socket_event(byte[] data_btye) {

    	//System.out.println(TAG+"====socket_event=1===="+data_btye);
    	try
    	{
        	String s=StringUtil.bytesToHexString(data_btye);
        	System.out.println(TAG+"====socket_event=2====");
        	if(_dataEvent!=null) {
        		_dataEvent.receiveData(s);
        	}


    	}catch(Exception ext) {
    		ext.printStackTrace();
    	}
      
    }
    public void close() {
    	isRun=false;
        try {
        	
        	socket_thread.join();
        	socket_send_thread.join();
        	socket.close(); //�ر�Socket
        	hedaSocket.set_socket(null);
        	System.out.println(TAG+"====close=====");
        	if(_operaterEvent!=null) {
        		_operaterEvent.close(this.Name);
        	}
           
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} //�ر�ServerSocket

    }
}
