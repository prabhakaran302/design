package com.games.kalah.service.dto;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
public class GameRequest {
	@NotNull(message = "Game Id is mandatory")
	private Long gameId;

	@NotNull
	@Min(1)
	@Max(14)
	private Integer pitId;
}
