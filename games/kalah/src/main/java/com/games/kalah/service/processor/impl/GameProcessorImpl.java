package com.games.kalah.service.processor.impl;

import java.net.InetAddress;
import java.util.Arrays;
import java.util.concurrent.ThreadLocalRandom;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.games.kalah.domain.Game;
import com.games.kalah.domain.Player;
import com.games.kalah.service.dto.GameRequest;
import com.games.kalah.service.dto.GameResponse;
import com.games.kalah.service.dto.GameStartResponse;
import com.games.kalah.service.dto.GameStatus;
import com.games.kalah.service.processor.GameProcessor;
import com.games.kalah.util.Constants;

@Service
public class GameProcessorImpl implements GameProcessor {

	@Value("${server.port}")
	private String port;

	@Override
	public GameStartResponse startGame() {
		GameStartResponse gameStartResponse = new GameStartResponse();
		gameStartResponse.setId(ThreadLocalRandom.current().nextLong(Constants.ID_LIMIT));
		initGame(gameStartResponse.getId());
		gameStartResponse.setUri(getURI(gameStartResponse.getId()));
		return gameStartResponse;
	}

	private String getURI(Long id) {
		StringBuilder sb = new StringBuilder();
		sb.append(Constants.HTTP).append(Constants.COLON).append(Constants.FORWAD_SLASH).append(Constants.FORWAD_SLASH)
				.append(getLocalhost()).append(Constants.COLON).append(port).append(Constants.Api.GAME)
				.append(Constants.FORWAD_SLASH).append(id);
		return sb.toString();
	}

	private Game initGame(Long id) {
		Game game = new Game(Arrays.asList(new Player("Kratos", 6), new Player("Nathan Drake", 13)));
		game.setId(id);
		game.setTakeTurnPlayer(game.getPlayers().get(0));
		return game;
	}

	private String getLocalhost() {
		String str = "";
		try {
			InetAddress ia = InetAddress.getLocalHost();
			str = ia.getHostAddress();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return str;
	}

	@Override
	public GameResponse processGame(GameRequest gameRequest) {
		GameResponse gameResponse = createResponse(gameRequest);
		return gameResponse;
	}

	private GameResponse createResponse(GameRequest gameRequest) {
		GameStatus status = null;
		return GameResponse.builder().gameId(gameRequest.getGameId()).url(getURI(gameRequest.getGameId()))
				.status(status).build();
	}

}
