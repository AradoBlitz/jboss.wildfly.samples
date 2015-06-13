package jboss.wildfly.samples.chat;

import java.util.logging.Logger;

import javax.json.Json;
import javax.websocket.EncodeException;
import javax.websocket.Encoder;
import javax.websocket.EndpointConfig;

import jboss.wildfly.samples.chat.domain.ChatMessage;

public class ChatMessageEncoder implements Encoder.Text<ChatMessage> {

	private Logger log = Logger.getLogger(getClass().getName());
	
	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void init(EndpointConfig arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String encode(ChatMessage arg0) throws EncodeException {
		
		log.info("Decode message: " + arg0);
		return Json.createObjectBuilder()
				.add("message",arg0.getMessage())
				.build().toString();
	}

}
