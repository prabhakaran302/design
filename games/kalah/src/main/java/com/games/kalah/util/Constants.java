package com.games.kalah.util;

public interface Constants {

	interface Api	{
		String TURNS = "/games/{gameId}/pits/{pitId}";
		String GAME = "/games";
	}	
	
	String COLON = ":";
	String HTTP = "http";
	String FORWAD_SLASH = "/";
	Long ID_LIMIT = 9999999999L;

}
