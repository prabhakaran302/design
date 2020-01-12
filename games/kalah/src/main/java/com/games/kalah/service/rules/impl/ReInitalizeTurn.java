package com.games.kalah.service.rules.impl;

import org.springframework.stereotype.Component;

import com.games.kalah.domain.Game;

@Component
public class ReInitalizeTurn extends CheckPlayerTurn {

	@Override
	public void applyRule(Game game) throws Exception {
		if (canPlayerTakeTurn(game)) {
			if (game.getTakeTurnPlayer() == game.getPlayers().get(0)) {
				game.setTakeTurnPlayer(game.getPlayers().get(1));
			} else {
				game.setTakeTurnPlayer(game.getPlayers().get(0));
			}
			game.setGameCurrentMessage("Turn " + game.getTakeTurnPlayer().getName());
		}
	}

	@Override
	public int getOrder() {
		return 2;
	}

}
