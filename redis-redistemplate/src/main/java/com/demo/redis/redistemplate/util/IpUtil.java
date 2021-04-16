package com.demo.redis.redistemplate.util;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.List;

/**
 * author pczhao
 * date  2019-12-31 17:59
 */

@Slf4j
public class IpUtil {

    public static String getLocalIpAddr() {
        try {
            // 遍历所有的网络接口
            List<String> addrList = getLocalIpAddrList();
            for (String addr : addrList) {
                // 排除docker内部ip
                if (!addr.startsWith("172.17")) {
                    return addr;
                }
            }
            // 如果没有发现 non-loopback地址.只能用最次选的方案
            InetAddress jdkSuppliedAddress = InetAddress.getLocalHost();
            return jdkSuppliedAddress.getHostAddress();
        } catch (Exception e) {
            log.warn("get local IP addr error!", e);
        }
        return null;
    }

    public static List<String> getLocalIpAddrList() {
        List<String> ipList = new ArrayList<String>();
        try {
            // 遍历所有的网络接口
            for (Enumeration<NetworkInterface> ifaces = NetworkInterface.getNetworkInterfaces(); ifaces
                    .hasMoreElements(); ) {
                NetworkInterface iface = ifaces.nextElement();
                // 在所有的接口下再遍历IP
                for (Enumeration<InetAddress> inetAddrs = iface.getInetAddresses(); inetAddrs.hasMoreElements(); ) {
                    InetAddress inetAddr = inetAddrs.nextElement();
                    if (!inetAddr.isLoopbackAddress()) {// 排除本地回送ip
                        ipList.add(inetAddr.getHostAddress());
                    }
                }
            }
        } catch (Exception e) {
            log.warn("get local IP addr list error!", e);
        }
        return ipList;
    }

    public static String getIpAddr(HttpServletRequest request) {
        if (null == request) {
            log.warn("get IP addr request is null");
            return null;
        }
        try {
            // 对于通过多个代理的情况，第一个非unknown的有效IP字符串为客户端真实IP,多个IP按照','分割
            List<String> ipArrs = getIpAddrList(request);
            if (null == ipArrs) {
                return null;
            }
            for (String ipArr : ipArrs) {
                if (!StringUtils.isBlank(ipArr) && !StringUtils.equalsIgnoreCase("unknown", ipArr)) {
                    return ipArr;
                }
            }
        } catch (Exception e) {
            log.warn("get IP addr error!", e);
        }
        return null;
    }

    public static List<String> getIpAddrList(HttpServletRequest request) {
        if (null == request) {
            log.warn("get IP addr list request is null");
            return null;
        }
        try {
            String ipAddress = request.getHeader("X-Forwarded-For");
            if (StringUtils.isBlank(ipAddress) || StringUtils.equalsIgnoreCase("unknown", ipAddress)) {
                ipAddress = request.getHeader("Proxy-Client-IP");
            }
            if (StringUtils.isBlank(ipAddress) || StringUtils.equalsIgnoreCase("unknown", ipAddress)) {
                ipAddress = request.getHeader("WL-Proxy-Client-IP");
            }
            if (StringUtils.isBlank(ipAddress) || StringUtils.equalsIgnoreCase("unknown", ipAddress)) {
                ipAddress = request.getRemoteAddr();
                if (StringUtils.equals(ipAddress, "127.0.0.1") || StringUtils.equals(ipAddress, "0:0:0:0:0:0:0:1")) {
                    // 根据网卡取本机配置的IP
                    InetAddress inet = null;
                    try {
                        inet = InetAddress.getLocalHost();
                    } catch (UnknownHostException e) {
                        log.warn(e.getMessage(), e);
                        return null;
                    }
                    ipAddress = inet.getHostAddress();
                }
            }
            return Arrays.asList(StringUtils.split(ipAddress, ","));
        } catch (Exception e) {
            log.warn("get IP addr list error!", e);
        }
        return null;
    }
}
