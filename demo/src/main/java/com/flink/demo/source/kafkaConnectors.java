package com.flink.demo.source;

import org.apache.flink.api.common.eventtime.WatermarkStrategy;
import org.apache.flink.api.common.serialization.SimpleStringSchema;
import org.apache.flink.connector.kafka.source.KafkaSource;
import org.apache.flink.connector.kafka.source.enumerator.initializer.OffsetsInitializer;
import org.apache.flink.streaming.api.datastream.DataStreamSource;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;

/**
 * @author cosmoxu
 * @version connectors, v 0.1 2023/2/22 14:19
 */
public class kafkaConnectors {
    public static void main(String[] args) throws Exception {
        final StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();

        // Kafka 获取数据到流
        KafkaSource<String> source = KafkaSource.<String>builder()
                .setBootstrapServers("9.135.89.169:9092")
                .setTopics("topic1")
                .setGroupId("webGroup")
                .setStartingOffsets(OffsetsInitializer.earliest())
                .setValueOnlyDeserializer(new SimpleStringSchema())
                .build();
        DataStreamSource<String> lines = env.fromSource(source, WatermarkStrategy.noWatermarks(), "Kafka Source");
        lines.print();

        env.execute();
    }
}
