package com.shine.leaf.server.node;

import com.shine.commons.utils.Exceptions;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.UnknownHostException;

/**
 * @author fuyongde
 * 获取硬件信息
 */
public class HardwareUtils {

    public static String getMacAddress() {
        //获得网络接口对象（即网卡），并得到mac地址，mac地址存在于一个byte数组中。
        byte[] mac = getMac();

        //下面代码是把mac地址拼装成String
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < mac.length; i++) {
            if (i != 0) {
                sb.append("-");
            }
            //mac[i] & 0xFF 是为了把byte转化为正整数
            String s = Integer.toHexString(mac[i] & 0xFF);
            sb.append(s.length() == 1 ? 0 + s : s);
        }

        //把字符串所有小写字母改为大写成为正规的mac地址并返回
        return sb.toString().toUpperCase();
    }

    private static byte[] getMac() {
        //获得网络接口对象（即网卡），并得到mac地址，mac地址存在于一个byte数组中。
        byte[] mac;
        try {
            InetAddress ia = InetAddress.getLocalHost();
            mac = NetworkInterface.getByInetAddress(ia).getHardwareAddress();
        } catch (UnknownHostException | SocketException e) {
            throw Exceptions.unchecked(e);
        }
        return mac;
    }
}
