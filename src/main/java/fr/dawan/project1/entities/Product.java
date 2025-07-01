package fr.dawan.project1.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Entity //pour indiquer que c'est une entité mappée avec la Bdd
@Table(name = "t_products") //pour spécifier un nom, un schéma, etc...

public class Product extends BaseEntity {

    @Column(nullable = false, unique = true)
    private String name;

    @Column(precision = 2)
    private double price;

    @ManyToOne(cascade=CascadeType.ALL)
    //@JoinColumn(name = "cat_id") si on veut personnaliser la colonne
    private Category category;

    //@ManyToMany
    //@JoinTable
    //private Set<Supplier> suppliers;

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
