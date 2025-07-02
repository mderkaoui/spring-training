package fr.dawan.project1.dto;

public class SupplierProdDto {

    private String supplierName;
    private String productName;

    public SupplierProdDto() {
    }

    public SupplierProdDto(String supplierName, String productName) {
        this.supplierName = supplierName;
        this.productName = productName;
    }

    public String getSupplierName() {
        return supplierName;
    }

    public void setSupplierName(String supplierName) {
        this.supplierName = supplierName;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }
}
