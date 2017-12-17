package com.valhallagame.chatserviceclient.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ChatMessage {
	private String senderDisplayUsername;
	private String senderDisplayCharacterName;
	private String message;
	private ChatType chatType;
}
