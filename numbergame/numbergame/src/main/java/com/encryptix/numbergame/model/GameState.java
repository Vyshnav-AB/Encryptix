package com.encryptix.numbergame.model;

public class GameState {
	private int targetNumber;
	private int attempts;
	private boolean isGameOver;
	private String message;
	private String username;
	private int maxAttempts;
	private int score;
	private int roundsWon;
	private int roundsFailed;

	public GameState(int targetNumber, String username, int maxAttempts) {
		this.targetNumber = targetNumber;
		this.attempts = 0;
		this.isGameOver = false;
		this.message = "";
		this.username = username;
		this.maxAttempts = maxAttempts;
		this.score = 0;
		this.roundsWon = 0;
		this.roundsFailed = 0;
	}

	public int getTargetNumber() {
		return targetNumber;
	}

	public void setTargetNumber(int targetNumber) {
		this.targetNumber = targetNumber;
	}

	public int getAttempts() {
		return attempts;
	}

	public void setAttempts(int attempts) {
		this.attempts = attempts;
	}

	public void incrementAttempts() {
		this.attempts++;
	}

	public boolean isGameOver() {
		return isGameOver;
	}

	public void setGameOver(boolean isGameOver) {
		this.isGameOver = isGameOver;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public int getMaxAttempts() {
		return maxAttempts;
	}

	public void setMaxAttempts(int maxAttempts) {
		this.maxAttempts = maxAttempts;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	public int getRoundsWon() {
		return roundsWon;
	}

	public void setRoundsWon(int roundsWon) {
		this.roundsWon = roundsWon;
	}

	public int getRoundsFailed() {
		return roundsFailed;
	}

	public void setRoundsFailed(int roundsFailed) {
		this.roundsFailed = roundsFailed;
	}

}
