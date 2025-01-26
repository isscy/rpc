package cn.ff.rpc.protocol.codec;

import cn.ff.rpc.protocol.serialization.JdkSerialization;
import cn.ff.rpc.protocol.serialization.Serialization;

/**
 * 实现编解码的接口，提供序列化和反序列化的默认方法
 */
public interface RpcCodec {

    default Serialization getJdkSerialization(){
        return new JdkSerialization();
    }

}
