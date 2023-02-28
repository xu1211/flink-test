

# flink-test


## 批处理与流处理
### DataSet 有界数据集
[demo代码](./demo/src/main/java/com/flink/demo/batchAndStream/WordCount.java)


### DataStream 无界流数据
[demo代码](./demo/src/main/java/com/flink/demo/batchAndStream.StreamWordCount.java)

---

## 1. 获取执行环境 env
[demo代码](./demo/src/main/java/com/flink/demo/env/streamEnv.java)

## 2. 获取数据源 source

[基本的 stream source](./demo/src/main/java/com/flink/demo/source/streamSource.java)

[kafka](./demo/src/main/java/com/flink/demo/source/kafkaConnectors.java)

## 3. ETL 算子
### 无状态转换
map()
一对一的转换

flatmap()
一对n的转换
### 有状态转换
ValueState

### 分组 keyed, 聚合

keyBy()
类似group by

window()
无界数据集拆分成一个个有界数据集

reduce()


## 4. 输出 sink
对外的输出操作都要利用 Sink 完成

[日志输出](./demo/src/main/java/com/flink/demo/sink/sink.java)
