package com.krry.util;

import javax.servlet.http.HttpServletRequest;
import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * date: 2020/1/2 13:05 <br>
 * author: Administrator <br>
 */
public class HttpServletRequestUtil {

    private static HttpServletRequestUtil instance;

    private HttpServletRequestUtil() {
    }

    public static HttpServletRequestUtil getInstance() {
        if (instance == null) {
            instance = new HttpServletRequestUtil();
        }
        return instance;
    }

    /**
     * 获取请求的ip地址
     * @param request
     * @return
     */
//    public String getIpAddr(HttpServletRequest request) {
//	String ip = request.getHeader("x-forwarded-for");
//
//	if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
//	    ip = request.getHeader("Proxy-Client-IP");
//	}
//	if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
//	    ip = request.getHeader("WL-Proxy-Client-IP");
//	}
//	if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
//	    ip = request.getRemoteAddr();
//	}
//	// 多次代理时取第一个ip
//	if (ip != null) {
//	    ip = ip.split(",")[0];
//	}
//	if("0:0:0:0:0:0:0:1".equals(ip)){
//		ip = "127.0.0.1";
//	}
//	return ip;
//    }

    public String getIpAddr(HttpServletRequest request){
        String ipAddress = request.getHeader("x-forwarded-for");
        if(ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
            ipAddress = request.getHeader("Proxy-Client-IP");
        }
        if(ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
            ipAddress = request.getHeader("WL-Proxy-Client-IP");
        }
        if(ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
            ipAddress = request.getRemoteAddr();
            if(ipAddress.equals("127.0.0.1") || ipAddress.equals("0:0:0:0:0:0:0:1")){
                //根据网卡取本机配置的IP
                InetAddress inet=null;
                try {
                    inet = InetAddress.getLocalHost();
                } catch (UnknownHostException e) {
                    e.printStackTrace();
                }
                ipAddress= inet.getHostAddress();
            }
        }
        //对于通过多个代理的情况，第一个IP为客户端真实IP,多个IP按照','分割
        if(ipAddress!=null && ipAddress.length()>15){ //"***.***.***.***".length() = 15
            if(ipAddress.indexOf(",")>0){
                ipAddress = ipAddress.substring(0,ipAddress.indexOf(","));
            }
        }
        return ipAddress;
    }
}
