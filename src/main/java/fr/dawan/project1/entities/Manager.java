package fr.dawan.project1.entities;

import jakarta.persistence.*;

import java.util.List;


@Entity
public class Manager extends BaseEntity {

    @Column(nullable = false, unique = true)
    private String name;

    @OneToOne(mappedBy="manager")
    private Player player;

    @ElementCollection
    @CollectionTable(name="manager_old_players")
    @Column(name="old_player_name")
    private List<String> oldPlayers;



    public List<String> getOldPlayers() {
        return oldPlayers;
    }

    public void setOldPlayers(List<String> oldPlayers) {
        this.oldPlayers = oldPlayers;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }
}
