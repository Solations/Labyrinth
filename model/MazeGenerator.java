package model;

import java.util.*;

/**
 * A class to generate the field of the game.
 */
public class MazeGenerator {

    /**
     * Generates a maze of the given width and height.
     *
     * @param width
     *          The width of the maze
     * @param height
     *          The height of the maze
     * @return
     *          The maze
     */
    public static ArrayList<ArrayList<FieldType>> generate(int width, int height) {
        ArrayList<ArrayList<FieldType>> maze = new ArrayList<>();
        for (int y = 0; y < height; y++) {
            ArrayList<FieldType> row = new ArrayList<>();
            for (int x = 0; x < width; x++) {
                row.add(FieldType.WALL);
            }
            maze.add(row);
        }

        carvePath(maze, 1, 1, width, height);
        maze.get(1).set(1, FieldType.START);
        maze.get(height - 2).set(width - 2, FieldType.DESTINATION);
        return maze;
    }

    /**
     * Carves a path into the given maze.
     *
     * @param maze
     *          The labyrinth to work on
     * @param x
     *          The current x position
     * @param y
     *          The current y position
     * @param width
     *          The width of the maze
     * @param height
     *          The height of the maze
     */
    private static void carvePath(ArrayList<ArrayList<FieldType>> maze, int x, int y, int width, int height) {
        int[] dirs = {0, 1, 2, 3};
        shuffleArray(dirs);
        maze.get(y).set(x, FieldType.WALKABLE);

        for (int dir : dirs) {
            int dx = 0, dy = 0;
            switch (dir) {
                case 0 -> dy = -1;
                case 1 -> dx = 1;
                case 2 -> dy = 1;
                case 3 -> dx = -1;
            }
            int nx = x + dx * 2;
            int ny = y + dy * 2;

            if (ny > 0 && ny < height - 1 && nx > 0 && nx < width - 1 && maze.get(ny).get(nx) == FieldType.WALL) {
                maze.get(y + dy).set(x + dx, FieldType.WALKABLE);
                carvePath(maze, nx, ny, width, height);
            }
        }
    }

    /**
     * Shuffles a given array
     *
     * @param array
     *          The array to be shuffled
     */
    private static void shuffleArray(int[] array) {
        Random rand = new Random();
        for (int i = array.length - 1; i > 0; i--) {
            int j = rand.nextInt(i + 1);
            int temp = array[i];
            array[i] = array[j];
            array[j] = temp;
        }
    }
}
