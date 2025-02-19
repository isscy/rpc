package cn.ff.rpc.consumer.common.initializer;

import cn.ff.rpc.consumer.common.handler.RpcConsumerHandler;
import cn.ff.rpc.protocol.codec.RpcDecoder;
import cn.ff.rpc.protocol.codec.RpcEncoder;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;

public class RpcConsumerInitializer  extends ChannelInitializer<SocketChannel> {


    @Override
    protected void initChannel(SocketChannel channel) throws Exception {
        ChannelPipeline cp = channel.pipeline();
        cp.addLast(new RpcEncoder());
        cp.addLast(new RpcDecoder());
        cp.addLast(new RpcConsumerHandler());
    }
}
