package com.oracle.producer;


import com.oracle.factory.MyConnectionFactory;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;

public class Rroducer {
	
	//定义常量值，交换机名和队列名
	private static final String EXCHANGE_NAME = "direct_exchange_test";
	private static final String QUEUE_NAME = "direct_queue_test";
	
	public static void main(String[] args) throws Exception {
		//获取连接
		Connection connection = MyConnectionFactory.getConnection();
		//创建信道
		Channel channel = connection.createChannel();
		//定义交换机名和类型
		channel.exchangeDeclare(EXCHANGE_NAME, "direct");
		//定义队列名和属性信息
		channel.queueDeclare(QUEUE_NAME, false, false, false, null);
		//绑定信息
		channel.queueBind(QUEUE_NAME, EXCHANGE_NAME, "mytest");
		//定义消息
		String message = "这是一个直连交换机发送的消息测试";
		//通过信道发送消息到RabbitMQ
		channel.basicPublish(EXCHANGE_NAME, "mytest", null, message.getBytes());
		System.out.println("消息已发送。。。");
		channel.close();
		connection.close();
	}

}
