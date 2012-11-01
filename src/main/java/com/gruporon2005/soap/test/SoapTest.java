/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gruporon2005.soap.test;

import com.gruporon2005.soap.magento.CatalogCategoryEntity;
import com.gruporon2005.soap.magento.CatalogCategoryTree;
import com.gruporon2005.soap.magento.CatalogInventoryStockItemEntity;
import com.gruporon2005.soap.magento.CatalogProductEntity;
import com.gruporon2005.soap.magento.CustomerAddressEntityItem;
import com.gruporon2005.soap.magento.CustomerCustomerEntity;
import com.gruporon2005.soap.magento.Mage_Api_Model_Server_V2_HandlerPortType;
import com.gruporon2005.soap.magento.MagentoServiceLocator;
import com.gruporon2005.soap.magento.SalesOrderEntity;
import com.gruporon2005.soap.magento.SalesOrderItemEntity;
import org.apache.log4j.Logger;


import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Calendar;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.DataFormat;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

/**
 *
 * @author yrch
 */
public class SoapTest {

    public static Logger log = Logger.getLogger(SoapTest.class);

    // Private constructor prevents instantiation from other classes
    private SoapTest() {
    }

    /**
     * SingletonHolder is loaded on the first execution of Singleton.getInstance()
     * or the first access to SingletonHolder.INSTANCE, not before.
     */
    private static class SoapTestHolder {

        private static final SoapTest INSTANCE = new SoapTest();
    }

    public static SoapTest getInstance() {
        return SoapTestHolder.INSTANCE;
    }

    /**
     *
     * @param catalogCategoryTree
     */
    private void generateSitemap(final CatalogCategoryTree catalogCategoryTree) {
        CatalogCategoryEntity[] childrens;
        try {
            log.info(catalogCategoryTree.getName());
            childrens = catalogCategoryTree.getChildren();
            for (CatalogCategoryEntity category : childrens) {
                recursiva(category);
            }
        } catch (Exception e) {
            log.error("Error sin controlar", e);
        }
    }

    /**
     *
     * @param catalogCategoryEntity
     */
    private void recursiva(final CatalogCategoryEntity catalogCategoryEntity) {
        try {
            log.info(catalogCategoryEntity.getName());
            CatalogCategoryEntity[] childrens = catalogCategoryEntity.getChildren();
            for (CatalogCategoryEntity category : childrens) {
                recursiva(category);
            }

        } catch (Exception e) {
            log.error(e);
        }
    }

    public void generarExcel(CustomerCustomerEntity[] customerList) {

        FileOutputStream fileOut = null;
        try {
            Workbook workbook = new HSSFWorkbook();
            fileOut = new FileOutputStream("workbook.xls");



            Sheet sheet = workbook.createSheet();

            CellStyle style = workbook.createCellStyle();
            Font font = workbook.createFont();
            font.setBoldweight(Font.BOLDWEIGHT_BOLD);
            style.setFont(font);


            Row cabecera = sheet.createRow(0);
            cabecera.createCell(0).setCellValue("Customer_Id");
            cabecera.createCell(1).setCellValue("Group_id");
            cabecera.createCell(2).setCellValue("Firstname");
            cabecera.createCell(3).setCellValue("Lastname");
            cabecera.createCell(4).setCellValue("Email");
            cabecera.createCell(5).setCellValue("Password_hash");

            cabecera.createCell(6).setCellValue("Website_id");
            cabecera.createCell(7).setCellValue("getStore_id");
            cabecera.createCell(8).setCellValue("Created_in");


            cabecera.createCell(9).setCellValue("Created_at");
            cabecera.createCell(10).setCellValue("Updated_at");
            cabecera.createCell(11).setCellValue("Increment_id");

            for (int i = 0; i < customerList.length; i++) {
                Row row = sheet.createRow(i + 1);

                // Create a cell and put a value in it.
                row.createCell(0).setCellValue(customerList[i].getCustomer_id());
                row.createCell(1).setCellValue(customerList[i].getGroup_id());
                row.createCell(2).setCellValue(customerList[i].getFirstname());
                row.createCell(3).setCellValue(customerList[i].getLastname());
                row.createCell(4).setCellValue(customerList[i].getEmail());
                row.createCell(5).setCellValue(customerList[i].getPassword_hash());

                row.createCell(6).setCellValue(customerList[i].getWebsite_id());
                //row.createCell(7).setCellValue(customerList[i].getStore_id());
                row.createCell(8).setCellValue(customerList[i].getCreated_in());


                row.createCell(9).setCellValue(customerList[i].getCreated_at());
                row.createCell(10).setCellValue(customerList[i].getUpdated_at());
                row.createCell(11).setCellValue(customerList[i].getIncrement_id());



            }



            workbook.write(fileOut);
            fileOut.close();


        } catch (FileNotFoundException ex) {
            log.error("No se encuentra el fichero", ex);
        } catch (IOException ex) {
            log.error("I/O error", ex);
        }


    }

