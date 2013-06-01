package com.gruporon2005.soap.helper;

import com.gruporon2005.aquarius.bean.Product;
import com.gruporon2005.aquarius.bean.SessionBean;
import com.gruporon2005.aquarius.bean.Store;
import com.gruporon2005.soap.magento.AssociativeEntity;
import com.gruporon2005.soap.magento.CatalogProductReturnEntity;
import com.gruporon2005.soap.magento.ComplexFilter;
import com.gruporon2005.soap.magento.CustomerCustomerEntity;
import com.gruporon2005.soap.magento.Filters;
import com.gruporon2005.soap.magento.Mage_Api_Model_Server_V2_HandlerPortType;
import com.gruporon2005.soap.magento.MagentoServiceLocator;
import com.gruporon2005.soap.magento.SalesOrderEntity;
import com.gruporon2005.soap.magento.SalesOrderItemEntity;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import org.apache.log4j.Logger;

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

public class OrderHelper {
    // El constructor privado no permite que se genere un constructor por defecto
    // (con mismo modificador de acceso que la definicion de la clase)

    private OrderHelper() {
    }

    public static OrderHelper getInstance() {
        return SingletonHolder.instance;
    }

    private static class SingletonHolder {

        private final static OrderHelper instance = new OrderHelper();
    }
    /**
     *
     */
    private static Logger log = Logger.getLogger(OrderHelper.class);
    private static int storeId;

    /**
     *
     * @param filtros
     * @param offset
     * @param pageSize
     * @return
     */
    public List<SalesOrderEntity> getOrderList(Filters filtros,
            int offset, int pageSize, int[] orderListSize, String handlerPortEndpointAddress) {

        SalesOrderEntity[] orderList = null;
        List<SalesOrderEntity> result = null;
        try {

            MagentoServiceLocator service = new MagentoServiceLocator();
            service.setMage_Api_Model_Server_V2_HandlerPortEndpointAddress(handlerPortEndpointAddress);
            Mage_Api_Model_Server_V2_HandlerPortType magento = service.getMage_Api_Model_Server_V2_HandlerPort();

            String sessionId = magento.login("soap", "test123");

            orderList = magento.salesOrderList(sessionId, filtros);


            orderListSize[0] = orderList.length;

            List<SalesOrderEntity> listOrder = Arrays.asList(orderList);
            Collections.sort(listOrder);
            if (offset + pageSize > listOrder.size()) {
                pageSize = listOrder.size() - offset;
            }

            result = listOrder.subList(offset, offset + pageSize);

            magento.endSession(sessionId);

        }
        catch (Exception e) {
            log.error("Error en getOrderList", e);

        }

        return result;

    }

    /**
     *
     * @param id
     * @return
     */
    public SalesOrderEntity getOrderInfo(String id, String handlerPortEndpointAddress) {
        SalesOrderEntity orderInfo = null;
        try {

            MagentoServiceLocator service = new MagentoServiceLocator();
            service.setMage_Api_Model_Server_V2_HandlerPortEndpointAddress(handlerPortEndpointAddress);
            Mage_Api_Model_Server_V2_HandlerPortType magento = service.getMage_Api_Model_Server_V2_HandlerPort();
            String sessionId = magento.login("soap", "test123");


            orderInfo = magento.salesOrderInfo(sessionId, id);


            magento.endSession(sessionId);

        }
        catch (org.apache.axis.AxisFault fault) {
            log.error(fault.getFaultString());
        }
        catch (Exception e) {
            log.error("Error en getOrderInfo", e);

        }

        return orderInfo;
    }

    /**
     *
     * @param id
     * @return
     */
    public CatalogProductReturnEntity getProductInfo(String id, String handlerPortEndpointAddress) {
        CatalogProductReturnEntity productInfo = null;
        try {

            MagentoServiceLocator service = new MagentoServiceLocator();
            service.setMage_Api_Model_Server_V2_HandlerPortEndpointAddress(handlerPortEndpointAddress);
            Mage_Api_Model_Server_V2_HandlerPortType magento = service.getMage_Api_Model_Server_V2_HandlerPort();

            String sessionId = magento.login("soap", "test123");


            productInfo = magento.catalogProductInfo(sessionId, id, null, null, null);


            magento.endSession(sessionId);

        }
        catch (Exception e) {
            log.error("El producto con sku " + id + " no existe");

        }

        return productInfo;
    }

