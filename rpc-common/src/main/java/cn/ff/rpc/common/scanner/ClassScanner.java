package cn.ff.rpc.common.scanner;

import java.io.File;
import java.io.IOException;
import java.net.JarURLConnection;
import java.net.URL;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

/**
 * 类扫描器：扫描当前工程下的类信息
 */
public class ClassScanner {


    /**
     * 文件： 扫描当前工程中指定包下的所有类信息
     */
    private static final String PROTOCOL_FILE = "file";
    /**
     * jar包： 扫描Jar文件中指定包下的所有类信息
     */
    private static final String PROTOCOL_JAR = "jar";
    /**
     * class文件的后缀： 扫描的过程中指定需要处理的文件的后缀信息
     */
    private static final String CLASS_FILE_SUFFIX = ".class";


    /**
     * 扫描指定包下的所有类信息
     *
     * @param packageName 指定的包名
     * @param recursive   是否递归扫描，true:是  false:否
     * @return 指定包下所有的完整类名的List集合
     */
    public static List<String> getClassNameList(String packageName, boolean recursive) throws Exception {
        List<String> classNameList = new ArrayList<String>(); //第一个class类的集合
        String packageDirName = packageName.replace('.', '/'); //获取包的名字 并进行替换
        //定义一个枚举的集合 并进行循环来处理这个目录下的things
        Enumeration<URL> dirs = Thread.currentThread().getContextClassLoader().getResources(packageDirName);
        while (dirs.hasMoreElements()) { //循环迭代下去
            URL url = dirs.nextElement(); //获取下一个元素
            String protocol = url.getProtocol(); //得到协议的名称
            if (PROTOCOL_FILE.equals(protocol)) { //如果是以文件的形式保存在服务器上
                String filePath = URLDecoder.decode(url.getFile(), StandardCharsets.UTF_8); //获取包的物理路径
                //以文件的方式扫描整个包下的文件 并添加到集合中
                findAndAddClassesInPackageByFile(packageName, filePath, recursive, classNameList);
            } else if (PROTOCOL_JAR.equals(protocol)) {
                packageName = findAndAddClassesInPackageByJar(packageName, classNameList, recursive, packageDirName, url);
            }
        }
        return classNameList;
    }


    /**
     * 扫描Jar文件中指定包下的所有类信息
     *
     * @param packageName    扫描的包名
     * @param classNameList  完成类名存放的List集合
     * @param recursive      是否递归调用
     * @param packageDirName 当前包名的前面部分的名称
     * @param url            包的url地址
     * @return 处理后的包名，以供下次调用使用
     */
    private static String findAndAddClassesInPackageByJar(String packageName, List<String> classNameList, boolean recursive, String packageDirName, URL url) throws IOException {
        JarFile jar = ((JarURLConnection) (url.openConnection())).getJarFile();
        Enumeration<JarEntry> entries = jar.entries(); // 从此jar包 得到一个枚举类
        while (entries.hasMoreElements()) {
            JarEntry entry = entries.nextElement(); //获取jar里的一个实体： 可能是 目录、META-INF等文件
            String name = entry.getName();
            if (name.charAt(0) == '/') {  // 如果是以 / 开头的
                name = name.substring(1);
            }
            if (name.startsWith(packageDirName)) { //如果前半部分和定义的包名相同
                int idx = name.lastIndexOf('/');
                String currentPackageDir = "";
                if (idx != -1) { //如果以"/"结尾 是一个包
                    currentPackageDir = name.substring(0, idx);
                    packageName = currentPackageDir.replace('/', '.'); //获取包名
                }
                if ((idx != -1 && currentPackageDir.equals(packageDirName)) || recursive) { //如果可以迭代下去 并且是一个包
                    if (name.endsWith(CLASS_FILE_SUFFIX) && !entry.isDirectory()) {//如果是一个.class文件 而且不是目录
                        String className = name.substring(packageName.length() + 1, name.length() - CLASS_FILE_SUFFIX.length()); //去掉后面的".class" 获取真正的类名
                        classNameList.add(packageName + '.' + className);
                    }
                }
            }
        }
        return packageName;
    }


    /**
     * 扫描当前工程中指定包下的所有类信息
     *
     * @param packageName   扫描的包名
     * @param packagePath   包在磁盘上的完整路径
     * @param recursive     是否递归调用
     * @param classNameList 类名称的集合
     */
    private static void findAndAddClassesInPackageByFile(String packageName, String packagePath, final boolean recursive, List<String> classNameList) {
        File dir = new File(packagePath);
        if (!dir.exists() || !dir.isDirectory()) {
            return;
        }
        // 获取包下的所有文件: 以.class结尾的文件，或者当允许递归的时候则也返回子目录
        File[] dirFiles = dir.listFiles(file -> (recursive && file.isDirectory()) || (file.getName().endsWith(CLASS_FILE_SUFFIX)));
        if (dirFiles == null) {
            return;
        }
        for (File file : dirFiles) {
            if (file.isDirectory()) {
                findAndAddClassesInPackageByFile(packageName + "." + file.getName(), file.getAbsolutePath(), recursive, classNameList);
            } else {//如果是java类文件 去掉后面的.class 只留下类名
                String substring = file.getName().substring(0, file.getName().length() - CLASS_FILE_SUFFIX.length());
                classNameList.add(packageName + "." + substring);
            }
        }
    }


}
