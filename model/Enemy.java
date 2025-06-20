package model;

/**
 * A class to represent enemies of the player in the game.
 */
public class Enemy {
    /**
     * The x position of the enemy.
     */
    private int xPosition;
    /**
     * The y position of the enemy.
     */
    private int yPosition;

    /**
     * Creates a new instance of Enemy.
     *
     * @param xPosition
     *          The x position of the new enemy
     * @param yPosition
     *          The y position of the new enemy
     */
    public Enemy(int xPosition, int yPosition) {
        this.xPosition = xPosition;
        this.yPosition = yPosition;
    }

    /**
     * Moves the enemy towards a target position.
     *
     * @param targetX
     *          The x coordinate of the target position.
     * @param targetY
     *          The y coordinate of the target position.
     * @param world
     *          The world the enemy exists in.
     */
    public void moveTowards(int targetX, int targetY, World world) {
        int dx = Integer.compare(targetX, xPosition);
        int dy = Integer.compare(targetY, yPosition);

        int newX = xPosition + dx;
        int newY = yPosition + dy;

        if (world.isWalkable(newX, newY)) {
            xPosition = newX;
            yPosition = newY;
        }
    }

    /**
     * Gets the x position of the enemy.
     *
     * @return
     *      x position of enemy
     */
    public int getXPosition() {
        return xPosition;
    }

    /**
     * Gets the y position of the enemy.
     *
     * @return
     *      y position of enemy
     */
    public int getYPosition() {
        return yPosition;
    }
}
