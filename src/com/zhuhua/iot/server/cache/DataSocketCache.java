package com.zhuhua.iot.server.cache;

import java.util.ArrayList;
import java.util.List;

import com.zhuhua.iot.server.listener.HeDaSocketListener;
import com.zhuhua.iot.server.listener.IHeDaSocketListener;
/****
 * hedasocket ≥ÿπ‹¿Ì
 * 
 * ***/
public class DataSocketCache {

	static List<IHeDaSocketListener> HeDaSocketList=new ArrayList<IHeDaSocketListener>();

	public static void add(IHeDaSocketListener v) {
		HeDaSocketList.add(v);
	}
	public static void remove(String name) {
		int index=0;
		for (IHeDaSocketListener iHeDaSocketListener : HeDaSocketList) {
			index++;
			HeDaSocketListener _hedaSocketListener=(HeDaSocketListener)iHeDaSocketListener;
			if(_hedaSocketListener.Name.equals(name)) {
				_hedaSocketListener=null;
				break;
			}
		}
		HeDaSocketList.remove(index);
	}
	public static int getSize() {
		return HeDaSocketList.size();
	}
}
