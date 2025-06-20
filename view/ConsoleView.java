package view;

import model.FieldType;
import model.World;

import java.util.ArrayList;

/**
 * Gibt die Welt in der Konsole aus (nur zu Debug-Zwecken).
 */
public class ConsoleView implements View {
	@Override
	public void update(World world) {
		final int playerX = world.getPlayerX();
		final int playerY = world.getPlayerY();
		final ArrayList<ArrayList<FieldType>> labyrinth = world.getLabyrinth();

		for (int row = 0; row < world.getHeight(); row++) {
			for (int col = 0; col < world.getWidth(); col++) {
				if (row == playerY && col == playerX) {
					System.out.print("*");
				} else {
					FieldType field = labyrinth.get(row).get(col);
					switch (field) {
						case WALL -> System.out.print("#");
						case WALKABLE -> System.out.print("-");
						case START -> System.out.print("S");
						case DESTINATION -> System.out.print("D");
					}
				}
			}
			System.out.println();
		}
		System.out.println();
	}
}
