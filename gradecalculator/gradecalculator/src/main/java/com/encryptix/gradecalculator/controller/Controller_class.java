package com.encryptix.gradecalculator.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.encryptix.gradecalculator.model.Model_class;

@Controller
public class Controller_class {

	@GetMapping("/")
	public String front(Model model) {
		model.addAttribute("marks", new Model_class());
		return "Front";
	}

	@PostMapping("/calculate")
	public String result(@ModelAttribute Model_class mod, Model model) {
		int total = (mod.getBiology() + mod.getChemistry() + mod.getEnglish() + mod.getMaths() + mod.getPhysics());
		mod.setTotalmarks(total);
		double tm = 500.00;
		double percnt = (total / tm) * 100;
		mod.setPercentage(percnt);

		String grad = calculateGrade(percnt);
		mod.setGrade(grad);
		model.addAttribute("results", mod);
		return "Results";
	}

	private String calculateGrade(double averagePercentage) {
		if (averagePercentage >= 90) {
			return "A";
		} else if (averagePercentage >= 80) {
			return "B";
		} else if (averagePercentage >= 70) {
			return "C";
		} else if (averagePercentage >= 60) {
			return "D";
		} else {
			return "F";
		}
	}
}
