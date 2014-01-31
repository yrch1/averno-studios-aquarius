/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gruporon2005.aquarius.struts.action;

import com.gruporon2005.aquarius.bean.SessionBean;
import com.gruporon2005.aquarius.struts.form.LoginForm;
import java.io.ByteArrayInputStream;
import java.security.MessageDigest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import org.apache.log4j.Logger;

/**
 *
 * @author yrch
 */
public class LoginAction extends org.apache.struts.action.Action {

    private static Logger log = Logger.getLogger(LoginAction.class);
    private final static String SUCCESS = "success";
    private final static String FAILURE = "failure";

    public String encryptMD5(String code) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] input = code.getBytes(); //"UTF8");
            input = md.digest(input);
            code = toHexadecimal(input); //new String(input,"UTF8");

            return code;
        }
        catch (Exception e) {
            return code;
        }

    }

    private String toHexadecimal(byte[] datos) {
        String resultado = "";
        ByteArrayInputStream input = new ByteArrayInputStream(datos);
        String cadAux;
        boolean ult0 = false;
        int leido = input.read();
        while (leido != -1) {
            cadAux = Integer.toHexString(leido);
            if (cadAux.length() < 2) { //Hay que a�adir un 0
                resultado += "0";
                if (cadAux.length() == 0) {
                    ult0 = true;
                }
            } else {
                ult0 = false;
            }
            resultado += cadAux;
            leido = input.read();
        }
        if (ult0)//quitamos el 0 si es un caracter aislado
        {
            resultado =
                    resultado.substring(0, resultado.length() - 2) + resultado.charAt(resultado.length() - 1);
        }
        return resultado;
    }

    @Override
    public ActionForward execute(ActionMapping mapping,
            ActionForm form, HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        LoginForm loginForm = (LoginForm) form;
        ActionForward result = mapping.findForward(FAILURE);
        try {

            String username = loginForm.getUserName();
            String password = encryptMD5(loginForm.getPassword());





            //

            Connection conexion = null;
            Statement stmt = null;
            ResultSet rs = null;
            PreparedStatement ps = null;

            try {


                Context initContext = new InitialContext();
                Context envContext = (Context) initContext.lookup("java:/comp/env");
                DataSource ds = (DataSource) envContext.lookup("jdbc/AquariusDB");

                conexion = ds.getConnection();
                stmt = conexion.createStatement();

                ps = conexion.prepareStatement("SELECT password FROM USERS WHERE name = ?");
                ps.setString(1, username);
                rs = ps.executeQuery();
                rs.last();


                if (rs.getRow() == 1) {
                    rs.beforeFirst();
                    rs.next();
                    String passwordToCheck = rs.getString("password");
                    if (passwordToCheck.equals(password)) {

                        SessionBean sessionBean = (SessionBean) request.getSession().getAttribute("sessionBean");
                        sessionBean.setUserLoggedIn(true);
                        result = mapping.findForward(SUCCESS);
                    }

                } else {
                    log.fatal("error");
                    result = mapping.findForward(FAILURE);
                }


                rs.close();
                rs = null;
                stmt.close();
                stmt = null;
                conexion.close(); // Return to connection pool
                conexion = null;  // Make sure we don't close it twice



            }
            catch (SQLException ex) {
                log.fatal(ex);
                result = mapping.findForward(FAILURE);
            }
            catch (NamingException ex) {
                log.fatal(ex);
                result = mapping.findForward(FAILURE);
            }
            finally {
                // Always make sure result sets and statements are closed,
                // and the connection is returned to the pool
                if (rs != null) {
                    try {
                        rs.close();
                    }
                    catch (SQLException e) {
                        log.error("Exception",e);
                    }
                    rs = null;
                }
                if (stmt != null) {
                    try {
                        stmt.close();
                    }
                    catch (SQLException e) {
                        log.error("Exception",e);
                    }
                    stmt = null;
                }
                if (conexion != null) {
                    try {
                        conexion.close();
                    }
                    catch (SQLException e) {
                        log.error("Exception",e);
                    }
                    conexion = null;
                }
                
            }
            //           


        }
        catch (Exception e) {
            log.error("Exception",e);
            result = mapping.findForward(FAILURE);
        }
        return result;
    }
}
