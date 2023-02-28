package com.flink.demo.env;

import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;

/**
 * 获取执行环境
 *
 * @author cosmoxu
 * @version streamEnv, v 0.1 2023/2/22 11:09
 */
public class streamEnv {
    public static void main(String[] args) throws Exception {
        // 1. StreamExecutionEnvironment
        /**
         * 本地执行环境
         *    可在调用时指定默认的并行度。
         */
        StreamExecutionEnvironment streamEnv1 = StreamExecutionEnvironment.createLocalEnvironment(1);
        /**
         * 集群执行环境
         *     将 Jar 提交到远程服务器。需要在调用时指定 JobManager 的 IP 和端口号，并指定要在集群中运行的 Jar 包。
         */
        StreamExecutionEnvironment streamEnv2 =StreamExecutionEnvironment.createRemoteEnvironment("jobManage-hostname", 6123,"YOURPATH//WordCount.jar");
        /**
         * getExecutionEnvironment 根据上下文自动处理：
         *     如果你在 IDE 中执行你的程序或将其作为一般的 Java 程序执行，那么它将创建一个本地环境，该环境将在你的本地机器上执行你的程序。
         *     如果你基于程序创建了一个 JAR 文件，并通过命令行运行它，Flink 集群管理器将执行程序的 main 方法
         */
        StreamExecutionEnvironment streamEnv3 = StreamExecutionEnvironment.getExecutionEnvironment();

        // 2. DataStream API 将你的应用构建为一个 job graph, 并附加到 StreamExecutionEnvironment
        DataStream<String> inputDataStream = streamEnv3.socketTextStream("localhost", 9900);

        // 3. graph 就被打包并发送到 JobManager 上，后者对作业并行处理并将其子任务分发给 Task Manager 来执行。每个作业的并行子任务将在 task slot 中执行。
        streamEnv3.execute();
    }
}
