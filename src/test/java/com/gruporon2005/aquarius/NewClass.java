/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gruporon2005.aquarius;

import com.gruporon2005.aquarius.bean.Product;
import com.gruporon2005.aquarius.bean.SessionBean;
import com.gruporon2005.aquarius.bean.Store;
import com.gruporon2005.soap.helper.OrderHelper;
import com.gruporon2005.soap.magento.AssociativeEntity;
import com.gruporon2005.soap.magento.ComplexFilter;
import com.gruporon2005.soap.magento.CustomerCustomerEntity;
import com.gruporon2005.soap.magento.Filters;
import com.gruporon2005.soap.magento.Mage_Api_Model_Server_V2_HandlerPortType;
import com.gruporon2005.soap.magento.MagentoServiceLocator;
import com.gruporon2005.soap.magento.SalesOrderEntity;
import com.gruporon2005.soap.magento.SalesOrderItemEntity;
import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;
import java.io.OutputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import javax.xml.rpc.ServiceException;

import static org.junit.Assert.*;

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
import org.junit.Ignore;
import org.junit.Test;

/**
 *
 * @author yrch
 */
public class NewClass {

    private static Logger log = Logger.getLogger(NewClass.class);

    public void test_1() {
        Connection conexion = null;
        try {

            System.setProperty(Context.INITIAL_CONTEXT_FACTORY,
                    "org.apache.naming.java.javaURLContextFactory");
            System.setProperty(Context.URL_PKG_PREFIXES,
                    "org.apache.naming");

            Context initContext = new InitialContext();

            initContext.createSubcontext("java:");
            initContext.createSubcontext("java:/comp");
            initContext.createSubcontext("java:/comp/env");
            initContext.createSubcontext("java:/comp/env/jdbc");

            Context envContext = (Context) initContext.lookup("java:/comp/env");

            assertTrue(envContext != null);

            // Construct DataSource
            MysqlDataSource ds = new MysqlDataSource();
            ds.setURL("jdbc:mysql://localhost:3306/Aquarius");
            ds.setUser("soap");
            ds.setPassword("soap123");

            initContext.bind("java:/comp/env/jdbc/AquariusDB", ds);



            conexion = ds.getConnection();


            assertTrue(conexion != null);

            conexion.close(); // Return to connection pool
            conexion = null;  // Make sure we don't close it twice


        }
        catch (SQLException e) {
            log.fatal(e);
        }
        catch (NamingException e) {
            log.fatal(e);

        }
        finally {
            // Always make sure result sets and statements are closed,
            // and the connection is returned to the pool
            /*if (rs != null) {
            try {
            rs.close();
            } catch (SQLException e) {
            ;
            }
            rs = null;
            }
            if (stmt != null) {
            try {
            stmt.close();
            } catch (SQLException e) {
            ;
            }
            stmt = null;
            }*/
            if (conexion != null) {
                try {
                    conexion.close();
                }
                catch (SQLException e) {
                    log.error(e);
                }
                conexion = null;
            }



        }
    }

    public void test2() {
        SalesOrderEntity orderInfo = OrderHelper.getInstance().getOrderInfo("100000289", "http://tienda.eleconomista.es/index.php/api/v2_soap/index/");
        log.error(orderInfo.getCustomer_firstname());
        assertTrue(orderInfo != null);
    }

    @Test
    public void testYQUDE() {
        MagentoServiceLocator service = new MagentoServiceLocator();
        String handlerPortEndpointAddress = "http://www.yoquierounodeesos.com/index.php/api/v2_soap/index/";
        service.setMage_Api_Model_Server_V2_HandlerPortEndpointAddress(handlerPortEndpointAddress);
        Mage_Api_Model_Server_V2_HandlerPortType magento;
        try {

            magento = service.getMage_Api_Model_Server_V2_HandlerPort();

            String sessionId = magento.login("soap", "test123");
            assertTrue(sessionId != null);
        }
        catch (Exception ex) {
            ex.printStackTrace();
            assertTrue(false);
        }

    }

    @Test
    public void testECONO() {
        MagentoServiceLocator service = new MagentoServiceLocator();
        String handlerPortEndpointAddress = "http://www.colomerandsons.com/index.php/api/v2_soap/index/";
        service.setMage_Api_Model_Server_V2_HandlerPortEndpointAddress(handlerPortEndpointAddress);
        Mage_Api_Model_Server_V2_HandlerPortType magento;
        try {

            magento = service.getMage_Api_Model_Server_V2_HandlerPort();

            String sessionId = magento.login("soap", "test123");
            assertTrue(sessionId != null);
            Filters filtros = new Filters();

            ComplexFilter cmp[] = new ComplexFilter[3];
            cmp[0] = new ComplexFilter("store_id", new AssociativeEntity("eq", "4"));
            cmp[1] = new ComplexFilter("status", new AssociativeEntity("eq", "Pending"));
            cmp[2] = new ComplexFilter("order_id", new AssociativeEntity("gt", "1698"));


            filtros.setComplex_filter(cmp);
            List<SalesOrderEntity> ventas = Arrays.asList(magento.salesOrderList(sessionId, filtros));
            System.out.print("prueba");
            for(SalesOrderEntity venta : ventas){
                System.out.println(venta.getOrder_id());
            }

        }
        catch (Exception ex) {
            ex.printStackTrace();
            assertTrue(false);
        }

    }

