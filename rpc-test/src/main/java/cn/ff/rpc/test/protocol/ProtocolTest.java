package cn.ff.rpc.test.protocol;

import cn.ff.rpc.protocol.base.RpcHeader;
import cn.ff.rpc.protocol.base.RpcHeaderFactory;
import cn.ff.rpc.protocol.base.RpcProtocol;
import cn.ff.rpc.protocol.base.RpcRequest;
import lombok.extern.slf4j.Slf4j;
@Slf4j
public class ProtocolTest {


    public static RpcProtocol<RpcRequest> getRpcProtocol(){
        RpcHeader header = RpcHeaderFactory.getRequestHeader("jdk");
        RpcRequest body = new RpcRequest();
        body.setOneway(false);
        body.setAsync(false);
        body.setClassName("cn.ff.rpc.protocol.base.RpcProtocol");
        body.setMethodName("hello");
        body.setGroup("demo");
        body.setParameters(new Object[]{"demo"});
        body.setParameterTypes(new Class[]{String.class});
        body.setVersion("1.0.0");
        RpcProtocol<RpcRequest> protocol = new RpcProtocol<>();
        protocol.setBody(body);
        protocol.setHeader(header);
        return protocol;
    }


    public static void main(String[] args) {
        RpcProtocol<RpcRequest> protocol = getRpcProtocol();
        log.info(protocol.toString());
    }
}
