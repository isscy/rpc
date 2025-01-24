package cn.ff.rpc.protocol.serialization;

import cn.ff.rpc.common.exception.SerializerException;

import java.io.*;

public class JdkSerialization implements Serialization {


    @Override
    public <T> byte[] serialize(T obj) {
        if (obj == null) {
            throw new SerializerException("待序列化对象为NULL");
        }
        try {
            ByteArrayOutputStream os = new ByteArrayOutputStream();
            ObjectOutputStream out = new ObjectOutputStream(os);
            out.writeObject(obj);
            return os.toByteArray();
        } catch (IOException e) {
            throw new SerializerException(e.getMessage(), e);
        }

    }

    @Override
    public <T> T deserialize(byte[] data, Class<T> cls) {
        if (data == null) {
            throw new SerializerException("待反序列化对象为NULL");
        }
        try {
            ByteArrayInputStream is = new ByteArrayInputStream(data);
            ObjectInputStream in = new ObjectInputStream(is);
            return (T) in.readObject();
        } catch (Exception e) {
            throw new SerializerException(e.getMessage(), e);
        }
    }
}
