package cn.ff.rpc.test.scanner;

import cn.ff.rpc.common.scanner.ClassScanner;
import cn.ff.rpc.common.scanner.reference.RpcReferenceScanner;
import cn.ff.rpc.common.scanner.server.RpcServiceScanner;
import org.junit.Test;

import java.util.List;

public class ScannerTest {

    /**
     * 扫描本包下所有类
     */
    @Test
    public void testScannerClassNameList() throws Exception {
        List<String> classNameList = ClassScanner.getClassNameList("cn.ff.rpc.test.scanner", true);
        classNameList.forEach(System.out::println);
        /*
         * 运行上述代码，输出的结果信息如下所示：
         * cn.ff.rpc.test.scanner.help.ConsumerBusinessService
         * cn.ff.rpc.test.scanner.help.ConsumerBusinessServiceImpl
         * cn.ff.rpc.test.scanner.help.DemoService
         * cn.ff.rpc.test.scanner.help.ProviderDemoServiceImpl
         * cn.ff.rpc.test.scanner.ScannerTest
         */
    }

    /**
     * 扫描本包下所有标注了@RpcService注解的类
     */
    @Test
    public void testScannerClassNameListByRpcService() throws Exception {
        RpcServiceScanner.doScannerWithRpcServiceAnnotationFilterAndRegistryService("cn.ff.rpc.test.scanner");
        /*
         *  运行上述代码，输出的结果信息如下所示：
         * RpcServiceScanner:36 - 当前标注了@RpcService注解的类实例名称===>>> cn.ff.rpc.test.scanner.help.ProviderDemoServiceImpl
         * RpcServiceScanner:37 - @RpcService注解上标注的属性信息如下：
         * RpcServiceScanner:38 - interfaceClass===>>> cn.ff.rpc.test.scanner.help.DemoService
         * RpcServiceScanner:39 - interfaceClassName===>>> cn.ff.rpc.test.scanner.help.DemoService
         * RpcServiceScanner:40 - version===>>> 1.0.0
         * RpcServiceScanner:41 - group===>>> demo
         */
    }

    /**
     * 扫描本包下所有标注了@RpcReference注解的类
     */
    @Test
    public void testScannerClassNameListByRpcReference() throws Exception {
        RpcReferenceScanner.doScannerWithRpcReferenceAnnotationFilter("cn.ff.rpc.test.scanner");
        /*
         * 运行上述代码，输出的结果信息如下所示：
         * RpcReferenceScanner:41 - 当前标注了@RpcReference注解的字段名称===>>> demoService
         * RpcReferenceScanner:42 - @RpcReference注解上标注的属性信息如下：
         * RpcReferenceScanner:43 - version===>>> 1.0.0
         * RpcReferenceScanner:44 - group===>>> demo
         * RpcReferenceScanner:45 - registryType===>>> zookeeper
         * RpcReferenceScanner:46 - registryAddress===>>> 127.0.0.1:2181
         */
    }
}
