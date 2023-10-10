package com.altrh.api.service;


import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.altrh.api.model.Game;
import com.altrh.api.repository.GameRepository;

@Service
public class GameService {
    @Autowired
    private GameRepository gameRepository;

    /** L'annotation Autowired permet de ne pas ajouter le constructeur 
     * pour réaliser l'injection de dépendance pour la proprété gameRépository
     */
    // public GameService (GameRepository gameRepository) {
    //     this.gameRepository = gameRepository;
    // }

    /**
     * Récupérer tous les jeux
     */
    public List<Game> getAllGames() {
        return (List<Game>) gameRepository.findAll();
    }
    
    /**!SECTION
     * Récupérer un jeu à partir de son identifiant 
     */
    public Game getGame(int id) {
        Optional<Game> optional = gameRepository.findById(id);
        // if (optional.isPresent()) {
        //     return optional.get(); // retourne l'objet Game qui est présent dans l'obejet Optional
        // }
        // return null;

        return optional.isPresent() ? optional.get() : null;
    }

    /**
     * La méthode saveGame sera utilisée pour les insert et les updates.
     * @param g Game
     * @return Game : retourne le jeu inséré ou actulisé
     */
    public Game saveGame(Game g) {
        return gameRepository.save(g);
    }

    /**  
     * Supprime un jeu selon son id
     */
    public void deleteGame(int id) {
        gameRepository.deleteById(id);
    }


}