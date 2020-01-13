package com.games.kalah.service.rules.impl;

import java.util.Arrays;

import org.springframework.stereotype.Component;

import com.games.kalah.model.Board;
import com.games.kalah.model.Game;
import com.games.kalah.model.Player;
import com.games.kalah.service.rules.RulesApplier;

@Component
public class CheckResult implements RulesApplier {

	@Override
	public void applyRule(Game game) throws Exception {
		boolean player1 = checkWhetherPlayerHasAllHousesEmpty(game, game.getPlayers().get(0).getStoreIndex());
		boolean player2 = checkWhetherPlayerHasAllHousesEmpty(game, game.getPlayers().get(1).getStoreIndex());
		if (player1 || player2) {
			System.out.println("Game Over..");
			int lsum = 0;
			int rsum = 0;
			if (player1) {
				lsum = moveToStoreIndex(game, game.getPlayers().get(1));
			} else {
				rsum = moveToStoreIndex(game, game.getPlayers().get(0));
			}

			if (lsum == rsum) {
				game.setWinner("Draw");
				game.setGameCurrentMessage("Game Over with Draw");
			} else {
				game.setWinner(game.getBoard().getHouses()[game.getPlayers().get(0).getStoreIndex()] > game.getBoard()
						.getHouses()[game.getPlayers().get(1).getStoreIndex()] ? game.getPlayers().get(0).getName()
								: game.getPlayers().get(1).getName());
				game.setGameCurrentMessage("Game Over with winner .. " + game.getWinner());
			}

			game.setGameInProcess(false);
			game.setOver(true);

		}
	}

	private int moveToStoreIndex(Game game, Player player) {
		int[] boardHouses = game.getBoard().getHouses();
		int sum = 0;
		for (int i = player.getStoreIndex() - Board.HOUSES_PER_PLAYER; i < player.getStoreIndex(); i++) {
			sum += boardHouses[i];
			boardHouses[i] = 0;
		}
		boardHouses[player.getStoreIndex()] += sum;
		return boardHouses[player.getStoreIndex()];
	}

	private boolean checkWhetherPlayerHasAllHousesEmpty(Game game, Integer storeIndex) {
		int[] playerHouses = Arrays.copyOfRange(game.getBoard().getHouses(), storeIndex - Board.HOUSES_PER_PLAYER,
				storeIndex);
		return Arrays.stream(playerHouses).sum() == 0;
	}

	@Override
	public int getOrder() {
		return 3;
	}

}
