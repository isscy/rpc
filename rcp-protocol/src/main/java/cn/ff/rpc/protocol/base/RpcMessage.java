package cn.ff.rpc.protocol.base;

import lombok.Data;

import java.io.Serializable;

/**
 * 基础消息类
 */
@Data
public class RpcMessage implements Serializable {
    /*
     * 是否单向发送
     */
    private boolean oneway;
    /*
     * 是否异步调用
     */
    private boolean async;

}
