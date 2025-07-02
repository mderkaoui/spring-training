package fr.dawan.project1.dto;

import java.io.Serializable;

public class CategoryDto extends BaseDto implements Serializable {

    private String name;
    private String imagePath;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }
}
