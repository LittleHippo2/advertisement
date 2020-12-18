package com.haol.advertisement.tools;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * 获取服务器ip地址
 */
public class GetIp {

    public static String getIp() throws UnknownHostException {
        String ip = InetAddress.getLocalHost().getHostAddress();
        return ip;
    }
}
