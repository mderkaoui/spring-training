package fr.dawan.project1.dto;

import fr.dawan.project1.enumerations.ProductCondition;

import java.io.Serializable;
import java.util.Set;

public class SupplierDto extends BaseDto implements Serializable {

    private String name;

    private Set<Long> suppliersId;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Long> getSuppliersId() {
        return suppliersId;
    }

    public void setSuppliersId(Set<Long> suppliersId) {
        this.suppliersId = suppliersId;
    }
}
