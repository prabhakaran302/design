package com.games.kalah.service.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GameStatus {
	private int[] boardValues;

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < boardValues.length; i++) {
			sb.append(i + 1 + " : " + boardValues[i] + ",");
		}
		return sb.substring(0, sb.lastIndexOf(",")).toString();
	}

}
