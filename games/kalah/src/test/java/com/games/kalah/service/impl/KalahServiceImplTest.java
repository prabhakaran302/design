package com.games.kalah.service.impl;

import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

import com.games.kalah.config.KalahConfig;
import com.games.kalah.exception.InvalidStoneFetchOperationException;
import com.games.kalah.service.dto.GameRequest;
import com.games.kalah.service.dto.GameResponse;
import com.games.kalah.service.dto.GameStartResponse;

@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
@EnableConfigurationProperties
@ContextConfiguration(classes = { KalahConfig.class }, loader = AnnotationConfigContextLoader.class)
@TestPropertySource(properties = { "server.port=8773", "player.north.index=6", "player.south.index=13" })
public class KalahServiceImplTest {
	@Autowired
	private KalahServiceImpl kalahServiceImpl;

	@Test
	public void testStartGame() throws Exception {
		GameStartResponse gameStartResponse = kalahServiceImpl.startGame();
		Assert.assertEquals("It should be http", "http", gameStartResponse.getUri().substring(0, 4));
	}

	@Test
	public void testProcessGame() throws Exception {
		GameStartResponse gameStartResponse = kalahServiceImpl.startGame();
		GameRequest gameRequest = new GameRequest();
		gameRequest.setGameId(gameStartResponse.getId());
		gameRequest.setPitId(4);
		GameResponse gameResponse = kalahServiceImpl.processGame(gameRequest);
		Assert.assertEquals("Both url should be same", gameStartResponse.getUri(), gameResponse.getUrl());
	}

	@Test
	public void whenExceptionThrown_thenAssertionSucceeds() {
		Exception exception = assertThrows(InvalidStoneFetchOperationException.class, () -> {
			GameRequest gameRequest = new GameRequest();
			gameRequest.setGameId((long) 1);
			gameRequest.setPitId(4);
			kalahServiceImpl.processGame(gameRequest);
		});

		Exception excepextion = assertThrows(InvalidStoneFetchOperationException.class, () -> {
			GameStartResponse gameStartResponse = kalahServiceImpl.startGame();
			GameRequest gameRequest = new GameRequest();
			gameRequest.setGameId(gameStartResponse.getId());
			gameRequest.setPitId(400);
			kalahServiceImpl.processGame(gameRequest);
		});
	}

	@Test
	public void testGameMessages() throws Exception {
		GameStartResponse gameStartResponse = kalahServiceImpl.startGame();
		GameRequest gameRequest = new GameRequest();
		gameRequest.setGameId(gameStartResponse.getId());
		gameRequest.setPitId(5);
		GameResponse gameResponse = kalahServiceImpl.processGame(gameRequest);
		gameRequest.setGameId(gameStartResponse.getId());
		gameRequest.setPitId(9);
		gameResponse = kalahServiceImpl.processGame(gameRequest);
		Assert.assertEquals("Invalid Pit Id", "Invalid Attempt ! No Stones at given pitID...",
				gameResponse.getStatus().getMessage());
	}
}
