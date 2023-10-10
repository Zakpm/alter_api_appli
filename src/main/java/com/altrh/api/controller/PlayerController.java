package com.altrh.api.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.altrh.api.model.Contest;
import com.altrh.api.model.Player;
import com.altrh.api.model.dto.ContestDTO;
import com.altrh.api.model.dto.PlayerDTO;
import com.altrh.api.service.PlayerService;

@RestController
public class PlayerController {

    @Autowired
    PlayerService service;

    @GetMapping("/players")
    public List<PlayerDTO> all() {
        List<PlayerDTO> list = new ArrayList<PlayerDTO>();
        for (Player player : service.getAllPlayers()) {
            list.add(new PlayerDTO(player));
        }
        return list ;
    }

    @GetMapping("/player/{id}")
    public PlayerDTO player(@PathVariable("id") int identifier){
        return new PlayerDTO(service.getPlayer(identifier));
    }

    @PostMapping("/player")
    /**
     * L'annotation de @RequestBody permet de récupérer le contenu du corps de la 
     * requête HTTP qui sera transformé en objet JAVA.
     * @param p
     * @return
     */
    public PlayerDTO insertGame(@RequestBody Player p) {
        return new PlayerDTO(service.savePlayer(p));
    }

    @DeleteMapping("/player/{id}")
    public Boolean deletePlayer(@PathVariable("id") int id) {
        
        /**  
         * Vérifier si le jeu existe, dans ce cas, supprimer le jeu et renvoyer true
         * sinon renvoyer false 
         */
        Player p = service.getPlayer(id);
         if (p != null){
            service.deletePlayer(id);
            return true;
         } else { 
            return false;
         }
    }

    /**
     * Modifier un player qui existe déja en bdd.
     * @param id int : identifiant du jeu à modifier
     * @param p Player : objet PLayer contenant les anciennes valeurs et les valeurs à modifier
     * @return
     */
    @PutMapping("/player/{id}")
    public PlayerDTO updatePlayer(@PathVariable("id") int id, @RequestBody Player p) {
        /**  Exo : terminer la méthode update */
        Player playerToUpdate = service.getPlayer(id);

        if (playerToUpdate != null) {
            p.setId(playerToUpdate.getId());
            return new PlayerDTO(service.savePlayer(p));
        }
        return null;
    }

    @GetMapping("/player/{id}/contests")
    public List<ContestDTO> contests(@PathVariable("id") int id) {
        Player p = service.getPlayer(id);
        List<ContestDTO> listContestDTO = new ArrayList<ContestDTO>();
        if (p != null) {
            for (Contest c : p.getContests()) {
                listContestDTO.add(new ContestDTO(c) );
            }
        }
        return listContestDTO;
    }
    
      
}