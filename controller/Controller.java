package controller;

import model.Direction;
import model.World;
import view.ControlPanel;
import view.GamePanel;

import javax.swing.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * Der Controller verarbeitet Tasteneingaben und Aktionen aus der GUI.
 */
public class Controller implements KeyListener {

	private final World world;
	private final GamePanel gamePanel;
	private final ControlPanel controlPanel;
	private final JFrame frame;

	public Controller(World world, GamePanel gamePanel, ControlPanel controlPanel, JFrame frame) {
		this.world = world;
		this.gamePanel = gamePanel;
		this.controlPanel = controlPanel;
		this.frame = frame;

		this.gamePanel.setFocusable(true);
		this.gamePanel.requestFocusInWindow();  // Fokus sicher setzen
		this.gamePanel.addKeyListener(this);    // Jetzt funktioniert Tastatursteuerung

		initActions();
	}


	private void initActions() {
		controlPanel.restartButton.addActionListener(e -> {
			int enemies = (int) controlPanel.difficultyBox.getSelectedItem();
			world.reset(enemies);
			gamePanel.requestFocusInWindow();
		});
	}

	@Override
	public void keyTyped(KeyEvent e) {}


	@Override
	public void keyPressed(KeyEvent e) {
		switch (e.getKeyCode()) {
			case KeyEvent.VK_UP -> world.movePlayer(Direction.UP);
			case KeyEvent.VK_DOWN -> world.movePlayer(Direction.DOWN);
			case KeyEvent.VK_LEFT -> world.movePlayer(Direction.LEFT);
			case KeyEvent.VK_RIGHT -> world.movePlayer(Direction.RIGHT);
			case KeyEvent.VK_SPACE -> gamePanel.requestFocusInWindow();

		}
	}

	@Override
	public void keyReleased(KeyEvent e) {}
}
