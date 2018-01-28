package com.valhallagame.chatserviceclient.message;

import javax.validation.constraints.NotNull;

import com.valhallagame.common.validation.CheckLowercase;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class WhisperPersonParameter {
	@NotNull
	@CheckLowercase
	private String username;
	@NotNull
	private String message;
	@NotNull
	private String targetDisplayUsername;
}
