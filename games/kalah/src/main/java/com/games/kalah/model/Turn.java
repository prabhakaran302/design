package com.games.kalah.model;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Turn implements Serializable {
	private static final long serialVersionUID = -2351349997599744884L;
	private int lastSownIndex;
	private int currentPitId;
}
