package com.games.kalah.service.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GameStatus {
	private int[] boardValues;
	private String message;

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < boardValues.length; i++) {
			sb.append(i + 1 + " : " + boardValues[i] + ",");
		}
		return sb.append(" message " + message).toString();
	}

}
