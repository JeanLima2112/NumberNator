package com.mycompany.numbergame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GameUI extends JFrame {
    private GameLogic gameLogic;
    
    private JTextField inputField;
    private JButton guessButton, hintButton, restartButton;
    private JLabel titleLabel, feedbackLabel, attemptsLabel, hintLabel;

    public GameUI() {
        gameLogic = new GameLogic();
        
        setTitle("NumberNator");
        setSize(350, 600);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new GridBagLayout());

        titleLabel = new JLabel("Tente Adivinhar o Número Secreto", SwingConstants.LEFT);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 16));
        
        feedbackLabel = new JLabel("Digite um número entre 1 e 100", SwingConstants.CENTER);
        feedbackLabel.setForeground(Color.BLUE);
        
        inputField = new JTextField();
        inputField.setHorizontalAlignment(JTextField.CENTER);
        
        guessButton = new JButton("Adivinhar");
        guessButton.addActionListener(new GuessListener());

        hintButton = new JButton("Dica");
        hintButton.addActionListener(e -> showHint());
        
        restartButton = new JButton("Reiniciar");
        restartButton.addActionListener(e -> resetGame());
        restartButton.setVisible(false); 

        attemptsLabel = new JLabel("Tentativas: 0", SwingConstants.CENTER);
        hintLabel = new JLabel("", SwingConstants.CENTER);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        add(titleLabel, gbc);

        gbc.gridy = 1;
        add(feedbackLabel, gbc);

        gbc.gridy = 2;
        add(inputField, gbc);

        gbc.gridwidth = 1;
        gbc.gridy = 3;
        gbc.gridx = 0;
        add(guessButton, gbc);

        gbc.gridx = 1;
        add(hintButton, gbc);

        gbc.gridy = 4;
        gbc.gridx = 0;
        gbc.gridwidth = 2;
        add(attemptsLabel, gbc);

        gbc.gridy = 5;
        add(hintLabel, gbc);

        gbc.gridy = 6;
        add(restartButton, gbc);
    }

    private void showHint() {
        hintLabel.setText(gameLogic.getHint());
    }

    private void resetGame() {
        gameLogic.resetGame();
        feedbackLabel.setText("Digite um número entre 1 e 100");
        attemptsLabel.setText("Tentativas: 0");
        hintLabel.setText("");
        guessButton.setEnabled(true);
        restartButton.setVisible(false);
    }

    private class GuessListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                int palpite = Integer.parseInt(inputField.getText());
                boolean acertou = gameLogic.checkGuess(palpite);

                if (acertou) {
                    feedbackLabel.setText("Parabéns! Você acertou o número!");
                    guessButton.setEnabled(false); 
                    restartButton.setVisible(true); 
                } else {
                    feedbackLabel.setText(palpite < gameLogic.getNumeroSecreto() ? "O número secreto é maior." : "O número secreto é menor.");
                }

                attemptsLabel.setText("Tentativas: " + gameLogic.getTentativas());
            } catch (NumberFormatException ex) {
                feedbackLabel.setText("Por favor, insira um número válido.");
            }
            inputField.setText("");
        }
    }
}
