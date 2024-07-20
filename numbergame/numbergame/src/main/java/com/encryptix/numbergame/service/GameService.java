package com.encryptix.numbergame.service;

import com.encryptix.numbergame.model.GameState;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class GameService {
	private GameState gameState;
	private final Random random = new Random();

	public void startNewGame(String username, int maxAttempts) {
		int targetNumber = random.nextInt(100) + 1;
		if (gameState == null || !gameState.getUsername().equals(username)) {
			gameState = new GameState(targetNumber, username, maxAttempts);
		} else {
			gameState.setTargetNumber(targetNumber);
			gameState.setAttempts(0);
			gameState.setGameOver(false);
			gameState.setMessage("");
			gameState.setMaxAttempts(maxAttempts);
		}
	}

	public GameState makeGuess(int guess) {
		if (gameState == null || gameState.isGameOver()) {
			gameState.setMessage("No active game. Please start a new game.");
			return gameState;
		}

		gameState.incrementAttempts();

		if (guess < gameState.getTargetNumber()) {
			gameState.setMessage("Too low!");
		} else if (guess > gameState.getTargetNumber()) {
			gameState.setMessage("Too high!");
		} else {
			gameState.setGameOver(true);
			gameState.setRoundsWon(gameState.getRoundsWon() + 1);
			int roundScore = (gameState.getMaxAttempts() - gameState.getAttempts() + 1) * 10;
			gameState.setScore(gameState.getScore() + roundScore);
			gameState.setMessage("Correct! You guessed the number in " + gameState.getAttempts()
					+ " attempts. You scored " + roundScore + " points.");
		}

		if (gameState.getAttempts() >= gameState.getMaxAttempts() && !gameState.isGameOver()) {
			gameState.setGameOver(true);
			gameState.setRoundsFailed(gameState.getRoundsFailed() + 1);
			gameState.setMessage("Game Over! You've used all your attempts. The correct number was "
					+ gameState.getTargetNumber() + ".");
		}

		return gameState;
	}

	public void resetGame() {
		gameState = null;
	}

	public GameState getGameState() {
		return gameState;
	}
}
