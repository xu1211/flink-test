package com.flink.demo.batchAndStream;

import com.flink.demo.MyFlatMapper;
import org.apache.flink.api.java.tuple.Tuple2;
import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;

/**
 * 先打开端口:   nc -lk 9900
 * 然后启动代码
 *
 * @author cosmoxu
 * @version StreamWordCount, v 0.1 2023/2/16 14:41
 */
public class StreamWordCount {
    public static void main(String[] args) throws Exception {
        // 获取执行环境 StreamExecutionEnvironment
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
