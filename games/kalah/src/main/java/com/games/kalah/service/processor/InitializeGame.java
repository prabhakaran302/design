package com.games.kalah.service.processor;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.games.kalah.domain.Board;
import com.games.kalah.domain.Game;
import com.games.kalah.domain.Player;
import com.games.kalah.domain.Turn;

@Component
public class InitializeGame {

	@Value("${player.north.name}")
	private String playerNorthName;
	@Value("${player.south.name}")
	private String playerSouthName;
	@Value("${player.north.index}")
	private String playerNorthIndex;
	@Value("${player.south.index}")
	private String playerSouthIndex;

	public Game initGame(Long gameId) {
		Game game = new Game(Arrays.asList(new Player(playerNorthName, Integer.parseInt(playerNorthIndex)),
				new Player(playerSouthName, Integer.parseInt(playerSouthIndex))));
		game.setId(gameId);
		game.setTakeTurnPlayer(game.getPlayers().get(0));
		Turn turn = new Turn();
		game.setTurn(turn);
		fillBoardWithInitialValues(game);
		return game;
	}

	public void fillBoardWithInitialValues(Game game) {
		Board board = game.getBoard();
		if (!board.isFilled()) {
			int[] houses = board.getHouses();

			for (int i = 0; i < houses.length; i++) {
				if (i != Integer.parseInt(playerNorthIndex) && i != Integer.parseInt(playerSouthIndex)) {
					houses[i] = Board.STONES_PER_HOUSE;
				}
			}
			board.setFilled(Boolean.TRUE);
			game.setGameInProcess(Boolean.TRUE);
		}
	}

}
