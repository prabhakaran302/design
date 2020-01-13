package com.games.kalah.model;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@EqualsAndHashCode
public class Player implements Serializable {
	private static final long serialVersionUID = 2008813752277302509L;

	String name;
	int storeIndex;
}
