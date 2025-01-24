package cn.ff.rpc.common.utils;

import java.util.concurrent.atomic.AtomicLong;

/**
 * 消息id生成
 */
public class IdFactory {

    private final static AtomicLong REQUEST_ID_GEN = new AtomicLong(0);

    public static Long getId(){
        return REQUEST_ID_GEN.incrementAndGet();
    }

}
