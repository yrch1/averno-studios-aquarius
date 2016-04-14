package es.avernostudios.aquarius.aquarius.bean;

import java.util.Date;

public class Product {
    private String sku;
    private String owner;
    private String additional;
    private int id;
    private Date modification;
    private String supplier;

    /**
     *
     */
    public Product() {
    }

    /**
     *
     * @param sku
     * @param owner
     * @param additional
     */
    public Product(String sku, String owner, String additional, String supplier) {
        this.sku = sku;
        this.owner = owner;
        this.additional = additional;
        this.supplier = supplier;
    }

    /**
     * @return the sku
     */
    public String getSku() {
        return sku;
    }

    /**
     * @param sku the sku to set
     */
    public void setSku(String sku) {
        this.sku = sku;
    }

    /**
     * @return the owner
     */
    public String getOwner() {
        return owner;
    }

    /**
     * @param owner the owner to set
     */
    public void setOwner(String owner) {
        this.owner = owner;
    }

    /**
     * @return the additional
     */
    public String getAdditional() {
        return additional;
    }

    /**
     * @param additional the additional to set
     */
    public void setAdditional(String additional) {
        this.additional = additional;
    }

    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * @return the modification
     */
    public Date getModification() {
        return modification;
    }

    /**
     * @param modification the modification to set
     */
    public void setModification(Date modification) {
        this.modification = modification;
    }

    /**
     * @return the supplier
     */
    public String getSupplier() {
        return supplier;
    }

    /**
     * @param supplier the supplier to set
     */
    public void setSupplier(String supplier) {
        this.supplier = supplier;
    }
}
