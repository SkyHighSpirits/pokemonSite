package com.example.pokemonsite.repository;

import com.example.pokemonsite.model.Pokemon;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class ProductRepository {

    //database-properties
    private final String DB_URL = "jdbc:mysql://localhost:3306/pokedex";
    private final String UID = "root";
    private final String PWD = "0253";

    public List<Pokemon> getAll(){
        List<Pokemon> pokemons = new ArrayList<>();
        try {
            Connection connection = DriverManager.getConnection(DB_URL, UID, PWD);
            Statement statement = connection.createStatement();
            final String SQL_QUERY = "SELECT * FROM pokemon";
            ResultSet resultSet = statement.executeQuery(SQL_QUERY);
            while (resultSet.next()){
                int pokedex_number = resultSet.getInt(1);
                String name = resultSet.getString(2);
                int speed = resultSet.getInt(3);
                int special_defence = resultSet.getInt(4);
                int special_attack = resultSet.getInt(5);
                int defence = resultSet.getInt(6);
                int attack = resultSet.getInt(7);
                int hp = resultSet.getInt(8);
                String primary_type = resultSet.getString(9);
                String secondary_type = resultSet.getString(10);
                Pokemon pokemon = new Pokemon(pokedex_number,name,speed,special_defence,special_attack,defence,attack, hp, primary_type, secondary_type);
                pokemons.add(pokemon);
                System.out.println(pokemon);
            }

        }
        catch(SQLException e)
        {
            System.out.println("Could not query database");
            e.printStackTrace();
        }
        return pokemons;
    }

    public void addPokemon(Pokemon pokemon)
    {
        try {
            Connection connection = DriverManager.getConnection(DB_URL, UID, PWD);
            final String CREATE_QUERY = "INSERT INTO pokemon(pokedex_number, name, speed, special_defence, special_attack, defence, attack, hp, primary_type, secondary_type) VALUES (?,?,?,?,?,?,?,?,?,?)";
            PreparedStatement preparedStatement = connection.prepareStatement(CREATE_QUERY);

            preparedStatement.setInt(1, pokemon.getPokedex_number());
            preparedStatement.setString(2, pokemon.getName());
            preparedStatement.setInt(3, pokemon.getSpeed());
            preparedStatement.setInt(4, pokemon.getSpecial_defence());
            preparedStatement.setInt(5, pokemon.getSpecial_attack());
            preparedStatement.setInt(6, pokemon.getDefence());
            preparedStatement.setInt(7, pokemon.getAttack());
            preparedStatement.setInt(8, pokemon.getHp());
            preparedStatement.setString(9, pokemon.getPrimary_type());
            preparedStatement.setString(10, pokemon.getSecondary_type());

            preparedStatement.executeUpdate();
        }
        catch (SQLException e)
        {
            System.out.println("Could not create a pokemon");
            e.printStackTrace();
        }

    }
}





