package com.altrh.api.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.altrh.api.model.Player;
import com.altrh.api.repository.PlayerRepository;


@Service
public class PlayerService {
    @Autowired
    private PlayerRepository playerRepository;

    public List<Player> getAllPlayers() {
        return (List<Player>) playerRepository.findAll();
    }

    public Player getPlayer(int id) {
        Optional<Player> optional = playerRepository.findById(id);
        // if (optional.isPresent()) {
        //     return optional.get(); // retourne l'objet Game qui est présent dans l'obejet Optional
        // }
        // return null;

        return optional.isPresent() ? optional.get() : null;
    }

     /**
     * La méthode saveGame sera utilisée pour les insert et les updates.
     * @param p Player
     * @return Game : retourne le jeu inséré ou actulisé
     */
    public Player savePlayer(Player p) {
        return playerRepository.save(p);
    }

    /**  
     * Supprime un jeu selon son id
     */
    public void deletePlayer(int id) {
        playerRepository.deleteById(id);
    }


    
}