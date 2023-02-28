package com.flink.demo.sink;

import com.flink.demo.MyFlatMapper;
import org.apache.flink.api.java.tuple.Tuple2;
import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;

/**
 * @author cosmoxu
 * @version sink, v 0.1 2023/2/22 14:51
 */
public class sink {
    public static void main(String[] args) throws Exception {
        StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();
        DataStream<Person> source = env.fromElements(
                new Person("one", 35),
                new Person("two", 35),
                new Person("three", 2));

        /**
         * sink
         *   对流中的每个元素都调用 toString() 方法。
         *   打印其结果到 task manager 的日志中（如果运行在 IDE 中时，将追加到你的 IDE 控制台）
         */
        source.print();
        /**
         * 输出:
         * 12> three: age 2
         * 10> one: age 35
         * 11> two: age 35
         *
         * 10> 和 11> 指出输出来自哪个 sub-task（即 thread）
         */

        env.execute();
    }

    public static class Person {
        public String name;
        public Integer age;

        public Person() {
        }

        public Person(String name, Integer age) {
            this.name = name;
            this.age = age;
        }

        public String toString() {
            return this.name.toString() + ": age " + this.age.toString();
        }
    }
}
