package com.flink.demo.batchAndStream;

import com.flink.demo.MyFlatMapper;
import org.apache.flink.api.java.DataSet;
import org.apache.flink.api.java.ExecutionEnvironment;
import org.apache.flink.api.java.tuple.Tuple2;

/**
 * @author cosmoxu
 * @version WordCount, v 0.1 2023/2/16 14:38
 */
public class WordCount {
    public static void main(String[] args) throws Exception {
        // 创建执行环境 ExecutionEnvironment
        ExecutionEnvironment env = ExecutionEnvironment.getExecutionEnvironment();
        // 从文件中读取数据
        String inputPath = "file:////Users/yuchunxu/Documents/GitHub/xu/flink-test/demo/src/main/resources/word.txt";
        DataSet<String> inputDataSet = env.readTextFile(inputPath);
        // 空格分词打散之后，对单词进行 groupby 分组，然后用 sum 进行聚合
        DataSet<Tuple2<String, Integer>> wordCountDataSet =
                inputDataSet.flatMap(new MyFlatMapper())
                        .groupBy(0)
                        .sum(1);
        // 打印输出
        wordCountDataSet.print();
    }
}