package com.games.kalah.domain;

import java.io.Serializable;

import com.games.kalah.exception.InvalidStoneFetchOperationException;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class Board implements Serializable {

	private static final long serialVersionUID = 5359065505114013338L;

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
