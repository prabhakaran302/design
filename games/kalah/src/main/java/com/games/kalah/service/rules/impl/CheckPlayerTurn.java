package com.games.kalah.service.rules.impl;

import org.springframework.stereotype.Component;

import com.games.kalah.domain.Board;
import com.games.kalah.domain.Game;
import com.games.kalah.service.rules.RulesApplier;

@Component
public abstract class CheckPlayerTurn implements RulesApplier {
	boolean canPlayerTakeTurn(Game game, int chosenHouse) {
		int storeIndex = game.getTakeTurnPlayer().getStoreIndex();

		return chosenHouse < storeIndex && chosenHouse >= storeIndex - Board.HOUSES_PER_PLAYER;

	}
}
