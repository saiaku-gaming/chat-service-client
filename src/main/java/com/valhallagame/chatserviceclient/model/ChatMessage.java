package com.valhallagame.chatserviceclient.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ChatMessage {
	private String senderUsername;
	private String senderCharacterName;
	private String message;
	private ChatType chatType;
}
