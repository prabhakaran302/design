package com.games.kalah.domain;

import java.util.List;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor(access = AccessLevel.PUBLIC)
public class Game {

	private Long id;
	private final Board board = new Board();
	private final List<Player> players;
	private boolean over = Boolean.FALSE;
	private boolean started = Boolean.FALSE;
	private String winner;
	private Turn currentTurn;
	private Turn previousTurn;
	private Player takeTurnPlayer;
}
