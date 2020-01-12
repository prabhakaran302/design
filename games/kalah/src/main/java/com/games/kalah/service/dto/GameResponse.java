package com.games.kalah.service.dto;

import lombok.Builder;
import lombok.Getter;

@Builder(builderClassName = "Builder")
@Getter
public class GameResponse {
	private Long id;

	@Override
	public String toString() {
		return "id=" + id + ", url=" + url + ", status=" + status;
	}

	private String url;
	private GameStatus status;
}
