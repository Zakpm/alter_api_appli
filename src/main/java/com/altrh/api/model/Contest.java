package com.altrh.api.model;

import java.sql.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

import com.altrh.api.tools.DateTime;

import lombok.Data;

@Data
@Entity
public class Contest {
    @Id /* clé peimaire */
    @GeneratedValue(strategy = GenerationType.IDENTITY) /* auto-increment */
    private Integer id;

    private Date startDate;
    
    @ManyToOne(cascade = CascadeType.ALL)
    private Game game;

    @ManyToOne
    private Player winner;

    @ManyToMany(mappedBy = "contests")
    private List<Player> players;

    /** La méthode toString() va être exécutée lorsque l'on veut utiliser
     * un objet en tant que caractères.
     * Cette méthode peut être implémentée dans n'importe quelle classe.
     */
    @Override
    public String toString() {
        String text = "partie n°" + this.id + " du jeu " + this.game.getTitle() + " jouée le " + DateTime.format(this.startDate);

        if (this.winner != null) {
            text += ", remportée par  " + this.winner.getNickname();
        }
        return text;
    }
}