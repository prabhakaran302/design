package com.games.kalah.service.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GameRequest {
	@NotBlank(message = "Game Id is mandatory")
	private Long gameId;

	@NotNull
	private Integer pitId;
}
