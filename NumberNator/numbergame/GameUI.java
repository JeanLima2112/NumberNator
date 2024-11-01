package NumberNator.numbergame;
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
        setSize(400, 600);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new GridBagLayout());
        getContentPane().setBackground(new Color(34, 34, 34));

        titleLabel = new JLabel("Tente Adivinhar o Número Secreto", SwingConstants.LEFT);
        titleLabel.setFont(new Font("Roboto", Font.BOLD, 20));
        titleLabel.setForeground(new Color(255, 215, 0));

        feedbackLabel = new JLabel("Digite um número entre 1 e 1000", SwingConstants.LEFT);
        feedbackLabel.setForeground(new Color(255, 165, 0));

        inputField = new JTextField(10);
        inputField.setHorizontalAlignment(JTextField.CENTER);
        inputField.addActionListener(new GuessListener());
        inputField.setForeground(Color.WHITE); 
        inputField.setBackground(new Color(34, 34, 34));
        inputField.setBorder(new RoundBorder(15, Color.GRAY));

        guessButton = new JButton("Adivinhar");
        guessButton.addActionListener(new GuessListener());
        guessButton.setForeground(Color.RED);
        guessButton.setBorder(new RoundBorder(15, Color.RED)); 
        guessButton.setContentAreaFilled(false);  

        hintButton = new JButton("Dica");
        hintButton.setForeground(Color.GREEN);
        hintButton.addActionListener(e -> showHint());
        hintButton.setBorder(new RoundBorder(15, Color.GREEN));
        hintButton.setContentAreaFilled(false);

        restartButton = new JButton("Reiniciar");
        restartButton.addActionListener(e -> resetGame());
        restartButton.setVisible(false);
        restartButton.setForeground(Color.CYAN); 
        restartButton.setBorder(new RoundBorder(15, Color.CYAN));
        restartButton.setContentAreaFilled(false);

        attemptsLabel = new JLabel("Tentativas: 0", SwingConstants.LEFT);
        attemptsLabel.setForeground(new Color(224, 224, 224));

        hintLabel = new JLabel("", SwingConstants.CENTER);
        hintLabel.setForeground(new Color(50, 205, 50));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(0, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 1;
        add(attemptsLabel, gbc);

        gbc.gridy++;
        add(new JLabel(" "), gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        gbc.gridwidth = 2;
        add(titleLabel, gbc);

        for (int i = 0; i < 2; i++) {
            gbc.gridy++;
            add(new JLabel(" "), gbc);
        }
        gbc.gridy++;
        add(feedbackLabel, gbc);

        gbc.gridy++;
        add(new JLabel(" "), gbc);

        gbc.gridy++;
        add(inputField, gbc);

        gbc.gridy++;
        add(new JLabel(" "), gbc);

        gbc.gridwidth = 2;
        gbc.gridy++;
        gbc.gridx = 0;
        add(guessButton, gbc);

        gbc.gridy++;
        add(new JLabel(" "), gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        add(hintButton, gbc);

        gbc.gridy++;
        add(restartButton, gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        add(hintLabel, gbc);

        for (int i = 0; i < 3; i++) {
            gbc.gridy++;
            add(new JLabel(" "), gbc);
        }
    }

    private void showHint() {
        hintButton.setVisible(false);
        hintLabel.setText(gameLogic.getHint());
    }

    private void resetGame() {
        gameLogic.resetGame();
        feedbackLabel.setText("Digite um número entre 1 e 1000");
        attemptsLabel.setText("Tentativas: 0");
        hintLabel.setText("");
        hintLabel.setVisible(true);
        hintButton.setVisible(true);
        guessButton.setVisible(true);
        restartButton.setVisible(false);
        inputField.setVisible(true);
    }

    private class GuessListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                int palpite = Integer.parseInt(inputField.getText());
                boolean acertou = gameLogic.checkGuess(palpite);

                if (acertou) {
                    feedbackLabel.setText("Parabéns! Você acertou o número!");
                    guessButton.setVisible(false);
                    hintButton.setVisible(false);
                    restartButton.setVisible(true);
                    inputField.setVisible(false);
                    hintLabel.setVisible(false);
                } else {
                    feedbackLabel.setText(palpite < gameLogic.getNumeroSecreto() ? "O número secreto é maior."
                            : "O número secreto é menor.");
                }

                attemptsLabel.setText("Tentativas: " + gameLogic.getTentativas());
            } catch (NumberFormatException ex) {
                feedbackLabel.setText("Por favor, insira um número válido.");
            }
            inputField.setText("");
        }
    }
}
