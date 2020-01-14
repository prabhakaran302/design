package com.games.kalah.config;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import com.games.kalah.repository.GameRepository;
import com.games.kalah.service.processor.GameProcessor;
import com.games.kalah.service.processor.impl.GameProcessorImpl;
import com.games.kalah.service.rules.RulesApplier;
import com.games.kalah.service.rules.impl.CaptureStones;
import com.games.kalah.service.rules.impl.CheckResult;
import com.games.kalah.service.rules.impl.MoveStones;
import com.games.kalah.service.rules.impl.ReInitalizeTurn;

@ComponentScan("com.games.kalah.service")
@Configuration
public class KalahConfig {
	@Bean
	public GameProcessor getCollectionsBean() {
		return new GameProcessorImpl();
	}

	@Bean
	public GameRepository getGameReposiory() {
		return new GameRepository();
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
