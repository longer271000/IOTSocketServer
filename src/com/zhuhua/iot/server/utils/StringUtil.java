package com.zhuhua.iot.server.utils;

public class StringUtil {

	/**
     * 16���Ʊ�ʾ���ַ���ת��Ϊ�ֽ�����
     *
     * @param hexString 16���Ʊ�ʾ���ַ���
     * @return byte[] �ֽ�����
     */
    public static byte[] hexStringToByteArray(String hexString) {
        hexString = hexString.replaceAll(" ", "");
        int len = hexString.length();
        byte[] bytes = new byte[len / 2];
        for (int i = 0; i < len; i += 2) {
            // ��λһ�飬��ʾһ���ֽ�,��������ʾ��16�����ַ�������ԭ��һ���ֽ�
            bytes[i / 2] = (byte) ((Character.digit(hexString.charAt(i), 16) << 4) + Character
                    .digit(hexString.charAt(i + 1), 16));
        }
        return bytes;
    }
    /**
     * byte[]����ת��Ϊ16���Ƶ��ַ���
     *
     * @param bytes Ҫת�����ֽ�����
     * @return ת����Ľ��
     */
    public static String bytesToHexString(byte[] bytes) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < bytes.length; i++) {
            String hex = Integer.toHexString(0xFF & bytes[i]);
            if (hex.length() == 1) {
                sb.append('0');
            }
            sb.append(hex);
        }
        return sb.toString();
    }
    public static String bytesToHexString2(byte[] src){  
        StringBuilder stringBuilder = new StringBuilder("");  
        if (src == null || src.length <= 0) {  
            return null;  
        }  
        for (int i = 0; i < src.length; i++) {  
            int v = src[i] & 0xFF;  
            String hv = Integer.toHexString(v);  
            if (hv.length() < 2) {  
                stringBuilder.append(0);  
            }  
            stringBuilder.append(hv);  
        }  
        return stringBuilder.toString();  
    } 

    public static String bytes2HexString(byte[] b) {
	    String ret = "";
	    for (int i = 0; i < b.length; i++) {
	    String hex = Integer.toHexString(b[i] & 0xFF);
	    if (hex.length() == 1) {
	    	hex = "0" + hex;
	    }
	    	ret += hex;
	    }
	    return ret;
    }


    public static String toHex(byte[] buffer) {
	    StringBuffer sb = new StringBuffer(buffer.length * 2);
	    for (int i = 0; i < buffer.length; i++) {
		    sb.append(Character.forDigit((buffer[i] & 240) >> 4, 16));
		    sb.append(Character.forDigit(buffer[i] & 15, 16));
	    }
	    return sb.toString();
    }
}
