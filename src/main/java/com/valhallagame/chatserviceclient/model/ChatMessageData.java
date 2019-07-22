package com.valhallagame.chatserviceclient.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ChatMessageData {
	@NotBlank
	private String senderDisplayCharacterName;
	@NotBlank
	private String message;
	@NotNull
	private ChatType chatType;
}
