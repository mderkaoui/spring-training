package fr.dawan.project1.dto;

import fr.dawan.project1.entities.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.io.Serializable;

public class ProductDto extends BaseDto implements Serializable {

    private String name;
    private double price;

    private long categoryId;


    public long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(long categoryId) {
        this.categoryId = categoryId;
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
