package com.games.kalah.domain;

import java.io.Serializable;
import java.util.List;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor(access = AccessLevel.PUBLIC)
public class Game implements Serializable {

	private static final long serialVersionUID = -1955252658899979989L;
	
	private Long id;
	private final Board board = new Board();
	private final List<Player> players;
	private boolean gameInProcess = Boolean.FALSE;
	private String winner;
	private Player takeTurnPlayer;
	private int chosenHouseIndex;

}
