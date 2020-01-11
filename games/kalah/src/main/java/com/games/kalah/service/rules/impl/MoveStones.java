package com.games.kalah.service.rules.impl;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.games.kalah.domain.Game;

@Component
public class MoveStones extends CheckPlayerTurn {

	@Value("${player.north.index}")
	private String playerNorthIndex;
	@Value("${player.south.index}")
	private String playerSouthIndex;

	@Override
	public void applyRule(Game game) throws Exception {
		if (canPlayerTakeTurn(game, game.getChosenHouseIndex())) {
			moveStone(game, game.getChosenHouseIndex());
		} else {
			throw new Exception("Its another player turn, choose appropriate pitId(index)");
		}
	}

	private void moveStone(Game game, int chosenHouse) {
		int targetArray[] = game.getBoard().getHouses();

		int index = game.getTakeTurnPlayer().getStoreIndex();
		int ignoreIndex = index == Integer.parseInt(playerNorthIndex) ? Integer.parseInt(playerSouthIndex)
				: Integer.parseInt(playerNorthIndex);
		int lastSownIndex = chosenHouse - 1;

		boolean flag = true;
		while (flag) {
			int val = targetArray[lastSownIndex];
			targetArray[lastSownIndex] = 0;
			int tempIndex = lastSownIndex + 1;
			while (val > 0) {
				if (tempIndex != ignoreIndex) {
					targetArray[tempIndex] += 1;
					tempIndex++;
					val--;
				}
				if (tempIndex == targetArray.length - 1) {
					tempIndex = 0;
				}
			}
			lastSownIndex = tempIndex - 1;
			if (targetArray[lastSownIndex] == 1)
				flag = false;
		}
	}

	@Override
	public int getOrder() {
		return 0;
	}

}