    /**
     *
     */
    public void exportCustomer() {
        CustomerCustomerEntity[] customerList;

        try {
            MagentoServiceLocator service = new MagentoServiceLocator();
            Mage_Api_Model_Server_V2_HandlerPortType magento = service.getMage_Api_Model_Server_V2_HandlerPort();

            String sessionId = magento.login("soap", "test123");

            try {

                customerList = magento.customerCustomerList(sessionId, null);


                generarExcel(customerList);

            } catch (Exception e) {
                log.error("Se ha producido un error", e);
            }



            magento.endSession(sessionId);

        } catch (Exception e) {
            log.error("Se ha producido un error", e);
        }




    }

    /**
     * 
     */
    public void exportOrder() {
        SalesOrderEntity[] orderList;
        FileOutputStream fileOut;
        CustomerCustomerEntity customerInfo;
        SalesOrderItemEntity[] items;
        SalesOrderEntity pedido;
        CustomerAddressEntityItem shippingAddressInfo;
        SalesOrderEntity orderInfo;
        short dataFormat;



        try {
            MagentoServiceLocator service = new MagentoServiceLocator();
            Mage_Api_Model_Server_V2_HandlerPortType magento = service.getMage_Api_Model_Server_V2_HandlerPort();

            String sessionId = magento.login("soap", "test123");

            try {

                Calendar fechaActual = Calendar.getInstance();


                //Creamos un nuevo libro
                Workbook workbook = new HSSFWorkbook();
                fileOut = new FileOutputStream("workbook.xls");

                DataFormat format = workbook.createDataFormat();



                Sheet sheet = workbook.createSheet();

                CellStyle style = workbook.createCellStyle();
                Font font = workbook.createFont();
                font.setBoldweight(Font.BOLDWEIGHT_BOLD);
                //  font.setFontHeight((short)16);
                style.setWrapText(true);
                style.setFont(font);
                style.setFillForegroundColor(HSSFColor.LIME.index);
                style.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);


                //ajuste de las columnas al contenido
                for (int i = 0; i < 18; i++) {
                    sheet.autoSizeColumn(i);
                }




                /*NUMEROPEDIDO
                TIPO
                SERVICIO
                CANTIDAD
                REEMBOLSO
                SEGURO
                NOMBRE CONSIGNATARIO
                NIF
                DIRECCION CONSIGNATARIO
                CODIGO POSTAL
                POBLACION
                PROVINCIA
                TELEFONO1
                TELEFONO2
                CODIGO PRODUCTO
                PROPIETARIO
                SOLICITANTE
                OBSERVACIONES
                 */


                Row cabecera = sheet.createRow(0);

                Cell cell = cabecera.createCell(0);
                cell.setCellValue("NUMEROPEDIDO");
                cell.setCellStyle(style);

                cell = cabecera.createCell(1);
                cell.setCellValue("TIPO SERVICIO");
                cell.setCellStyle(style);


                cell = cabecera.createCell(2);
                cell.setCellValue("CANTIDAD");
                cell.setCellStyle(style);

                cell = cabecera.createCell(3);
                cell.setCellValue("REEMBOLSO");
                cell.setCellStyle(style);

                cell = cabecera.createCell(4);
                cell.setCellValue("SEGURO");
                cell.setCellStyle(style);

                cell = cabecera.createCell(5);
                cell.setCellValue("NOMBRE CONSIGNATARIO");
                cell.setCellStyle(style);

                cell = cabecera.createCell(6);
                cell.setCellValue("NIF");
                cell.setCellStyle(style);

                cell = cabecera.createCell(7);
                cell.setCellValue("DIRECCION CONSIGNATARIO");
                cell.setCellStyle(style);

                cell = cabecera.createCell(8);
                cell.setCellValue("CODIGO POSTAL");
                cell.setCellStyle(style);

                cell = cabecera.createCell(9);
                cell.setCellValue("POBLACION");
                cell.setCellStyle(style);

                cell = cabecera.createCell(10);
                cell.setCellValue("PROVINCIA");
                cell.setCellStyle(style);

                cell = cabecera.createCell(11);
                cell.setCellValue("TELEFONO1");
                cell.setCellStyle(style);

                cell = cabecera.createCell(12);
                cell.setCellValue("TELEFONO2");
                cell.setCellStyle(style);

                cell = cabecera.createCell(13);
                cell.setCellValue("CODIGO PRODUCTO");
                cell.setCellStyle(style);

                cell = cabecera.createCell(14);
                cell.setCellValue("PROPIETARIO");
                cell.setCellStyle(style);

                cell = cabecera.createCell(15);
                cell.setCellValue("SOLICITANTE");
                cell.setCellStyle(style);

                cell = cabecera.createCell(16);
                cell.setCellValue("OBSERVACIONES");
                cell.setCellStyle(style);

                cell = cabecera.createCell(17);
                cell.setCellValue("MAGENTO ID");
                cell.setCellStyle(style);




                //
                CellStyle style2 = workbook.createCellStyle();
                style2.setAlignment(CellStyle.ALIGN_CENTER);
                Font font2 = workbook.createFont();
                font2.setFontHeight((short) 12);

                CellStyle style3 = workbook.createCellStyle();
                style3.setAlignment(CellStyle.ALIGN_CENTER);


                orderList = magento.salesOrderList(sessionId, null);


                // magento.customerAddressList(sessionId, 99);
                int i = 0, pos = 0;
                for (SalesOrderEntity order : orderList) {

                    // magento.salesOrderCompletar(sessionId, order.getIncrement_id());

                    orderInfo = magento.salesOrderInfo(sessionId, order.getIncrement_id());

                    customerInfo = magento.customerCustomerInfo(sessionId, Integer.parseInt(order.getCustomer_id()), null);


                    Row row = sheet.createRow(i + 1);

                    log.info(orderInfo.getOrder_id() + " " + order.getCustomer_id());


                    cell = row.createCell(3);
                    if (orderInfo.getPayment().getMethod().equals("cashondelivery")) {
                        cell.setCellValue(Float.valueOf(orderInfo.getPayment().getAmount_ordered()));
                        style2.setDataFormat(format.getFormat("0.00"));

                    } else {
                        cell.setCellValue("");
                    }

                    cell.setCellStyle(style2);

                    cell = row.createCell(4);
                    cell.setCellValue("");
                    cell.setCellStyle(style2);

                    cell = row.createCell(5);
                    cell.setCellValue(customerInfo.getFirstname() + " " + customerInfo.getLastname());
                    cell.setCellStyle(style2);

                    cell = row.createCell(6);
                    if (customerInfo.getTaxvat() != null) {
                        cell.setCellValue(customerInfo.getTaxvat());

                    } else {
                        cell.setCellValue("NIF");
                    }
                    cell.setCellStyle(style2);



                    if (orderInfo.getShipping_address() != null) {
                        cell = row.createCell(7);
                        cell.setCellValue(orderInfo.getShipping_address().getStreet());
                        cell.setCellStyle(style2);

                        cell = row.createCell(8);
                        cell.setCellValue(orderInfo.getShipping_address().getPostcode());
                        cell.setCellStyle(style2);


                        cell = row.createCell(9);
                        cell.setCellValue(orderInfo.getShipping_address().getCity());
                        cell.setCellStyle(style2);

                        cell = row.createCell(10);
                        cell.setCellValue(orderInfo.getShipping_address().getRegion());
                        cell.setCellStyle(style2);

                        cell = row.createCell(11);
                        cell.setCellValue(orderInfo.getShipping_address().getTelephone());
                        cell.setCellStyle(style2);

                    }

                    if (orderInfo.getBilling_address() != null) {
                        cell = row.createCell(12);
                        cell.setCellValue(orderInfo.getBilling_address().getTelephone());
                        cell.setCellStyle(style2);
                    }




                    cell = row.createCell(16);
                    cell.setCellValue("OBSERVACIONES");
                    cell.setCellStyle(style2);

                    cell = row.createCell(17);
                    cell.setCellValue("ABC-" + orderInfo.getIncrement_id());
                    cell.setCellStyle(style2);



                    items = orderInfo.getItems();
                    if (items != null) {
                        int j = 0;
                        for (SalesOrderItemEntity item : items) {
                            if (j == 0) {
                                j++;
                            } else {
                                i++;
                                row = sheet.createRow(i + 1);
                            }

                            cell = row.createCell(0);
                            cell.setCellValue(String.format("ABC-%1$te%1$tm%1$ty%1$tH%1$tM-%2$d", fechaActual, pos));
                            cell.setCellStyle(style2);

                            cell = row.createCell(1);
                            cell.setCellValue("3");
                            cell.setCellStyle(style2);

                            cell = row.createCell(2);
                            cell.setCellValue(Float.valueOf(item.getQty_ordered()));
                            style3.setDataFormat(format.getFormat("0"));
                            cell.setCellStyle(style3);

                            cell = row.createCell(13);
                            cell.setCellValue(item.getSku());
                            cell.setCellStyle(style2);

                            cell = row.createCell(14);
                            cell.setCellValue("GRON");
                            cell.setCellStyle(style2);

                            cell = row.createCell(15);
                            cell.setCellValue("TIENDA ABC");
                            cell.setCellStyle(style2);



                        }
                    }



                    i++;
                    pos++;
                }


                for (i = 0; i < 18; i++) {
                    sheet.autoSizeColumn(i);
                }

                workbook.write(fileOut);
                fileOut.close();

            } catch (Exception e) {
                log.error("Se ha producido un error", e);
            }



            magento.endSession(sessionId);

        } catch (Exception e) {
            log.error("Se ha producido un error", e);
        }


    }

    public void testSoap() {
        CustomerCustomerEntity[] customerList;
        SalesOrderEntity[] salesOrderList;
        CatalogCategoryTree catalogCategoryTree;
        CatalogProductEntity[] productList = null;
        CatalogInventoryStockItemEntity[] inventoryStockItemList;


        try {

            MagentoServiceLocator service = new MagentoServiceLocator();
            Mage_Api_Model_Server_V2_HandlerPortType magento = service.getMage_Api_Model_Server_V2_HandlerPort();

            String sessionId = magento.login("soap", "test123");
            try {

                customerList = magento.customerCustomerList(sessionId, null);
                for (CustomerCustomerEntity customer : customerList) {
                    log.info(customer.getFirstname());
                }

                salesOrderList = magento.salesOrderList(sessionId, null);
                for (SalesOrderEntity salesOrder : salesOrderList) {
                    log.info(salesOrder.getBilling_lastname());
                }

                catalogCategoryTree = magento.catalogCategoryTree(sessionId, null, null);
                generateSitemap(catalogCategoryTree);

                /*productList = magento.catalogProductList(sessionId, null, "");

                for(CatalogProductEntity producto : productList ){
                log.info("producto -> " + producto);
                }*/

                /* inventoryStockItemList = magento.catalogInventoryStockItemList(sessionId,null);

                for(CatalogInventoryStockItemEntity product : inventoryStockItemList){
                log.info("P.Name: " + product.getProduct_id() + " P.Sku: " + product.getSku() );
                }*/

            } catch (Exception e) {
                log.error("testSoap2 -> Se ha producido un error", e);
            }

            magento.endSession(sessionId);

        } catch (Exception e) {
            log.error("testSoap2 -> Se ha producido un error", e);

        }
    }

    public void getDirecciones() {

        try {

            MagentoServiceLocator service = new MagentoServiceLocator();
            Mage_Api_Model_Server_V2_HandlerPortType magento = service.getMage_Api_Model_Server_V2_HandlerPort();

            String sessionId = magento.login("soap", "test123");
            CustomerCustomerEntity customerInfo = magento.customerCustomerInfo(sessionId, 6, null);
            /*CustomerAddressEntity[] customerAddresses = magento.customerAddressList(sessionId, customerInfo.getCustomer_id());
            for(CustomerAddressEntity address : customerAddresses ){

            log.info(address.getCity());
            }*/


            magento.endSession(sessionId);

        } catch (Exception e) {
            log.error("testSoap2 -> Se ha producido un error", e);

        }
    }

    



    
}
