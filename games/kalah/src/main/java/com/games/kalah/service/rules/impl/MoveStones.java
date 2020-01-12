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
			System.out.println("Moving stones....");
			moveStone(game, game.getTurn().getCurrentPitId());
		} else {
			game.setGameCurrentMessage("Invalid Attempt : Another person Turn");
		}
	}

	private void moveStone(Game game, int pitId) {

		int targetArray[] = game.getBoard().getHouses();
		int index = game.getTakeTurnPlayer().getStoreIndex();
		int ignoreIndex = index == Integer.parseInt(playerNorthIndex) ? Integer.parseInt(playerSouthIndex)
				: Integer.parseInt(playerNorthIndex);
		int lastSownIndex = pitId - 1;

		boolean flag = true;
		boolean lastIndexFlag = false;
		while (flag) {
			int val = targetArray[lastSownIndex];
			targetArray[lastSownIndex] = 0;
			int tempIndex = lastSownIndex + 1;
			while (val > 0) {
				lastIndexFlag = false;
				if (tempIndex != ignoreIndex) {
					targetArray[tempIndex] += 1;
					tempIndex++;
					val--;
				}
				if (tempIndex == ignoreIndex && tempIndex != targetArray.length - 1) {
					tempIndex++;
					lastIndexFlag = true;
				} else if (ignoreIndex == tempIndex && tempIndex == targetArray.length - 1)
					tempIndex = 0;
				else if (tempIndex == targetArray.length) {
					tempIndex = 0;
				}
			}

			lastSownIndex = lastIndexFlag == false ? tempIndex - 1 : tempIndex - 2;
			if (lastSownIndex == -1 && ignoreIndex == targetArray.length - 1) {
				lastSownIndex = targetArray.length - 2;
			}
			if (lastSownIndex == game.getTakeTurnPlayer().getStoreIndex()) {
				flag = false;
				game.getTurn().setLastSownIndex(lastSownIndex);
			}
			if (lastSownIndex == -1 && ignoreIndex != targetArray.length - 1) {
				flag = false;
				game.getTurn().setLastSownIndex(targetArray.length - 1);
			} else if (targetArray[lastSownIndex] == 1) {
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
