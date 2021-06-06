package com.oracle.dead;

import java.util.HashMap;
import java.util.Map;

import com.oracle.factory.MyConnectionFactory;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;

public class Producer {
	private static final String EN = "exchange_name";
	private static final String QN = "queue_name";
	public static void main(String[] args) throws Exception {
		Connection connection = MyConnectionFactory.getConnection();
		Channel channel = connection.createChannel();
		//正常编写一个direct类型的交换机
		Map<String, Object> propMaps = new HashMap<String, Object>();
		propMaps.put("x-message-ttl", 10000);
		propMaps.put("x-dead-letter-exchange", "DEL_EXCHANGE");
		propMaps.put("x-dead-letter-routing-key", "#");
		
		channel.exchangeDeclare(EN, "direct");
		channel.queueDeclare(QN, false, false, false, propMaps);
		channel.queueBind(QN, EN, "abc");
		channel.basicPublish(EN, "abc", null, "正常的消息".getBytes());
		System.out.println("发送完成");
		channel.close();
		connection.close();
	}

}
