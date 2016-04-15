package es.avernostudios.aquarius.bean;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class Store {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="store_info_id")
    private int id;
    private String nemo;
    private String requester;
    private String endpoint;
    @Column(name="magento_store_id")
    private int magentoStoreId;


    /**
     * 
     */
    public Store(){

    }

    /**
     * 
     * @param id
     * @param nemo
     * @param requester
     */
    public Store(int id, String nemo, String requester, String endpoint,int magentoStoreId) {
        this.id = id;
        this.nemo = nemo;
        this.requester = requester;
        this.endpoint = endpoint;
        this.magentoStoreId = magentoStoreId;
    }

    /**
     * 
     * @return
     */
    @Override
    public String toString(){

        String result = "";

        result += " id: " + getId();
        result += " nemo: " +getNemo();
        result += " requester: "+getRequester();
        result += " endpoint: "+ getEndpoint();
        result += " magentoStoreId: "+getMagentoStoreId();

        return result;
    }



}
