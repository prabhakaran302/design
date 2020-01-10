package com.games.kalah.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.games.kalah.service.dto.GameRequest;
import com.games.kalah.service.dto.GameResponse;
import com.games.kalah.service.dto.GameStartResponse;
import com.games.kalah.service.processor.GameProcessor;
import com.games.kalah.util.Constants;

@RestController
@RequestMapping(value = "/", produces = { "application/json" })
public class KalahController {

	@Autowired
	private GameProcessor gameProcessor;

	@PutMapping(value = Constants.Api.TURNS)
	public ResponseEntity<GameResponse> makeTurn(@RequestBody GameRequest gameRequest, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			return null;
		} else {
			return new ResponseEntity<GameResponse>(gameProcessor.processGame(gameRequest), HttpStatus.OK);
		}
	}

	@PostMapping(value = Constants.Api.GAME)
	public ResponseEntity<GameStartResponse> startGame() {
		return new ResponseEntity<GameStartResponse>(gameProcessor.startGame(), HttpStatus.CREATED);
	}
}
