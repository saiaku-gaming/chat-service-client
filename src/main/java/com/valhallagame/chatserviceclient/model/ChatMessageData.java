package com.valhallagame.chatserviceclient.model;

import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ChatMessageData {
	@NotNull
	private String senderDisplayCharacterName;
	@NotNull
	private String message;
	@NotNull
	private ChatType chatType;
}
