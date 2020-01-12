package com.games.kalah.service.rules.impl;

import org.springframework.stereotype.Component;

import com.games.kalah.domain.Game;
import com.games.kalah.service.rules.RulesApplier;

@Component
public class CheckResult implements RulesApplier {

	@Override
	public void applyRule(Game game) throws Exception {

	}

	@Override
	public int getOrder() {
		return 3;
	}

}
