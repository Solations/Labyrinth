package model;

import controller.GameState;
import view.View;

import java.util.ArrayList;
import java.util.Random;

/**
 * The world is our model. It saves the bare minimum of information required to
 * accurately reflect the state of the game. Note how this does not know
 * anything about graphics.
 */
public class World {
	/** The world's width. */
	private final int width;
	/** The world's height. */
	private final int height;
	/** The players x position in the world. */
	private int playerX = 1;
	/** The players y position in the world. */
	private int playerY = 1;
	/** The actual labyrinth.*/
	private ArrayList<ArrayList<FieldType>> labyrinth;
	/** Set of views registered to be notified of world updates. */
	private final ArrayList<View> views = new ArrayList<>();
	/** Set of enemies. */
	private final ArrayList<Enemy> enemies = new ArrayList<>();
	/** The current state of the game. */
	private GameState gameState = GameState.RUNNING;

	/**
	 * Creates a new instant of world.
	 *
	 * @param width
	 * 			The width of the new instance
	 * @param height
	 * 			The height of the new instance
	 * @param enemyCount
	 * 			Number of enemies
	 */
	public World(int width, int height, int enemyCount) {
		this.width = width;
		this.height = height;
		this.labyrinth = MazeGenerator.generate(width, height);
		spawnEnemies(enemyCount);
	}

	/**
	 * Spawns enemies at random positions in the labyrinth.
	 * @param count
	 * 			Number of enemies to be spawned
	 */
	private void spawnEnemies(int count) {
		enemies.clear();
		Random rand = new Random();
		while (enemies.size() < count) {
			int x = rand.nextInt(width);
			int y = rand.nextInt(height);
			if (labyrinth.get(y).get(x) == FieldType.WALKABLE && (x != playerX || y != playerY)) {
				enemies.add(new Enemy(x, y));
			}
		}
	}

	/**
	 * Moves the player along the given direction.
	 *
	 * @param direction
	 * 			Direction where to move
	 */
	public void movePlayer(Direction direction) {
		if (gameState != GameState.RUNNING) return;

		int newX = playerX + direction.deltaX;
		int newY = playerY + direction.deltaY;

		if (isWalkable(newX, newY)) {
			playerX = newX;
			playerY = newY;
		}

		for (Enemy enemy : enemies) {
			enemy.moveTowards(playerX, playerY, this);
		}

		updateGameState();
		updateViews();
	}

	/**
	 * Updates the state of the game.
	 */
	private void updateGameState() {
		for (Enemy e : enemies) {
			if (e.getXPosition() == playerX && e.getYPosition() == playerY) {
				gameState = GameState.LOST;
				return;
			}
		}

		if (labyrinth.get(playerY).get(playerX) == FieldType.DESTINATION) {
			gameState = GameState.WON;
		}
	}

	/**
	 * Returns whether a field is walkable
	 *
	 * @param x
	 * 			x position of the field
	 * @param y
	 * 			y position of the field
	 * @return
	 * 			true if field is walkable, false else
	 */
	public boolean isWalkable(int x, int y) {
		if (x < 0 || y < 0 || x >= width || y >= height) return false;
		return labyrinth.get(y).get(x) != FieldType.WALL;
	}

	/**
	 * registers a new view.
	 *
	 * @param view
	 * 			The view to be registered
	 */
	public void registerView(View view) {
		views.add(view);
		view.update(this);
	}

	/**
	 * Updates all registered views.
	 */
	private void updateViews() {
		for (View view : views) {
			view.update(this);
		}
	}

	/**
	 * Starts a new game.
	 *
	 * @param enemyCount
	 * 			Number of enemies in the new game
	 */
	public void reset(int enemyCount) {
		this.playerX = 1;
		this.playerY = 1;
		this.labyrinth = MazeGenerator.generate(width, height);
		this.gameState = GameState.RUNNING;
		spawnEnemies(enemyCount);
		updateViews();
	}

	// Getter

	/**
	 * Gets the width of the field.
	 *
	 * @return
	 * 		The width
	 */
	public int getWidth() {
		return width;
	}

	/**
	 * Gets the height of the field.
	 *
	 * @return
	 * 		The height
	 */
	public int getHeight() {
		return height;
	}

	/**
	 * Gets the x position of the player.
	 *
	 * @return
	 * 		The x position of the player
	 */
	public int getPlayerX() {
		return playerX;
	}

	/**
	 * Gets the y position of the player.
	 *
	 * @return
	 * 		The y position of the player
	 */
	public int getPlayerY() {
		return playerY;
	}

	/**
	 * Gets a copy of the labyrinth.
	 *
	 * @return
	 * 		The labyrinth.
	 */
	public ArrayList<ArrayList<FieldType>> getLabyrinth() {
		return new ArrayList<>(labyrinth);
	}

	/**
	 * Gets a copy of the list of enemies.
	 *
	 * @return
	 * 		The list of enemies
	 */
	public ArrayList<Enemy> getEnemies() {
		return new ArrayList<>(enemies);
	}

	/**
	 * Gets the current game state.
	 *
	 * @return
	 * 		The game state
	 */
	public GameState getGameState() {
		return gameState;
	}

}
