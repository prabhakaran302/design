package com.games.kalah.service;

import java.net.UnknownHostException;

import javax.validation.Valid;

import com.games.kalah.service.dto.GameRequest;
import com.games.kalah.service.dto.GameResponse;
import com.games.kalah.service.dto.GameStartResponse;

public interface KalahService {

	GameStartResponse startGame() throws UnknownHostException;

	GameResponse processGame(@Valid GameRequest gameRequest) throws UnknownHostException, Exception;

}
