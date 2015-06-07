package jboss.wildfly.samples;

import static org.junit.Assert.*;

import java.util.Hashtable;
import java.util.Properties;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.naming.Context;
import javax.naming.InitialContext;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class RemoteJMS_IT {

	private ConnectionFactory connectionFactory;
	private Destination destination;
	private Connection connection;
	private Session session;
	private MessageProducer producer;
	private MessageConsumer consumer;
	private InitialContext remoteContext;

	@Before
	public void init() throws Exception {
		Properties environment = new Properties();
		environment.put(Context.INITIAL_CONTEXT_FACTORY, "org.jboss.naming.remote.client.InitialContextFactory");
		environment.put(Context.PROVIDER_URL, "http-remoting://localhost:8080");
		
		remoteContext = new InitialContext(environment); 
		connectionFactory = (ConnectionFactory) remoteContext.lookup("jms/RemoteConnectionFactory");
		destination = (Destination) remoteContext.lookup("jms/queue/TestQueue");
		connection = connectionFactory.createConnection("jmsuser","jms1");
		session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
		producer = session.createProducer(destination);
		consumer = session.createConsumer(destination);
		connection.start();
	}
	
	@After
	public void tearDown() throws Exception{
		remoteContext.close();
		connection.stop();
	}
	
	@Test
	public void getAccesToJmsConnectionFactory() throws Exception {
		
		assertNotNull(connectionFactory);		
		assertNotNull(destination);		
		assertNotNull(connection);		
		assertNotNull(session);		
		assertNotNull(producer);		
		assertNotNull(consumer);
	}
	
	@Test
	public void sendMesssage() throws Exception {
		
		TextMessage textMsg = session.createTextMessage("Jms message");
		producer.send(textMsg);
		Message receive = consumer.receive();
		assertNotNull(receive);
		assertEquals("Jms message", receive.getBody(String.class));
	}
}
