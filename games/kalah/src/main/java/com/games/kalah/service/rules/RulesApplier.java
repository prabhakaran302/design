package com.games.kalah.service.rules;

import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;

import com.games.kalah.domain.Game;

@Component
public interface RulesApplier extends Ordered {
	public void applyRule(Game game) throws Exception;
}
