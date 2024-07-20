package com.encryptix.numbergame.controller;

import com.encryptix.numbergame.model.GameState;
import com.encryptix.numbergame.service.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class GameController {

	@Autowired
	private GameService gameService;

	@GetMapping("/game")
	public String gamePage(Model model) {
		model.addAttribute("gameState", gameService.getGameState());
		return "game";
	}

	@PostMapping("/start")
	public String startNewGame(@RequestParam String username, @RequestParam int maxAttempts, Model model) {
		gameService.startNewGame(username, maxAttempts);
		model.addAttribute("gameState", gameService.getGameState());
		return "redirect:/game";
	}

	@PostMapping("/guess")
	public String makeGuess(@RequestParam int guess, Model model) {
		GameState gameState = gameService.makeGuess(guess);
		model.addAttribute("gameState", gameState);
		return "game";
	}

	@PostMapping("/reset")
	public String resetGame(Model model) {
		gameService.resetGame();
		return "redirect:/game";
	}
}
