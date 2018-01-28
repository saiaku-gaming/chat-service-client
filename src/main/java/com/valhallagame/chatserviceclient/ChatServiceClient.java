package com.valhallagame.chatserviceclient;

import java.io.IOException;

import com.valhallagame.chatserviceclient.message.GeneralChatParameter;
import com.valhallagame.chatserviceclient.message.InstanceChatParameter;
import com.valhallagame.chatserviceclient.message.PartyChatParameter;
import com.valhallagame.chatserviceclient.message.WhisperCharacterParameter;
import com.valhallagame.chatserviceclient.message.WhisperPersonParameter;
import com.valhallagame.common.DefaultServicePortMappings;
import com.valhallagame.common.RestCaller;
import com.valhallagame.common.RestResponse;

public class ChatServiceClient {
	private static ChatServiceClient chatServiceClient;

	private String chatServiceServerUrl = "http://localhost:" + DefaultServicePortMappings.CHAT_SERVICE_PORT;
	private RestCaller restCaller;

	private ChatServiceClient() {
		restCaller = new RestCaller();
	}

	public static void init(String friendServiceServerUrl) {
		ChatServiceClient client = get();
		client.chatServiceServerUrl = friendServiceServerUrl;
	}

	public static ChatServiceClient get() {
		if (chatServiceClient == null) {
			chatServiceClient = new ChatServiceClient();
		}

		return chatServiceClient;
	}

	public RestResponse<String> whisperPerson(String username, String message, String targetDisplayUsername) throws IOException {
		WhisperPersonParameter input = new WhisperPersonParameter(username, message, targetDisplayUsername);
		return restCaller.postCall(chatServiceServerUrl + "/v1/chat/whisper-person", input, String.class);
	}

	public RestResponse<String> whisperCharacter(String username, String message, String targetDisplayCharacterName) throws IOException {
		WhisperCharacterParameter input = new WhisperCharacterParameter(username, message, targetDisplayCharacterName);
		return restCaller.postCall(chatServiceServerUrl + "/v1/chat/whisper-character", input, String.class);
	}

	public RestResponse<String> instanceChat(String username, String message) throws IOException {
		InstanceChatParameter input = new InstanceChatParameter(username, message);
		return restCaller.postCall(chatServiceServerUrl + "/v1/chat/instance-chat", input, String.class);
	}

	public RestResponse<String> generalChat(String username, String message) throws IOException {
		GeneralChatParameter input = new GeneralChatParameter(username, message);
		return restCaller.postCall(chatServiceServerUrl + "/v1/chat/general-chat", input, String.class);
	}

	public RestResponse<String> partyChat(String username, String message) throws IOException {
		PartyChatParameter input = new PartyChatParameter(username, message);
		return restCaller.postCall(chatServiceServerUrl + "/v1/chat/party-chat", input, String.class);
	}

}
