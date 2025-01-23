package cn.ff.rpc.protocol.base;

import cn.ff.rpc.annotation.RpcConstants;
import cn.ff.rpc.common.scanner.IdFactory;
import cn.ff.rpc.protocol.enumeration.RpcType;

/**
 * 消息头的工厂类
 */
public class RpcHeaderFactory {

    public static RpcHeader getRequestHeader(String serializationType) {
        RpcHeader header = new RpcHeader();
        long requestId = IdFactory.getId();
        header.setMagic(RpcConstants.MAGIC);
        header.setRequestId(requestId);
        header.setMsgType((byte) RpcType.REQUEST.getType());
        header.setStatus((byte) 0x1);
        header.setSerializationType(serializationType);
        return header;
    }
}
