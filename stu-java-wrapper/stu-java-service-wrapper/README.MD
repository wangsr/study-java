这个是java service wrapper的实现
http://wrapper.tanukisoftware.com/doc/english/integrate-simple-nix.html

实现方式4种,这里是第一种,最简单的实现,WrapperSimpleApp。

使用wrapper需要使用4个目录(bin、conf、lib、logs)
1、首先解压wrapper的工具包,然后解压,解压的目录称为${WRAPPER_HOME}
2、新建四个目录
bin、conf、lib、logs
3、拷贝

    3.1、
        {WRAPPER_HOME}/bin/wrapper
        {WRAPPER_HOME}/src/bin/sh.script.in
        到bin目录下,sh.script.in 修改为适合的名称(能表示你的应用的名称)
    3.2、
        {WRAPPER_HOME}/lib/libwrapper.so
        {WRAPPER_HOME}/lib/wrapper.jar
        到lib目录下
    3.3、
        {WRAPPER_HOME}/src/conf/wrapper.conf.in
        到conf目录下,更名为wrapper.conf(去掉".in"后缀)
    3.4、
        logs目录无需copy
    
4、修改配置文件

    4.1、配置bin目录的的启动脚本,sh.script.in(重命名为合适的名称)
        设置:APP_NAME, APP_LONG_NAME(应用名称)
        其他可以保持默认,根据实际情况,自行定制
    4.2、配置conf目录先得wrapper.conf文件
        a、设置:java cmd
            wrapper.java.command=%JAVA_HOME%/bin/java
        b、设置:Java Classpath (include wrapper.jar)
            wrapper.java.classpath.1=../lib/wrapper.jar
            wrapper.java.classpath.2=../lib/*.jar
            wrapper.java.classpath.3=../conf/
        c、设置:Java Library Path (location of Wrapper.DLL or libwrapper.so)
            wrapper.java.library.path.1=../lib
        c、设置平台cpu架构
            # Java Bits.  On applicable platforms, tells the JVM to run in 32 or 64-bit mode.
            wrapper.java.additional.auto_bits=TRUE
        d、设置:wrapper.java.additional(JVM settings, Java Arguments)
            wrapper.java.additional.1=-server
            wrapper.java.additional.2=-Xms256m
            wrapper.java.additional.3=-Xmx512m
            wrapper.java.additional.4=-XX:PermSize=128m
            wrapper.java.additional.5=-XX:SurvivorRatio=2
            wrapper.java.additional.6=-XX:+UseParallelGC
        e、设置:堆内存
            # Initial Java Heap Size (in MB)
            #wrapper.java.initmemory=3
            # Maximum Java Heap Size (in MB)
            #wrapper.java.maxmemory=64
        f、【设置,自己的主函数入口类】【!!!】
            # Java Main class.(第一种集成方式)
            wrapper.java.mainclass=org.tanukisoftware.wrapper.WrapperSimpleApp
            # Application parameters.  Add parameters as needed starting from 1
            wrapper.app.parameter.1=org.wsr.stu.Wrapper
            wrapper.app.parameter.n=mian方法的参数
        g、设置日志属性(按日roll)
            wrapper.logfile=../logs/wrapper.YYYYMMDD.log
            wrapper.logfile.rollmode=DATE
            wrapper.logfile.maxfiles=90
            

最终的目录形式:

bin

    xxx:执行文件(shell文件)
    wrapper:的执行文件(和具体的硬件相关)

conf

    wrapper.conf

lib
    
    wrapper.jar
    xxx:libwrapper的lib文件

logs
    
    空目录
    
使用assembly打包:

    将依赖的jar打到lib目录下
    将配置文件打到conf目录下


PS:
overview:

    The Method 1 is to use the WrapperSimpleApp helper class to launch the application. 
    This is by far the simplest way to integrate with the Wrapper, and where possible, 
    it is highly recommended.
    There are some things to be aware of when using this method however. 
    When the Wrapper shuts down the JVM, there is no direct call to an application 
    requesting that it shutdown cleanly. 
    Rather, the Wrapper will exit the JVM by calling System.exit () from within the JVM. 
    If the application has registered its own Shutdown Hook, it will be invoked, 
    giving the application a chance to shutdown cleanly. If on the other hand, 
    a Shutdown Hook is not registered, then the application will suddenly exit 
    like pressing CTRL-C in the console (command window). 
    Both cases, with and without a Shutdown Hook, provide the exact same behavior 
    as if the application was running without the Wrapper.
    When integrating with this Method 1 the WrapperSimpleApp helper class 
    replaces an application's main class. 
    This gives the WrapperSimpleApp class a chance to immediately initialize 
    the WrapperManager and register the JVM with the Wrapper. 
    The WrapperSimpleApp class then manages all interaction with the Wrapper 
    as well as the life-cycle of an application. 
    When the Wrapper sends a start message to the JVM via the WrapperManager, 
    the main method of the application's actual main class is called.
    The WrapperSimpleApp helper class is told how to launch the application
     by passing the application's main class name, 
    followed by any additional application parameters to the main method of the WrapperSimpleApp.
