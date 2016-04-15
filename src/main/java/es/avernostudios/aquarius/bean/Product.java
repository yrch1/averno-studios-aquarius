package es.avernostudios.aquarius.bean;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
public class Product {
    private String sku;
    private String owner;
    private String additional;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="product_info_id")
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
}
