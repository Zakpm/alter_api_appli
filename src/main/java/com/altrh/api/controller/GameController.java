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
import com.altrh.api.model.Game;
import com.altrh.api.model.dto.GameDTO;
import com.altrh.api.service.GameService;

@RestController
/**!SECTION
 * L'annotation RestController, chaque méthode du controller va retourner du JSON.
 * Tout ce qui sera retourné sera transformé en JSON. C'est l'annotation à utiliser pour une API.
 */
public class GameController {

    @Autowired 
    GameService service;

    @GetMapping("/games")
    public List<GameDTO> all() {
        List<GameDTO> list = new ArrayList<GameDTO>();
        List<Game> gameList = service.getAllGames();

        for (Game c : gameList) {
            list.add(new GameDTO(c));
        }
        return list; 
    }

    /**
     * la partie entre {} de l'URL est une partie variable.
     * on dit que c'est un paramètre de la route.
     */
    @GetMapping("/game/{id}")
    public GameDTO game(@PathVariable("id") int identifier){
        Game g = service.getGame(identifier);
        return new GameDTO(g);

    }


    @PostMapping("/game")
    /**
     * L'annotation de @RequestBody permet de récupérer le contenu du corps de la 
     * requête HTTP qui sera transformé en objet JAVA.
     * @param g
     * @return
     */
    public GameDTO insertGame(@RequestBody Game g) {
        // TODO : contrôler les valeurs avant la sauvegarde
        return new GameDTO(service.saveGame(g));
    }

    @DeleteMapping("/game/{id}")
    public Boolean deleteGame(@PathVariable("id") int id) {
        
        /**  
         * Vérifier si le jeu existe, dans ce cas, supprimer le jeu et renvoyer true
         * sinon renvoyer false 
         */
        Game g = service.getGame(id);
         if (g != null){
            service.deleteGame(id);
            return true;
         } else { 
            return false;
         }
    }

    /**
     * Modifier un jeu qui existe déja en bdd.
     * @param id int : identifiant du jeu à modifier
     * @param g Game : objet Game contenant les anciennes valeurs et les valeurs à modifier
     * @return
     */
    @PutMapping("/game/{id}")
    public GameDTO updateGame(@PathVariable("id") int id, @RequestBody Game g) {
        /**  Exo : terminer la méthode update */
        Game gameToUpdate = service.getGame(id);

        if (gameToUpdate != null) {
            if (g.getTitle()!= null) {
                gameToUpdate.setTitle(g.getTitle());
            }
            if(g.getMinPlayers()!= null) {
                gameToUpdate.setMinPlayers(g.getMinPlayers());
            }
            if(g.getMaxPlayers()!= null) {
                gameToUpdate.setMaxPlayers(g.getMaxPlayers());
            }
            service.saveGame(gameToUpdate);
            return new GameDTO(gameToUpdate);
        }
        return null;
    }
     
    @GetMapping("/game/{id}/contests")
    public List<Contest> contests(@PathVariable("id") int id) {
        Game game = service.getGame(id);
        if(game != null) {
            return game.getContests();
        }
        return null;
    }
}

