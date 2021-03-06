package com.wankrfun.crania.http.retrofit;

import androidx.annotation.NonNull;

import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Dns;

/**
 * @ProjectName: Wankrfun
 * @Package: com.wankrfun.crania.http.retrofit
 * @ClassName: ApiDns
 * @Description: 调换集合中ipv4 ipv6位置，将ipv4当到集合首位
 * @Author: 鹿鸿祥
 * @CreateDate: 1/7/21 10:12 AM
 * @UpdateUser: 更新者
 * @UpdateDate: 1/7/21 10:12 AM
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
public class ApiDns implements Dns {
    @Override
    public List<InetAddress> lookup(@NonNull String hostname) throws UnknownHostException {
        if (hostname == null) {
            throw new UnknownHostException("hostname == null");
        } else {
            try {
                List<InetAddress> mInetAddressesList = new ArrayList<>();
                InetAddress[] mInetAddresses = InetAddress.getAllByName(hostname);
                for (InetAddress address : mInetAddresses) {
                    if (address instanceof Inet4Address) {
                        mInetAddressesList.add(0, address);
                    } else {
                        mInetAddressesList.add(address);
                    }
                }
                return mInetAddressesList;
            } catch (NullPointerException var4) {
                UnknownHostException unknownHostException = new UnknownHostException("Broken system behaviour");
                unknownHostException.initCause(var4);
                throw unknownHostException;
            }
        }
    }
}
