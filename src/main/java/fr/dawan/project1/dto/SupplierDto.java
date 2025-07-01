package fr.dawan.project1.dto;

import fr.dawan.project1.enumerations.ProductCondition;

import java.io.Serializable;
import java.util.Set;

public class SupplierDto extends BaseDto implements Serializable {

    private String name;

    private Set<Long> productsId;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Long> getProductsId() {
        return productsId;
    }

    public void setProductsId(Set<Long> productsId) {
        this.productsId = productsId;
    }
}
