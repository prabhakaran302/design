package com.games.kalah.service.rules.impl;

import com.games.kalah.domain.Game;
import com.games.kalah.service.rules.RulesApplier;

public class ReInitalizeTurn implements RulesApplier {

	@Override
	public void applyRule(Game game) throws Exception {

	}

	@Override
	public int getOrder() {

		return 1;
	}

}
