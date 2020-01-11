package com.games.kalah.service.impl;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.concurrent.ThreadLocalRandom;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.games.kalah.domain.Game;
import com.games.kalah.service.KalahService;
import com.games.kalah.service.dto.GameRequest;
import com.games.kalah.service.dto.GameResponse;
import com.games.kalah.service.dto.GameStartResponse;
import com.games.kalah.service.dto.GameStatus;
import com.games.kalah.service.processor.GameProcessor;
import com.games.kalah.util.Constants;

@Service
public class KalahServiceImpl implements KalahService {

	@Autowired
	private GameProcessor gameProcessor;

	@Value("${server.port}")
	private String port;

	@Override
	public GameStartResponse startGame() throws UnknownHostException {
		GameStartResponse gameStartResponse = new GameStartResponse();
		Long gameId = ThreadLocalRandom.current().nextLong(Constants.ID_LIMIT);
		gameStartResponse.setUri(getURI(gameId));
		gameStartResponse.setId(gameId);
		gameProcessor.startGame(gameId);
		return gameStartResponse;
	}

	private String getURI(Long id) throws UnknownHostException {
		StringBuilder sb = new StringBuilder();
		sb.append(Constants.HTTP).append(Constants.COLON).append(Constants.FORWAD_SLASH).append(Constants.FORWAD_SLASH)
				.append(getLocalhost()).append(Constants.COLON).append(port).append(Constants.Api.GAME)
				.append(Constants.FORWAD_SLASH).append(id);
		return sb.toString();
	}

	private String getLocalhost() throws UnknownHostException {
		return InetAddress.getLocalHost().getHostAddress();
	}

	@Override
	public GameResponse processGame(@Valid GameRequest gameRequest) throws Exception {
		return createResponse(gameProcessor.processGame(gameRequest));
	}

	private GameResponse createResponse(Game game) throws UnknownHostException {
		GameStatus status = new GameStatus();
		status.setBoardValues(game.getBoard().getHouses());
		return GameResponse.builder().gameId(game.getId()).url(getURI(game.getId())).status(status).build();
	}

}
