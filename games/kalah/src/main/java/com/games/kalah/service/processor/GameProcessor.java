package com.games.kalah.service.processor;

import javax.validation.Valid;

import com.games.kalah.model.Game;
import com.games.kalah.service.dto.GameRequest;

public interface GameProcessor {

	void startGame(Long gameId);

	Game processGame(@Valid GameRequest gameRequest) throws Exception;

}
