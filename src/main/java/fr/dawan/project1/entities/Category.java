package fr.dawan.project1.entities;

import fr.dawan.project1.dto.CategoryDto;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(indexes = @Index(name="idx_catagory_name", columnList = "name"))
public class Category extends BaseEntity {

    @Column(nullable = false, unique = true)
    private String name;

    /**
     * path where the image is stored
     */
    private String imagePath;

    //si besoin de faire du bi-directionnel
    //@OneToMany(mappedBy = "category", cascade = CascadeType.ALL)
    //private List<Product> products;


//    public List<Product> getProducts() {
//        return products;
//    }
//
//    public void setProducts(List<Product> products) {
//        this.products = products;
//    }


    public Category() {
    }

    public Category(String name, String imagePath) {
        this.name = name;
        this.imagePath = imagePath;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


}
