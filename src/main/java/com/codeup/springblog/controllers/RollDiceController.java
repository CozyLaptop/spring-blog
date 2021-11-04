package com.codeup.springblog.controllers;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class RollDiceController {
    @GetMapping("/roll-dice")
    public String showDiceRoll() {
        return "diceRoll";
    }

    @PostMapping("/roll-dice")
    public String postDiceRoll(@RequestParam(name = "userGuess")String userGuess, Model model) {
        int diceRoll = (int) (Math.random() * 5 + 1);
        System.out.println(diceRoll);
        model.addAttribute("userGuess", "Your guess is " + userGuess);
        model.addAttribute("diceRoll", "The dice roll is " + diceRoll);
        if (userGuess.equals(String.valueOf(diceRoll))){
            model.addAttribute("answer", "You guessed correctly! Good job!");
        } else model.addAttribute("answer", "You guessed incorrectly! Try again.");
        return "diceRoll";
    }
}
