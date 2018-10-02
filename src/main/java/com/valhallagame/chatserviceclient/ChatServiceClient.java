package com.valhallagame.chatserviceclient;

import com.valhallagame.chatserviceclient.message.*;
import com.valhallagame.common.AbstractServiceClient;
import com.valhallagame.common.DefaultServicePortMappings;
import com.valhallagame.common.RestResponse;

import java.io.IOException;

public class ChatServiceClient extends AbstractServiceClient {

	private static ChatServiceClient chatServiceClient;

	private ChatServiceClient() {
		serviceServerUrl = "http://localhost:" + DefaultServicePortMappings.CHAT_SERVICE_PORT;
	}

	public static void init(String serviceServerUrl) {
		ChatServiceClient client = get();
		client.serviceServerUrl = serviceServerUrl;
	}

	public static ChatServiceClient get() {
		if (chatServiceClient == null) {
			chatServiceClient = new ChatServiceClient();
		}
		return chatServiceClient;
	}

	public RestResponse<String> whisperPerson(String username, String message, String targetDisplayUsername) throws IOException {
		WhisperPersonParameter input = new WhisperPersonParameter(username, message, targetDisplayUsername);
		return restCaller.postCall(serviceServerUrl + "/v1/chat/whisper-person", input, String.class);
	}

	public RestResponse<String> whisperCharacter(String username, String message, String targetDisplayCharacterName) throws IOException {
		WhisperCharacterParameter input = new WhisperCharacterParameter(username, message, targetDisplayCharacterName);
		return restCaller.postCall(serviceServerUrl + "/v1/chat/whisper-character", input, String.class);
	}

	public RestResponse<String> instanceChat(String username, String message) throws IOException {
		InstanceChatParameter input = new InstanceChatParameter(username, message);
		return restCaller.postCall(serviceServerUrl + "/v1/chat/instance-chat", input, String.class);
	}

	public RestResponse<String> generalChat(String username, String message) throws IOException {
		GeneralChatParameter input = new GeneralChatParameter(username, message);
		return restCaller.postCall(serviceServerUrl + "/v1/chat/general-chat", input, String.class);
	}

	public RestResponse<String> partyChat(String username, String message) throws IOException {
		PartyChatParameter input = new PartyChatParameter(username, message);
		return restCaller.postCall(serviceServerUrl + "/v1/chat/party-chat", input, String.class);
	}
}
