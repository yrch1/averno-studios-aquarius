/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package es.avernostudios.aquarius.aquarius.servlet;

import es.avernostudios.aquarius.aquarius.bean.SessionBean;
import es.avernostudios.aquarius.soap.helper.ProductInfoHelper;
import es.avernostudios.aquarius.soap.helper.StoreInfoHelper;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;

/**
 *
 * @author yrch
 */
public class ActionServlet extends org.apache.struts.action.ActionServlet {

    private static Logger log = Logger.getLogger(ActionServlet.class);

    @Override
    protected void process(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        try {
            SessionBean bin = (SessionBean) request.getSession().getAttribute("sessionBean");
            if (bin == null) {


                // Creo el objeto en concreto
                SessionBean sessionBean = new SessionBean();

                //Iniciamos el hash que contiene los informacion de los productos
                ProductInfoHelper a = ProductInfoHelper.getInstance();
                sessionBean.setProductInfoHash(a.getInfo());

                //Iniciamos el hash que contiene los informacion de los productos
                StoreInfoHelper b = StoreInfoHelper.getInstance();

                sessionBean.setStoreInfoHash(b.getInfo(sessionBean.getSession()));

                request.getSession().setAttribute("sessionBean", sessionBean);
                log.info("sessionBean Inicializado");
            }
        }
        catch (Exception e) {
            log.fatal("Error en ActionServlet ", e);
        }

        super.process(request, response);
    }
}
