package com.altrh.api.controller;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.altrh.api.model.Contest;
import com.altrh.api.model.Game;
import com.altrh.api.model.Player;
import com.altrh.api.model.dto.ContestDTO;
import com.altrh.api.model.dto.PlayerDTO;
import com.altrh.api.service.ContestService;
import com.altrh.api.service.GameService;
import com.altrh.api.service.PlayerService;

@RestController
public class ContestController {

    @Autowired
    ContestService service;

    @Autowired
    PlayerService ps;

    @Autowired
    GameService gameService;

    

    @GetMapping("/contests")
    public List<ContestDTO> all() {
        /** exo : cette méthode doit retourner une liste d'objet contestDTO
         * qui représente tous les Constets de la bdd
         */
        List<ContestDTO> list = new ArrayList<ContestDTO>();
        List<Contest> contestsList = service.getAllContests();
        for (Contest c : contestsList) {
            list.add(new ContestDTO(c));
        }
        return list;
    }

    @GetMapping("/contest/{id}")
    public ContestDTO contest(@PathVariable("id") int identifier) {
        Contest c = service.getContest(identifier);
        return c != null ? new ContestDTO(c) : null;
    }

    @PostMapping("/contest")
    /** L'annotation RequestParam permet de récupérer un paramètre passé dans le  corps de la requête
     * HTTP (méthode POST). Pour récupérer les paramètre passés dans l'URL (méthode GET) il faut utiliser PathParam
     */
    public ContestDTO insertContest(
                                @RequestParam String start_date, 
                                @RequestParam int game_id,
                                @RequestParam Optional<Integer> winner_id) {
        Contest newContest = new Contest();
        /**
         * valueOf est une méthode statique de la classe java.sql.Date qui retourne un objet Date
         * crée à partir d'un String.
         */
        Game g =  gameService.getGame(game_id);
        if (g != null) {
            newContest.setGame(g);
            newContest.setStartDate(Date.valueOf(start_date));
            if (winner_id.isPresent()) {
                Player winner = ps.getPlayer(winner_id.get());
                if (winner != null) {
                    newContest.setWinner(winner);
                }
            }
            return new ContestDTO(service.saveContest(newContest));
        }
        return null;
    }

    @DeleteMapping("/contest/{id}")
    public Boolean deleteContest(@PathVariable("id") int id) {
        Contest p = service.getContest(id);
        if( p != null ) {
            service.deleteContest(id);
            return true;
        } else {
            return false;
        }
    }

    @PutMapping("/contest/{id}")
    public ContestDTO updateContest(@PathVariable("id") int id, @RequestBody Contest p) {
      Contest contestToUpdate = service.getContest(id);

      if( contestToUpdate != null ) {
        p.setId(contestToUpdate.getId());
        return new ContestDTO(service.saveContest(p));
      }
      return null;
    }

    /** Exercice: Ajouter un endpoint pour affihcer tous les joueurs d'une partie 
     * donné (dans l'URL, on récupère l'id de la partie à retourner)
     * 
     * 2. Modifier la classe ContestDTO pour retourner le noms des joueurs de la parties
     */
    @GetMapping("/contest/{id}/players")
    public List<PlayerDTO> contestplayers(@PathVariable("id") int id) {
        Contest c = service.getContest(id);
        if(c != null) {
            List<PlayerDTO> players = new ArrayList<>();
            /**
             * La liste ddes players liés à un objet contest se trouve
             * dans sa propriété "players", que l'on a définit dans l'entité 
             * Contest comme étant la relation ManyToMany avec l'entité Player.
             */
            for (Player player : c.getPlayers()) {
                players.add(new PlayerDTO(player));
            }
            return players;
        }
        return new ArrayList<PlayerDTO>();
        
    }
}