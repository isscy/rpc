package cn.ff.rpc.protocol.serialization;

public interface Serialization {


    /**
     * 序列化
     */
    <T> byte[] serialize(T obj);


    /**
     * 反序列化
     */
    <T> T deserialize(byte[] data, Class<T> cls);
}
