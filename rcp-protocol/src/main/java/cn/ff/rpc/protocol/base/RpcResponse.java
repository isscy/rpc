package cn.ff.rpc.protocol.base;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serial;

/**
 * 响应消息类: RPC的响应类，对应的请求id在响应头中
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class RpcResponse extends RpcMessage {

    @Serial
    private static final long serialVersionUID = 10086L;
    private String error;
    private Object result;
}
