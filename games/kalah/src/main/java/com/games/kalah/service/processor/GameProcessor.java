package com.games.kalah.service.processor;

import com.games.kalah.service.dto.GameRequest;
import com.games.kalah.service.dto.GameResponse;
import com.games.kalah.service.dto.GameStartResponse;

public interface GameProcessor {

	GameStartResponse startGame();

	GameResponse processGame(GameRequest gameRequest);

}
