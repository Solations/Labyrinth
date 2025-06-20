package model;

public class Enemy {
    private int x, y;

    public Enemy(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void moveTowards(int targetX, int targetY, World world) {
        int dx = Integer.compare(targetX, x);
        int dy = Integer.compare(targetY, y);

        int newX = x + dx;
        int newY = y + dy;

        if (world.isWalkable(newX, newY)) {
            x = newX;
            y = newY;
        }
    }

    public int getX() { return x; }
    public int getY() { return y; }
}
