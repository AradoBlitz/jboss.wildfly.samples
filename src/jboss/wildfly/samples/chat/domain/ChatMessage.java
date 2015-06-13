package jboss.wildfly.samples.chat.domain;

public class ChatMessage {

	private String message;
	
	public void setMessage(String message){
		this.message = message;
		
	}
	
	public String getMessage() {
		// TODO Auto-generated method stub
		return message;
	}

	@Override
	public String toString() {
		return "ChatMessage [message=" + message + "]";
	}

	
}
