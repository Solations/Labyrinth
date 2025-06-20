package model;

import controller.GameState;
import view.View;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class World {
	private final int width;
	private final int height;
	private int playerX = 1;
	private int playerY = 1;
	private ArrayList<ArrayList<FieldType>> labyrinth;
	private final ArrayList<View> views = new ArrayList<>();
	private final List<Enemy> enemies = new ArrayList<>();
	private GameState gameState = GameState.RUNNING;

	public World(int width, int height, int enemyCount) {
		this.width = width;
		this.height = height;
		this.labyrinth = MazeGenerator.generate(width, height);
		spawnEnemies(enemyCount);
	}

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

		checkGameState();
		updateViews();
	}

	private void checkGameState() {
		for (Enemy e : enemies) {
			if (e.getX() == playerX && e.getY() == playerY) {
				gameState = GameState.LOST;
				return;
			}
		}

		if (labyrinth.get(playerY).get(playerX) == FieldType.DESTINATION) {
			gameState = GameState.WON;
		}
	}

	public boolean isWalkable(int x, int y) {
		if (x < 0 || y < 0 || x >= width || y >= height) return false;
		return labyrinth.get(y).get(x) != FieldType.WALL;
	}

	public void registerView(View view) {
		views.add(view);
		view.update(this);
	}

	private void updateViews() {
		for (View view : views) {
			view.update(this);
		}
	}

	public void reset(int enemyCount) {
		this.playerX = 1;
		this.playerY = 1;
		this.labyrinth = MazeGenerator.generate(width, height);
		this.gameState = GameState.RUNNING;
		spawnEnemies(enemyCount);
		updateViews();
	}

	// Getter
	public int getWidth() { return width; }
	public int getHeight() { return height; }
	public int getPlayerX() { return playerX; }
	public int getPlayerY() { return playerY; }
	public ArrayList<ArrayList<FieldType>> getLabyrinth() { return new ArrayList<>(labyrinth); }
	public List<Enemy> getEnemies() { return enemies; }
	public GameState getGameState() { return gameState; }
}
