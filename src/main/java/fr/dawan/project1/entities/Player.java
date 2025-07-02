package fr.dawan.project1.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;

@Entity
public class Player extends BaseEntity {

    @Column(nullable = false, unique = true)
    private String name;

    @OneToOne
    @JoinColumn(unique=true, updatable=false)
    private Manager manager;



    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Manager getManager() {
        return manager;
    }

    public void setManager(Manager manager) {
        this.manager = manager;
    }
}
