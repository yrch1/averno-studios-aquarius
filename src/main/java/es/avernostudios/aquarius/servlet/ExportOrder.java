/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package es.avernostudios.aquarius.servlet;

import es.avernostudios.aquarius.bean.SessionBean;
import es.avernostudios.aquarius.bean.Store;
import es.avernostudios.aquarius.soap.helper.OrderHelper;
import java.io.IOException;
import java.io.OutputStream;
import java.text.DateFormat;
import java.util.Calendar;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;

/**
 *
 * @author yrch
 */
public class ExportOrder extends HttpServlet {

    private static Logger log = Logger.getLogger(ExportOrder.class);

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("application/vnd.ms-excel");
        OutputStream out = response.getOutputStream();
        try {
            Calendar ahora = Calendar.getInstance();
            DateFormat df = DateFormat.getDateInstance(DateFormat.MEDIUM);

            SessionBean sessionBean = (SessionBean) request.getSession().getAttribute("sessionBean");

            if (sessionBean != null) {

                Store storeBean = sessionBean.getStoreInfoHash().get(sessionBean.getStoreId());

                response.setHeader("Refresh", "0");
                response.addHeader("Content-Disposition", "attachment;filename=" +storeBean.getNemo()+"-"+ df.format(ahora.getTime()) + ".xls");
                request.getParameter("order_ids");

                String handlerPortEndpointAddress = storeBean.getEndpoint();



                String[] orderIds = request.getParameterValues("order_ids");
                if (orderIds != null) {
                    OrderHelper.getInstance().exportOrder(out, orderIds, sessionBean,handlerPortEndpointAddress,false);
                }


            } else {
                log.fatal("No ha sido posible iniciar el sessionBean");
            }
        } finally {
            out.close();
        }


        log.info("Redirigido");
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /** 
     * Handles the HTTP <code>GET</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /** 
     * Handles the HTTP <code>POST</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /** 
     * Returns a short description of the servlet.
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
}
