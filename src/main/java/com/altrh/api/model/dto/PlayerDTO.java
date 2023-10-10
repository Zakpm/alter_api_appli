package com.altrh.api.model.dto;

import java.util.ArrayList;
import java.util.List;

import com.altrh.api.model.Contest;
import com.altrh.api.model.Player;
import com.altrh.api.tools.DateTime;

import lombok.Data;

@Data
public class PlayerDTO {
    private Integer id;

    private String email;

    private String nickname;

    private List<String> wins;

    private List<String> contests;
    

    public PlayerDTO(Player p) {
        this.id = p.getId();
        this.email = p.getEmail();
        this.nickname = p.getNickname();

        this.wins = new ArrayList<String>();
        if (p.getWins() != null ) {
            for (Contest c : p.getWins()) {
                this.wins.add(c.toString());
            }
        }

        this.contests = new ArrayList<String>();
        if (p.getContests() != null ) {
            for (Contest c : p.getContests()) {
                this.contests.add(c + "");
            }
        }

    }
    
}