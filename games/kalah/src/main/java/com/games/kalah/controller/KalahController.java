package com.games.kalah.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/kalah")
@RequiredArgsConstructor
public class KalahController {
	@GetMapping
	public void findAll() {
		System.out.println("Done");
	}
}
