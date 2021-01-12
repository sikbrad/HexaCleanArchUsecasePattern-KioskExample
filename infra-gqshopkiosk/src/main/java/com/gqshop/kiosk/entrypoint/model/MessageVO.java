package com.gqshop.kiosk.entrypoint.model;

public class MessageVO {

	private final String sendMessage;
	private final int id;
	private final String status;
	
	public MessageVO(String sendMessage, int id, String status) {
		super();
		this.sendMessage = sendMessage;
		this.id = id;
		this.status = status;
	}

	public String getSendMessage() {
		return sendMessage;
	}

	public int getId() {
		return id;
	}
	
	public String getStatus() {
		return status;
	}
}