    @Ignore
    @Test
    public void test3() {
        MagentoServiceLocator service = new MagentoServiceLocator();
        String handlerPortEndpointAddress = "http://www.yoquierounodeesos.com/index.php/api/v2_soap/index/";
        service.setMage_Api_Model_Server_V2_HandlerPortEndpointAddress(handlerPortEndpointAddress);
        Mage_Api_Model_Server_V2_HandlerPortType magento;
        String ids = "100000289,100000290";
        try {

            magento = service.getMage_Api_Model_Server_V2_HandlerPort();

            String sessionId = magento.login("soap", "test123");

            Filters filtros = new Filters();

            ComplexFilter cmp[] = new ComplexFilter[1];
            cmp[0] = new ComplexFilter("increment_id", new AssociativeEntity("in", ids));

            filtros.setComplex_filter(cmp);
            SalesOrderEntity[] orderList = magento.salesOrderList(sessionId, filtros);
            assertTrue(orderList.length == 2);
        }
        catch (Exception ex) {
            ex.printStackTrace();
            assertTrue(false);
        }

    }

    @Ignore
    @Test
    public void test5() {
        MagentoServiceLocator service = new MagentoServiceLocator();
        String handlerPortEndpointAddress = "http://www.yoquierounodeesos.com/index.php/api/v2_soap/index/";
        service.setMage_Api_Model_Server_V2_HandlerPortEndpointAddress(handlerPortEndpointAddress);
        Mage_Api_Model_Server_V2_HandlerPortType magento;
        String ids = "100000289,100000290";
        try {

            magento = service.getMage_Api_Model_Server_V2_HandlerPort();

            String sessionId = magento.login("soap", "test123");

            Filters filtros = new Filters();

            ComplexFilter cmp[] = new ComplexFilter[1];
            cmp[0] = new ComplexFilter("increment_id", new AssociativeEntity("in", ids));

            filtros.setComplex_filter(cmp);
            SalesOrderEntity[] orderList = magento.salesOrderList(sessionId, null);
            assertTrue(orderList.length > 2);
        }
        catch (Exception ex) {
            ex.printStackTrace();
            assertTrue(false);
        }

    }

    @Ignore
    @Test
    public void test4() {
        exportOrder(null, new String[]{"100000289,100000290"}, "http://tienda.eleconomista.es/index.php/api/v2_soap/index/");
    }

    public void exportOrder(OutputStream out, String[] orderIds, String handlerPortEndpointAddress) {
        SalesOrderEntity[] orderList;
        CustomerCustomerEntity customerInfo;
        SalesOrderItemEntity[] items;
        SalesOrderEntity orderInfo;





        try {
            MagentoServiceLocator service = new MagentoServiceLocator();
            service.setMage_Api_Model_Server_V2_HandlerPortEndpointAddress(handlerPortEndpointAddress);
            Mage_Api_Model_Server_V2_HandlerPortType magento = service.getMage_Api_Model_Server_V2_HandlerPort();

            String sessionId = magento.login("soap", "test123");

            try {

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

                cell = cabecera.createCell(18);
                cell.setCellValue("MAGENTO ID");
                cell.setCellStyle(style);

                cell = cabecera.createCell(19);
                cell.setCellValue("Email Contacto");
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

                        cell = row.createCell(18);
                        cell.setCellValue("Prueba" + "-" + orderInfo.getIncrement_id());
                        cell.setCellStyle(style2);

                        cell = row.createCell(19);
                        cell.setCellValue(orderInfo.getCustomer_email());
                        cell.setCellStyle(style2);



                        items = orderInfo.getItems();
                        if (items != null) {
                            int j = 0;
                            String skuCompuesto = "";
                            for (SalesOrderItemEntity item : items) {

                                if (item.getProduct_type().equals("configurable")) {
                                    skuCompuesto = item.getSku();
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
                                cell.setCellValue(String.format("prueba" + "-%1$td%1$tm%1$ty%1$tH%1$tM-%2$d", fechaActual, pos));
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
                                cell.setCellValue("nuse");
                                cell.setCellStyle(style2);

                                skuCompuesto = "";



                            }
                        }

                        //cambiamos el estado de los pedidos
                        //log.fatal("Esta comentado lo de completar");
                        magento.salesOrderCompletar(sessionId, orderInfo.getIncrement_id());

                        i++;
                        pos++;


                    }


                    for (i = 0; i < 20; i++) {
                        sheet.autoSizeColumn(i);
                    }


                } else {
                    log.error("No se encuentran los elementos a tramitar");
                }

                //workbook.write(out);

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