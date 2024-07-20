package com.encryptix.atm.controller;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.encryptix.atm.model.Model_class;

@Controller
public class Controller_class {

    private Model_class bankAccount;

    public  Controller_class() {
        this.bankAccount = new Model_class(10000.00);
    }

    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("message", "");
        return "ATM";
    }

    @PostMapping("/atm/deposit")
    public String deposit(@RequestParam double amount, Model model) {
        if (amount <= 0) {
            model.addAttribute("message", "Invalid amount. Deposit must be greater than zero.");
            return "ATM";
        }
        bankAccount.deposit(amount);
        model.addAttribute("message", "Successfully deposited $" + amount + ". Current balance: $" + bankAccount.getBalance());
        return "ATM";
    }

    @PostMapping("/atm/withdraw")
    public String withdraw(@RequestParam double amount, Model model) {
        if (amount <= 0) {
            model.addAttribute("message", "Invalid amount. Withdrawal must be greater than zero.");
            return "ATM";
        }
        boolean success = bankAccount.withdraw(amount);
        if (success) {
            model.addAttribute("message", "Successfully withdrew $" + amount + ". Current balance: $" + bankAccount.getBalance());
        } else {
            model.addAttribute("message", "Insufficient funds. Current balance: $" + bankAccount.getBalance());
        }
        return "ATM";
    }

    @GetMapping("/atm/balance")
    public String checkBalance(Model model) {
        model.addAttribute("message", "Current balance: $" + bankAccount.getBalance());
        return "ATM";
    }
}
