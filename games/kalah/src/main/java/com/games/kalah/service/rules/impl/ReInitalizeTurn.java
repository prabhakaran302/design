package com.games.kalah.service.rules.impl;

import org.springframework.stereotype.Component;

import com.games.kalah.domain.Game;

@Component
public class ReInitalizeTurn extends CheckPlayerTurn {

	@Override
	public void applyRule(Game game) throws Exception {
		if (canPlayerTakeTurn(game) && !repeatTurn(game)) {
			System.out.println("Reinitialize turn....");
			if (game.getTakeTurnPlayer() == game.getPlayers().get(0)) {
				game.setTakeTurnPlayer(game.getPlayers().get(1));
			} else {
				game.setTakeTurnPlayer(game.getPlayers().get(0));
			}
			game.setGameCurrentMessage("Next Turn " + game.getTakeTurnPlayer().getName() + " With store Index "
					+ game.getTakeTurnPlayer().getStoreIndex());
		}
	}

	@Override
	public int getOrder() {
		return 2;
	}

}
