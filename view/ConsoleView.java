package view;

import model.FieldType;
import model.World;

import java.util.ArrayList;

/**
 * A view that prints the current state of the world to the console upon every
 * update.
 */
public class ConsoleView implements View {

	@Override
	public void update(World world) {
		// The player's position
		final int playerX = world.getPlayerX();
		final int playerY = world.getPlayerY();
		final ArrayList<ArrayList<FieldType>> labyrinth = world.getLabyrinth();

		for (int row = 0; row < world.getHeight(); row++) {
			for (int col = 0; col < world.getWidth(); col++) {
				// If the player is here, print #, otherwise print .
				if (row == playerY && col == playerX) {
					System.out.print("*");
				} else {
					FieldType field = labyrinth.get(row).get(col);
					switch (field) {
						case WALL -> System.out.print("#");
						case WALKABLE ->  System.out.print("-");
						case START -> System.out.print("S");
						case DESTINATION -> System.out.print("D");
					}
				}
			}

			// A newline after every row
			System.out.println();
		}

		// A newline between every update
		System.out.println();
	}

}
