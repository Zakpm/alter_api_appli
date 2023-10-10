package com.altrh.api.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import lombok.Data;

@Entity /* cette classe aura le rôle d'une entité = correspond à une table de la bdd  */
@Data /* permet de ne pas ajouter les getter et setters dans la classe */
public class Game {
    @Id /* clé peimaire */
    @GeneratedValue(strategy = GenerationType.IDENTITY) /* auto-increment */
    private Integer id;

    @Column(length = 50, nullable = false)
    private String title;

    @Column(name = "min_players")
    private Integer minPlayers;

    private Integer maxPlayers;

    /** le paramètre mappedBy permet de préciser le nom de la prorpiété qui correspond à la relation
     * dans l'entité qui est lié par cette relation
     */
    @OneToMany(mappedBy = "game")
    private List<Contest> contests;

}