package com.oracle.factory;

import java.io.IOException;

import com.rabbitmq.client.Connection;

public class MyConnectionFactory {
	private static final String HOST_ADDRESS="192.168.153.146";
	private static final Integer PORT=5672;
	private static final String VIRTUAL_HOST="/";
	private static final String USER_NAME="guest";
	private static final String PASSWORD="guest";

	public static Connection getConnection() throws IOException {
	    com.rabbitmq.client.ConnectionFactory factory=new com.rabbitmq.client.ConnectionFactory();
	    factory.setHost(HOST_ADDRESS);
	    factory.setPort(PORT);
	    factory.setVirtualHost(VIRTUAL_HOST);
	    factory.setUsername(USER_NAME);
	    factory.setPassword(PASSWORD);
	    Connection connection=factory.newConnection();
	    return connection;
	}


}
