package com.games.kalah.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Turn {
	private int chosenHouse;
	private int lastSownIndex;
}
