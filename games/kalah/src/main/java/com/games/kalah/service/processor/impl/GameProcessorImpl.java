package com.games.kalah.service.processor.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.games.kalah.model.Game;
import com.games.kalah.repository.GameRepository;
import com.games.kalah.service.dto.GameRequest;
import com.games.kalah.service.processor.GameProcessor;
import com.games.kalah.service.processor.InitializeGame;
import com.games.kalah.service.rules.RulesApplier;

public class GameProcessorImpl implements GameProcessor {

	@Autowired
	private List<RulesApplier> rulesAppliersList;

	@Autowired
	private InitializeGame initializeGame;
	@Autowired
	private GameRepository gameRepository;

	@Override
	public void startGame(Long gameId) {
		Game gameEntity = initializeGame.initGame(gameId);
		gameRepository.save(gameEntity);
	}

	@Override
	public Game processGame(GameRequest gameRequest) throws Exception {
		Game game = gameRepository.getOne(gameRequest.getGameId());
		if (game == null || gameRequest.getPitId() > 13)
			throw new Exception("Game not Exists...");
		if (!game.isOver()) {
			game.getTurn().setCurrentPitId(gameRequest.getPitId());
			if (game.getBoard().getHouses()[gameRequest.getPitId() - 1] == 0) {
				game.setGameCurrentMessage("Invalid Attempt ! No Stones at given pitID...");
				return game;
			}
			rulesAppliersList.forEach(applier -> {
				try {
					applier.applyRule(game);
				} catch (Exception e) {
					e.printStackTrace();
				}
			});
			gameRepository.save(game);
		} else {
			game.setGameCurrentMessage("Game Already Over....");
		}
		return game;

	}

}
