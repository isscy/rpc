package cn.ff.rpc.consumer.common.handler;

import cn.ff.rpc.protocol.base.RpcProtocol;
import cn.ff.rpc.protocol.base.RpcRequest;
import cn.ff.rpc.protocol.base.RpcResponse;
import com.alibaba.fastjson.JSONObject;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import io.netty.channel.Channel;

import java.net.SocketAddress;

@Slf4j
public class RpcConsumerHandler extends SimpleChannelInboundHandler<RpcProtocol<RpcResponse>> {
    @Getter
    private volatile Channel channel;
    @Getter
    private SocketAddress remotePeer;

    /**
     * Netty激活连接
     */
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        super.channelActive(ctx);
        this.remotePeer = this.channel.remoteAddress();
    }

    /**
     * Netty注册连接
     */
    @Override
    public void channelRegistered(ChannelHandlerContext ctx) throws Exception {
        super.channelRegistered(ctx);
        this.channel = ctx.channel();
    }

    /**
     * Netty接收数据
     */
    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, RpcProtocol<RpcResponse> protocol) throws Exception {
        log.info("服务消费者接收到的数据===>>>{}", JSONObject.toJSONString(protocol));
    }

    /**
     * 服务消费者向服务提供者发送请求
     */
    public void sendRequest(RpcProtocol<RpcRequest> protocol) {
        log.info("服务消费者发送的数据===>>>{}", JSONObject.toJSONString(protocol));
        channel.writeAndFlush(protocol);
    }

    public void close() {
        channel.writeAndFlush(Unpooled.EMPTY_BUFFER).addListener(ChannelFutureListener.CLOSE);
    }

}
