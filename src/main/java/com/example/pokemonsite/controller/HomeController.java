package com.example.pokemonsite.controller;

import com.example.pokemonsite.model.Pokemon;
import com.example.pokemonsite.repository.ProductRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.sql.SQLException;

@Controller
public class HomeController {

    ProductRepository productRepository;

    public HomeController(ProductRepository productRepository){
        this.productRepository = productRepository;
    }

    @GetMapping("/")
    public String index(Model model){
        model.addAttribute("pokemons", productRepository.getAll());
        return "index";
    }

    @GetMapping("/create")
    public String showCreate(){
        return "create";
    }

    @PostMapping("/create")
    public String createPokemon(@RequestParam("nr") int newNumber, @RequestParam("name") String newName, @RequestParam("speed") int newSpeed, @RequestParam("special_defence") int newSpecialDefence, @RequestParam("special_attack") int newSpecialAttack, @RequestParam("defence") int newDefence, @RequestParam("attack") int newAttack, @RequestParam("hp") int newHP, @RequestParam("primary_type") String newPrimaryType, @RequestParam("secondary_type") String newSecondaryType)
    {
        Pokemon pokemon = new Pokemon();
        pokemon.setPokedex_number(newNumber);
        pokemon.setName(newName);
        pokemon.setSpeed(newSpeed);
        pokemon.setSpecial_defence(newSpecialDefence);
        pokemon.setSpecial_attack(newSpecialAttack);
        pokemon.setDefence(newDefence);
        pokemon.setAttack(newAttack);
        pokemon.setHp(newHP);
        pokemon.setPrimary_type(newPrimaryType);
        pokemon.setSecondary_type(newSecondaryType);

        productRepository.addPokemon(pokemon);

        return "redirect:/";
    }

}
