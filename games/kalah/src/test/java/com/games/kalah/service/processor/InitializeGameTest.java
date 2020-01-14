package com.games.kalah.service.processor;

import java.util.concurrent.ThreadLocalRandom;

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
import com.games.kalah.model.Game;
import com.games.kalah.util.Constants;

@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
@EnableConfigurationProperties
@ContextConfiguration(classes = { KalahConfig.class }, loader = AnnotationConfigContextLoader.class)
@TestPropertySource(properties = { "player.north.index=6", "player.south.index=13", "player.south.name=kratos" })
public class InitializeGameTest {
	@Autowired
	private InitializeGame initializeGame;

	@Test
	public void testInitGame() {
		Long id = ThreadLocalRandom.current().nextLong(Constants.ID_LIMIT);
		Game game = initializeGame.initGame(id);
		Assert.assertEquals("There should be 2 players to play game", 2, game.getPlayers().size());
		Assert.assertEquals("Game Id ", id, game.getId());
		Assert.assertEquals("name ", "kratos", game.getPlayers().get(1).getName());
		int array[] = new int[] { 6, 6, 6, 6, 6, 6, 0, 6, 6, 6, 6, 6, 6, 0 };
		Assert.assertArrayEquals(array, game.getBoard().getHouses());

	}

}
