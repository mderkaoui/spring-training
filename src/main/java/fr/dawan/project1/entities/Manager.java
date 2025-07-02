package fr.dawan.project1.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToOne;

@Entity
public class Manager extends BaseEntity {

    @Column(nullable = false, unique = true)
    private String name;

    @OneToOne(mappedBy="manager")
    private Player player;


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
