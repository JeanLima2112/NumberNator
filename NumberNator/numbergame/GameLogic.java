package com.mycompany.numbergame;

import java.util.Random;

public class GameLogic {
    private int numeroSecreto;
    private int tentativas;

    public GameLogic() {
        resetGame();
    }

    public void resetGame() {
        Random random = new Random();
        numeroSecreto = random.nextInt(1000) + 1;
        tentativas = 0;
    }

    public int getTentativas() {
        return tentativas;
    }

    public int getNumeroSecreto() {
        return numeroSecreto;
    }

    public void incrementTentativas() {
        tentativas++;
    }

    public boolean checkGuess(int palpite) {
        incrementTentativas();
        return palpite == numeroSecreto;
    }

    public String getHint() {
        return (numeroSecreto % 2 == 0) ? "Dica: O número é par." : "Dica: O número é ímpar.";
    }
}
