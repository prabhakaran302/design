package com.games.kalah.service.rules.impl;

import org.springframework.stereotype.Component;

import com.games.kalah.domain.Game;

@Component
public class CaptureStones extends CheckPlayerTurn {

	@Override
	public void applyRule(Game game) throws Exception {
		if (canPlayerTakeTurn(game) && canCaptureStonePlayer(game)) {
			System.out.println("capture stones event....");
			int[] target = game.getBoard().getHouses();
			int lastSownIndex = game.getTurn().getLastSownIndex();
			if (target[lastSownIndex] == 1) {
				int ownStone = target[lastSownIndex];
				int opposite = target[Math.abs(12 - lastSownIndex)];
				target[game.getTakeTurnPlayer().getStoreIndex()] += ownStone + opposite;
				target[lastSownIndex] = 0;
				target[Math.abs(12 - lastSownIndex)] = 0;
			}
		}
	}

	@Override
	public int getOrder() {
		return 1;
	}

}