    public void exportOrder(OutputStream out, String[] orderIds, SessionBean sessionBean, String handlerPortEndpointAddress, boolean sandbox) {
        SalesOrderEntity[] orderList;
        CustomerCustomerEntity customerInfo;
        SalesOrderItemEntity[] items;
        SalesOrderEntity orderInfo;






        try {
            MagentoServiceLocator service = new MagentoServiceLocator();
            service.setMage_Api_Model_Server_V2_HandlerPortEndpointAddress(handlerPortEndpointAddress);
            Mage_Api_Model_Server_V2_HandlerPortType magento = service.getMage_Api_Model_Server_V2_HandlerPort();

            String sessionId = magento.login("soap", "test123");

            int numColumnas = 22;

            try {


                Store storeBean = sessionBean.getStoreInfoHash().get(sessionBean.getStoreId());

                Calendar fechaActual = Calendar.getInstance();


                //Creamos un nuevo libro
                Workbook workbook = new HSSFWorkbook();

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
                for (int i = 0; i < 22; i++) {
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
                cell.setCellValue("FECHA PEDIDO");
                cell.setCellStyle(style);

                cell = cabecera.createCell(2);
                cell.setCellValue("TIPO SERVICIO");
                cell.setCellStyle(style);


                cell = cabecera.createCell(3);
                cell.setCellValue("CANTIDAD");
                cell.setCellStyle(style);

                cell = cabecera.createCell(4);
                cell.setCellValue("REEMBOLSO");
                cell.setCellStyle(style);

                cell = cabecera.createCell(5);
                cell.setCellValue("SEGURO");
                cell.setCellStyle(style);

                cell = cabecera.createCell(6);
                cell.setCellValue("NOMBRE CONSIGNATARIO");
                cell.setCellStyle(style);

                cell = cabecera.createCell(7);
                cell.setCellValue("NIF");
                cell.setCellStyle(style);

                cell = cabecera.createCell(8);
                cell.setCellValue("DIRECCION CONSIGNATARIO");
                cell.setCellStyle(style);

                cell = cabecera.createCell(9);
                cell.setCellValue("CODIGO POSTAL");
                cell.setCellStyle(style);

                cell = cabecera.createCell(10);
                cell.setCellValue("POBLACION");
                cell.setCellStyle(style);

                cell = cabecera.createCell(11);
                cell.setCellValue("PROVINCIA");
                cell.setCellStyle(style);

                cell = cabecera.createCell(12);
                cell.setCellValue("TELEFONO Facturacion");
                cell.setCellStyle(style);

                cell = cabecera.createCell(13);
                cell.setCellValue("TELEFONO Envio");
                cell.setCellStyle(style);

                cell = cabecera.createCell(14);
                cell.setCellValue("CODIGO PRODUCTO");
                cell.setCellStyle(style);

                cell = cabecera.createCell(15);
                cell.setCellValue("PROPIETARIO");
                cell.setCellStyle(style);

                cell = cabecera.createCell(16);
                cell.setCellValue("SOLICITANTE");
                cell.setCellStyle(style);

                cell = cabecera.createCell(17);
                cell.setCellValue("OBSERVACIONES");
                cell.setCellStyle(style);

                if (sessionBean.getStoreId() != 17 && sessionBean.getStoreId() != 19) {
                    cell = cabecera.createCell(18);
                    cell.setCellValue("MAGENTO ID");
                    cell.setCellStyle(style);
                    cell = cabecera.createCell(19);
                    cell.setCellValue("Email Contacto");
                    cell.setCellStyle(style);
                } else {
                    cell = cabecera.createCell(18);
                    cell.setCellValue("Email Contacto");
                    cell.setCellStyle(style);
                    cell = cabecera.createCell(19);
                    cell.setCellValue("M�todo de pago");
                    cell.setCellStyle(style);
                }



                cell = cabecera.createCell(20);
                cell.setCellValue("Precio unitario con IVA");
                cell.setCellStyle(style);

                cell = cabecera.createCell(21);
                cell.setCellValue("Subtoral con IVA");
                cell.setCellStyle(style);


                //
                CellStyle style2 = workbook.createCellStyle();
                style2.setAlignment(CellStyle.ALIGN_CENTER);
                Font font2 = workbook.createFont();
                font2.setFontHeight((short) 12);

                CellStyle style3 = workbook.createCellStyle();
                style3.setAlignment(CellStyle.ALIGN_CENTER);


                String ids = "";

                for (String id : orderIds) {

                    ids += id + ",";
                }
                ids = ids.substring(0, ids.length() - 1);

                Filters filtros = new Filters();


                log.error("Se van a procesar los pedidos -> " + ids);

                ComplexFilter cmp[] = new ComplexFilter[1];
                cmp[0] = new ComplexFilter("increment_id", new AssociativeEntity("in", ids));

                filtros.setComplex_filter(cmp);

                orderList = magento.salesOrderList(sessionId, filtros);

                if (orderList.length > 0) {

                    int i = 0, pos = 0;
                    for (SalesOrderEntity order : orderList) {


                        orderInfo = magento.salesOrderInfo(sessionId, order.getIncrement_id());
                        String dni = "DNI";

                        if (orderInfo.getCustomer_id() != null && !orderInfo.getCustomer_id().equals("")) {
                            customerInfo = magento.customerCustomerInfo(sessionId, Integer.parseInt(orderInfo.getCustomer_id()), null);
                            dni = customerInfo.getTaxvat();
                        } else {
                            dni = "INVITADO";
                        }




                        Row row = sheet.createRow(i + 1);

                        SimpleDateFormat formatoDeFecha = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                        Date fecha1 = formatoDeFecha.parse(order.getCreated_at());
                        SimpleDateFormat formatoDeFecha2 = new SimpleDateFormat("dd/MM/yyyy");

                        cell = row.createCell(1);
                        cell.setCellValue(formatoDeFecha2.format(fecha1));
                        cell.setCellStyle(style2);



                        cell = row.createCell(4);
                        if (orderInfo.getPayment().getMethod().equals("cashondelivery")) {
                            cell.setCellValue(Float.valueOf(orderInfo.getPayment().getAmount_ordered()));
                            style2.setDataFormat(format.getFormat("0.00"));

                        } else {
                            cell.setCellValue("");
                        }

                        cell.setCellStyle(style2);



                        cell = row.createCell(5);
                        cell.setCellValue("");
                        cell.setCellStyle(style2);

                        cell = row.createCell(7);
                        cell.setCellValue(dni);
                        cell.setCellStyle(style2);



                        if (orderInfo.getShipping_address() != null) {

                            cell = row.createCell(6);
                            cell.setCellValue(orderInfo.getShipping_address().getFirstname() + " " + orderInfo.getShipping_address().getLastname());
                            cell.setCellStyle(style2);

                            cell = row.createCell(8);
                            cell.setCellValue(orderInfo.getShipping_address().getStreet() + " " + orderInfo.getShipping_address().getCompany());
                            cell.setCellStyle(style2);

                            cell = row.createCell(9);
                            cell.setCellValue(orderInfo.getShipping_address().getPostcode());
                            cell.setCellStyle(style2);


                            cell = row.createCell(10);
                            cell.setCellValue(orderInfo.getShipping_address().getCity());
                            cell.setCellStyle(style2);

                            cell = row.createCell(11);
                            cell.setCellValue(orderInfo.getShipping_address().getRegion());
                            cell.setCellStyle(style2);

                            cell = row.createCell(13);
                            cell.setCellValue(orderInfo.getShipping_address().getTelephone());
                            cell.setCellStyle(style2);

                        }

                        if (orderInfo.getBilling_address() != null) {
                            cell = row.createCell(12);
                            cell.setCellValue(orderInfo.getBilling_address().getTelephone());
                            cell.setCellStyle(style2);
                        }




                        cell = row.createCell(17);
                        cell.setCellValue("");
                        cell.setCellStyle(style2);

                        if (sessionBean.getStoreId() != 17 && sessionBean.getStoreId() != 19) {
                            cell = row.createCell(18);
                            cell.setCellValue(storeBean.getNemo() + "-" + orderInfo.getIncrement_id());
                            cell.setCellStyle(style2);
                            cell = row.createCell(19);
                            cell.setCellValue(orderInfo.getCustomer_email());
                            cell.setCellStyle(style2);
                        } else {
                            cell = row.createCell(18);
                            cell.setCellValue(orderInfo.getCustomer_email());
                            cell.setCellStyle(style2);
                            cell = row.createCell(19);
                            cell.setCellValue(orderInfo.getPayment().getMethod());
                            cell.setCellStyle(style2);
                        }






                        BigDecimal precioUnitario = BigDecimal.ZERO;
                        BigDecimal totalFilas = BigDecimal.ZERO;
                        items = orderInfo.getItems();
                        if (items != null) {
                            int j = 0;
                            String skuCompuesto = "";
                            for (SalesOrderItemEntity item : items) {


                                DecimalFormat df = new DecimalFormat("#,##0.00");

                                totalFilas = totalFilas.add(( new BigDecimal(item.getRow_total()) ).add(new BigDecimal(item.getBase_tax_amount())));



                                if (item.getProduct_type().equals("configurable")) {
                                    try {
                                        CatalogProductReturnEntity a = getProductInfo(item.getProduct_id(), handlerPortEndpointAddress);
                                        if (a != null) {
                                            skuCompuesto = a.getSku();
                                        }

                                    }
                                    catch (Exception e1) {
                                        log.error("El producto ->" + item.getSku() + " no se ha podido obtener ", e1);
                                    }


                                    continue;
                                } else {
                                    skuCompuesto += item.getSku();
                                }

                                if (j == 0) {
                                    j++;
                                } else {
                                    i++;
                                    row = sheet.createRow(i + 1);
                                }

                                cell = row.createCell(0);
                                cell.setCellValue(String.format(storeBean.getNemo() + "-%1$td%1$tm%1$ty%1$tH%1$tM-%2$d", fechaActual, pos));
                                cell.setCellStyle(style2);

                                cell = row.createCell(2);
                                cell.setCellValue("3");
                                cell.setCellStyle(style2);

                                cell = row.createCell(3);
                                cell.setCellValue(Float.valueOf(item.getQty_ordered()));
                                style3.setDataFormat(format.getFormat("0"));
                                cell.setCellStyle(style3);

                                cell = row.createCell(14);
                                cell.setCellValue(skuCompuesto);
                                cell.setCellStyle(style2);




                                cell = row.createCell(16);
                                cell.setCellValue(storeBean.getRequester());
                                cell.setCellStyle(style2);


                                String owner = "ERROR-01";
                                try {

                                    if (sessionBean.getProductInfoHash().containsKey(skuCompuesto)) {
                                        Product productInfo = (Product) sessionBean.getProductInfoHash().get(skuCompuesto);
                                        owner = productInfo.getOwner();
                                        if (owner.equals("") || owner == null) {
                                            owner = "ERROR-02";
                                        }

                                        cell = row.createCell(15);
                                        cell.setCellValue(owner);
                                        cell.setCellStyle(style2);


                                        if (productInfo.getAdditional() != null && !productInfo.getAdditional().equals("")) {

                                            if (!productInfo.getAdditional().equals("-")) {
                                                i++;
                                                row = sheet.createRow(i + 1);



                                                Product productInfoAdditional = (Product) sessionBean.getProductInfoHash().get(productInfo.getAdditional());
                                                owner = ( productInfo.getOwner() );

                                                cell = row.createCell(0);
                                                cell.setCellValue(String.format(storeBean.getNemo() + "-%1$td%1$tm%1$ty%1$tH%1$tM-%2$d", fechaActual, pos));
                                                cell.setCellStyle(style2);

                                                cell = row.createCell(2);
                                                cell.setCellValue("3");
                                                cell.setCellStyle(style2);

                                                cell = row.createCell(3);
                                                cell.setCellValue(Float.valueOf(item.getQty_ordered()));
                                                style3.setDataFormat(format.getFormat("0"));
                                                cell.setCellStyle(style3);

                                                cell = row.createCell(14);
                                                cell.setCellValue(productInfoAdditional.getSku());
                                                cell.setCellStyle(style2);



                                                cell = row.createCell(15);
                                                cell.setCellValue(productInfoAdditional.getOwner());
                                                cell.setCellStyle(style2);

                                                cell = row.createCell(16);
                                                cell.setCellValue(storeBean.getRequester());
                                                cell.setCellStyle(style2);


                                            }
                                        } else {
                                            log.error("Falta por dar de alta este producto " + skuCompuesto);
                                        }


                                    } else {
                                        log.error("Falta por dar de alta este producto " + skuCompuesto);
                                    }
                                }
                                catch (Exception e) {
                                    log.error("Falla aqui ->" + item.getSku(), e);
                                }



                                skuCompuesto = "";

                                cell = row.createCell(21);
                                cell.setCellValue(df.format(totalFilas));
                                cell.setCellStyle(style2);

                                cell = row.createCell(20);
                                cell.setCellValue(df.format(totalFilas.divide(new BigDecimal(item.getQty_ordered()))));
                                cell.setCellStyle(style2);

                                precioUnitario = BigDecimal.ZERO;
                                totalFilas = BigDecimal.ZERO;



                            }
                        }

                        //cambiamos el estado de los pedidos
                        if (!sandbox) {
                            try {
                                magento.salesOrderCompletar(sessionId, orderInfo.getIncrement_id());
                            }
                            catch (Exception e) {
                                log.error(e);
                            }
                        } else {
                            log.debug("sandbox");
                        }

                        i++;
                        pos++;


                    }


                    for (i = 0; i < numColumnas; i++) {
                        sheet.autoSizeColumn(i);
                    }


                } else {
                    log.error("No se encuentran los elementos a tramitar");
                }

                workbook.write(out);

            }
            catch (Exception e) {
                log.error("Se ha producido un error", e);
            }



            magento.endSession(sessionId);

        }
        catch (Exception e) {
            log.error("Se ha producido un error", e);
        }
    }
}
