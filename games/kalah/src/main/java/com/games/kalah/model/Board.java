package com.games.kalah.model;

import java.io.Serializable;

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
}
