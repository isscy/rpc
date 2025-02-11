package cn.ff.rpc.test.provider;

import cn.ff.rpc.provider.RpcSingleServer;
import org.junit.Test;

public class RpcSingleServerTest {


    /**
     * 测试：启动服务提供者
     */
    @Test
    public void startRpcSingleServer(){
        RpcSingleServer singleServer = new RpcSingleServer("127.0.0.1:27880", "cn.ff.rpc.test.provider", "cglib");
        singleServer.startNettyServer();
    }
}
