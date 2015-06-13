package jboss.wildfly.samples.chat;

import java.io.StringReader;

import javax.json.Json;
import javax.json.JsonObject;
import javax.websocket.DecodeException;
import javax.websocket.Decoder;
import javax.websocket.EndpointConfig;

import jboss.wildfly.samples.chat.domain.ChatMessage;

public class ChatMessageDecoder implements Decoder.Text<ChatMessage> {

	@Override
	public void destroy() {
		// TODO Auto-generated method stub

	}

	@Override
	public void init(EndpointConfig arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public ChatMessage decode(String arg0) throws DecodeException {
		ChatMessage message = new ChatMessage();
		JsonObject json = Json.createReader(new StringReader(arg0)).readObject();
		message.setMessage(json.getString("message"));
		return message;
	}

	@Override
	public boolean willDecode(String arg0) {
		// TODO Auto-generated method stub
		return true;
	}

}
