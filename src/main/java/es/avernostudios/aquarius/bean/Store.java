package es.avernostudios.aquarius.bean;


public class Store {

    
    private int id;
    private String nemo;
    private String requester;
    private String apiUrl;
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
    public Store(int id, String nemo, String requester, String apiUrl,int magentoStoreId) {
        this.id = id;
        this.nemo = nemo;
        this.requester = requester;
        this.apiUrl = apiUrl;
        this.magentoStoreId = magentoStoreId;
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
     * @return the nemo
     */
    public String getNemo() {
        return nemo;
    }

    /**
     * @param nemo the nemo to set
     */
    public void setNemo(String nemo) {
        this.nemo = nemo;
    }

    /**
     * @return the requester
     */
    public String getRequester() {
        return requester;
    }

    /**
     * @param requester the requester to set
     */
    public void setRequester(String requester) {
        this.requester = requester;
    }

        /**
     * @return the apiUrl
     */
    public String getApiUrl() {
        return apiUrl;
    }

    /**
     * @param apiUrl the apiUrl to set
     */
    public void setApiUrl(String apiUrl) {
        this.apiUrl = apiUrl;
    }

    /**
     * @return the magentoStoreId
     */
    public int getMagentoStoreId() {
        return magentoStoreId;
    }

    /**
     * @param magentoStoreId the magentoStoreId to set
     */
    public void setMagentoStoreId(int magentoStoreId) {
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
        result += " apiUrl: "+getApiUrl();
        result += " magentoStoreId: "+getMagentoStoreId();

        return result;
    }



}
