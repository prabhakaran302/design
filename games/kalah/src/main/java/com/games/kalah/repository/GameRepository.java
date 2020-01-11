package com.games.kalah.repository;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import org.springframework.stereotype.Repository;

import com.games.kalah.domain.Game;

@Repository
public class GameRepository {

	public void save(Game gameEntity) {
		String filename = gameEntity.getId() + "-file.ser";
		try {
			FileOutputStream file = new FileOutputStream(filename);
			ObjectOutputStream out = new ObjectOutputStream(file);
			out.writeObject(gameEntity);
			out.close();
			file.close();
		} catch (IOException ex) {
			System.out.println("Game Object Not Saved in File System....." + ex.getLocalizedMessage());
		}
	}

	public Game getOne(Long gameId) {
		Game game = null;
		try {
			FileInputStream file = new FileInputStream(gameId + "-file.ser");
			ObjectInputStream in = new ObjectInputStream(file);

			game = (Game) in.readObject();

			in.close();
			file.close();
		}

		catch (IOException | ClassNotFoundException ex) {
			System.out.println("Not able to deserialize object " + ex.getLocalizedMessage());
		}
		return game;
	}

}
