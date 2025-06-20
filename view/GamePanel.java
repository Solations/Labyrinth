package view;

import controller.GameState;
import model.Enemy;
import model.FieldType;
import model.World;

import javax.swing.*;
import java.awt.*;

/**
 * Zeichnet das Spielfeld, Spieler und Gegner. Ersetzt GraphicView, weil schöner hehe
 */
public class GamePanel extends JPanel implements View {

    private final Dimension fieldSize;
    private World world;

    public GamePanel(Dimension fieldSize) {
        this.fieldSize = fieldSize;
        setPreferredSize(new Dimension(21 * fieldSize.width, 21 * fieldSize.height));
    }

    @Override
    public void update(World world) {
        this.world = world;
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (world == null) return;

        for (int y = 0; y < world.getHeight(); y++) {
            for (int x = 0; x < world.getWidth(); x++) {
                FieldType type = world.getLabyrinth().get(y).get(x);
                switch (type) {
                    case WALL -> g.setColor(Color.DARK_GRAY);
                    case WALKABLE -> g.setColor(Color.LIGHT_GRAY);
                    case START -> g.setColor(Color.GREEN);
                    case DESTINATION -> g.setColor(Color.BLUE);
                }
                g.fillRect(x * fieldSize.width, y * fieldSize.height, fieldSize.width, fieldSize.height);
            }
        }

        // Spieler zeichnen
        g.setColor(Color.YELLOW);
        g.fillOval(world.getPlayerX() * fieldSize.width, world.getPlayerY() * fieldSize.height,
                fieldSize.width, fieldSize.height);

        // Gegner zeichnen
        g.setColor(Color.RED);
        for (Enemy enemy : world.getEnemies()) {
            g.fillOval(enemy.getX() * fieldSize.width, enemy.getY() * fieldSize.height,
                    fieldSize.width, fieldSize.height);
        }

        // Spielstatus anzeigen
        if (world.getGameState() == GameState.LOST) {
            drawMessage(g, "GAME OVER");
        } else if (world.getGameState() == GameState.WON) {
            drawMessage(g, "YOU WIN");
        }
    }

    private void drawMessage(Graphics g, String msg) {
        g.setColor(Color.BLACK);
        g.setFont(new Font("Arial", Font.BOLD, 36));
        FontMetrics fm = g.getFontMetrics();
        int x = (getWidth() - fm.stringWidth(msg)) / 2;
        int y = getHeight() / 2;
        g.drawString(msg, x, y);
    }
}
