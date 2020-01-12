package com.games.kalah.config;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.games.kalah.service.processor.GameProcessor;
import com.games.kalah.service.processor.impl.GameProcessorImpl;
import com.games.kalah.service.rules.RulesApplier;
import com.games.kalah.service.rules.impl.CaptureStones;
import com.games.kalah.service.rules.impl.CheckResult;
import com.games.kalah.service.rules.impl.MoveStones;
import com.games.kalah.service.rules.impl.ReInitalizeTurn;

@Configuration
public class KalahConfig {
	@Bean
	public GameProcessor getCollectionsBean() {
		return new GameProcessorImpl();
	}

	@Bean
	public List<RulesApplier> rulesAppliers() {
		ArrayList<RulesApplier> rulesAppliers = new ArrayList<RulesApplier>();
		rulesAppliers.add(new MoveStones());
		rulesAppliers.add(new CaptureStones());
		rulesAppliers.add(new ReInitalizeTurn());
		rulesAppliers.add(new CheckResult());
		return rulesAppliers;
	}
}
