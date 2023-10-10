package com.altrh.api.model.dto;

import java.util.ArrayList;
import java.util.List;

import com.altrh.api.model.Contest;
import com.altrh.api.model.Game;
import com.altrh.api.tools.DateTime;

import lombok.Data;

@Data
public class GameDTO {
    private Integer id;

    private String title;

    private Integer minPlayers;

    private Integer maxPlayers;

    private List<String> contests;

    public GameDTO (Game game) {
        this.id = game.getId();
        this.title = game.getTitle();
        this.minPlayers = game.getMinPlayers();
        this.maxPlayers = game.getMaxPlayers();
        this.contests = new ArrayList<String>();
        if (game.getContests() != null) {
            for(Contest c : game.getContests()) {
                this.contests.add("Partie n°" + c.getId() + " joué le " + DateTime.format(c.getStartDate()) );
            }
        }
    }
}