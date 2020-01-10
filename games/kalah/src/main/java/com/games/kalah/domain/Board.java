package com.games.kalah.domain;

import com.games.kalah.exception.InvalidStoneFetchOperationException;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Board {

	public static final int STONES_PER_HOUSE = 6;
	public static final int HOUSES_PER_PLAYER = 6;

	private final int[] houses = new int[14];
	private boolean filled = Boolean.FALSE;

	public int getByIndex(int index) {
		if (index >= houses.length || index < 0) {
			throw new InvalidStoneFetchOperationException("index " + index + " is invalid. Check it again.");
		}
		return houses[index];
	}

}
