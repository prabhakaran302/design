package com.games.kalah.service.rules.impl;

import org.springframework.stereotype.Component;

import com.games.kalah.model.Board;
import com.games.kalah.model.Game;
import com.games.kalah.service.rules.RulesApplier;

@Component
public abstract class CheckPlayerTurn implements RulesApplier {
	boolean canPlayerTakeTurn(Game game) {
		int storeIndex = game.getTakeTurnPlayer().getStoreIndex();
		return !game.isOver() && game.getTurn().getCurrentPitId() <= storeIndex
				&& game.getTurn().getCurrentPitId() >= storeIndex - Board.HOUSES_PER_PLAYER;
	}

	boolean canCaptureStonePlayer(Game game) {
		return game.getBoard().getHouses()[game.getTurn().getLastSownIndex()] == 1
				&& (game.getTakeTurnPlayer().getStoreIndex() - game.getTurn().getLastSownIndex() > 0
						&& game.getTakeTurnPlayer().getStoreIndex()
								- game.getTurn().getLastSownIndex() <= Board.HOUSES_PER_PLAYER);
	}

	boolean repeatTurn(Game game) {
		if (game.getTakeTurnPlayer().getStoreIndex() == game.getTurn().getLastSownIndex()) {
			game.setGameCurrentMessage("Next Turn " + game.getTakeTurnPlayer().getName() + " With store Index "
					+ game.getTakeTurnPlayer().getStoreIndex());
			return true;
		}
		return false;
	}
}
