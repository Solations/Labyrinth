package view;

import javax.swing.*;
import java.awt.*;

/**
 * Panel mit Steuerelementen: Schwierigkeitsgrad, Neustart
 */
public class ControlPanel extends JPanel {
    public JButton restartButton;
    public JComboBox<Integer> difficultyBox;

    public ControlPanel() {
        setLayout(new FlowLayout());

        restartButton = new JButton("Neustart");
        difficultyBox = new JComboBox<>(new Integer[]{1, 2, 3, 5, 10});
        difficultyBox.setSelectedItem(2);

        add(new JLabel("Gegner:"));
        add(difficultyBox);
        add(restartButton);
    }
}
