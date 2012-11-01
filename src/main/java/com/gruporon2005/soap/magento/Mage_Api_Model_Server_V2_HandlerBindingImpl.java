/**
 * Mage_Api_Model_Server_V2_HandlerBindingImpl.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.gruporon2005.soap.magento;

public class Mage_Api_Model_Server_V2_HandlerBindingImpl implements com.gruporon2005.soap.magento.Mage_Api_Model_Server_V2_HandlerPortType{
    public boolean endSession(java.lang.String sessionId) throws java.rmi.RemoteException {
        return false;
    }

    public java.lang.String login(java.lang.String username, java.lang.String apiKey) throws java.rmi.RemoteException {
        return null;
    }

    public java.lang.String startSession() throws java.rmi.RemoteException {
        return null;
    }

    public com.gruporon2005.soap.magento.ApiEntity[] resources(java.lang.String sessionId) throws java.rmi.RemoteException {
        return null;
    }

    public com.gruporon2005.soap.magento.ExistsFaltureEntity[] globalFaults(java.lang.String sessionId) throws java.rmi.RemoteException {
        return null;
    }

    public com.gruporon2005.soap.magento.ExistsFaltureEntity[] resourceFaults(java.lang.String resourceName, java.lang.String sessionId) throws java.rmi.RemoteException {
        return null;
    }

    public com.gruporon2005.soap.magento.DirectoryCountryEntity[] directoryCountryList(java.lang.String sessionId) throws java.rmi.RemoteException {
        return null;
    }

    public com.gruporon2005.soap.magento.DirectoryRegionEntity[] directoryRegionList(java.lang.String sessionId, java.lang.String country) throws java.rmi.RemoteException {
        return null;
    }

    public com.gruporon2005.soap.magento.CustomerCustomerEntity[] customerCustomerList(java.lang.String sessionId, com.gruporon2005.soap.magento.Filters filters) throws java.rmi.RemoteException {
        return null;
    }

    public int customerCustomerCreate(java.lang.String sessionId, com.gruporon2005.soap.magento.CustomerCustomerEntityToCreate customerData) throws java.rmi.RemoteException {
        return -3;
    }

    public com.gruporon2005.soap.magento.CustomerCustomerEntity customerCustomerInfo(java.lang.String sessionId, int customerId, java.lang.String[] attributes) throws java.rmi.RemoteException {
        return null;
    }

    public boolean customerCustomerUpdate(java.lang.String sessionId, int customerId, com.gruporon2005.soap.magento.CustomerCustomerEntityToCreate customerData) throws java.rmi.RemoteException {
        return false;
    }

    public boolean customerCustomerDelete(java.lang.String sessionId, int customerId) throws java.rmi.RemoteException {
        return false;
    }

    public com.gruporon2005.soap.magento.CustomerGroupEntity[] customerGroupList(java.lang.String sessionId) throws java.rmi.RemoteException {
        return null;
    }

    public com.gruporon2005.soap.magento.CustomerAddressEntityItem[] customerAddressList(java.lang.String sessionId, int customerId) throws java.rmi.RemoteException {
        return null;
    }

    public int customerAddressCreate(java.lang.String sessionId, int customerId, com.gruporon2005.soap.magento.CustomerAddressEntityCreate addressData) throws java.rmi.RemoteException {
        return -3;
    }

    public com.gruporon2005.soap.magento.CustomerAddressEntityItem customerAddressInfo(java.lang.String sessionId, int addressId) throws java.rmi.RemoteException {
        return null;
    }

    public boolean customerAddressUpdate(java.lang.String sessionId, int addressId, com.gruporon2005.soap.magento.CustomerAddressEntityCreate addressData) throws java.rmi.RemoteException {
        return false;
    }

    public boolean customerAddressDelete(java.lang.String sessionId, int addressId) throws java.rmi.RemoteException {
        return false;
    }

    public int catalogCategoryCurrentStore(java.lang.String sessionId, java.lang.String storeView) throws java.rmi.RemoteException {
        return -3;
    }

    public com.gruporon2005.soap.magento.CatalogCategoryTree catalogCategoryTree(java.lang.String sessionId, java.lang.String parentId, java.lang.String storeView) throws java.rmi.RemoteException {
        return null;
    }

    public com.gruporon2005.soap.magento.CatalogCategoryEntityNoChildren[] catalogCategoryLevel(java.lang.String sessionId, java.lang.String website, java.lang.String storeView, java.lang.String parentCategory) throws java.rmi.RemoteException {
        return null;
    }

    public com.gruporon2005.soap.magento.CatalogCategoryInfo catalogCategoryInfo(java.lang.String sessionId, int categoryId, java.lang.String storeView, java.lang.String[] attributes) throws java.rmi.RemoteException {
        return null;
    }

    public int catalogCategoryCreate(java.lang.String sessionId, int parentId, com.gruporon2005.soap.magento.CatalogCategoryEntityCreate categoryData, java.lang.String storeView) throws java.rmi.RemoteException {
        return -3;
    }

    public boolean catalogCategoryUpdate(java.lang.String sessionId, int categoryId, com.gruporon2005.soap.magento.CatalogCategoryEntityCreate categoryData, java.lang.String storeView) throws java.rmi.RemoteException {
        return false;
    }

    public boolean catalogCategoryMove(java.lang.String sessionId, int categoryId, int parentId, java.lang.String afterId) throws java.rmi.RemoteException {
        return false;
    }

    public boolean catalogCategoryDelete(java.lang.String sessionId, int categoryId) throws java.rmi.RemoteException {
        return false;
    }

    public com.gruporon2005.soap.magento.CatalogAssignedProduct[] catalogCategoryAssignedProducts(java.lang.String sessionId, int categoryId) throws java.rmi.RemoteException {
        return null;
    }

    public boolean catalogCategoryAssignProduct(java.lang.String sessionId, int categoryId, java.lang.String product, java.lang.String position, java.lang.String productIdentifierType) throws java.rmi.RemoteException {
        return false;
    }

    public boolean catalogCategoryUpdateProduct(java.lang.String sessionId, int categoryId, java.lang.String product, java.lang.String position, java.lang.String productIdentifierType) throws java.rmi.RemoteException {
        return false;
    }

    public boolean catalogCategoryRemoveProduct(java.lang.String sessionId, int categoryId, java.lang.String product, java.lang.String productIdentifierType) throws java.rmi.RemoteException {
        return false;
    }

    public int catalogProductCurrentStore(java.lang.String sessionId, java.lang.String storeView) throws java.rmi.RemoteException {
        return -3;
    }

    public com.gruporon2005.soap.magento.CatalogProductEntity[] catalogProductList(java.lang.String sessionId, com.gruporon2005.soap.magento.Filters filters, java.lang.String storeView) throws java.rmi.RemoteException {
        return null;
    }

    public com.gruporon2005.soap.magento.CatalogProductReturnEntity catalogProductInfo(java.lang.String sessionId, java.lang.String product, java.lang.String storeView, com.gruporon2005.soap.magento.CatalogProductRequestAttributes attributes, java.lang.String productIdentifierType) throws java.rmi.RemoteException {
        return null;
    }

    public int catalogProductCreate(java.lang.String sessionId, java.lang.String type, java.lang.String set, java.lang.String sku, com.gruporon2005.soap.magento.CatalogProductCreateEntity productData) throws java.rmi.RemoteException {
        return -3;
    }

    public boolean catalogProductUpdate(java.lang.String sessionId, java.lang.String product, com.gruporon2005.soap.magento.CatalogProductCreateEntity productData, java.lang.String storeView, java.lang.String productIdentifierType) throws java.rmi.RemoteException {
        return false;
    }

    public int catalogProductSetSpecialPrice(java.lang.String sessionId, java.lang.String product, java.lang.String specialPrice, java.lang.String fromDate, java.lang.String toDate, java.lang.String storeView, java.lang.String productIdentifierType) throws java.rmi.RemoteException {
        return -3;
    }

    public com.gruporon2005.soap.magento.CatalogProductReturnEntity catalogProductGetSpecialPrice(java.lang.String sessionId, java.lang.String product, java.lang.String storeView, java.lang.String productIdentifierType) throws java.rmi.RemoteException {
        return null;
    }

    public int catalogProductDelete(java.lang.String sessionId, java.lang.String product, java.lang.String productIdentifierType) throws java.rmi.RemoteException {
        return -3;
    }

    public int catalogProductAttributeCurrentStore(java.lang.String sessionId, java.lang.String storeView) throws java.rmi.RemoteException {
        return -3;
    }

    public com.gruporon2005.soap.magento.CatalogAttributeEntity[] catalogProductAttributeList(java.lang.String sessionId, int setId) throws java.rmi.RemoteException {
        return null;
    }

    public com.gruporon2005.soap.magento.CatalogAttributeOptionEntity[] catalogProductAttributeOptions(java.lang.String sessionId, java.lang.String attributeId, java.lang.String storeView) throws java.rmi.RemoteException {
        return null;
    }

    public com.gruporon2005.soap.magento.CatalogProductAttributeSetEntity[] catalogProductAttributeSetList(java.lang.String sessionId) throws java.rmi.RemoteException {
        return null;
    }

    public com.gruporon2005.soap.magento.CatalogProductTypeEntity[] catalogProductTypeList(java.lang.String sessionId) throws java.rmi.RemoteException {
        return null;
    }

    public com.gruporon2005.soap.magento.CatalogProductTierPriceEntity[] catalogProductAttributeTierPriceInfo(java.lang.String sessionId, java.lang.String product, java.lang.String productIdentifierType) throws java.rmi.RemoteException {
        return null;
    }

    public int catalogProductAttributeTierPriceUpdate(java.lang.String sessionId, java.lang.String product, com.gruporon2005.soap.magento.CatalogProductTierPriceEntity[] tier_price, java.lang.String productIdentifierType) throws java.rmi.RemoteException {
        return -3;
    }

    public int catalogCategoryAttributeCurrentStore(java.lang.String sessionId, java.lang.String storeView) throws java.rmi.RemoteException {
        return -3;
    }

    public com.gruporon2005.soap.magento.CatalogAttributeEntity[] catalogCategoryAttributeList(java.lang.String sessionId) throws java.rmi.RemoteException {
        return null;
    }

    public com.gruporon2005.soap.magento.CatalogAttributeOptionEntity[] catalogCategoryAttributeOptions(java.lang.String sessionId, java.lang.String attributeId, java.lang.String storeView) throws java.rmi.RemoteException {
        return null;
    }

    public int catalogProductAttributeMediaCurrentStore(java.lang.String sessionId, java.lang.String storeView) throws java.rmi.RemoteException {
        return -3;
    }

    public com.gruporon2005.soap.magento.CatalogProductImageEntity[] catalogProductAttributeMediaList(java.lang.String sessionId, java.lang.String product, java.lang.String storeView, java.lang.String productIdentifierType) throws java.rmi.RemoteException {
        return null;
    }

    public com.gruporon2005.soap.magento.CatalogProductImageEntity catalogProductAttributeMediaInfo(java.lang.String sessionId, java.lang.String product, java.lang.String file, java.lang.String storeView, java.lang.String productIdentifierType) throws java.rmi.RemoteException {
        return null;
    }

    public com.gruporon2005.soap.magento.CatalogProductAttributeMediaTypeEntity[] catalogProductAttributeMediaTypes(java.lang.String sessionId, java.lang.String setId) throws java.rmi.RemoteException {
        return null;
    }

    public java.lang.String catalogProductAttributeMediaCreate(java.lang.String sessionId, java.lang.String product, com.gruporon2005.soap.magento.CatalogProductAttributeMediaCreateEntity data, java.lang.String storeView, java.lang.String productIdentifierType) throws java.rmi.RemoteException {
        return null;
    }

    public int catalogProductAttributeMediaUpdate(java.lang.String sessionId, java.lang.String product, java.lang.String file, com.gruporon2005.soap.magento.CatalogProductAttributeMediaCreateEntity data, java.lang.String storeView, java.lang.String productIdentifierType) throws java.rmi.RemoteException {
        return -3;
    }

    public int catalogProductAttributeMediaRemove(java.lang.String sessionId, java.lang.String product, java.lang.String file, java.lang.String productIdentifierType) throws java.rmi.RemoteException {
        return -3;
    }

    public com.gruporon2005.soap.magento.CatalogProductLinkEntity[] catalogProductLinkList(java.lang.String sessionId, java.lang.String type, java.lang.String product, java.lang.String productIdentifierType) throws java.rmi.RemoteException {
        return null;
    }

    public java.lang.String catalogProductLinkAssign(java.lang.String sessionId, java.lang.String type, java.lang.String product, java.lang.String linkedProduct, com.gruporon2005.soap.magento.CatalogProductLinkEntity data, java.lang.String productIdentifierType) throws java.rmi.RemoteException {
        return null;
    }

    public java.lang.String catalogProductLinkUpdate(java.lang.String sessionId, java.lang.String type, java.lang.String product, java.lang.String linkedProduct, com.gruporon2005.soap.magento.CatalogProductLinkEntity data, java.lang.String productIdentifierType) throws java.rmi.RemoteException {
        return null;
    }

    public java.lang.String catalogProductLinkRemove(java.lang.String sessionId, java.lang.String type, java.lang.String product, java.lang.String linkedProduct, java.lang.String productIdentifierType) throws java.rmi.RemoteException {
        return null;
    }

    public java.lang.String[] catalogProductLinkTypes(java.lang.String sessionId) throws java.rmi.RemoteException {
        return null;
    }

    public com.gruporon2005.soap.magento.CatalogProductLinkAttributeEntity[] catalogProductLinkAttributes(java.lang.String sessionId, java.lang.String type) throws java.rmi.RemoteException {
        return null;
    }

    public com.gruporon2005.soap.magento.SalesOrderEntity[] salesOrderList(java.lang.String sessionId, com.gruporon2005.soap.magento.Filters filters) throws java.rmi.RemoteException {
        return null;
    }

    public com.gruporon2005.soap.magento.SalesOrderEntity[] salesOrderListLimited(java.lang.String sessionId, com.gruporon2005.soap.magento.Filters filters, java.lang.String idArray) throws java.rmi.RemoteException {
        return null;
    }

    public com.gruporon2005.soap.magento.SalesOrderEntity salesOrderInfo(java.lang.String sessionId, java.lang.String orderIncrementId) throws java.rmi.RemoteException {
        return null;
    }

    public int salesOrderAddComment(java.lang.String sessionId, java.lang.String orderIncrementId, java.lang.String status, java.lang.String comment, java.lang.String notify) throws java.rmi.RemoteException {
        return -3;
    }

    public int salesOrderHold(java.lang.String sessionId, java.lang.String orderIncrementId) throws java.rmi.RemoteException {
        return -3;
    }

    public int salesOrderUnhold(java.lang.String sessionId, java.lang.String orderIncrementId) throws java.rmi.RemoteException {
        return -3;
    }

    public int salesOrderCancel(java.lang.String sessionId, java.lang.String orderIncrementId) throws java.rmi.RemoteException {
        return -3;
    }

    public int salesOrderCompletar(java.lang.String sessionId, java.lang.String orderIncrementId) throws java.rmi.RemoteException {
        return -3;
    }

    public com.gruporon2005.soap.magento.SalesOrderShipmentEntity[] salesOrderShipmentList(java.lang.String sessionId, com.gruporon2005.soap.magento.Filters filters) throws java.rmi.RemoteException {
        return null;
    }

    public com.gruporon2005.soap.magento.SalesOrderShipmentEntity salesOrderShipmentInfo(java.lang.String sessionId, java.lang.String shipmentIncrementId) throws java.rmi.RemoteException {
        return null;
    }

    public java.lang.String salesOrderShipmentCreate(java.lang.String sessionId, java.lang.String orderIncrementId, com.gruporon2005.soap.magento.OrderItemIdQty[] itemsQty, java.lang.String comment, int email, int includeComment) throws java.rmi.RemoteException {
        return null;
    }

    public int salesOrderShipmentAddComment(java.lang.String sessionId, java.lang.String shipmentIncrementId, java.lang.String comment, java.lang.String email, java.lang.String includeInEmail) throws java.rmi.RemoteException {
        return -3;
    }

    public int salesOrderShipmentAddTrack(java.lang.String sessionId, java.lang.String shipmentIncrementId, java.lang.String carrier, java.lang.String title, java.lang.String trackNumber) throws java.rmi.RemoteException {
        return -3;
    }

    public int salesOrderShipmentRemoveTrack(java.lang.String sessionId, java.lang.String shipmentIncrementId, java.lang.String trackId) throws java.rmi.RemoteException {
        return -3;
    }

    public com.gruporon2005.soap.magento.AssociativeEntity[] salesOrderShipmentGetCarriers(java.lang.String sessionId, java.lang.String orderIncrementId) throws java.rmi.RemoteException {
        return null;
    }

    public com.gruporon2005.soap.magento.SalesOrderInvoiceEntity[] salesOrderInvoiceList(java.lang.String sessionId, com.gruporon2005.soap.magento.Filters filters) throws java.rmi.RemoteException {
        return null;
    }

    public com.gruporon2005.soap.magento.SalesOrderInvoiceEntity salesOrderInvoiceInfo(java.lang.String sessionId, java.lang.String invoiceIncrementId) throws java.rmi.RemoteException {
        return null;
    }

    public java.lang.String salesOrderInvoiceCreate(java.lang.String sessionId, java.lang.String invoiceIncrementId, com.gruporon2005.soap.magento.OrderItemIdQty[] itemsQty, java.lang.String comment, java.lang.String email, java.lang.String includeComment) throws java.rmi.RemoteException {
        return null;
    }

    public java.lang.String salesOrderInvoiceAddComment(java.lang.String sessionId, java.lang.String invoiceIncrementId, java.lang.String comment, java.lang.String email, java.lang.String includeComment) throws java.rmi.RemoteException {
        return null;
    }

    public java.lang.String salesOrderInvoiceCapture(java.lang.String sessionId, java.lang.String invoiceIncrementId) throws java.rmi.RemoteException {
        return null;
    }

    public java.lang.String salesOrderInvoiceVoid(java.lang.String sessionId, java.lang.String invoiceIncrementId) throws java.rmi.RemoteException {
        return null;
    }

    public java.lang.String salesOrderInvoiceCancel(java.lang.String sessionId, java.lang.String invoiceIncrementId) throws java.rmi.RemoteException {
        return null;
    }

    public com.gruporon2005.soap.magento.CatalogInventoryStockItemEntity[] catalogInventoryStockItemList(java.lang.String sessionId, java.lang.String[] products) throws java.rmi.RemoteException {
        return null;
    }

    public int catalogInventoryStockItemUpdate(java.lang.String sessionId, java.lang.String product, com.gruporon2005.soap.magento.CatalogInventoryStockItemUpdateEntity data) throws java.rmi.RemoteException {
        return -3;
    }

}
