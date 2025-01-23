package cn.ff.rpc.protocol.base;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serial;

/**
 * 请求消息类: Rpc请求封装类，对应的请求id在消息头中
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class RpcRequest extends RpcMessage {
    @Serial
    private static final long serialVersionUID = 10086L;

    /**
     * 类名称
     */
    private String className;
    /**
     * 方法名称
     */
    private String methodName;
    /**
     * 参数类型数组
     */
    private Class<?>[] parameterTypes;
    /**
     * 参数数组
     */
    private Object[] parameters;
    /**
     * 版本号
     */
    private String version;
    /**
     * 服务分组
     */
    private String group;
}
