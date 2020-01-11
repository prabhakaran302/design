package com.games.kalah.domain;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class Player implements Serializable {
	private static final long serialVersionUID = 2008813752277302509L;

	String name;
	int storeIndex;
}
