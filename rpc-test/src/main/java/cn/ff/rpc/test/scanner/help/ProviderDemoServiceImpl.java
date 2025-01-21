package cn.ff.rpc.test.scanner.help;


import cn.ff.rpc.annotation.RpcService;

@RpcService(interfaceClass = DemoService.class, interfaceClassName = "cn.ff.rpc.test.scanner.help.DemoService", version = "1.0.0", group = "demo")
public class ProviderDemoServiceImpl implements DemoService {


}
