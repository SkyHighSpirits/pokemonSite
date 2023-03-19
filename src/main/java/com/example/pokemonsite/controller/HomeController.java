package com.example.pokemonsite.controller;

import com.example.pokemonsite.repository.ProductRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

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
}
