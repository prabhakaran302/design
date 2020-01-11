package com.games.kalah.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.games.kalah.service.KalahService;
import com.games.kalah.service.dto.GameRequest;
import com.games.kalah.service.dto.GameResponse;
import com.games.kalah.service.dto.GameStartResponse;
import com.games.kalah.util.Constants;

@RestController
@RequestMapping(value = "/", produces = { "application/json" })
public class KalahController {

	@Autowired
	private KalahService kalahService;

	@PutMapping(value = Constants.Api.TURNS)
	public ResponseEntity<?> makeTurn(@Valid @RequestBody GameRequest gameRequest, BindingResult bindingResult) {
		try {
			if (bindingResult.hasErrors()) {
				return new ResponseEntity<String>("Error in request", HttpStatus.BAD_REQUEST);
			} else {
				return new ResponseEntity<GameResponse>(kalahService.processGame(gameRequest), HttpStatus.OK);
			}
		} catch (Exception e) {
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PostMapping(value = Constants.Api.GAME)
	public ResponseEntity<?> startGame() {
		try {
			return new ResponseEntity<GameStartResponse>(kalahService.startGame(), HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
