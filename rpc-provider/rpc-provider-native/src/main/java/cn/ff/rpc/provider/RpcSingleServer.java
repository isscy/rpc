package cn.ff.rpc.provider;

import cn.ff.rpc.common.scanner.server.RpcServiceScanner;
import cn.ff.rpc.provider.common.server.base.BaseServer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 以Java原生方式启动启动Rpc
 */
public class RpcSingleServer extends BaseServer {
    private final Logger logger = LoggerFactory.getLogger(RpcSingleServer.class);


    public RpcSingleServer(String serverAddress, String scanPackage) {
        //调用父类构造方法
        super(serverAddress);
        try {
            this.handlerMap = RpcServiceScanner.doScannerWithRpcServiceAnnotationFilterAndRegistryService(scanPackage);
        } catch (Exception e) {
            logger.error("RPC Server init error", e);
        }
    }


}
