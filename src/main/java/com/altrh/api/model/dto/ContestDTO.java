package com.altrh.api.model.dto;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import com.altrh.api.model.Contest;
import com.altrh.api.model.Player;

import lombok.Data;

@Data
public class ContestDTO {
    private Integer id;

    private Date startDate;

    private String game;

    private String winner;

    private List<String> players;

    public ContestDTO (Contest contest) {
        this.id = contest.getId();
        startDate = contest.getStartDate();
        this.game = contest.getGame().getTitle();
        this.winner = contest.getWinner() != null ? contest.getWinner().getNickname() : "pas de vainqueur désigné";
        this.players = new ArrayList<String>();
        if (contest.getPlayers() != null) {
            for (Player p : contest.getPlayers() ) {
                this.players.add(p.getId() + " - " + p.getNickname());
            }
        }
    }
    
}