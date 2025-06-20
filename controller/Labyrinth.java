package controller;

import model.World;
import view.ConsoleView;
import view.ControlPanel;
import view.GamePanel;

import javax.swing.*;
import java.awt.*;

/**
 * Main-Klasse zur Initialisierung von Model, View und Controller.
 */
public class Labyrinth {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            int width = 21;
            int height = 21;
            int enemies = 2;

            World world = new World(width, height, enemies);

            Dimension fieldSize = new Dimension(25, 25);
            GamePanel gamePanel = new GamePanel(fieldSize);
            ControlPanel controlPanel = new ControlPanel();
            //ConsoleView consoleView = new ConsoleView();

            world.registerView(gamePanel);
            //world.registerView(consoleView);

            JFrame frame = new JFrame("Labyrinth-Person");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setLayout(new BorderLayout());
            frame.add(gamePanel, BorderLayout.CENTER);
            frame.add(controlPanel, BorderLayout.SOUTH);
            frame.pack();
            frame.setResizable(false);
            frame.setVisible(true);

            new Controller(world, gamePanel, controlPanel, frame);
        });
    }
}
