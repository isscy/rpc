package cn.ff.rpc.test.consumer;

import cn.ff.rpc.protocol.base.RpcHeaderFactory;
import cn.ff.rpc.protocol.base.RpcProtocol;
import cn.ff.rpc.protocol.base.RpcRequest;
import cn.ff.rpc.protocol.base.RpcResponse;
import com.alibaba.fastjson.JSONObject;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class RpcTestConsumerHandler extends SimpleChannelInboundHandler<RpcProtocol<RpcResponse>> {

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        log.info("发送数据开始...");
        //模拟发送数据
        RpcProtocol<RpcRequest> protocol = new RpcProtocol<>();
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
        log.info("服务消费者发送的数据===>>>{}", JSONObject.toJSONString(protocol));
        ctx.writeAndFlush(protocol);
        log.info("发送数据完毕...");
    }

    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext,
                                RpcProtocol<RpcResponse> protocol) throws Exception {
        log.info("服务消费者接收到的数据===>>>{}", JSONObject.toJSONString(protocol));
    }


}
