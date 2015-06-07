package jboss.wildfly.samples;

import static org.junit.Assert.*;

import javax.annotation.Resource;
import javax.inject.Inject;
import javax.jms.JMSContext;
import javax.jms.Queue;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.Asset;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(Arquillian.class)
public class EmbeddedJmsIT {

	@Deployment
	public static JavaArchive deployment(){
		return ShrinkWrap.create(JavaArchive.class)
				.addAsManifestResource(EmptyAsset.INSTANCE, "beans.xml");
	}
	
	@Resource(mappedName="java:jboss/exported/jms/queue/TestQueue")
	private Queue queueExample; 
	
	@Inject
	private JMSContext context;
	
	@Test
	public void checkEngine() throws Exception {
		assertNotNull("No queue found", queueExample);
		assertNotNull("JmsContext isn't injected",context);
		
	}
	
	@Test
	public void sendMessageToQueue() throws Exception {
		context.createProducer().send(queueExample, "Test message.");
		assertEquals("Test message.",context.createConsumer(queueExample).receiveBody(String.class));
	}
}
