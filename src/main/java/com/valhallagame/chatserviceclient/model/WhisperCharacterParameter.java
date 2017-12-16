package com.valhallagame.chatserviceclient.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class WhisperCharacterParameter {
	private String senderUsername;
	private String message;
	private String targetCharacterName;
	private String senderCharacterName;
}
