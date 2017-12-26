package com.valhallagame.chatserviceclient;

import java.io.IOException;

import com.valhallagame.chatserviceclient.message.ChatParameter;
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

	public RestResponse<String> whisperPerson(WhisperPersonParameter whisperParameter) throws IOException {
		return restCaller.postCall(chatServiceServerUrl + "/v1/chat/whisper-person", whisperParameter, String.class);
	}

	public RestResponse<String> whisperCharacter(WhisperCharacterParameter whisperParameter) throws IOException {
		return restCaller.postCall(chatServiceServerUrl + "/v1/chat/whisper-character", whisperParameter, String.class);
	}

	public RestResponse<String> instanceChat(ChatParameter chatParameter) throws IOException {
		return restCaller.postCall(chatServiceServerUrl + "/v1/chat/instance-chat", chatParameter, String.class);
	}

	public RestResponse<String> generalChat(ChatParameter chatParameter) throws IOException {
		return restCaller.postCall(chatServiceServerUrl + "/v1/chat/general-chat", chatParameter, String.class);
	}

	public RestResponse<String> partyChat(ChatParameter chatParameter) throws IOException {
		return restCaller.postCall(chatServiceServerUrl + "/v1/chat/party-chat", chatParameter, String.class);
	}

}
