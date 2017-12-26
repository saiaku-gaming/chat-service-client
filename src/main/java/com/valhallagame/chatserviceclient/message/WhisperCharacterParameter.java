package com.valhallagame.chatserviceclient.message;

import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class WhisperCharacterParameter {
	@NotNull
	private String senderUsername;
	@NotNull
	private String message;
	@NotNull
	private String targetDisplayCharacterName;
}