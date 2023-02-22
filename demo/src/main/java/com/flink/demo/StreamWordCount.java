package com.flink.demo;

import org.apache.flink.api.java.tuple.Tuple2;
import org.apache.flink.api.java.utils.ParameterTool;
import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;

/**
 * nc -lk 9900
 *
 * @author cosmoxu
 * @version StreamWordCount, v 0.1 2023/2/16 14:41
 */
public class StreamWordCount {
    public static void main(String[] args) throws Exception {
        // 获取执行环境
        /*
         会根据上下文做正确的处理：
           如果你在 IDE 中执行你的程序或将其作为一般的 Java 程序执行，那么它将创建一个本地环境，该环境将在你的本地机器上执行你的程序。
           如果你基于程序创建了一个 JAR 文件，并通过命令行运行它，Flink 集群管理器将执行程序的 main 方法
        */
        StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();
        // 加载数据
        DataStream<String> inputDataStream = env.socketTextStream("localhost", 9900);
        // 执行算子
        DataStream<Tuple2<String, Integer>> wordCountDataStream = inputDataStream
                .flatMap(new MyFlatMapper())
                .keyBy(0)
                .sum(1);
        // 输出
        wordCountDataStream.print().setParallelism(1);
        // 触发执行
        env.execute();
    }
}
