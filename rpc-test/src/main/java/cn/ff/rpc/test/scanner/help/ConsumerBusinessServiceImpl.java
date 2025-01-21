package cn.ff.rpc.test.scanner.help;

import cn.ff.rpc.annotation.RpcReference;

public class ConsumerBusinessServiceImpl implements ConsumerBusinessService {

    @RpcReference(registryType = "zookeeper", registryAddress = "127.0.0.1:2181", version = "1.0.0", group = "demo")
    private DemoService demoService;

}
