# 第 21 关 - MQ 中间件

## 对应能力

- 能力简称：MQ 中间件
- 能力说明：RabbitMQ/Kafka/RocketMQ，理解生产者、消费者、消息可靠性

## 后端模拟工作场景

库存扣减、订单创建、通知发送要通过事件解耦，团队选择 RabbitMQ 作为训练实现。

## 这一关真正练什么

RabbitMQ/Kafka/RocketMQ，理解生产者、消费者、消息可靠性

## 相关技术关键词

MQ、RabbitMQ、Kafka、RocketMQ、Producer、Consumer、Topic、解耦、exactly once、at most once、at least once

## 你接到的任务

- 发布 OrderCreated 事件。
- 实现库存和通知两个消费者。
- 处理重复消息和消费失败重试。

## 验收标准

- 消费者幂等，不怕重复消息。
- 失败消息有日志或死信处理说明。
- 能解释三种投递语义的差异。

## 学习提醒

这一关不是为了堆技术名词，而是训练“接到真实后端工作后如何理解、拆解、实现、验证、解释”的能力。代码能跑只是底线，能说明为什么这样做才算过关。

