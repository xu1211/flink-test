package com.flink.demo.source;

import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;

import java.util.ArrayList;
import java.util.List;

/**
 * @author cosmoxu
 * @version streamSource, v 0.1 2023/2/22 11:40
 */
public class streamSource {
    public static void main(String[] args) throws Exception {
        final StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();

        // 构造 DataStream<Person>
        DataStream<Person> flintstones1 = env.fromElements(
                new Person("Fred", 35),
                new Person("Wilma", 35),
                new Person("Pebbles", 2));
        flintstones1.print("flintstones1:  ");

        List<Person> people = new ArrayList<Person>();
        people.add(new Person("Fred", 35));
        people.add(new Person("Wilma", 35));
        people.add(new Person("Pebbles", 2));
        DataStream<Person> flintstones2 = env.fromCollection(people);
        flintstones2.print("flintstones2:  ");

        // 读取文件 获取数据到流
        DataStream<String> filelines = env.readTextFile("file:///src/main/resources/word.txt");
        filelines.print("filelines:  ");

        // socket 获取数据到流
        DataStream<String> socketlines = env.socketTextStream("localhost", 9900);
        socketlines.print();

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
