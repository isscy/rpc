package cn.ff.rpc.test.consumer;

import cn.ff.rpc.consumer.common.RpcConsumer;
import cn.ff.rpc.protocol.base.RpcHeaderFactory;
import cn.ff.rpc.protocol.base.RpcProtocol;
import cn.ff.rpc.protocol.base.RpcRequest;

public class RpcConsumerHandlerTest {


    public static void main(String[] args) throws Exception {
        RpcConsumer consumer = RpcConsumer.getInstance();
        consumer.sendRequest(getRpcRequestProtocol());
        Thread.sleep(2000);
        consumer.close();
    }

    private static RpcProtocol<RpcRequest> getRpcRequestProtocol() {
        //模拟发送数据
        RpcProtocol<RpcRequest> protocol = new RpcProtocol<RpcRequest>();
        protocol.setHeader(RpcHeaderFactory.getRequestHeader("jdk"));
        RpcRequest request = new RpcRequest();
        request.setClassName("cn.ff.rpc.test.provider.help.DemoService");
        request.setGroup("demo");
        request.setMethodName("hello");
        request.setParameters(new Object[]{"demo"});
        request.setParameterTypes(new Class[]{String.class});
        request.setVersion("1.0.0");
        request.setAsync(false);
        request.setOneway(false);
        protocol.setBody(request);
        return protocol;
    }
}
