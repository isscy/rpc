package cn.ff.rpc.protocol.base;

import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

/**
 * Rpc协议：RpcProtocol就是最终定义的协议类
 * 实际上传输的数据就是RpcProtocol的类对象序列化后的二进制流。
 */
@Data
public class RpcProtocol<T> implements Serializable {
    @Serial
    private static final long serialVersionUID = 10086L;


    /**
     * 消息头
     */
    private RpcHeader header;
    /**
     * 消息体
     */
    private T body;


}
