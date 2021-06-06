package com.oracle.fanout;


import com.oracle.factory.MyConnectionFactory;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;

public class FanoutProducer {
	
	private static final String EN = "fanout_exchange";
	private static final String QN = "fanout_queue";
	public static void main(String[] args) throws Exception {
		Connection connection = MyConnectionFactory.getConnection();
		Channel channel = connection.createChannel();
		/*channel.exchangeDeclare(EN, "fanout");
		channel.queueDeclare(QN, false, false, false, null);
		channel.queueBind(QN, EN, "");*/
		String message = "fanout的生产消息";
		channel.basicPublish(EN, "",null , message.getBytes());
		System.out.println("消息已发送");
		channel.close();
		connection.close();
	}
	

}
