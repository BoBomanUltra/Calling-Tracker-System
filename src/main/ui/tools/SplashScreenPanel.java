package ui.tools;

import ui.MainUIHandler;

import javax.swing.*;
import java.awt.*;

/*
 * SplashScreenPanel represents a splash screen that appears when the application starts.
 * It displays a "C" in blue before transitioning to the main menu after 3 seconds.
 */
public class SplashScreenPanel extends JPanel {
    private MainUIHandler handler;

    // MODIFIES: this
    // EFFECTS: constructs a SplashScreenPanel with a splash label "C" in blue
    public SplashScreenPanel(MainUIHandler handler) {
        this.handler = handler;
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);

        JLabel splashLabel = new JLabel("C");
        splashLabel.setFont(new Font("Serif", Font.BOLD, 100));
        splashLabel.setForeground(Color.BLUE);

        gbc.gridx = 0;
        gbc.gridy = 0;
        add(splashLabel, gbc);
    }
}


