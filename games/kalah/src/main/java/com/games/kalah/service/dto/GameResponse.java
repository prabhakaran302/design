package com.games.kalah.service.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Builder(builderClassName = "Builder")
@Getter
@ToString
public class GameResponse {
	private Long gameId;
	private String url;
	private GameStatus status;
}
