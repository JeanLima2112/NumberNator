package com.mycompany.numbergame;

import javax.swing.SwingUtilities;

public class NumberGame {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            GameUI gameUI = new GameUI();
            gameUI.setVisible(true);
        });
    }
}