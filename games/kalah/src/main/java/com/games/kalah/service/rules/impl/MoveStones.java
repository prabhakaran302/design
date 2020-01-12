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
		if (canPlayerTakeTurn(game)) {
			moveStone(game, game.getTurn().getCurrentPitId());
		} else {
			game.setGameCurrentMessage("Another person Turn");
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
				if (ignoreIndex == tempIndex && tempIndex == targetArray.length - 1)
					tempIndex = 0;
				else if (tempIndex == targetArray.length) {
					tempIndex = 0;
				}
			}
			lastSownIndex = tempIndex - 1;
			if (targetArray[lastSownIndex] == 1) {
				flag = false;
				game.getTurn().setLastSownIndex(lastSownIndex);
			}
		}
	}

	@Override
	public int getOrder() {
		return 0;
	}

}
